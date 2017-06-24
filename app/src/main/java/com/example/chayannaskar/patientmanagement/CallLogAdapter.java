package com.example.chayannaskar.patientmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.ArrayList;


/**
 * Created by chayannaskar on 20/06/17.
 */

public class CallLogAdapter extends ArrayAdapter<CallList> {

    private Context ctx;
    private ArrayList<CallList> callLists;
    private String TAG = "CLADAP";
    private PatientDataAccess patientDataAccess;





    public CallLogAdapter(@NonNull Context context, ArrayList<CallList> list) {
        super(context, R.layout.call_row_layout, list);
        ctx = context;
        callLists = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            row = inflater.inflate(R.layout.call_row_layout, parent, false);

        }else{
            row = convertView;
        }
        if (callLists != null) {

            TextView tvname = (TextView) row.findViewById(R.id.tvname);
            TextView tvnumber = (TextView) row.findViewById(R.id.tvnumber);
            TextView tvdate = (TextView) row.findViewById(R.id.tvdate);
            TextView tvtype = (TextView) row.findViewById(R.id.tvtype);

            CallList clist = callLists.get(position);





            tvnumber.setText(clist.getNumber());

            String date = clist.getDate();
            patientDataAccess = new PatientDataAccess(getContext(), 1);
            Patient p = patientDataAccess.searchNumber(clist.getNumber());
            if (p.getCriticalpriorty() == 1) {
                row.setBackgroundResource(R.color.row_critical);
            } else if (p.getCriticalpriorty() == 2) {
                row.setBackgroundResource(R.color.row_moderate);
            } else {
                row.setBackgroundResource(R.color.row_fine);
            }
            tvname.setText(p.getName());



            tvdate.setText(clist.getDate());
            tvtype.setText(clist.getType());
        }



        return row;
    }
}
