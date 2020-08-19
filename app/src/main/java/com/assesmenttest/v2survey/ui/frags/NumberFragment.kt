package com.assesmenttest.v2survey.ui.frags

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.models.Survey


class NumberFragment(private val survey: Survey) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_number, container, false)
        val ques = view.findViewById<TextView>(R.id.question)

        val q = survey.question
        val req = view.findViewById<TextView>(R.id.req)
        req.setTextColor(Color.RED)

        if (survey.required) {
            req.visibility = View.VISIBLE
        } else {
            req.visibility = View.GONE
        }
        ques.text = q

        return view
    }

}