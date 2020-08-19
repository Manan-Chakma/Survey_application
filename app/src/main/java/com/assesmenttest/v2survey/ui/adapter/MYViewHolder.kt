package com.assesmenttest.v2survey.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.database.FormSurvey

class MYViewHolder(
    itemView: View,
    private val formSurvey: List<FormSurvey>
) : RecyclerView.ViewHolder(itemView) {


    private val formNo = itemView.findViewById<TextView>(R.id.textView)

    init {

        itemView.setOnClickListener {v ->
            val data = formSurvey[adapterPosition].surveys
            var finalStr = ""
            for(element in data) {
                finalStr += element.question + "\n"
                finalStr += element.answer + "\n"
                finalStr += "\n"
            }

            Toast.makeText( v.context, finalStr, Toast.LENGTH_LONG).show()
        }

    }

    @SuppressLint("SetTextI18n")
    fun bind(pos: Int) {
        formNo.text = "Survey NO: ${pos + 1}"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            formSurvey: List<FormSurvey>
        ): MYViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card, parent, false)
            return MYViewHolder(view, formSurvey)
        }
    }
}