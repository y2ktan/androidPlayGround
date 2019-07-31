package ressphere.example.com.videorecordingtutorial

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_video.*
import java.util.*


class VideoActivity : AppCompatActivity() {

    private val requiredPermissions = arrayOf<String>(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA)

    private lateinit var cameraHelper:CameraHelper
    private val mSurfaceTextureListener = object : TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
            cameraHelper.setupCamera(width, height, windowManager.defaultDisplay.rotation)
            cameraHelper.connectCamera(::requestCameraPermissions)
        }

        override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {

        }

        override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
            return false
        }

        override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        cameraHelper = CameraHelper(applicationContext, ::startPreview)
        videoOnlineImageButton.setOnClickListener {
            onCaptureClick(it)
        }
    }

    override fun onResume() {
        super.onResume()
        cameraHelper.start();
        if (textureView.isAvailable) {
            cameraHelper.setupCamera(textureView.width, textureView.height, windowManager.getDefaultDisplay().getRotation())
            cameraHelper.connectCamera(::requestCameraPermissions)
        } else {
            textureView.setSurfaceTextureListener(mSurfaceTextureListener)
        }
    }

    override fun onPause() {
        cameraHelper.closeCamera()
        cameraHelper.stop()
        super.onPause()
    }


    override fun onWindowFocusChanged(hasFocas: Boolean) {
        super.onWindowFocusChanged(hasFocas)
        val decorView = window.decorView
        if (hasFocas) {
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }

    private fun areCameraPermissionGranted(): Boolean {

        for (permission in requiredPermissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun requestCameraPermissions():Boolean {
        val hasPermission = areCameraPermissionGranted()
        if(!hasPermission) {
            ActivityCompat.requestPermissions(
                    this,
                    requiredPermissions,
                    MEDIA_RECORDER_REQUEST)
        }
        return hasPermission
    }

    private fun onCaptureClick(view: View) {

        if (areCameraPermissionGranted()) {
            //startCapture()
        } else {
            requestCameraPermissions()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {

        if (MEDIA_RECORDER_REQUEST !== requestCode) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }

        var areAllPermissionsGranted = true
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                areAllPermissionsGranted = false
                break
            }
        }

        if (areAllPermissionsGranted) {
            //startCapture()
        } else {
            // User denied one or more of the permissions, without these we cannot record
            // Show a toast to inform the user.
            Toast.makeText(applicationContext,
                    getString(R.string.need_camera_permissions),
                    Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun startPreview() {
        val surfaceTexture = textureView.getSurfaceTexture()
        surfaceTexture.setDefaultBufferSize(cameraHelper.previewSize.width, cameraHelper.previewSize.height)
        val previewSurface = Surface(surfaceTexture)

        try {
            val captureRequestBuilder = cameraHelper.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            captureRequestBuilder?.addTarget(previewSurface)

            cameraHelper.createCaptureSession(Arrays.asList(previewSurface, cameraHelper.imageReader.getSurface()),
                    object : CameraCaptureSession.StateCallback() {
                        override fun onConfigured(session: CameraCaptureSession) {
                            Log.d(TAG, "onConfigured: startPreview")
                            val previewCaptureSession = session
                            try {
                                previewCaptureSession.setRepeatingRequest(captureRequestBuilder!!.build(),
                                        null, cameraHelper.handler)
                            } catch (e: CameraAccessException) {
                                e.printStackTrace()
                            }

                        }

                        override fun onConfigureFailed(session: CameraCaptureSession) {
                            Log.d(TAG, "onConfigureFailed: startPreview")

                        }
                    }, null)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }

    }






    companion object {
        private const val MEDIA_RECORDER_REQUEST = 0
        private const val TAG = "VideoActivity"
    }

}
