package ru.bazalikova.puzzle.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

internal object JSONHelper {
    private const val FILE_NAME = "expressions.json"

    fun importFromJSON(context: Context): List<Lesson>? {
        var streamReader: InputStreamReader? = null
        var fileInputStream: InputStream? = null
        try {
            fileInputStream = context.assets.open(FILE_NAME)
            streamReader = InputStreamReader(fileInputStream)
            val gson = Gson()
            val lessonItems: LessonItems = gson.fromJson(streamReader, LessonItems::class.java)
            return lessonItems.lessons
        } catch (ex: IOException) {
            ex.printStackTrace()
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return null
    }

    private class LessonItems {
        @SerializedName("lessons")
        var lessons: List<Lesson>? = null
    }
}