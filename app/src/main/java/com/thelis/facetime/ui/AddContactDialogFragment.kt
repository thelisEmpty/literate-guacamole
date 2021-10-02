package com.thelis.facetime.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.thelis.facetime.R
import com.thelis.facetime.data.Contact
import com.thelis.facetime.databinding.FragmentAddContactDialogBinding


class AddContactDialogFragment : DialogFragment() {

    private var _binding: FragmentAddContactDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ContactViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAddContactDialogBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {
                getString(R.string.added_contact)
            } else {
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })

        binding.buttonAdd.setOnClickListener {
            val fullName = binding.editTextFullName.text.toString().trim()
            val url = binding.editTextFullUrl.text.toString().trim()
            if (fullName.isEmpty()) {
                binding.editTextFullName.error = "Заполни это поле"
                return@setOnClickListener
            }
            if (url.isEmpty()) {
                binding.editTextFullUrl.error = "Заполни это поле"
                return@setOnClickListener
            }
            val contact = Contact()
            contact.name = fullName
            contact.url = url

            viewModel.addContact(contact)

        }
    }
}
