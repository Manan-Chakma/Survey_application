package com.assesmenttest.v2survey.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.assesmenttest.v2survey.ui.repo.SurveyRepo
import com.assesmenttest.v2survey.utils.Resource
import kotlinx.coroutines.Dispatchers.IO

class SurveyViewModel(private val mainRepository: SurveyRepo) : ViewModel() {

    fun getSurveys() = liveData(IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getSurveys()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}