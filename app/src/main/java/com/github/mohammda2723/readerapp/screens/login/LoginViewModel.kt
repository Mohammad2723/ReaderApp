package com.github.mohammda2723.readerapp.screens.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    // connect to firebase aut
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun logInWithEmailAndPassword(email: String, pass: String, context: Context,home: () -> Unit ) =
        viewModelScope.launch {

            try {

                auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("FB", "say welcome Back :" + task.result.toString())
                            // navigate to homeScreen
                            home()
                            Toast.makeText(context, "hello welcome back $email", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            // check connection
                            try {

                                Log.e("FB", task.result.toString())

                            } catch (e: Exception) {
                                // connection timeout or 403
                                Log.e("FB", "connection timeout or 403")
                            }
                        }
                    }


            } catch (ex: Exception) {
                Log.e("FB", "LogInWithEmailAndPass error in exception: ${ex.message}")
            }

        }


    fun SignInWithEmailAndPass(email: String, pass: String) {

    }


}