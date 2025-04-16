package com.quadris.riskybusiness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnLogout: Button? = null
        var btnCreateAssessment: Button? = null
        var btnPreviousAssessments: Button? = null
        var btnInformationHub: Button? = null

        btnLogout = findViewById(R.id.btnLogout)
        btnCreateAssessment = findViewById(R.id.btnCreateAssessment)
        btnPreviousAssessments = findViewById(R.id.btnPreviousAssessments)
        btnInformationHub = findViewById(R.id.btnInformationHub)


        //logout button
        btnLogout.setOnClickListener {
            //logout from app
            FirebaseAuth.getInstance().signOut()
            //takes us back to login page
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }

        //Move to create an assessment
        btnCreateAssessment.setOnClickListener {
            //move to assessment page
            startActivity(Intent(this@MainActivity, CreateAssessment1::class.java))
            //or could use onBackPressed()
        }

        btnPreviousAssessments.setOnClickListener {
            startActivity(Intent(this@MainActivity, PreviousAssessmentsActivity::class.java))
        }
        btnInformationHub.setOnClickListener {  }

    }




}
