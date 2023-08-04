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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    // connect to firebase auth
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun logInWithEmailAndPassword(email: String, pass: String, context: Context, home: () -> Unit) =
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
                                //connection is Ok But FB going wrong
                                Log.e("FB", task.result.toString())

                            } catch (e: Exception) {
                                // connection interrupted or 403
                                Log.e("FB", "connection timeout or 403")
                            }
                        }
                    }


            } catch (ex: Exception) {
                Log.e("FB", "LogInWithEmailAndPass error in exception: ${ex.message}")
            }

        }


    fun CreateUserWithEmailAndPassword(email: String, pass: String, home: () -> Unit) {

        if (_loading.value == false) {
            _loading.value = true
            try {
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("FB", "Your account is created")
                        // get name for save in FBStore
                        val displayName = task.result?.user?.email.toString().split("@")[0]
                        createUser(name = displayName)
                        home()
                    } else {
                        //check connection and fb failed
                        try {
                            //FB flier
                            Log.e("FB", "FB going wrong : ${task.result.toString()}")

                        } catch (e: Exception) {
                            // connection failed
                            Log.e("FB", "connection interrupter or 403")

                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("FB", "Something wrong")
            }
            _loading.value = false
        }


    }

    private fun createUser(name: String) {
        //get userid from firebase auth
        val userID = auth.currentUser?.uid

        //create a user ->>>>>> name and uid >>>>> hashmap >>>>>>save in firebaseStore

        val user = mutableMapOf<String, Any>()
        user["user_id"] = userID.toString()
        user["display_name"] = name

        // connect to firebase store and save new user
        FirebaseFirestore.getInstance().collection("users").add(user)

    }


}