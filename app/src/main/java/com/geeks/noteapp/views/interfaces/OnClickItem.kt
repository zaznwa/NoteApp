package com.geeks.noteapp.views.interfaces

import com.geeks.noteapp.model.data.models.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)
    fun onClick(noteModel: NoteModel)
}