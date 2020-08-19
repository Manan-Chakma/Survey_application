package com.assesmenttest.v2survey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.database.MyLocalDatabase
import com.assesmenttest.v2survey.ui.adapter.MyRecyclerViewAdapter
import com.assesmenttest.v2survey.ui.viewmodel.DashBoardViewModel
import com.assesmenttest.v2survey.ui.viewmodel.DashBoardViewModelFactory

class DashboardActivity : AppCompatActivity() {
    private lateinit var viewModel: DashBoardViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setUpViewModel()
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        observeData()
    }


    // observe data and setup recyclerview - gridlayout
    private fun observeData() {
        viewModel.lFinal.observe(this, Observer { list ->
            if (list != null) {
                recyclerView.adapter = MyRecyclerViewAdapter(list)
            }
        })
    }

    // set up viewmodel
    private fun setUpViewModel() {
        val application = application
        val dataSource = MyLocalDatabase.getInstance(application).formDao
        val viewModelFactory = DashBoardViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(DashBoardViewModel::class.java)

    }
}