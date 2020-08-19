package com.assesmenttest.v2survey.ui.adapter


import android.util.Log
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.assesmenttest.v2survey.R
import com.assesmenttest.v2survey.models.Survey
import com.assesmenttest.v2survey.ui.frags.*
import com.assesmenttest.v2survey.utils.getOptionsArray

class MyViewPagerAdapter(
    fm: FragmentActivity,
    private val fList: List<Fragment>,
    private val surveys: List<Survey>
) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return fList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fList[position]
    }


    // on detached from window save the answers
    override fun onViewDetachedFromWindow(holder: FragmentViewHolder) {

        when (fList[holder.adapterPosition]) {
            is TextTypeFragment -> {
                val ans = holder.itemView.findViewById<TextView>(R.id.answer_text)
                if (ans.text.toString().trim().isNotBlank()) {
                    surveys[holder.adapterPosition].answer = ans.text.toString().trim()
                    //Log.d("MYTAG", holder.adapterPosition.toString())
                }
            }
            is MultipleChoiceFragment -> {
                val group = holder.itemView.findViewById<RadioGroup>(R.id.multiple_choice)
                val id = group.checkedRadioButtonId
                val mList = surveys[holder.adapterPosition].options?.let { getOptionsArray(it) }
                if (mList != null) {
                    if (id >= 0 && id < mList.size) {
                        surveys[holder.adapterPosition].answer =
                            mList[id]
                        //Log.d("MYTAG", holder.adapterPosition.toString())
                    }
                }
            }
            is DropDownTypeFragment -> {
                val choice = holder.itemView.findViewById<Spinner>(R.id.spinner)
                val pos = choice.selectedItemPosition
                val mList = surveys[holder.adapterPosition].options?.let { getOptionsArray(it) }
                if (mList != null) {
                    if (pos >= 0 && pos < mList.size) {
                        surveys[holder.adapterPosition].answer =
                            mList[pos]
                        //Log.d("MYTAG", holder.adapterPosition.toString())
                    }
                }
            }
            is CheckBoxFragment -> {
                val s = (fList[holder.adapterPosition] as CheckBoxFragment).getSelectedItem()
                if (s != -1) {
                    val mList = surveys[holder.adapterPosition].options?.let { getOptionsArray(it) }
                    if (mList != null) {
                        surveys[holder.adapterPosition].answer =
                            mList[s]
                        //Log.d("MYTAG", holder.adapterPosition.toString())
                    }
                }
            }
            is NumberFragment -> {
                val ans = holder.itemView.findViewById<TextView>(R.id.number)
                if (ans.text.toString().trim().isNotBlank()) {
                    surveys[holder.adapterPosition].answer = ans.text.toString().trim()
                    //Log.d("MYTAG", holder.adapterPosition.toString())
                }
            }
            else -> {
                super.onViewDetachedFromWindow(holder)
            }
        }
    }

    // returns finished surveys
    fun getAnswers(): List<Survey> {
        return surveys
    }

}