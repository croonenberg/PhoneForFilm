package com.example.phoneforfilm.ui.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.model.Contact
import com.example.phoneforfilm.databinding.ItemContactBinding

class ContactPickerAdapter(
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactPickerAdapter.ContactViewHolder>() {

    private var contacts: List<Contact> = emptyList()

    fun submitList(list: List<Contact>) {
        contacts = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int = contacts.size

    inner class ContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.tvName.text = contact.name
            binding.tvPhone.text = contact.phoneNumber
            binding.root.setOnClickListener {
                onClick(contact)
            }
        }
    }
}
