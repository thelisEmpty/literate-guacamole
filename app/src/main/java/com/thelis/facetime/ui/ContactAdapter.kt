package com.thelis.facetime.ui

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.thelis.facetime.data.Contact
import com.thelis.facetime.databinding.RecyclerViewContactBinding


class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    var contacts = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewContactBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        holder.binding.textViewName.text = contacts[position].name
        holder.binding.textViewContacts.text = contacts[position].url

        holder.binding.callBtn.setOnClickListener { context ->
            Log.d("contactWeb","${contacts[position].name} , ${contacts[position].url}")
            val context = holder.binding.textViewContacts.context
            val intent =  Intent(context, WebView::class.java)
            intent.putExtra("url", contacts[position].url)
            context.startActivity(intent)


        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun addContact(contact: Contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact)
        } else {
            val index = contacts.indexOf(contact)
            if (contact.isDeleted) {
                contacts.removeAt(index)
            } else {
                contacts[index] = contact
            }

        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RecyclerViewContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}