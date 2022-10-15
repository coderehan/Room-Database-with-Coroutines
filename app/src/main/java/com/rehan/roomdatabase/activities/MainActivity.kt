package com.rehan.roomdatabase.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rehan.roomdatabase.R
import com.rehan.roomdatabase.adapters.UserAdapter
import com.rehan.roomdatabase.models.User
import com.rehan.roomdatabase.viewmodels.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var etName : EditText
    private lateinit var etAge : EditText
    private lateinit var btnSave : Button
    private lateinit var fabAdd : FloatingActionButton
    private lateinit var builder : AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var rvUser: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userAdapter = UserAdapter(this, ArrayList<User>())
        rvUser = findViewById(R.id.rvUser)
        rvUser.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Now whatever the data we have in table, it is now available in 'it' keyword below
        userViewModel.getAllUserData(applicationContext)?.observe(this, Observer {
            userAdapter.setData(it as ArrayList<User>)      // setting data in our adapter as list of data
        })

        fabAdd.setOnClickListener{
            openDialog()
        }

    }

    private fun openDialog(){
        builder = AlertDialog.Builder(this)
        val itemView: View = LayoutInflater.from(applicationContext).inflate(R.layout.input_layout, null)
        dialog = builder.create()
        dialog.setView(itemView)
        etName = itemView.findViewById(R.id.etName)
        etAge = itemView.findViewById(R.id.etAge)
        btnSave = itemView.findViewById(R.id.btnSave)
        btnSave.setOnClickListener{
            saveDataInRoomDatabase()
        }
        dialog.show()
    }

    private fun saveDataInRoomDatabase() {
        val name = etName.text.toString().trim()
        val age = etAge.text.toString().trim()

        if(TextUtils.isEmpty(name) && TextUtils.isEmpty(age)){
            Toast.makeText(this, "Please enter fields properly", Toast.LENGTH_LONG).show()
        }
        else{
            userViewModel.insert(this, User(name, Integer.parseInt(age)))
            dialog.dismiss()
        }
    }

}