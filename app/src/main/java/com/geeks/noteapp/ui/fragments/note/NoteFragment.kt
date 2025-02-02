package com.geeks.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.data.models.NoteModel
import com.geeks.noteapp.databinding.FragmentNoteBinding
import com.geeks.noteapp.ui.adapters.NoteAdapter
import com.geeks.noteapp.ui.interfaces.OnClickItem

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(onLongClick = this, onClick = this)

    private var isLinearLayoutManager = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        getData()
    }


    private fun initialize() = with(binding) {
        rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListener() = with(binding) {
        btnPlus.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }

        btnSwitchLayout.setOnClickListener {
            toggleLayoutManager()
        }
    }

    private fun toggleLayoutManager() = with(binding) {
        val recyclerView = rvNote

        val newLayoutManager: RecyclerView.LayoutManager = if (isLinearLayoutManager) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }

        recyclerView.layoutManager = newLayoutManager
        isLinearLayoutManager = !isLinearLayoutManager
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { listModel ->
            noteAdapter.submitList(listModel)
        }
    }

    override fun onLongClick(model: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить заметку?")
            setPositiveButton("Удалить") { dialog, _ ->
                App.appDatabase?.noteDao()?.delete(model)
            }
            setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}