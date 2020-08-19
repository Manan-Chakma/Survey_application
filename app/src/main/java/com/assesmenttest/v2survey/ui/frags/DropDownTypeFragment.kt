package com.assesmenttest.v2survey.ui.frags

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.models.Survey
import com.assesmenttest.v2survey.utils.getOptionsArray


class DropDownTypeFragment(private val survey: Survey) : Fragment(),
    AdapterView.OnItemSelectedListener {

    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_drop_down_type, container, false)
        setupQues(view)


        spinner = view.findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this

        val options = survey.options
        var data = mutableListOf<String>()
        if (options != null) {
            data = getOptionsArray(options)
        }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter;

        return view
    }

    private fun setupQues(view: View) {
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
    }

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //Toast.makeText(requireContext(),position.toString() , Toast.LENGTH_LONG).show();
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}