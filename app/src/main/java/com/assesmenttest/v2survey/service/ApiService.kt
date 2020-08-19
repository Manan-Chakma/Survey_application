package com.assesmenttest.v2survey.service

import com.assesmenttest.v2survey.models.Survey
import retrofit2.http.GET

interface ApiService {
    @GET("getSurvey")
    suspend fun getSurvey(): List<Survey>
}