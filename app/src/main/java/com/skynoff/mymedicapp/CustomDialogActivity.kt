package com.skynoff.mymedicapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class CustomDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_dialog)


        class CustomDialog(context: Context) : AlertDialog.Builder(context) {

            fun show(title: String, message: String) {

                val builder = AlertDialog.Builder(context)
                builder.setTitle(title)
                builder.setMessage(message)
                builder.setIcon(R.drawable.pop_up)

                // Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()

            }

        }

    }
}