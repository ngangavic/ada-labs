package com.ngangavictor.adalabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}