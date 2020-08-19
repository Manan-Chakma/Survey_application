package com.assesmenttest.v2survey.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.models.DataModel

class MyListAdapter(
    private val mContext: Activity,
    private val mList: ArrayList<DataModel>
) : ArrayAdapter<DataModel>(mContext, R.layout.unit_row, mList) {

    private var pos = -1// if nothing is selected

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = mContext.layoutInflater
        val rowView = inflater.inflate(R.layout.unit_row, null, true)



        val option = rowView.findViewById(R.id.txtName) as TextView
        val checkBox = rowView.findViewById(R.id.checkBox) as CheckBox


        option.text = mList[position].name
        checkBox.isChecked = mList[position].checked


        checkBox.setOnCheckedChangeListener { compoundButton, b ->
            // if one is selected deselect others
            for((i, element) in mList.withIndex()) {
                if(position == i) {
                   element.checked = checkBox.isChecked
                    pos = if(element.checked) {
                        position
                    } else {
                        -1
                    }
                } else {
                    element.checked = false
                }
            }
            notifyDataSetChanged() // only one can be selected
        }

        return rowView
    }

    fun getCheckedPosition(): Int {
        return pos
    }
}
