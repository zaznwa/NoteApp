package com.geeks.noteapp.presenter.notelist

import com.geeks.noteapp.App
import com.geeks.noteapp.model.data.models.NoteModel

class NotePresenter(private val view: NoteContract.View) : NoteContract.Presenter {
    override fun loadNotes() {
        App.appDatabase?.noteDao()?.getAll()?.observeForever { notes ->
            view.showNotes(notes)
        }
    }

    override fun deleteNote(note: NoteModel) {
        App.appDatabase?.noteDao()?.delete(note)
        loadNotes()
    }
}