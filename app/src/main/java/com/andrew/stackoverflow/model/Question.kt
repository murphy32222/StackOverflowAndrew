package com.andrew.stackoverflow.model

import com.google.gson.annotations.SerializedName

data class Question(val title: String, @SerializedName("answer_count") val answerCount: Int)