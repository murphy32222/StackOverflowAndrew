package com.andrew.stackoverflow.restapi

import com.andrew.stackoverflow.model.Question
import com.andrew.stackoverflow.responses.QuestionsRestResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RestApiService {

    @GET("search/advanced?order=desc&sort=activity&accepted=True&answers=2&site=stackoverflow")
    fun searchQuestions(): Observable<QuestionsRestResponse>
}