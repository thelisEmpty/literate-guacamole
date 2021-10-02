package com.thelis.facetime.ui

import android.app.AlertDialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.thelis.facetime.R
import com.thelis.facetime.R.*
import com.thelis.facetime.databinding.FragmentContactsBinding
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.recycler_view_contact.*
import com.thelis.facetime.R.id as id1


class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private val adapter = ContactAdapter()


    private lateinit var viewModel: ContactViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewContacts.adapter = adapter


       /* binding.callBtn.setOnClickListener {
            val intent = Intent(context)
            startActivity(intent)
        }*/


        binding.addBtn.setOnClickListener {
            AddContactDialogFragment().show(childFragmentManager, "")
        }

        viewModel.contact.observe(viewLifecycleOwner, Observer {
            adapter.addContact(it)
        })

        viewModel.getRealTimeUpdate()
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewContacts)

    }

    private val simpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val currenContact = adapter.contacts[position]
                when (direction) {
                    ItemTouchHelper.RIGHT -> {
                        UpdateContactDialogFragment(currenContact).show(childFragmentManager, "")
                    }
                    ItemTouchHelper.LEFT -> {
                        AlertDialog.Builder(requireContext()).also {
                            it.setTitle("Ты уверен что хчоешь удалить этот контакт?")
                            it.setPositiveButton("Да") { dilog, which ->
                                viewModel.deleteContact(currenContact)
                                binding.recyclerViewContacts.adapter?.notifyItemRemoved(position)
                                Toast.makeText(context, "Контакт удален", Toast.LENGTH_SHORT).show()
                            }
                        }.create().show()
                    }
                }
                binding.recyclerViewContacts.adapter?.notifyDataSetChanged()
            }

        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}