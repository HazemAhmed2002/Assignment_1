package com.hzmahmed.assignment_1

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hzmahmed.assignment_1.databinding.CalRowItemBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyView>() {
    val db = Firebase.firestore
    var count = 0
    inner class MyView(private val binding: CalRowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pos: Int) {
            db.collection("Contacts")
                .get()
                .addOnSuccessListener { result ->
                    count = result.size()
                    var row =result.documents[pos]
                        binding.textName.text = row.data!!["name"].toString()
                        binding.textPhone.text = row.data!!["phoneNum"].toString()
                        binding.textAddress.text = row.data!!["address"].toString()
                    binding.remove.setOnClickListener {
                        db.collection("Contacts").document(row.id).delete()
                        count--
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("hzm", "Error getting documents.", exception)
                }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = CalRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyView(view)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return count
    }

    fun addItem(name: String, phone: String, address: String) {
        this.count++
    }

    fun removeItem() {
        if(this.count > 0) this.count--

    }
}