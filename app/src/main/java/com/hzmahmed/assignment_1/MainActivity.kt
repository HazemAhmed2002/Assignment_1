package com.hzmahmed.assignment_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hzmahmed.assignment_1.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    val listAdapter = ListAdapter()
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
                "address" to address
            )

            db.collection("Contacts")
                .add(Contacts)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                }
            listAdapter.addItem(name,phoneNum,address)
            listAdapter.notifyItemChanged(changingConfigurations)


            binding.editTextName.text.clear()
            binding.editTextNumber.text.clear()
            binding.editTextAddress.text.clear()


            listAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "List Refresh", Toast.LENGTH_SHORT).show()
            binding.refreshLayout01.isRefreshing = false
        }

        binding.recyclerview01.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }


        binding.refreshLayout01.setOnRefreshListener {
            listAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "List Refresh", Toast.LENGTH_SHORT).show()
            binding.refreshLayout01.isRefreshing = false
        }
    }
}