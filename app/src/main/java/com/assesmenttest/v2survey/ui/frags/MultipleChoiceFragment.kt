package com.assesmenttest.v2survey.ui.frags

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.models.Survey
import com.assesmenttest.v2survey.utils.getOptionsArray

class MultipleChoiceFragment(private val survey: Survey) : Fragment() {

    private lateinit var radioGroup:RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_multiple_choice, container, false)
        val ques = view.findViewById<TextView>(R.id.question)

        val q = survey.question
        val req = view.findViewById<TextView>(R.id.req)
        req.setTextColor(Color.RED)


        if(survey.required) {
            req.visibility = View.VISIBLE
        } else {
            req.visibility = View.GONE
        }


        ques.text = q



        val options = survey.options
        var data = mutableListOf<String>()
        if (options != null) {
            data = getOptionsArray(options)
        }

            // setting up radio group
        radioGroup = view.findViewById(R.id.multiple_choice)
        for((i, element) in data.withIndex()) {
            val rButton = RadioButton(requireContext())
            rButton.id = i
            rButton.text = element
            radioGroup.addView(rButton)
        }

        return view
    }


}