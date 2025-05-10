package com.geeks.noteapp.views.fragments.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geeks.noteapp.R
import com.geeks.noteapp.model.data.models.NoteModel
import com.geeks.noteapp.databinding.FragmentNoteBinding
import com.geeks.noteapp.presenter.notelist.NoteContract
import com.geeks.noteapp.presenter.notelist.NotePresenter
import com.geeks.noteapp.views.adapters.NoteAdapter
import com.geeks.noteapp.views.interfaces.OnClickItem

class NoteFragment : Fragment(), OnClickItem, NoteContract.View {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(onLongClick = this, onClick = this)

    private var isLinearLayoutManager = true

    private val presenter by lazy { NotePresenter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
//        getData()
        presenter.loadNotes()
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

    override fun onLongClick(note: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить заметку?")
            setPositiveButton("Удалить") { dialog, _ ->
                presenter.deleteNote(note)
            }
            setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }


    //    private fun getData() {
//        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { listModel ->
//            noteAdapter.submitList(listModel)
//        }
//    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }

//    override fun onLongClick(noteModel: NoteModel, note: NoteModel) {
//        val builder = AlertDialog.Builder(requireContext())
//        with(builder) {
//            setTitle("Удалить заметку?")
//            setPositiveButton("Удалить") { dialog, _ ->
//                presenter.deleteNote(note)
//            }
//            setNegativeButton("Отмена") { dialog, _ ->
//                dialog.cancel()
//            }
//            show()
//        }
//        builder.create()
//    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showNotes(notes: List<NoteModel>) {
        noteAdapter.submitList(notes)
        noteAdapter.notifyDataSetChanged()
    }

    override fun showError(message: String) {
        Log.e("NotesFragment", message)
    }


}