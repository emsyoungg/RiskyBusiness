package com.quadris.riskybusiness

import android.content.Intent
import android.database.Cursor
import android.database.SQLException
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.FileNotFoundException
import java.util.*
import kotlin.collections.ArrayList

class PreviousAssessmentsActivity : signSignature(){

    var myDB: databaseHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_assessments)


        findViewById<Button>(R.id.btnBack).setOnClickListener {
            startActivity(Intent(this@PreviousAssessmentsActivity, MainActivity::class.java))
        }


        val listView = findViewById<ListView>(R.id.edit_listView)
        myDB = databaseHelper(this@PreviousAssessmentsActivity)

        val theList = ArrayList<String>()
        val data = myDB!!.getListContents()


        if (data!!.count == 0) {
            Toast.makeText(this@PreviousAssessmentsActivity, "The database is empty", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(0) + "             " + data.getString(1));
                theList.sortDescending()
                val adapter = ArrayAdapter(this, R.layout.listview_item, theList)
                val listView: ListView = findViewById(R.id.edit_listView)
                listView.setAdapter(adapter)
            }
        }

        //when list view is clicked...
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id -> // finds which item in the list that is clicked
                val itemValue = listView.getItemAtPosition(position) as String //gets the data from that item
                val recordID = itemValue[0] //gets the ID of the item in the list which will be used to access the correct PDF

                // Toast the values //used for testing
                //Toast.makeText(applicationContext, "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG).show()

                var record = ArrayList<String>() //array to store the data from the record in
                val recordData = myDB!!.getRowByID(recordID) //calls a function to get the data from the database

                if (recordData!!.count ==0){
                    Toast.makeText(this@PreviousAssessmentsActivity, "PDF not found", Toast.LENGTH_LONG).show();
                }else{
                    recordData.moveToFirst()
                    for (n in 0..13)
                    record.add(recordData.getString(n)) //adds the data from the record from each column to the array

                    try { PrintPDF( //sends the data from the array to generate a PDF
                        record[1],
                        record[2],
                        record[3],
                        record[4],
                        record[5],
                        record[6],
                        record[7],
                        record[8],
                        record[9],
                        record[10],
                        record[11],
                        record[12],
                        record[13]).getPDF()

                        Toast.makeText(this@PreviousAssessmentsActivity, "PDF found", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@PreviousAssessmentsActivity, PDFviewer::class.java))// moves to PDF viewer

                    } catch (e: FileNotFoundException) {
                        Toast.makeText(this@PreviousAssessmentsActivity, "PDF not found", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }


            }
    }
}

