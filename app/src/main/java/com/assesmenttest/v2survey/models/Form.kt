package com.assesmenttest.v2survey.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form")
data class Form (
    @PrimaryKey @ColumnInfo(name = "formID") val formID: Int
)