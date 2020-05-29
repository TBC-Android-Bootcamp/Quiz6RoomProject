package com.example.quiz6room

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun save(view: View) {
        AsyncTask.execute{
            val user = User()
            user.firstName = etTitle.text.toString()
            user.lastName = etDescription.text.toString()
            db.userDao().insertAll(user)
        }
    }


    fun read(view: View) {
        AsyncTask.execute{
            val size = db.userDao().getAll().size
            for(user in db.userDao().getAll()){
                d("userFirstName", "${user.firstName}")

                runOnUiThread {
                    tvTitle.text = user.firstName
                    tvDescription.text = user.lastName
                }
            }
        }
    }

    fun delete(view: View) {
        AsyncTask.execute {
            val user = User()
            user.firstName = etTitle.text.toString()
            user.lastName = etDescription.text.toString()
            db.userDao().delete(user)

            runOnUiThread {
                tvTitle.text = ""
                tvDescription.text = ""
            }
        }
    }
}
