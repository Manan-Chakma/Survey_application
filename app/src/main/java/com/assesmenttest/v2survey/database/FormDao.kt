package com.assesmenttest.v2survey.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.assesmenttest.v2survey.models.Form
import com.assesmenttest.v2survey.models.Survey

@Dao
interface FormDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLargeNumberOfSurvey(survey: List<Survey>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLargeSurveyForm(form: List<Form>)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertForm(form: Form)

    @Transaction
    @Query("SELECT * FROM form")
    fun getFormWithSurvey(): LiveData<List<FormSurvey>>

    @Transaction
    @Query("SELECT * FROM form")
    fun getFinalData(): List<FormSurvey>


    @Query("Select * from survey")
    fun getSurveys(): List<Survey>

    @Query("Select MAX(formID) from form")
    fun getMaxFormID(): Int

    @Query("Select MAX(surveyId) from survey")
    fun getMaxSurveyID(): Int

/*    @Query("Select * from form")
    fun getForms(): LiveData<List<Form>>*/

}