package com.example.chayannaskar.patientmanagement;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chayannaskar on 23/06/17.
 */

public class SmsAdapter extends ArrayAdapter<Sms> {
    private Context ctx;
    private ArrayList<Sms> smsLists;
    private PatientDataAccess patientDataAccess;

    public SmsAdapter(@NonNull Context context, ArrayList<Sms> list) {
        super(context, R.layout.sms_row_layout, list);
        ctx = context;
        smsLists = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View row = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            row = inflater.inflate(R.layout.sms_row_layout, parent, false);

        }else{
            row = convertView;
        }
        if (smsLists != null) {

            TextView tvaddress = (TextView) row.findViewById(R.id.tvnumber);
            TextView tvbody = (TextView) row.findViewById(R.id.tvbody);
            TextView tvname = (TextView) row.findViewById(R.id.tvname);

            Sms sms = smsLists.get(position);
            tvaddress.setText(sms.getAddress());
            tvbody.setText(sms.getBody());
            patientDataAccess = new PatientDataAccess(getContext(), 1);
            Patient p = patientDataAccess.searchNumber(sms.getAddress());
            tvname.setText(p.getName());
            if (p.getCriticalpriorty() == 1) {
                row.setBackgroundResource(R.color.row_critical);
            } else if (p.getCriticalpriorty() == 2) {
                row.setBackgroundResource(R.color.row_moderate);
            } else {
                row.setBackgroundResource(R.color.row_fine);
            }

        }

        return row;
    }
}
