package ressphere.example.com.videorecordingtutorial

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.media.Image
import android.media.ImageReader
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.util.Size
import android.util.SparseIntArray
import android.view.Surface
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CameraHelper(private val context:Context, private val startPreview: ()->Unit){
    private var cameraDevice:CameraDevice? = null
    private lateinit var cameraId: String
    private var handleThread: HandlerThread? =null
    var handler:Handler?=null
        private set
    private var totalRotation: Int = 0
    lateinit var previewSize: Size
        private set
    lateinit var videoSize: Size
        private set

    lateinit var imageReader: ImageReader
        private set

    private val onImageAvailableListener = ImageReader.OnImageAvailableListener { reader ->
        handler?.post(ImageSaver(reader.acquireLatestImage()))
    }



    inner class ImageSaver(private val mImage: Image) : Runnable {
        private val imageFileName:String
        private val mediaDir = File(context.filesDir, "/media")

        init {
            imageFileName = getAndCreateImageFileName()
        }

        @SuppressLint("SimpleDateFormat")
        private fun getAndCreateImageFileName():String {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val prepend = "IMAGE_" + timestamp + "_"
            val imageFile = File.createTempFile(prepend, ".jpg", mediaDir)
            return imageFile.absolutePath

        }

        override fun run() {
            val byteBuffer = mImage.getPlanes()[0].getBuffer()
            val bytes = ByteArray(byteBuffer.remaining())
            byteBuffer.get(bytes)

            var fileOutputStream: FileOutputStream? = null
            try {
                fileOutputStream = FileOutputStream(imageFileName)
                fileOutputStream!!.write(bytes)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                mImage.close()

                val mediaStoreUpdateIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                mediaStoreUpdateIntent.data = Uri.fromFile(File(imageFileName))
                context.sendBroadcast(mediaStoreUpdateIntent)

                if (fileOutputStream != null) {
                    try {
                        fileOutputStream!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }

        }
    }

    private val ORIENTATIONS = SparseIntArray()
    init
    {
        ORIENTATIONS.append(Surface.ROTATION_0, 0);
        ORIENTATIONS.append(Surface.ROTATION_90, 90);
        ORIENTATIONS.append(Surface.ROTATION_180, 180);
        ORIENTATIONS.append(Surface.ROTATION_270, 270);
    }

    val cameraDeviceStateCallback:CameraDevice.StateCallback = object: CameraDevice.StateCallback(){
        override fun onOpened(camera: CameraDevice?) {
            cameraDevice = camera
            startPreview()

        }

        override fun onDisconnected(camera: CameraDevice?) {
             camera?.close()
             cameraDevice = null
        }

        override fun onError(camera: CameraDevice?, error: Int) {
            onDisconnected(camera)
        }

    }

    fun setupCamera(width:Int, height:Int, deviceOrientation:Int){
        try {
            val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager?
            for(cameraId in cameraManager?.cameraIdList!!){
                val cameraCharacteristics = cameraManager.getCameraCharacteristics (cameraId);
                if (cameraCharacteristics.get(CameraCharacteristics.LENS_FACING) !=
                        CameraCharacteristics.LENS_FACING_FRONT) {
                    val map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                    totalRotation = sensorToDeviceRotation(cameraCharacteristics, deviceOrientation)
                    val swapRotation = totalRotation == 90 || totalRotation == 270
                    var rotatedWidth = width
                    var rotatedHeight = height
                    if (swapRotation) {
                        rotatedWidth = height
                        rotatedHeight = width
                    }

                    previewSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture::class.java), rotatedWidth, rotatedHeight)
                    videoSize = chooseOptimalSize(map.getOutputSizes(MediaRecorder::class.java), rotatedWidth, rotatedHeight)
                    val mImageSize = chooseOptimalSize(map.getOutputSizes(ImageFormat.JPEG), rotatedWidth, rotatedHeight)
                    handler?.let {
                        imageReader = ImageReader.newInstance(mImageSize.getWidth(), mImageSize.getHeight(), ImageFormat.JPEG, 1)
                        imageReader.setOnImageAvailableListener(onImageAvailableListener, it)
                    }
                    this.cameraId = cameraId;
                    break
                }
            }
        } catch (e: CameraAccessException){
            e.printStackTrace()
        }

    }

    private fun sensorToDeviceRotation(cameraCharacteristics: CameraCharacteristics, deviceOrientation: Int): Int {
        var deviceOrientation = deviceOrientation
        val sensorOrienatation = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)!!
        deviceOrientation = ORIENTATIONS.get(deviceOrientation)
        return (sensorOrienatation + deviceOrientation + 360) % 360
    }

    fun start(){
        synchronized(this) {
            handleThread = HandlerThread("CameraHelper")
            handleThread?.start()
            handler = Handler(handleThread?.looper)
        }

    }

    fun stop(){
        synchronized(this) {
            handleThread?.let {
                it.quitSafely()
                try {
                    it.join()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            handleThread = null
            handler = null
        }
    }

    @SuppressLint("MissingPermission")
    fun connectCamera(requestPermission: ()->Boolean) {
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (requestPermission()) {
                    cameraManager.openCamera(cameraId, cameraDeviceStateCallback, handler)
                }
            } else {
                cameraManager.openCamera(cameraId, cameraDeviceStateCallback, handler)
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }

    }

    fun closeCamera(){
        cameraDevice?.close()
    }

    private fun chooseOptimalSize(choices: Array<Size>, width: Int, height: Int): Size {
        val bigEnough = ArrayList<Size>()
        for (option in choices) {
            if (option.getHeight() === option.getWidth() * height / width &&
                    option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option)
            }
        }
        return if (bigEnough.size > 0) {
            Collections.min(bigEnough, object:Comparator<Size>{
                override fun compare(lhs: Size?, rhs: Size?): Int {
                    return java.lang.Long.signum((lhs!!.width * lhs.height).toLong() - (rhs!!.width * rhs.height).toLong())
                }

            })
        } else {
            choices[0]
        }
    }

    fun createCaptureRequest(templatType:Int = CameraDevice.TEMPLATE_PREVIEW): CaptureRequest.Builder?{
       return cameraDevice?.createCaptureRequest(templatType)
    }

    fun createCaptureSession(outputs:List<Surface> ,
                             callback:CameraCaptureSession.StateCallback , handler:Handler?=null){
        cameraDevice?.createCaptureSession(outputs, callback, handler)
    }
}