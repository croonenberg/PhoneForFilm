
package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.local.entity.Contact
import com.example.phoneforfilm.databinding.ItemContactBinding

class ContactAdapter(
    private var items: List<Contact> = emptyList(),
    private val onClick: (Contact) -> Unit = {}
) : RecyclerView.Adapter<ContactAdapter.VH>() {

    inner class VH(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) = with(binding) {
            textViewName.text = contact.name
            imageViewAvatar.load(contact.avatarUri) {
                placeholder(R.drawable.avatar_placeholder)
                error(R.drawable.avatar_placeholder)
                crossfade(true)
            }
            root.setOnClickListener { onClick(contact) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<Contact>) {
        items = newItems
        notifyDataSetChanged()
    }
}
