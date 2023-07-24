package com.github.mohammda2723.readerapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.mohammda2723.readerapp.ui.theme.ReaderAppTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaderAppTheme {
                // A surface container using the 'background' color from the theme

                val db = FirebaseFirestore.getInstance()
                val data : MutableMap<String,Any> = HashMap()
                data["Name"] = "Mohammad"
                data["Family"] ="Ebrahimi"
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    db.collection("users").add(data).addOnSuccessListener {
                      Log.d("google fireStore",it.id)

                    }.addOnFailureListener {
                        Log.d("google fireStore",it.message.toString())

                    }
                }
            }
        }
    }
}