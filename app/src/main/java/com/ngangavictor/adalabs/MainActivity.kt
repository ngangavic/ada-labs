package com.ngangavictor.adalabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var inputEmail:EditText
    lateinit var inputPassword:EditText
    lateinit var buttonSignIn:Button
    lateinit var textViewResetPassword:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputPassword=findViewById(R.id.inputPassword)
        inputEmail=findViewById(R.id.inputEmail)
        buttonSignIn=findViewById(R.id.buttonSignIn)
        textViewResetPassword=findViewById(R.id.textViewResetPassword)

        buttonSignIn.setOnClickListener {
            login()
        }
    }

    private fun verifyEmail(email:String):Boolean{
        return email.contains(".") && email.contains("@")
    }

    private fun login(){
        val email=inputEmail.text.toString()
        val password=inputPassword.text.toString()
        if(email.isEmpty()){
            inputEmail.requestFocus()
            inputEmail.error="Required"
        }else if (password.isEmpty()){
            inputPassword.requestFocus()
            inputPassword.error="Required"
        }else if(!verifyEmail(email)){
            inputEmail.requestFocus()
            inputEmail.error="Email invalid"
        }else{
            startActivity(Intent(this,NoteActivity::class.java))
            finish()
        }
    }
}