package com.assesmenttest.v2survey.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assesmenttest.v2survey.database.FormDao
import com.assesmenttest.v2survey.database.FormSurvey
import com.assesmenttest.v2survey.models.Survey
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashBoardViewModel(
    database: FormDao,
    application: Application
) : AndroidViewModel(application) {

    var lFinal: LiveData<List<FormSurvey>> = MutableLiveData<List<FormSurvey>>()

    init {
        // get saved forms of surveys from local db
        lFinal = database.getFormWithSurvey()
    }


}