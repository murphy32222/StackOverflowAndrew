package com.andrew.stackoverflow.interfaces

import com.andrew.stackoverflow.model.Question

interface DataListener {
    fun onFailure(message: String)
    fun onSuccess(questions: List<Question>?)
}