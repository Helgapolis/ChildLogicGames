package ru.bazalikova.puzzle.data

import com.google.gson.annotations.SerializedName

class Sample
{
    @SerializedName("sample")
    lateinit var sample: String
    @SerializedName("answers")
    lateinit var answers: List<Int>
    @SerializedName("answer")
    var answer: Int = 0
}