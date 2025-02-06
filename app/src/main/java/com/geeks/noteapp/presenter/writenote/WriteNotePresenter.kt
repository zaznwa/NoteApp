package com.geeks.noteapp.presenter.writenote

import com.geeks.noteapp.App
import com.geeks.noteapp.model.data.models.NoteModel

class WriteNotePresenter(
    private val view: WriteNoteContract.View,
) : WriteNoteContract.Presenter {
    override fun saveNote(note: NoteModel) {
        App.appDatabase?.noteDao()?.insert(note)
    }

    override fun updateNote(note: NoteModel) {
        App.appDatabase?.noteDao()?.update(note)
    }
}