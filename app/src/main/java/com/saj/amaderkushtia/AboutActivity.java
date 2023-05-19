package com.saj.amaderkushtia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private ImageView fbId,linkedinId,fbPageId, fbPageWebId,fbGroupWebId;
    private TextView sajLinkId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        fbId = findViewById(R.id.fbId);
        linkedinId = findViewById(R.id.linkedinId);
        fbPageId = findViewById(R.id.fbPageId);
        sajLinkId = findViewById(R.id.sajLinkId);

        fbPageWebId = findViewById(R.id.fbPageWebId);
        fbGroupWebId = findViewById(R.id.fbGroupWebId);


        fbId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.facebook.com/saj0cse"));
                startActivity(intent);
            }
        });


        linkedinId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.linkedin.com/in/saj0cse"));
                startActivity(intent);
            }
        });

        fbPageId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.facebook.com/saj.developer"));
                startActivity(intent);
            }
        });

        sajLinkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.facebook.com/saj.developer"));
                startActivity(intent);
            }
        });

        //=======================website link=========================

        fbPageWebId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/amaderkushtia.net/"));
                startActivity(intent);
            }
        });

        fbGroupWebId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/groups/1705019496394688/"));
                startActivity(intent);
            }
        });

        //=======================website link=========================


    }
}