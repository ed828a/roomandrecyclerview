package com.dew.edward.roomandrecyclerview.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dew.edward.roomandrecyclerview.LOG_TAG
import com.dew.edward.roomandrecyclerview.R
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        buttonSave.setOnClickListener{
            // Todo: save to database
            Log.d(LOG_TAG, "onClick firstName: ${textFirstName.text.toString()}")
        }


    }


}
