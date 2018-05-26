package com.dew.edward.roomandrecyclerview

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.dew.edward.roomandrecyclerview.adapter.UserAdapter
import com.dew.edward.roomandrecyclerview.controller.CreateUserActivity
import com.dew.edward.roomandrecyclerview.database.AppDatabase
import com.dew.edward.roomandrecyclerview.model.User
import com.dew.edward.roomandrecyclerview.model.UserDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val LOG_TAG = "LOG"
const val REQUEST_CODE = 2
const val USER = "user"

class MainActivity : AppCompatActivity() {
    lateinit var mUsers: MutableList<User>
    lateinit var mUserDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            startActivityForResult(Intent(this, CreateUserActivity::class.java), REQUEST_CODE)
        }

        mUsers = mutableListOf()
        val db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "app-database")
                .allowMainThreadQueries()  //bad practice
                .build()
        mUserDao = db.userDao()
        val users = mUserDao.allUsers
        users.forEach { mUsers.add(it) }

        recyclerView.adapter = UserAdapter(this, mUsers) {
            val intent = Intent(this, CreateUserActivity::class.java)
            intent.putExtra(USER, it)
            startActivityForResult(intent, REQUEST_CODE)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val user = data?.getParcelableExtra<User>(USER)
            if (user != null) {
                mUserDao.insertAll(user)
                mUsers.add(user)
                recyclerView.adapter.notifyDataSetChanged()
            }
        }
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
