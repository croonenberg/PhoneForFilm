package com.example.phoneforfilm.ui.contact
@file:Suppress("unused", "UnusedImport")

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.local.entity.Contact
import com.example.phoneforfilm.databinding.ItemContactPickerBinding

class ContactPickerAdapter(
    private var contacts: List<Contact>,
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactPickerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactPickerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.binding.textName.text = contact.name
        holder.binding.root.setOnClickListener { onClick(contact) }
    }

    override fun getItemCount(): Int = contacts.size

    fun updateData(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemContactPickerBinding) : RecyclerView.ViewHolder(binding.root)
}
