package com.example.chayannaskar.patientmanagement;

import java.lang.ref.PhantomReference;

/**
 * Created by chayannaskar on 23/06/17.
 */

public class Sms {

    private String address;
    private String body;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Sms(String address, String body) {
        this.address = address;
        this.body = body;
    }

    public Sms() {
    }
}
