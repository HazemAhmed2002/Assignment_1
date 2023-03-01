package com.hzmahmed.assignment_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
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


        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val phoneNum = binding.editTextNumber.text.toString()
            val address = binding.editTextAddress.text.toString()

            val Contacts = hashMapOf(
                "name" to name,
                "phoneNum" to phoneNum,
                "address" to address,
            )

            db.collection("Contacts")
                .add(Contacts)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                }
        }

        binding.buttonSave3.setOnClickListener {
            db.collection("Contacts")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Toast.makeText(this, "${document.data}", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Error getting documents.", Toast.LENGTH_SHORT).show()
                }
        }
    }
}