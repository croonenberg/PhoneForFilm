package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.databinding.ItemContactBinding

class ContactAdapter(
    private val contacts: List<Contact>,
    private val onClick: ((Contact) -> Unit)? = null
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.tvContactNameContact.text = contact.name
            binding.tvPhoneNumber.text = contact.phoneNumber
            binding.root.setOnClickListener {
                onClick?.invoke(contact)
            }
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
}
