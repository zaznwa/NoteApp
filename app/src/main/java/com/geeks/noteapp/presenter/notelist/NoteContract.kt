package com.geeks.noteapp.presenter.notelist

import com.geeks.noteapp.model.data.models.NoteModel


interface NoteContract {
    interface View{
        fun showNotes(notes: List<NoteModel>)
        fun showError(message:String)
    }
     interface Presenter{
        fun loadNotes()
        fun deleteNote(note: NoteModel)
    }

}