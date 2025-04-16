package com.quadris.riskybusiness

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.print.PrintManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import java.io.File
import android.R.*
import androidx.core.content.ContextCompat.getSystemService


class PDFviewer : AppCompatActivity() {
    //initialise file
    var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfviewer)

        //connect to xml file view
        var pdfView = findViewById<PDFView>(R.id.pdfView)
        //find path of pdf document
        file = File("/storage/emulated/0/Download/RiskAssessment.pdf")
        pdfView.fromFile(file).load()



        findViewById<Button>(R.id.btnBack).setOnClickListener {
            startActivity(Intent(this@PDFviewer, MainActivity::class.java))
        }

        findViewById<Button>(R.id.btnPrint).setOnClickListener {

        }

        findViewById<Button>(R.id.btnEmail).setOnClickListener {

        }

    }

}