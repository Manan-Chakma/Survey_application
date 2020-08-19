package com.assesmenttest.v2survey.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.assesmenttest.v2survey.MainActivity
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.models.Survey
import com.assesmenttest.v2survey.service.ApiHelper
import com.assesmenttest.v2survey.service.RetrofitBuilder
import com.assesmenttest.v2survey.ui.adapter.MyViewPagerAdapter
import com.assesmenttest.v2survey.ui.frags.*
import com.assesmenttest.v2survey.ui.viewmodel.SurveyViewModel
import com.assesmenttest.v2survey.ui.viewmodel.SurveyViewModelFactory
import com.assesmenttest.v2survey.utils.Status
import java.util.*
import kotlin.collections.ArrayList

class SurveyActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var progressBar: ProgressBar

    private lateinit var mAdapter: MyViewPagerAdapter
    private lateinit var viewModel: SurveyViewModel
    private var mSurveyFrag = mutableListOf<Fragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        setSupportActionBar(findViewById(R.id.my_toolbar))


        // basic setup
        viewPager = findViewById(R.id.view_pager)
        progressBar = findViewById(R.id.progress)
        viewPager.isUserInputEnabled = false
        setupViewModel()
        setupObservers()


        val progressBar = findViewById<ProgressBar>(R.id.determinateBar)


        // increment viewpager currentItem
        findViewById<Button>(R.id.next).setOnClickListener {
            if (viewPager.currentItem + 1 == mSurveyFrag.size) {
                saveData()
            } else {
                progressBar.progress = (viewPager.currentItem + 1) * (100 / mSurveyFrag.size)
                viewPager.currentItem = viewPager.currentItem + 1

            }
        }
        // decrement view pager current Item
        findViewById<Button>(R.id.back).setOnClickListener {

            // if back pressed and current item is the first one
            if (viewPager.currentItem <= 0) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                progressBar.progress = (viewPager.currentItem - 1) * (100 / mSurveyFrag.size)
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }

        findViewById<Button>(R.id.done).setOnClickListener {
            saveData()
        }
    }

    // check and send data to SaveActivity through MainActivity to save surveys
    private fun saveData() {
        val answers = mAdapter.getAnswers() as ArrayList
        var canSave = true
        // checking if all the required fields are filled or not
        for (elements in answers) {
            if (elements.required && elements.answer.isNullOrBlank()) {
                canSave = false
                Toast.makeText(
                    this,
                    R.string.requiredfieldstext,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
        if (canSave) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putParcelableArrayListExtra("SAVE_DATA", answers)
            startActivity(intent)
            finish()
        }
    }


    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            totalProgress = 0
            viewPager.removeAllViews()
            super.onBackPressed()
        } else {
            progressBar.progress = (viewPager.currentItem - 1) * (100 / mSurveyFrag.size)
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    companion object {
        var totalProgress: Int = 0
    }

    // viewModel setup
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            SurveyViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(SurveyViewModel::class.java)
    }

    // observers for incoming data
    private fun setupObservers() {
        viewModel.getSurveys().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        viewPager.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE

                        resource.data?.let { users ->
                            retrieveList(users)
                        }
                    }
                    Status.ERROR -> {
                        viewPager.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        viewPager.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    // after successful retrieval of data make list of Fragments of certain types
    private fun retrieveList(surveys: List<Survey>) {
        mSurveyFrag.clear()
        for (element in surveys) {
            when (element.type?.trim()?.toLowerCase(Locale.ROOT)) {
                "text" -> {
                    mSurveyFrag.add(TextTypeFragment(element))
                }
                "multiple choice" -> {
                    mSurveyFrag.add(MultipleChoiceFragment(element))
                }
                "dropdown" -> {
                    mSurveyFrag.add(DropDownTypeFragment(element))
                }
                "checkbox" -> {
                    mSurveyFrag.add(CheckBoxFragment(element))
                }
                "number" -> {
                    mSurveyFrag.add(NumberFragment(element))
                }
                else -> {
                    Log.d("MYTAG", "Unknown Type")
                }
            }
        }

        mSurveyFrag.add(FlagFragment())
        mAdapter = MyViewPagerAdapter(this, mSurveyFrag, surveys)
        viewPager.adapter = mAdapter

    }

}