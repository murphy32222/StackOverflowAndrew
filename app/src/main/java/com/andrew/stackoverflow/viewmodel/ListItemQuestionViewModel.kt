package com.andrew.stackoverflow.viewmodel

import android.content.Context
import androidx.databinding.BaseObservable
import com.andrew.stackoverflow.model.Question

class ListItemQuestionViewModel(var context: Context, var question: Question) : BaseObservable() {

    companion object {
        fun setQuestion(listItemQuestionViewModel: ListItemQuestionViewModel, question: Question) {
            listItemQuestionViewModel.question = question
            listItemQuestionViewModel.notifyChange()
        }
    }
}