package com.ressphere.firestoreexample

import com.google.firebase.firestore.Exclude

data class CallMessage(val radioId:String = "", val message: String = "", @get:Exclude var documentId:String = "")