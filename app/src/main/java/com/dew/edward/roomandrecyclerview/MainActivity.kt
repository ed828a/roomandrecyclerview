package com.dew.edward.roomandrecyclerview

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.dew.edward.roomandrecyclerview.adapter.UserAdapter
import com.dew.edward.roomandrecyclerview.controller.CreateUserActivity
import com.dew.edward.roomandrecyclerview.model.User

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val LOG_TAG = "LOG"

class MainActivity : AppCompatActivity() {
    lateinit var users: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            startActivity(Intent(this, CreateUserActivity::class.java))
        }
        users = arrayListOf()
        recyclerView.adapter = UserAdapter(this, users){
            val intent = Intent(this, CreateUserActivity::class.java)
            intent.putExtra("user", it)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
