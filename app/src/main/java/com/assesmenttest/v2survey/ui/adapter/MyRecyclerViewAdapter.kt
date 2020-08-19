package com.assesmenttest.v2survey.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assesmenttest.v2survey.database.FormSurvey

class MyRecyclerViewAdapter(private val formSurvey: List<FormSurvey>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MYViewHolder.create(parent, formSurvey)
    }

    override fun getItemCount(): Int {
        return formSurvey.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        try {
            (holder as MYViewHolder).bind(position)
        } catch (e: Exception) {
            // Log.d("MyTag", "${e.message}")
        }
    }

}