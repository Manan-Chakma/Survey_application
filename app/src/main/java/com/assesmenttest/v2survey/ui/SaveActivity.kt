package com.assesmenttest.v2survey.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assesmenttest.v2survey.MainActivity
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.database.MyLocalDatabase
import com.assesmenttest.v2survey.models.Form
import com.assesmenttest.v2survey.models.Survey
import com.assesmenttest.v2survey.ui.viewmodel.FormViewModel
import com.assesmenttest.v2survey.ui.viewmodel.FormViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveActivity : AppCompatActivity() {

    private lateinit var viewModel: FormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        // get the fetched data and save to local room db
        if (intent.hasExtra("SAVE_DATA")) {
            val extras = intent.extras
            val list = extras?.getParcelableArrayList<Survey>("SAVE_DATA")
            setUpViewModel()
            if (list != null) {
                filterAndInsert(list)
            }
        }

        findViewById<Button>(R.id.goback_button).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    // set the primary key before inserting in room db both for
    // form and survey, it'll help to make a one to many relation between form and survey ( a form has a list of survey)
    private fun filterAndInsert(list: java.util.ArrayList<Survey>) {
        CoroutineScope(IO).launch {
            val fId = getMinFIdVal()+1
            var id = getMinIdVal()+1
            for (element in list) {
                element.surveyId = getID(id++)
                element.fId = getFID(fId)
            }

            viewModel.insertData(list)
            viewModel.insertForm(Form(fId))
        }
    }

    private suspend fun getID(id: Int) = withContext(IO) {
        id
    }
    private suspend fun getFID(id: Int) = withContext(IO) {
        id
    }

    // get min survey id to set value of primary key of Survey Obj
    private suspend fun getMinIdVal(): Int {
        return  getMinId()
    }
    // get min survey id to set value of primary key of Survey Obj
    private suspend fun getMinId() = withContext(IO){
            viewModel.getMSurveyId()
    }


    // get min form id to set value of primary key of Form Obj
    private suspend fun getMinFIdVal(): Int {
        return  getMinFId()
    }
    // get min form id
    private suspend fun getMinFId() = withContext(IO){
        viewModel.getMFormId()
    }



    // set up viewmodel
    private fun setUpViewModel() {
        val application = application
        val dataSource = MyLocalDatabase.getInstance(application).formDao
        val viewModelFactory = FormViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(FormViewModel::class.java)

    }
}