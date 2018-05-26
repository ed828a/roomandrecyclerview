package com.dew.edward.roomandrecyclerview.controller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.dew.edward.roomandrecyclerview.R
import com.dew.edward.roomandrecyclerview.USER
import com.dew.edward.roomandrecyclerview.model.User
import kotlinx.android.synthetic.main.activity_create_user.*

const val FIRST_NAME = "first name"
const val LAST_NAME = "last name"
const val EMAIL = "email"

class CreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        if (intent.hasExtra(USER)){
            val user = intent.getParcelableExtra<User>(USER)
            textFirstName.setText(user.firstName, TextView.BufferType.EDITABLE)
            textLastName.setText(user.lastName, TextView.BufferType.EDITABLE)
            textEmail.setText(user.email)
        }

        buttonSave.setOnClickListener{

            val replyIntent = Intent()

            if (textFirstName.text.isNotEmpty() && textLastName.text.isNotEmpty() && textEmail.text.isNotEmpty()) {
                val user = User(textFirstName.text.toString(), textLastName.text.toString(), textEmail.text.toString())
                replyIntent.putExtra(USER, user)
                setResult(Activity.RESULT_OK, replyIntent)

                finish()
            } else {
                Snackbar.make(it, "Please don't let any field empty.", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show()
            }

        }
    }


}
