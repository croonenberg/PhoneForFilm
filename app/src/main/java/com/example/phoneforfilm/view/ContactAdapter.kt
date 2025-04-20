package com.example.phoneforfilm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.databinding.ItemContactBinding

class ContactAdapter(
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var contacts: List<Contact> = emptyList()

    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.tvContactName.text = contact.name
            binding.root.setOnClickListener { onClick(contact) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int = contacts.size

    fun submitList(newList: List<Contact>) {
        contacts = newList
        notifyDataSetChanged()
    }
}
