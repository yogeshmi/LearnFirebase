package com.yogeshmi.learnfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yogeshmi.learnfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var gBinding: ActivityMainBinding
    val database = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(gBinding.root)
        // Write a message to the database

        val myRef = database.getReference("message")



        gBinding.btnAdd.setOnClickListener {
            myRef.setValue(gBinding.etData.text.toString())
        }
        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.value
                Log.e("YM", "Value is: $value")
                gBinding.tvFromFirebase.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("YM", "Failed to read value.", error.toException())
            }

        })
    }
}