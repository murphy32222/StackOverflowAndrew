package com.andrew.stackoverflow.repositories

import android.content.Context
import com.andrew.stackoverflow.StackOverflowApplication
import com.andrew.stackoverflow.interfaces.DataListener
import com.andrew.stackoverflow.model.Question
import com.andrew.stackoverflow.responses.QuestionsRestResponse
import com.andrew.stackoverflow.restapi.RestApiService
import com.andrew.stackoverflow.restapi.RestServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

class QuestionsRepository(var context: Context, var dataListener: DataListener) {

    private var restServiceFactory: RestServiceFactory = RestServiceFactory()
    private var apiService: RestApiService = restServiceFactory.provideApi()

    fun fetchQuestions(): DisposableObserver<QuestionsRestResponse>? {
        return apiService.searchQuestions()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(StackOverflowApplication.instance?.defaultSubscribeScheduler())
            .subscribeWith(DataFetchOperationObserver())
    }

    private inner class DataFetchOperationObserver: DisposableObserver<QuestionsRestResponse>() {

        override fun onComplete() {
        }

        override fun onError(e: Throwable?) {
            dataListener.onFailure(e?.message.toString())
        }

        override fun onNext(value: QuestionsRestResponse?) {
            dataListener.onSuccess(value?.items)
        }

    }
}