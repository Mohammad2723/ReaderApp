package com.github.mohammda2723.readerapp.screens.login

import android.util.Log
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

    fun logInWithEmailAndPassword(email: String, pass: String) = viewModelScope.launch {

        try {

            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("FB","say welcome Back :"+ task.result.toString())

//                        TODO("say welcome back")

                    } else {
                        Log.e("FB", task.result.toString())
                    }
                }


        } catch (ex: Exception) {
            Log.e("FB", "LogInWithEmailAndPass error in exception: ${ex.message}")
        }

    }


    fun SignInWithEmailAndPass(email: String, pass: String) {

    }


}