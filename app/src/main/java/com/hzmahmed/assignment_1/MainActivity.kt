package com.hzmahmed.assignment_1

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hzmahmed.assignment_1.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var mNameEditText: EditText
    private lateinit var mNumberEditText: EditText
    private lateinit var mAddressEditText: EditText
    private lateinit var mSaveButton: Button
    private lateinit var mContactsListView: ListView

    lateinit var binding: ActivityMainBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}