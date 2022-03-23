package com.ngangavictor.adalabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class SignUpActivity : AppCompatActivity() {

    lateinit var inputEmail: EditText
    lateinit var inputPassword: EditText
    lateinit var inputCPassword: EditText
    lateinit var buttonSignUp: Button
    lateinit var textViewResetPassword: TextView
    lateinit var layoutHaveAccount: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        inputPassword=findViewById(R.id.inputPassword)
        inputCPassword=findViewById(R.id.inputCPassword)
        inputEmail=findViewById(R.id.inputEmail)
        buttonSignUp=findViewById(R.id.buttonSignUp)
        textViewResetPassword=findViewById(R.id.textViewResetPassword)
        layoutHaveAccount=findViewById(R.id.layoutSignUp)

        buttonSignUp.setOnClickListener {
            register()
        }

        layoutHaveAccount.setOnClickListener{
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
        val email=inputEmail.text.toString()
        val password=inputPassword.text.toString()
        val cPassword=inputCPassword.text.toString()
        if(email.isEmpty()){
            inputEmail.requestFocus()
            inputEmail.error="Required"
        }else if (password.isEmpty()){
            inputPassword.requestFocus()
            inputPassword.error="Required"
        }else if(!verifyEmail(email)){
            inputEmail.requestFocus()
            inputEmail.error="Email invalid"
        }else if(!checkPasswordMatch(password,cPassword)){
            inputCPassword.requestFocus()
            inputCPassword.error="Password do not match"
        }
        else{
            startActivity(Intent(this,NoteActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,SignUpActivity::class.java))
        finish()
    }
}