package ru.bazalikova.puzzle.data

import com.google.gson.annotations.SerializedName

class Lesson {
    @SerializedName("prefix_name")
    lateinit var prefixName: String
    @SerializedName("puzzle_size")
    var puzzleSize: Int = 0
    @SerializedName("samples")
    lateinit var samples: List<Sample>
}