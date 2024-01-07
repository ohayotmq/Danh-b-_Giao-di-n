package com.example.simpledanhba

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val phoneNumberTextView: TextView = findViewById(R.id.phoneNumberTextView)

        val name = intent.getStringExtra("name")
        val phoneNumber = intent.getStringExtra("phoneNumber")

        nameTextView.text = "Tên: $name"
        phoneNumberTextView.text = "Số điện thoại: $phoneNumber"
    }
}

