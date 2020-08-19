package com.assesmenttest.v2survey.ui.frags

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.models.DataModel
import com.assesmenttest.v2survey.models.Survey
import com.assesmenttest.v2survey.ui.adapter.MyListAdapter
import com.assesmenttest.v2survey.utils.getOptionsArray


class CheckBoxFragment(private val element: Survey) : Fragment() {


    private lateinit var ques: TextView
    private lateinit var listView: ListView
    private var mList = arrayListOf<DataModel>()
    private lateinit var myListAdapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_check_box, container, false)


        listView = view.findViewById(R.id.listView)
        ques = view.findViewById(R.id.question)

        // get the options from string
        val options = element.options?.let { getOptionsArray(it) }
        if (options != null) {
            mList.clear()
            for (el in options) {
                // make a list
                mList.add(DataModel(el, false))
            }
        }

        val q = element.question

        val req = view.findViewById<TextView>(R.id.req)
        req.setTextColor(Color.RED)

        if(element.required) {
            req.visibility = View.VISIBLE
        } else {
            req.visibility = View.GONE
        }
        ques.text = q
        myListAdapter = MyListAdapter(requireActivity(), mList)
        listView.adapter = myListAdapter

        return view
    }
    // get the selected from adapter
    fun getSelectedItem(): Int {
        return myListAdapter.getCheckedPosition()
    }




}