package com.example.chayannaskar.patientmanagement;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewPatientFragment extends Fragment {

    private PatientDataAccess pda;

    private EditText etname, etphone, etafflication, etdate, etcritical;


    public NewPatientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_patient, container, false);
        //String phone, String name, String afflication, String date, int criticalpriorty
        etphone = (EditText) root.findViewById(R.id.etphoneno);
        etname = (EditText) root.findViewById(R.id.etname);
        etafflication = (EditText) root.findViewById(R.id.etaffliction);
        etdate = (EditText) root.findViewById(R.id.etdate);





        root.findViewById(R.id.btncreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sdate = etdate.getText().toString();
                Date date = null, currdate;
                try {
                    date = new SimpleDateFormat("dd-MM-yyyy").parse(sdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                currdate = Calendar.getInstance().getTime();
                int diffDays = (int) ((currdate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
                Log.v("Date", "difference in days"+diffDays);

                int priority;

                if(diffDays <=11){
                    priority = 1;
                }else if(diffDays <=21){
                    priority = 2;
                }else{
                    priority = 3;
                }

                Log.v("Date", "Priorty"+priority);


                Patient p = new Patient(etphone.getText().toString(), etname.getText().toString(), etafflication.getText().toString(), etdate.getText().toString(), priority);

                pda = new PatientDataAccess(getActivity(), 1);

                pda.createNewPatient(p);

                Toast.makeText(getContext(), "Creation Successful", Toast.LENGTH_SHORT).show();


            }
        });



        return root;
    }
}
