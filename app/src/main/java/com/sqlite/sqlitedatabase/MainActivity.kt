package com.sqlite.sqlitedatabase

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.sqlite.sqlitedatabase.model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragment_dialog.view.*


class MainActivity : AppCompatActivity() {
    private lateinit var db: SqliteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = SqliteHelper(this)

        add.setOnClickListener {
            db.writableDatabase
            db.onInsertData(User(name.text.toString(), email.text.toString(), password.text.toString()));

            Toast.makeText(this, "data insert", Toast.LENGTH_SHORT).show()
            db.close()
        }
        show.setOnClickListener {
            val cursur = db.onGetData()
            val buffer = StringBuffer()
            if (cursur.moveToFirst()) {
                do {
                    buffer.append("name : ${cursur.getString(1)} \n")
                    buffer.append("email : ${cursur.getString(2)} \n")
                    buffer.append("password : ${cursur.getString(3)}\n")
                } while (cursur.moveToNext())
            }
            Toast.makeText(this, buffer.toString(), Toast.LENGTH_LONG).show()
        }
        update.setOnClickListener {


            db.onUpdateData(tvid.text.toString(), User(name.text.toString(), email.text.toString(), password.text.toString()))
            Toast.makeText(this, "update", Toast.LENGTH_SHORT).show()
            db.close()
        }
        delete.setOnClickListener {
            if (!tvid.equals(""))
            db.onDelete(tvid.text.toString())
        }
    }

}
