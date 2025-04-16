package com.quadris.riskybusiness

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log

class databaseHelper(context: Context?) : SQLiteOpenHelper(context, "RiskAnalyserDatabase", null, 1) {
    //database structure
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val createTable =
            "create table assessmentTable(assessmentID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "customerName STRING," +
                    "position STRING, " +
                    "location STRING, " +
                    "dateReported STRING, " +
                    "timeReported STRING, " +
                    "details STRING, " +
                    "reportedTo STRING, " +
                    "reviewedBy STRING, " +
                    "escalated STRING, " +
                    "dateEscalated STRING," +
                    "actionTaken STRING," +
                    "assessmentChanges STRING," +
                    "changesDate STRING" +
                    ");"
        sqLiteDatabase.execSQL(createTable)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }


    //initialises database attributes
    fun insert(
        customerName: String?,
        position: String?,
        location: String?,
        dateReported: String?,
        timeReported: String?,
        details: String?,
        reportedTo: String?,
        reviewedBy: String?,
        escalated: String?,
        dateEscalated: String?,
        actionTaken: String?,
        assessmentChanges: String?,
        changesDate: String?,
        //inputs variables into fields
    ): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("customerName", customerName)
        contentValues.put("position", position)
        contentValues.put("location", location)
        contentValues.put("dateReported", dateReported)
        contentValues.put("timeReported", timeReported)
        contentValues.put("details", details)
        contentValues.put("reportedTo", reportedTo)
        contentValues.put("reviewedBy", reviewedBy)
        contentValues.put("escalated", escalated)
        contentValues.put("dateEscalated", dateEscalated)
        contentValues.put("actionTaken", actionTaken)
        contentValues.put("assessmentChanges", assessmentChanges)
        contentValues.put("changesDate", changesDate)


        //returns true if added data is successful
        Log.d("databaseHelper", "add data: adding $customerName to assessmentTable")
        val result = sqLiteDatabase.insert("assessmentTable", null, contentValues)
        return result != -1L
    }
//gets id and date of assessments
    fun getListContents(): Cursor? {
        val sqLiteDatabase = this.writableDatabase
        return  sqLiteDatabase.rawQuery("SELECT assessmentID, dateReported FROM assessmentTable", null)
    }
//gets data from record
    fun getRowByID(assessmentID: Char): Cursor? {
        val sqLiteDatabase = this.writableDatabase
        return  sqLiteDatabase.rawQuery("SELECT * FROM assessmentTable WHERE assessmentID=$assessmentID",null)
    }

}
