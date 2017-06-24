package com.example.chayannaskar.patientmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PrescriptionActivity extends AppCompatActivity {

    private DrugsDataAccess dda;
    private ArrayList<String> drugsList, diseaselist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        Intent intent = getIntent();
        final String number = intent.getStringExtra("number");
        String affliction = intent.getStringExtra("disease");
        Log.v("Number",":"+ affliction);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("To :  " + number);



        final Spinner medicine1 = (Spinner) findViewById(R.id.medicine1);
        final Spinner medicine2 = (Spinner) findViewById(R.id.medicine2);
        final Spinner medicine3 = (Spinner) findViewById(R.id.medicine3);
        final Spinner medicine4 = (Spinner) findViewById(R.id.medicine4);
        final Spinner medicine5 = (Spinner) findViewById(R.id.medicine5);
        final EditText et1 = (EditText) findViewById(R.id.et1);
        final EditText et2 = (EditText) findViewById(R.id.et2);
        final EditText et3 = (EditText) findViewById(R.id.et3);
        final EditText et4 = (EditText) findViewById(R.id.et4);
        final EditText et5 = (EditText) findViewById(R.id.et5);

        dda = new DrugsDataAccess(getApplicationContext(), 1);
        drugsList = dda.getAllDrugs(affliction);
        ArrayList<String> dlist = new ArrayList<>();
        dlist.add("--none--");


        if(drugsList != null){
            for (String p : drugsList){
                dlist.add(p);
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, dlist);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            medicine1.setAdapter(dataAdapter);
            medicine2.setAdapter(dataAdapter);
            medicine3.setAdapter(dataAdapter);
            medicine4.setAdapter(dataAdapter);
            medicine5.setAdapter(dataAdapter);
        }



        findViewById(R.id.prescribe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prescribe = "Rx,\n";
                Log.v("res"," : "+ prescribe);
                if (!medicine1.getSelectedItem().toString().equals("--none--")){
                    prescribe = prescribe + medicine1.getSelectedItem().toString()+ " - " + et1.getText().toString() + "\n";

                }

                if (!medicine2.getSelectedItem().toString().equals("--none--")){

                    prescribe = prescribe + medicine2.getSelectedItem().toString()+ " - " + et2.getText().toString() + "\n";

                }
                if (!medicine3.getSelectedItem().toString().equals("--none--")){

                    prescribe = prescribe + medicine3.getSelectedItem().toString()+ " - " + et3.getText().toString() + "\n";

                }
                if (!medicine4.getSelectedItem().toString().equals("--none--")){

                    prescribe = prescribe + medicine4.getSelectedItem().toString()+ " - " + et4.getText().toString() + "\n";

                }

                if (!medicine5.getSelectedItem().toString().equals("--none--")){
                    prescribe = prescribe + medicine5.getSelectedItem().toString()+ " - " +et5.getText().toString() + "\n";

                }

                Log.v("pact"," : "+prescribe);

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, prescribe, null, null);

            }
        });


    }
}

