package com.assesmenttest.v2survey.ui.repo

import com.assesmenttest.v2survey.service.ApiHelper

class SurveyRepo(private val apiHelper: ApiHelper) {
    suspend fun getSurveys() = apiHelper.getSurveys()
}