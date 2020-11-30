package com.andrew.stackoverflow.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import com.andrew.stackoverflow.R
import com.andrew.stackoverflow.interfaces.DataListener
import com.andrew.stackoverflow.interfaces.RepoListener
import com.andrew.stackoverflow.model.Question
import com.andrew.stackoverflow.repositories.QuestionsRepository
import com.andrew.stackoverflow.responses.QuestionsRestResponse
import io.reactivex.observers.DisposableObserver

class QuestionViewModel(var context: Context?, var repoListener: RepoListener): DataListener {

    var errorMessage: ObservableField<String> = ObservableField<String>(context?.getString(R.string.default_error_message))

    private var questionsRepository: QuestionsRepository = QuestionsRepository(context!!, this)

    private var disposable: DisposableObserver<QuestionsRestResponse>? = null

    fun fetchQuestions() {
        disposable = questionsRepository.fetchQuestions()
    }

    fun destroy() {
        if (!disposable!!.isDisposed) disposable!!.dispose()
        disposable = null
        context = null
    }

    override fun onFailure(message: String) {
        errorMessage.set(message)
    }

    override fun onSuccess(questions: List<Question>?) {
        repoListener.onDataFetched(questions)
    }
}