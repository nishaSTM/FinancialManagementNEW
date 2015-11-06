package myapplication.admin.example.com.financialmanagement;

import java.util.Date;

class Sms {

    public  Date date;

    public  String from;

    public  String body;

    public Sms(Date date, String from, String body) {
        this.date = date;
        this.from = from;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "date=" + date +
                ", from='" + from + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}