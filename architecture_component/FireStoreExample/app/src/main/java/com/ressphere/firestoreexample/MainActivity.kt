package com.ressphere.firestoreexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast

import com.google.android.gms.tasks.OnFailureListener

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.util.*


class MainActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val messageRef = db.document("SmartMessage/Messages")
    private val messageCollectionRef = db.collection("SmartMessage")
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTextToSpeech()
        save.setOnClickListener {
            addMessageByRadioID(it)
        }
        onAddMultipleMessageListener()
    }

    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext
        ) { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(Locale.US)
            }
        }
    }

    private fun onAddMessageListener() {
        messageRef.addSnapshotListener(this, object : EventListener<DocumentSnapshot> {

            override fun onEvent(documentSnapshot: DocumentSnapshot?,
                                 error: FirebaseFirestoreException?) {
                if (error != null) {
                    Toast.makeText(this@MainActivity, "Error while loading!", Toast.LENGTH_SHORT)
                        .show()
                    Log.d(TAG, error.toString())
                    return
                }
                if (documentSnapshot?.exists() == true) {
                    val callMessage = documentSnapshot.toObject(CallMessage::class.java)
                    Toast.makeText(
                        applicationContext,
                        "radio ID: ${callMessage?.radioId} message: ${callMessage?.message}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    Log.d(
                        TAG,
                        "radio ID: ${callMessage?.radioId} message: ${callMessage?.message}"
                    )
                }
            }
        })
    }

    private fun onAddMultipleMessageListener() {
        messageCollectionRef.addSnapshotListener(this
        ) { queryDocumentSnapshots, error ->
            if (error != null) {
                Toast.makeText(this@MainActivity, "Error while loading!", Toast.LENGTH_SHORT)
                    .show()
                Log.d(TAG, error.toString())
            } else {
                if (queryDocumentSnapshots != null) {
                    for (documentSnapshot in queryDocumentSnapshots) {
                        val callMessage = documentSnapshot.toObject(CallMessage::class.java)
                        callMessage.documentId = documentSnapshot.id
                        Log.d(
                            TAG, "documentId: ${callMessage.documentId} radio ID: ${callMessage.radioId} " +
                                    "message: ${callMessage.message}"
                        )
                        if(callMessage.radioId == MY_RADIO_ID) {
                            textToSpeech.speak(callMessage.message,
                                TextToSpeech.QUEUE_FLUSH, null, null)
                        }
                    }
                }
            }
        }
    }

    private fun postMessage(v: View?) {
        val id: String = edit_text_radio_id.text.toString()
        val message: String = edit_text_radio_message.text.toString()
        val callMessage = CallMessage(id, message)
        messageRef.set(callMessage)
            .addOnSuccessListener(OnSuccessListener<Void?> {
                Toast.makeText(
                    this@MainActivity,
                    "Message post",
                    Toast.LENGTH_SHORT
                ).show()
            })
            .addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(this@MainActivity, "Error!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, e.toString())
            })
    }

    private fun addMessageByRadioID(v: View?) {
        val id: String = edit_text_radio_id.text.toString()
        val message: String = edit_text_radio_message.text.toString()
        val callMessage = CallMessage(id, message)
        messageCollectionRef.document(id).set(callMessage)
    }

    private fun addMessage(v: View?) {
        val id: String = edit_text_radio_id.text.toString()
        val message: String = edit_text_radio_message.text.toString()
        val callMessage = CallMessage(id, message)
        messageCollectionRef.add(callMessage)
    }

    fun loadMessage(v: View?) {
        messageRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val callMessage = documentSnapshot.toObject(CallMessage::class.java)
                    //val title = documentSnapshot.getString(KEY_TITLE)
                    //val description = documentSnapshot.getString(KEY_DESCRIPTION)
                    //Map<String, Object> note = documentSnapshot.getData();
                    //textViewData.setText("Title: $title\nDescription: $description")
                } else {
                    Toast.makeText(this@MainActivity, "Document does not exist", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@MainActivity, "Error!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, e.toString())
            }
    }

    fun loadMessages(v: View?) {
        messageCollectionRef
            .get()
            .addOnSuccessListener {
                for(documentSnapShot in it) {
                    val callMessage = documentSnapShot.toObject(CallMessage::class.java)
                    callMessage.documentId = documentSnapShot.id
                    Log.d(TAG, "radio ID: ${callMessage.radioId} radioMessage: ${callMessage.message}")
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@MainActivity, "Error!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, e.toString())
            }
    }



    companion object {
        private const val TAG = "MainActivity"
        private const val MY_RADIO_ID = "10701982"
    }
}