package com.assesmenttest.v2survey.service

class ApiHelper(private val apiService: ApiService) {

    suspend fun getSurveys() = apiService.getSurvey()
}