package com.assesmenttest.v2survey.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "survey")
data class Survey(
    @PrimaryKey @ColumnInfo(name = "surveyId") var surveyId: Int?,
    @ColumnInfo(name = "fId") var fId: Int?,
    val question: String?,
    val type: String?,
    val options: String?,
    val required: Boolean,
    var answer: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(surveyId)
        parcel.writeValue(fId)
        parcel.writeString(question)
        parcel.writeString(type)
        parcel.writeString(options)
        parcel.writeByte(if (required) 1 else 0)
        parcel.writeString(answer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Survey> {
        override fun createFromParcel(parcel: Parcel): Survey {
            return Survey(parcel)
        }

        override fun newArray(size: Int): Array<Survey?> {
            return arrayOfNulls(size)
        }
    }

}