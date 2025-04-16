package com.quadris.riskybusiness;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class emailActivity extends AppCompatActivity {
    Button btnEmail;
    String email;
    String filePath;
    String emailSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        Bundle bundle = getIntent().getExtras();

        btnEmail = findViewById(R.id.btnEmail);
        filePath = "/storage/emulated/0/Download/RiskAssessment.pdf";
        emailSubject = "My risk Assessment";

        if(bundle.getString("email")!= null)
        {
            email = bundle.getString("email");
        }//gets users email from login class

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);// makes the gmail app option appear
                intent.setType("application/pdf");//allows of email to attach pdf
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);//adds the subject of the email
                intent.putExtra(Intent.EXTRA_EMAIL, email);//adds users email address
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(filePath));//attaches file to email

                startActivity(intent);

            }
        });
    }

}
