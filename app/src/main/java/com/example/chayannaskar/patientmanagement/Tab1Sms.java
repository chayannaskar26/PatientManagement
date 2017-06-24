package com.example.chayannaskar.patientmanagement;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chayannaskar on 16/06/17.
 */

public class Tab1Sms extends Fragment {

    private ListView listview;
    private ArrayList<Sms> smslog;
    private SmsAdapter smsAdapter;
    private PatientDataAccess pda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String number;



        View root =inflater.inflate(R.layout.tab1sms, container, false);
        ContentResolver cr = getContext().getContentResolver();
        listview = (ListView) root.findViewById(R.id.listviewsms);
        Cursor smsInboxCursor = cr.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        pda = new PatientDataAccess(getContext(), 1);

        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return null;

        smslog = new ArrayList<>();
            do {
                Patient p = pda.searchNumber(smsInboxCursor.getString(indexAddress));
                //String str = "SMS From: " + smsInboxCursor.getString(indexAddress) +"\n" + smsInboxCursor.getString(indexBody) + "\n";
                //Log.v("Tab1","Str : "+smsInboxCursor.getString(indexAddress));

                String address = smsInboxCursor.getString(indexAddress);
                String body = smsInboxCursor.getString(indexBody);
                Log.v("Tab1","Str : "+address);

                if(p != null){

                    smslog.add(new Sms(address, body));

                }

            } while (smsInboxCursor.moveToNext());

        if (smslog != null) {


            smsAdapter = new SmsAdapter(getContext(), smslog);

            listview.setAdapter(smsAdapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    TextView tvnumber = (TextView) view.findViewById(R.id.tvnumber);

                    String number = tvnumber.getText().toString();
                    Log.v("tab1", "number : "+number);
                    Intent intent = new Intent(getActivity(), DiseaseSelectionActivity.class);
                    intent.putExtra("number", number);
                    startActivity(intent);

                }
            });
        }



        return root;
    }
}
