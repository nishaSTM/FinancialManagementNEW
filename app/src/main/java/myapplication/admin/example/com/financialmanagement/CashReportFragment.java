package myapplication.admin.example.com.financialmanagement;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.artroo.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by hp1 on 21-01-2015.
 */
public class CashReportFragment extends Fragment {

    ListView mainListView;
    ArrayList<User> db_dates;
    String selectedSpinnerValueFrom;
    String months[] = {"Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec"};
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


        db_dates = new ArrayList<User>();
        /*Bundle b = getArguments();
        if(b!=null) {
            db_dates = (ArrayList<User>) b.getSerializable("key");

        }*/
       // loadExpenses();
   }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cashreportfragment,container,false);

        final BarChart chart = (BarChart)v.findViewById(R.id.chart);
        final Spinner spinnerFrom = (Spinner) v.findViewById(R.id.bar_from);
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selectedSpinnerValueFrom = spinnerFrom.getSelectedItem().toString();
                loadExpenses(selectedSpinnerValueFrom);
                BarData data = new BarData(getXAxisValues(), getDataSet());
                chart.setData(data);
                chart.setDescription("My Chart");
                chart.animateXY(1000, 1000);
                chart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        return v;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        //loadExpenses();
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        for(int i=0;i<db_dates.size();i++)
        {
            BarEntry v1e1 = new BarEntry(db_dates.get(i).money, i); // Jan
            valueSet1.add(v1e1);
        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        //     dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        for(int i=0;i<db_dates.size();i++)
        {
            if(selectedSpinnerValueFrom.equals("Monthly")) {
                xAxis.add(months[Integer.parseInt(db_dates.get(i).month) - 1]);
            }
            else
            {
                xAxis.add(db_dates.get(i).month);
            }
        }

        return xAxis;
    }

    private void loadExpenses(String selectedSpinValue) {
        db_dates = new ArrayList<User>();
        //db_amnt = new ArrayList<String>();


        ExpenseDatabase dbase = new ExpenseDatabase(getActivity().getApplicationContext());
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);

        //currentMonth=currentMonth;
        int currentYear = cal.get(Calendar.YEAR);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

       // currentDay=currentDay+1;
        if (selectedSpinValue.equals("Monthly")) {
            currentMonth=currentMonth+1;
            String enddate = currentYear + " - " + currentMonth + " - " + currentDay;

            if ((currentMonth - 6 + 1) < 0)
                {
                    currentYear = currentYear - 1;

                    //currentMonth = currentMonth - 1;
                    currentMonth = 12 - (6 - currentMonth);
                }
             else {
                currentMonth = currentMonth - 6;
            }

            String startdate = currentYear + " - " + currentMonth + " -1";

            dbase.open();
            Cursor db_cur = dbase.fetchAllManualAlertsforpast6months(ExpenseDatabase.MANUAL_TABLE,
                    ExpenseDatabase.EXPENSE_INT, startdate, enddate);


        /*Cursor db_cur = dbase.fetchAllManualAlerts(ExpenseDatabase.MANUAL_TABLE,
                ExpenseDatabase.EXPENSE_INT);*/
            if (db_cur != null && db_cur.getCount() > 0) {
                db_cur.moveToFirst();
                do {
                    User data = new User(null, null, null, null, db_cur.getInt(0), db_cur.getString(1), null, null);
                    db_dates.add(data);

                } while (db_cur.moveToNext());
            }
            dbase.close();
        }
        else if(selectedSpinValue.equals("Yearly"))
        {
            currentMonth=currentMonth+1;
            String enddate = currentYear + "-" + currentMonth+1 + "-" + currentDay;
            Log.d("endYear###==",enddate);
            dbase.open();
            Cursor db_cur = dbase.fetchAllManualAlertsforyears(ExpenseDatabase.MANUAL_TABLE,
                    ExpenseDatabase.EXPENSE_INT,enddate);


        /*Cursor db_cur = dbase.fetchAllManualAlerts(ExpenseDatabase.MANUAL_TABLE,
                ExpenseDatabase.EXPENSE_INT);*/
            if (db_cur != null && db_cur.getCount() > 0) {
                db_cur.moveToFirst();
                do {
                    User data = new User(null, null, null, null, db_cur.getInt(0), db_cur.getString(1), null, null);
                    db_dates.add(data);

                } while (db_cur.moveToNext());
            }
            dbase.close();

        }
        else
        {

            currentMonth=currentMonth+1;
            String enddate = currentYear + " - " + currentMonth + " - " + currentDay;

            if ((currentDay - 7 + 1) < 0) {
                if(currentMonth==1 && currentDay<=7)
                {
                    currentYear=currentYear-1;
                }
                currentMonth = currentMonth - 1;
                currentDay = 31 -(7- currentDay) ;
            } else {
                currentDay = currentDay - 7;
            }

            String startdate = currentYear + " - " + currentMonth + " - " + currentDay + " -1";

            dbase.open();
            Cursor db_cur = dbase.fetchAllManualAlertsforWeeks(ExpenseDatabase.MANUAL_TABLE,
                    ExpenseDatabase.EXPENSE_INT, startdate, enddate);


        /*Cursor db_cur = dbase.fetchAllManualAlerts(ExpenseDatabase.MANUAL_TABLE,
                ExpenseDatabase.EXPENSE_INT);*/
            if (db_cur != null && db_cur.getCount() > 0) {
                db_cur.moveToFirst();
                do {
                    User data = new User(null, null, null, null, db_cur.getInt(0), db_cur.getString(1), null, null);
                    db_dates.add(data);

                } while (db_cur.moveToNext());
            }
            dbase.close();
        }
    }
}