package com.quadris.riskybusiness;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import static com.itextpdf.io.image.ImageDataFactory.create;



import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.image.*;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PrintPDF extends AppCompatActivity {
    String name;
    String position;
    String location;
    String hazardDate;
    String hazardTime;
    String details;
    String reportedTo;
    String reviewedBy;
    String escalated;
    String dateEscalated;
    String actionTaken;
    String assessmentChanges;
    String changesDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public PrintPDF(String name, String position, String location, String dateReported, String timeReported, String details, String reportedTo, String reviewedBy, String escalated, String dateEscalated, String actionTaken, String assessmentChanges, String changesDate) throws IOException {
        this.name = name;
        this.position = position;
        this.location = location;
        this.hazardDate = dateReported;
        this.hazardTime = timeReported;
        this.details = details;
        this.reportedTo = reportedTo;
        this.reviewedBy = reviewedBy;
        this.escalated = escalated;
        this.dateEscalated = dateEscalated;
        this.actionTaken = actionTaken;
        this.assessmentChanges = assessmentChanges;
        this.changesDate = changesDate;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getPDF() throws IOException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "RiskAssessment.pdf");
        OutputStream outputStream = new FileOutputStream(file);


        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);


//this is all the code I attempted at adding the logo to the pdf
        //draw logo at the top
        //getDrawable(R.drawable.quadris_logo);

        /*
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,200,125 , false);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapResized.compress(Bitmap.CompressFormat.PNG,100, stream);
        byte[] bitmapData = stream.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapData);
        Image image = new Image(imageData); */

       /* // Creating an ImageData object
        String imageFile = "drawable/quadris_logo.png";
        ImageData data = ImageDataFactory.create(imageFile);
        // Creating an Image object
        Image img = new Image(data);

            InputStream asset = getAssets().open("quadris_logo2.png");
    // get input stream
    Bitmap bitmap = BitmapFactory.decodeStream(asset);
    Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,200,125 , false);
    ByteArrayOutputStream stream = new ByteArrayOutputStream();*/
        // get input stream

       /* bitmapResized.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapData = stream.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapData);
        Image image = new Image(imageData);
*/
// Creating image by file name

   // String imgScr = "images\\quadris_logo.png";
    //ImageData data = ImageDataFactory.create("quadris_logo.png");
    //Image image = new Image(data);

        //title
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Paragraph title = new Paragraph("Quadris LTD").setBold().setFontSize(24).setTextAlignment(TextAlignment.CENTER);
        Paragraph title2 = new Paragraph("Risk Assessment").setTextAlignment(TextAlignment.LEFT).setFontSize(20);

        float columnWidth[] = {300,300};//Todo table
        Table table0 = new Table(columnWidth);

        table0.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY).setBorder(Border.NO_BORDER).add(new Paragraph("QUADRIS LTD: SK6 2SN").setBold()));
        table0.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY).setBorder(Border.NO_BORDER).add(new Paragraph("+44 (0) 161 537 4980").setTextAlignment(TextAlignment.RIGHT)));

        String dateStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

//Table
        float columnWidth1[] = {120, 160, 120, 160};
        Table table1 = new Table(columnWidth1);
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Name: ").setFontSize(14)));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(name).setFontSize(14)));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Position:  ").setFontSize(14)));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(position).setFontSize(14)));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Date Reported: ").setFontSize(14)));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(dateStamp).setFontSize(14)));//Todo add automatic date
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Time Reported: ").setFontSize(14)));
        table1.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(timeStamp).setFontSize(14))); //Todo add automatic time

        float columnWidth2[] = {140,460};
        Table table2 = new Table(columnWidth2);

        table2.addCell((new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY).add(new Paragraph("Question:").setBold())));
        table2.addCell((new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY).add(new Paragraph("Answer:").setBold())));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Location").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(location).setFontSize(13)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Hazard Date").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(hazardDate).setFontSize(13)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Hazard Time").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(hazardTime).setFontSize(13)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Hazard Details").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(details).setFontSize(13)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Reported To").setBold()));
        table2.addCell(new Cell().add(new Paragraph(reportedTo).setFontSize(13)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Reviewed By").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(reviewedBy).setFontSize(13)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Escalated?").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(escalated).setFontSize(13)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Date Escalated").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(dateEscalated).setFontSize(13)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Action Taken").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(actionTaken).setFontSize(14)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Assessment Changes").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(assessmentChanges).setFontSize(14)));
        table2.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY,50).add(new Paragraph("Changes Date").setFontSize(14).setBold()));
        table2.addCell(new Cell().add(new Paragraph(changesDate).setFontSize(14)));


        //document.add(image);
        document.add(title);
        document.add(title2);
        document.add(table0);
        document.add(new Paragraph("\n"));
        document.add(table1);
        document.add(new Paragraph("\n"));
        document.add(table2);
        document.close();

    }
}

