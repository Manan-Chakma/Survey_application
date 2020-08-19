package com.assesmenttest.v2survey.database

import androidx.room.Embedded
import androidx.room.Relation
import com.assesmenttest.v2survey.models.Form
import com.assesmenttest.v2survey.models.Survey

// establishing one to many relationship
data class FormSurvey(
    @Embedded
    val form: Form,
    @Relation(
        parentColumn = "formID",
        entityColumn = "fId"
    )
    val surveys: List<Survey>
)