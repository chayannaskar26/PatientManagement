package com.example.chayannaskar.patientmanagement;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewDrugFragment extends Fragment {

    private DrugsDataAccess drugsDataAccess;

    public NewDrugFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_drug, container, false);
        final EditText etaffliction = (EditText) root.findViewById(R.id.etaffliction);
        final EditText etdrug = (EditText) root.findViewById(R.id.etdrug);
        root.findViewById(R.id.btnsave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String affliction = etaffliction.getText().toString();
                String drug = etdrug.getText().toString();
                Prescription prescription = new Prescription(affliction, drug);
                drugsDataAccess = new DrugsDataAccess(getContext(), 1);
                drugsDataAccess.addToDrugList(prescription);
                Toast.makeText(getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();

            }
        });
        return root;
    }
}
