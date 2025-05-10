package com.geeks.noteapp.views.fragments.note

import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.model.data.models.NoteModel
import com.geeks.noteapp.databinding.FragmentNoteDetailBinding
import com.geeks.noteapp.presenter.writenote.WriteNoteContract
import com.geeks.noteapp.presenter.writenote.WriteNotePresenter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment(override val note: NoteModel) : Fragment(), WriteNoteContract.View {

    private lateinit var binding: FragmentNoteDetailBinding
    private var noteId: Int = -1

    private val presenter by lazy { WriteNotePresenter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateNote()
        setupListener()
        displayCurrentDate()
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }
        if (noteId != -1) {
            val id = App.appDatabase?.noteDao()?.getById(noteId)
            id?.let { noteModel ->
                binding.etTitle.setText(noteModel.title)
                binding.etText.setText(noteModel.description)

            }
        }
    }

    private fun displayCurrentDate() = with(binding) {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)

        tvDate.text = formattedDate
    }

    private fun setupListener() = with(binding) {
        btnGoback.setOnClickListener {
            findNavController().navigateUp()
        }

        btnDone.visibility = View.GONE

        etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
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
            if (noteId != -1) {
                val updateNote = NoteModel(etTitle, etText, etDate)
                updateNote.id = noteId
presenter.updateNote(note)
            }
            presenter.saveNote(note)
            findNavController().navigateUp()
        }
        btnColorPicker.setOnClickListener {
            val dialogView =
                LayoutInflater.from(requireContext()).inflate(R.layout.dialog_color_picker, null)

            val alertDialog = AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .setTitle("Поменять цвет")
                .create()

            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.setOnShowListener {
                val window = alertDialog.window
                val params = window?.attributes
                val density = requireContext().resources.displayMetrics.density
                val topMargin = (60 * density).toInt()
                val rightMargin = (60 * density).toInt()

                params?.gravity = Gravity.TOP or Gravity.END
                params?.x = rightMargin
                params?.y = topMargin
                window?.attributes = params
            }
            val colorViews = listOf(
                dialogView.findViewById<View>(R.id.color_yellow),
                dialogView.findViewById<View>(R.id.color_purple),
                dialogView.findViewById<View>(R.id.color_pink),
                dialogView.findViewById<View>(R.id.color_orange),
                dialogView.findViewById<View>(R.id.color_green),
                dialogView.findViewById<View>(R.id.color_blue)
            )

            colorViews.forEach { view ->
                view.setOnClickListener {
                    val selectedColor = view.tag.toString()
                    binding.root.setBackgroundColor(Color.parseColor(selectedColor))
                    alertDialog.dismiss()
                }
            }
            alertDialog.show()
        }
    }

    override fun showError(message: String) {
        Log.e(TAG, message)
    }

    override fun noteSaved() {
        Toast.makeText(requireContext(),"Note saved",Toast.LENGTH_SHORT).show()
    }

    override fun noteUpdated() {
        Toast.makeText(requireContext(),"Note updated",Toast.LENGTH_SHORT).show()

    }
}