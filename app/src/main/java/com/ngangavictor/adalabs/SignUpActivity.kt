package com.ngangavictor.adalabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ngangavictor.adalabs.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        auth = Firebase.auth

        binding. buttonSignUp.setOnClickListener {
            register()
        }

        binding.layoutHaveAccount.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
    }

    private fun verifyEmail(email:String):Boolean{
        return email.contains(".") && email.contains("@")
    }

    private fun checkPasswordMatch(pass1:String,pass2:String):Boolean{
        return pass1.equals(pass2)
    }

    private fun register(){
        val email=binding.inputEmail.text.toString()
        val password=binding.inputPassword.text.toString()
        val cPassword=binding.inputCPassword.text.toString()
        if(email.isEmpty()){
            binding.inputEmail.requestFocus()
            binding.inputEmail.error="Required"
        }else if (password.isEmpty()){
            binding.inputPassword.requestFocus()
            binding.inputPassword.error="Required"
        }else if(!verifyEmail(email)){
            binding.inputEmail.requestFocus()
            binding.inputEmail.error="Email invalid"
        }else if(!checkPasswordMatch(password,cPassword)){
            binding.inputCPassword.requestFocus()
            binding.inputCPassword.error="Password do not match"
        }
        else{
            auth.createUserWithEmailAndPassword(email, password)
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

    override fun onBackPressed() {
        startActivity(Intent(this,SignUpActivity::class.java))
        finish()
    }
}