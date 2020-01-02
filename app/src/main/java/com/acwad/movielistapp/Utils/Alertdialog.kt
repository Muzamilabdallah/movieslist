package com.acwad.movielistapp.Utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.acwad.movielistapp.R

object Alertdialog {

    fun popup(message: String, context: Context) {


        val dialog = Dialog(context)

        dialog.setContentView(R.layout.dialog)


        val button = dialog.findViewById(R.id.btn_dialog) as Button

        button.setOnClickListener(View.OnClickListener { dialog.dismiss() })

        val textView = dialog.findViewById<TextView>(R.id.text_dialog)

        textView.setText(message)





        dialog.show()


    }
}