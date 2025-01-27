@file:Suppress("UNREACHABLE_CODE")

package com.geeks.noteapp.ui.fragments.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.data.models.NoteModel
import com.geeks.noteapp.databinding.FragmentNoteBinding
import com.geeks.noteapp.databinding.FragmentNoteDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        displayCurrentDate()
    }

    private fun displayCurrentDate() = with(binding) {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)

        tvDate.text = formattedDate
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setupListener() = with(binding) {
        btnGoback.setOnClickListener {
            findNavController().navigateUp()
        }

        btnDone.visibility = View.GONE

        etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrEmpty()) {
                    btnDone.visibility = View.GONE
                } else {
                    btnDone.visibility = View.VISIBLE
                }
            }
        })

        btnDone.setOnClickListener {
            val etTitle = etTitle.text.toString()
            val etText = etText.text.toString()
            val etDate = tvDate.text.toString()

            App.appDatabase?.noteDao()?.insert(NoteModel(etTitle,etText,etDate))
            findNavController().navigateUp()
        }

    }
}