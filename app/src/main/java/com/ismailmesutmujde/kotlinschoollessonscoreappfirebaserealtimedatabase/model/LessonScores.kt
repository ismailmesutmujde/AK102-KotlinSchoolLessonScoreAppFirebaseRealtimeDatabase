package com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class LessonScores (var lesson_id : String? = "",
                         var lesson_name : String? = "",
                         var score1 : Int? = 0,
                         var score2 : Int? = 0) : Serializable {

}