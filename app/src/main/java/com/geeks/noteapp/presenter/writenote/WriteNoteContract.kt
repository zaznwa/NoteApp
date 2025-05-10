package com.geeks.noteapp.presenter.writenote

import com.geeks.noteapp.model.data.models.NoteModel

interface WriteNoteContract {
    interface View {
        abstract val note: NoteModel

        fun showError(message: String)
        fun noteSaved()
        fun noteUpdated()
    }

    interface Presenter {
        fun saveNote(note: NoteModel)
        fun updateNote(note: NoteModel)
    }
}