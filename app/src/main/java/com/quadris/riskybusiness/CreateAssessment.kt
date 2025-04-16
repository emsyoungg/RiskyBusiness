package com.quadris.riskybusiness

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
//import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Validators.or
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.io.FileNotFoundException

//import android.widget.Toast


//PAGE 1 of create assessment
class CreateAssessment1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_assessment1)

        //Moves between pages using buttons
        findViewById<Button>(R.id.btnCancel).setOnClickListener{
            startActivity(Intent(this@CreateAssessment1, MainActivity::class.java))
        }
        findViewById<Button>(R.id.btnNext).setOnClickListener{ //when next button pressed
            startActivity(Intent(this@CreateAssessment1, CreateAssessment2::class.java))
        }
    }
}


//page2 of create assessment
class CreateAssessment2 : AppCompatActivity() {
//create empty variables for the input boxes
    var editTextName:  EditText? = null
    var editTextPosition: EditText? = null
    var editTextLocation: EditText? = null
    var editTextHazardDate: EditText? = null
    var editTextHazardTime: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_assessment2)

//connect variables to XML file edit texts
        editTextName = findViewById(R.id.etName)
        editTextPosition = findViewById(R.id.etPosition)
        editTextLocation = findViewById(R.id.etLocation)
        editTextHazardDate = findViewById(R.id.etHazardDate)
        editTextHazardTime = findViewById(R.id.etHazardTime)

        //Move between pages using buttons
        findViewById<Button>(R.id.btnBack).setOnClickListener{
            startActivity(Intent(this@CreateAssessment2, CreateAssessment1::class.java))
        }
        findViewById<Button>(R.id.btnNext).setOnClickListener{
//save user inputs
            val name = editTextName!!.text.toString()
            val position = editTextPosition!!.text.toString()
            val location = editTextLocation!!.text.toString()
            val hazardDate = editTextHazardDate!!.text.toString()
            val hazardTime = editTextHazardTime!!.text.toString()

            val result: Boolean = checkEmpty(name, position, location, hazardDate, hazardTime)
            if (result){
                val toAssessment3 = Intent(this@CreateAssessment2, CreateAssessment3::class.java).apply {
                    putExtra("name",name)
                    putExtra("position",position)
                    putExtra("location", location)
                    putExtra("hazardDate", hazardDate)
                    putExtra("hazardTime", hazardTime) // sends val to create assessment 3
                }
                startActivity(toAssessment3)
            }else{
                Toast.makeText(this@CreateAssessment2, "Please fill out all the questions.", Toast.LENGTH_SHORT).show()
            }
        }


    }
    fun checkEmpty(name: String, position: String, location: String, hazardDate: String, hazardTime: String): Boolean {
        val inputs: Array<String> = arrayOf(name, position, location, hazardDate, hazardTime)
        var flag: Boolean = true

        for (i in inputs){
            if (i == ""){
                flag = false
            }
        }
        return flag
    }
}
//page 3 of create assessment
class CreateAssessment3 : AppCompatActivity() {
    var editTextDetails: EditText? = null
    var editTextReportedTo: EditText? = null
    var editTextReviewedBy: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_assessment3)

        editTextDetails = findViewById(R.id.etDetails)
        editTextReportedTo = findViewById(R.id.etReportedTo)
        editTextReviewedBy = findViewById(R.id.etReviewedBy)

        val name = intent.getStringExtra("name")
        val position = intent.getStringExtra("position")
        val location = intent.getStringExtra("location")
        val hazardDate = intent.getStringExtra("hazardDate")
        val hazardTime = intent.getStringExtra("hazardTime")


        //if yes selected show more views
        findViewById<Button>(R.id.btnYes).setOnClickListener {


            val details = editTextDetails!!.text.toString()
            val reportedTo = editTextReportedTo!!.text.toString()
            val reviewedBy = editTextReviewedBy!!.text.toString()

            //checks if empty
            val result: Boolean = checkEmpty(details, reportedTo, reviewedBy)
            if (result) {
                val toAssessment4 =
                    Intent(this@CreateAssessment3, CreateAssessment4::class.java).apply {
                        putExtra("name", name)
                        putExtra("position", position)
                        putExtra("location", location)
                        putExtra("hazardDate", hazardDate)
                        putExtra("hazardTime", hazardTime)
                        putExtra("details", details)
                        putExtra("reportedTo", reportedTo)
                        putExtra("reviewedBy", reviewedBy)
                    }
                startActivity(toAssessment4)
            } else {
                Toast.makeText(
                    this@CreateAssessment3,
                    "Please fill out all the questions.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        //move between pages using buttons
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            startActivity(Intent(this@CreateAssessment3, CreateAssessment2::class.java))
        }
//next button clicked
        findViewById<Button>(R.id.btnNext).setOnClickListener {

            val details = editTextDetails!!.text.toString()
            val reportedTo = editTextReportedTo!!.text.toString()
            val reviewedBy = editTextReviewedBy!!.text.toString()

            val escalated = "N/A"
            val dateEscalated = "N/A"
            val actionTaken = "N/A"
            val assessmentChanges = "N/A"
            val changesDate = "N/A"

            val result: Boolean = checkEmpty(details, reportedTo, reviewedBy)
            if (result) {

                val toSignSignature =
                    Intent(this@CreateAssessment3, signSignature::class.java).apply {
                        putExtra("name", name)
                        putExtra("position", position)
                        putExtra("location", location)
                        putExtra("hazardDate", hazardDate)
                        putExtra("hazardTime", hazardTime)
                        putExtra("details", details)
                        putExtra("reportedTo", reportedTo)
                        putExtra("reviewedBy", reviewedBy)
                        putExtra("escalated", escalated)
                        putExtra("dateEscalated", dateEscalated)
                        putExtra("actionTaken", actionTaken)
                        putExtra("assessmentChanges", assessmentChanges)
                        putExtra("changesDate", changesDate)
                    }

                startActivity(toSignSignature)

            } else {
                Toast.makeText(
                    this@CreateAssessment3,
                    "Please fill out all the questions.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    fun checkEmpty(details: String, reportedTo: String, reviewedBy: String): Boolean {
        val inputs: Array<String> = arrayOf(details, reportedTo, reviewedBy)
        var flag: Boolean = true

        for (i in inputs) {
            if (i == "") {
                flag = false
            }
        }
        return flag
    }
}


class CreateAssessment4 : AppCompatActivity() {

        //declare vars
    var editTextEscalated: EditText? = null
    var editTextEscalatedDate: EditText? = null
    var editTextActionTaken: EditText? = null
    var editTextChanges: EditText? = null
    var editTextDateOfChanges: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_assessment4)

        //get ass3 vars
        val name = intent.getStringExtra("name")
        val position = intent.getStringExtra("position")
        val location = intent.getStringExtra("location")
        val hazardDate = intent.getStringExtra("hazardDate")
        val hazardTime = intent.getStringExtra("hazardTime")
        val details = intent.getStringExtra("details")
        val reportedTo = intent.getStringExtra("reportedTo")
        val reviewedBy = intent.getStringExtra("reviewedBy")

        //connect to xml
        editTextEscalated = findViewById(R.id.etEscalated)
        editTextEscalatedDate = findViewById(R.id.etEscalateDate)
        editTextActionTaken = findViewById(R.id.etActionTaken)
        editTextChanges = findViewById(R.id.etChangesToAssessment)
        editTextDateOfChanges = findViewById(R.id.etDateOfChanges)

        //move between pages using buttons
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            startActivity(Intent(this@CreateAssessment4, CreateAssessment3::class.java))
        }
        findViewById<Button>(R.id.btnNext).setOnClickListener{


            val escalated = editTextEscalated!!.text.toString()
            val dateEscalated = editTextEscalatedDate!!.text.toString()
            val actionTaken = editTextActionTaken!!.text.toString()
            val assessmentChanges = editTextChanges!!.text.toString()
            val changesDate = editTextDateOfChanges!!.text.toString()

            //checks if empty
            val result: Boolean = checkEmpty(escalated, dateEscalated, actionTaken, assessmentChanges, changesDate)
            if (result) {

                val i = Intent(this@CreateAssessment4, signSignature::class.java).apply {
                    //send to signature class
                    putExtra("name", name)
                    putExtra("position", position)
                    putExtra("location", location)
                    putExtra("hazardDate", hazardDate)
                    putExtra("hazardTime", hazardTime)
                    putExtra("details", details)
                    putExtra("reportedTo", reportedTo)
                    putExtra("reviewedBy", reviewedBy)
                    putExtra("escalated", escalated)
                    putExtra("dateEscalated", dateEscalated)
                    putExtra("actionTaken", actionTaken)
                    putExtra("assessmentChanges", assessmentChanges)
                    putExtra("changesDate", changesDate)
                }
                startActivity(i)
            } else {
                Toast.makeText(
                    this@CreateAssessment4,
                    "Please fill out all the questions.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }

        fun checkEmpty(escalated: String, dateEscalated: String, actionTaken: String, assessmentChanges: String, changesDate: String): Boolean {
            val inputs: Array<String> = arrayOf(dateEscalated, actionTaken, assessmentChanges, changesDate)
            var flag: Boolean = true

            for (i in inputs) {
                if (i == "") {
                    flag = false
                }
            }
            return flag
        }
}

open class signSignature : AppCompatActivity() {

    var myHelper: databaseHelper? = null
    var sqLiteDatabase: SQLiteDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signsignature)

        //recieves all variables from other classes
        val name = intent.getStringExtra("name")
        val position = intent.getStringExtra("position")
        val location = intent.getStringExtra("location")
        val hazardDate = intent.getStringExtra("hazardDate")
        val hazardTime = intent.getStringExtra("hazardTime")
        val details = intent.getStringExtra("details")
        val reportedTo = intent.getStringExtra("reportedTo")
        val reviewedBy = intent.getStringExtra("reviewedBy")
        val escalated = intent.getStringExtra("escalated")
        val dateEscalated = intent.getStringExtra("dateEscalated")
        val actionTaken = intent.getStringExtra("actionTaken")
        val assessmentChanges = intent.getStringExtra("assessmentChanges")
        val changesDate = intent.getStringExtra("changesDate")

        myHelper = databaseHelper(this@signSignature)
        sqLiteDatabase = myHelper!!.writableDatabase
        findViewById<Button>(R.id.btnFinish).setOnClickListener {

                saveToDatabase(name, position, location, hazardDate, hazardTime, details, reportedTo,
                    reviewedBy, escalated, dateEscalated, actionTaken, assessmentChanges, changesDate)
            }


        //move between pages using buttons
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            startActivity(Intent(this@signSignature, CreateAssessment3::class.java))
        }

    }


    //saves inputs from the user to variables and calls the save function


    //This checks to see if the data has been added successfully to the database
    fun saveToDatabase(
        customerName: String?,
        position: String?,
        location: String?,
        hazardDate: String?,
        hazardTime: String?,
        details: String?,
        reportedTo: String?,
        reviewedBy: String?,
        escalated: String?,
        dateEscalated: String?,
        actionTaken: String?,
        assessmentChanges: String?,
        changesDate: String?,
    ) {
        val successful: Boolean = myHelper!!.insert(customerName, position, location, hazardDate, hazardTime,
            details, reportedTo, reviewedBy,
            escalated, dateEscalated, actionTaken, assessmentChanges, changesDate,
        )


        if (successful) {
            val context = applicationContext
            val text: CharSequence = "data added!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

            //export pdf
            val cursor = sqLiteDatabase!!.rawQuery("select * from assessmentTable", null)
            cursor.move(cursor.count)
            try {
                PrintPDF(customerName, position, location, hazardDate, hazardTime,
                    details, reportedTo, reviewedBy,
                    escalated, dateEscalated, actionTaken, assessmentChanges, changesDate).getPDF()


                Toast.makeText(this@signSignature, "PDF Created", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@signSignature, PDFviewer::class.java))
            } catch (e: FileNotFoundException) {
                Toast.makeText(this@signSignature, "PDF not created", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }


        } else {
            val context = applicationContext
            val text: CharSequence = "went wrong"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }
}