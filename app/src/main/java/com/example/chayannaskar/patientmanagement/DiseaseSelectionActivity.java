package com.example.chayannaskar.patientmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiseaseSelectionActivity extends AppCompatActivity {

    private DrugsDataAccess dda;
    private ArrayList<String> diseaselist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);

        Intent intent = getIntent();
        final String number = intent.getStringExtra("number");
        TextView tvnumber = (TextView) findViewById(R.id.tvphonenumber);
        tvnumber.setText("To : "+number);

        final Spinner affliction = (Spinner) findViewById(R.id.affliction);
        dda = new DrugsDataAccess(getApplicationContext(), 1);
        Log.v("DSA","dda running");

        diseaselist = new ArrayList<>();
        diseaselist = dda.getAllAfliction();
        if (diseaselist != null) {
            ArrayList<String> dlist = new ArrayList<>();
            dlist.add("--none--");
            for (String d : diseaselist) {
                dlist.add(d);
            }
            ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dlist);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            affliction.setAdapter(aa);
        }
        findViewById(R.id.btnnext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (affliction.getSelectedItem().toString().equals("--none--")){
                    Toast.makeText(getApplicationContext(), "Please Select Disease", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(getApplicationContext(), PrescriptionActivity.class);
                    i.putExtra("number", number);
                    i.putExtra("disease", affliction.getSelectedItem().toString());
                    Log.v("DSA", " : "+affliction.getSelectedItem().toString());
                    startActivity(i);
                }
            }
        });
    }
}
