package com.example.simpledanhba

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    private val contacts = listOf(
        Contact("Người 1", "123456789"),
        Contact("Người 2", "987654321")
        // Thêm các liên hệ khác nếu cần
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contacts.map { it.name })
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedContact = contacts[position]
            showContactDetails(selectedContact)
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val selectedContact = contacts[position]
            showContactOptions(selectedContact)
            true
        }
    }

    private fun showContactDetails(contact: Contact) {
        val intent = Intent(this, ContactDetailActivity::class.java)
        intent.putExtra("name", contact.name)
        intent.putExtra("phoneNumber", contact.phoneNumber)
        startActivity(intent)
    }

    private fun showContactOptions(contact: Contact) {
        val options = arrayOf("Gọi điện", "Nhắn tin")
        AlertDialog.Builder(this)
            .setTitle(contact.name)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> makePhoneCall(contact.phoneNumber)
                    1 -> sendSms(contact.phoneNumber)
                }
            }
            .show()
    }

    private fun makePhoneCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    private fun sendSms(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber"))
        startActivity(intent)
    }
}
