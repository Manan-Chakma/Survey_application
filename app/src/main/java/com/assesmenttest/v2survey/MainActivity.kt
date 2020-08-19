package com.assesmenttest.v2survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.assesmenttest.v2survey.models.Survey
import com.assesmenttest.v2survey.ui.DashboardActivity
import com.assesmenttest.v2survey.ui.SaveActivity
import com.assesmenttest.v2survey.ui.SurveyActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get survyes SurveyActivity and pass the surveys to SaveActivity to insert into local room database
        if (intent.hasExtra("SAVE_DATA")) {
            val extras = intent.extras
            val list = extras?.getParcelableArrayList<Survey>("SAVE_DATA")
            val iIntent = Intent(this, SaveActivity::class.java)
            iIntent.putParcelableArrayListExtra("SAVE_DATA", list)
            startActivity(iIntent)

        }

        findViewById<Button>(R.id.goto_survey).setOnClickListener {
            startActivity(Intent(this, SurveyActivity::class.java))
        }
        findViewById<Button>(R.id.dashboard).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

}