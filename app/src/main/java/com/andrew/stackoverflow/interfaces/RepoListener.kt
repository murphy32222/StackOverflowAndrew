package com.andrew.stackoverflow.interfaces

import com.andrew.stackoverflow.model.Question

interface RepoListener {
    fun onDataFetched(questions: List<Question>?)
}