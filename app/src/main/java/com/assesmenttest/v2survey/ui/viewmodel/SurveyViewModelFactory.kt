package com.assesmenttest.v2survey.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assesmenttest.v2survey.service.ApiHelper
import com.assesmenttest.v2survey.ui.repo.SurveyRepo

@Suppress("UNCHECKED_CAST")
class SurveyViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SurveyViewModel::class.java)) {
            return SurveyViewModel(SurveyRepo(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}