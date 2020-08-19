package com.assesmenttest.v2survey.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.assesmenttest.v2survey.database.FormDao
import com.assesmenttest.v2survey.models.Form
import com.assesmenttest.v2survey.models.Survey
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormViewModel(
    private val database: FormDao,
    application: Application
) : AndroidViewModel(application) {

    // insert list of survey
    fun insertData(list: ArrayList<Survey>) {
        viewModelScope.launch {
            insert(list)
        }
    }

    // insert list of survey
    private suspend fun insert(list: ArrayList<Survey>) {
        withContext(IO) {
            database.insertLargeNumberOfSurvey(list)
        }
    }


    // insert form to room db
    fun insertForm(form: Form) {
        viewModelScope.launch {
            insertFormData(form)
        }
    }

    // insert form to room db
    private suspend fun insertFormData(form: Form) {
        withContext(IO) {
            database.insertForm(form)
        }
    }


    // get max survey id for next survey lists primary key field
    suspend fun getMSurveyId(): Int {
        return getMaxSurveyId()
    }

    // get max survey id for next survey lists primary key field
    private suspend fun getMaxSurveyId() = withContext(IO) {
        database.getMaxSurveyID()
    }


    // get max form id for next form primary key field
    suspend fun getMFormId(): Int {
        return getMaxFormId()
    }

    // get max form id for next form primary key field
    private suspend fun getMaxFormId() = withContext(IO) {
        database.getMaxFormID()
    }


}