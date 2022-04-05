package com.ngangavictor.adalabs

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ngangavictor.adalabs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        auth = Firebase.auth

        binding.buttonSignIn.setOnClickListener {
            login()
        }

        binding.layoutSignUp.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

    }

    private fun verifyEmail(email:String):Boolean{
        return email.contains(".") && email.contains("@")
    }

    private fun login(){
        val email=binding.inputEmail.text.toString()
        val password=binding.inputPassword.text.toString()
        if(email.isEmpty()){
            binding.inputEmail.requestFocus()
            binding.inputEmail.error="Required"
        }else if (password.isEmpty()){
            binding.inputPassword.requestFocus()
            binding.inputPassword.error="Required"
        }else if(!verifyEmail(email)){
            binding.inputEmail.requestFocus()
            binding.inputEmail.error="Email invalid"
        }else{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this,NoteActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this,NoteActivity::class.java))
            finish()
        }
    }
}