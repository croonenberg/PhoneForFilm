package com.example.phoneforfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.databinding.ItemContactBinding

class ContactAdapter(
    private var items: List<Contact> = emptyList(),
    private val onClick: (Contact) -> Unit = {}
) : RecyclerView.Adapter<ContactAdapter.VH>() {

    inner class VH(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(c: Contact) = with(binding) {
            tvName.text = c.name
            root.setOnClickListener { onClick(c) }
        }
    }

    override fun onCreateViewHolder(p: ViewGroup, v: Int) = VH(
        ItemContactBinding.inflate(LayoutInflater.from(p.context), p, false)
    )

    override fun onBindViewHolder(h: VH, i: Int) = h.bind(items[i])
    override fun getItemCount() = items.size

    fun submitList(newItems: List<Contact>) {
        items = newItems
        notifyDataSetChanged()
    }
}
