package myapplication.admin.example.com.financialmanagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artroo.R;

public class CashMainActivity extends Activity implements View.OnClickListener {


    private ImageButton ib;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText et;

    //	ListView mainListView;
    ArrayList<User> db_dates;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashmaindeteeditor);
        Intent intent = getIntent();

        loadExpenses();


        ib = (ImageButton) findViewById(R.id.calendarButton);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        et = (EditText) findViewById(R.id.deteditor_date);
        ib.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        showDialog(0);
    }

    @Override

    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            et.setText(selectedYear + " - " + (selectedMonth + 1) + " - "
                    + selectedDay);
            month=selectedMonth+1;
            year=selectedYear;
            day=selectedDay;

        }
    };
    private void loadExpenses() {
        db_dates = new ArrayList<User>();
        //db_amnt = new ArrayList<String>();

        ExpenseDatabase dbase = new ExpenseDatabase(getApplicationContext());
        dbase.open();
        Cursor db_cur = dbase.fetchAllManualAlerts(ExpenseDatabase.MANUAL_TABLE,
                ExpenseDatabase.EXPENSE_INT);
        if (db_cur != null && db_cur.getCount() > 0) {
            db_cur.moveToFirst();
            do {
                User data=new User(db_cur.getString(1),db_cur.getString(2),db_cur.getString(3),db_cur.getString(4),db_cur.getInt(5),db_cur.getString(6),db_cur.getString(7),db_cur.getString(8));

                //data.setAmount(db_cur.getLong(1)+"");data.setName(db_cur.getString(3));
                //Log.d("date==",db_cur.getString(1)+"--"+db_cur.getString(2)+""+db_cur.getString(3)+"--"+db_cur.getString(4)+"--"+db_cur.getString(5));

                db_dates.add(data);

            } while (db_cur.moveToNext());
        }
        dbase.close();
    }

    public void Create(View view)
    {

        Spinner spinnerFrom = (Spinner) findViewById(R.id.deteditor_from);
        String selectedSpinnerValueFrom = spinnerFrom.getSelectedItem().toString();
        Spinner spinnerTo = (Spinner) findViewById(R.id.deteditor_to);
        String selectedSpinnerValueTo = spinnerTo.getSelectedItem().toString();
        EditText moneyEditText=(EditText)findViewById(R.id.deteditor_money);
        EditText noteEditText=(EditText)findViewById(R.id.deteditor_note);
        EditText dateEditText=(EditText)findViewById(R.id.deteditor_date);
        	Log.d("date",dateEditText.getText().toString()+"");
        if(selectedSpinnerValueFrom.equals("None") &&selectedSpinnerValueTo.equalsIgnoreCase("None"))
        {
            Toast.makeText(getApplicationContext(), "all fields cannot be null", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ExpenseDatabase db = new ExpenseDatabase(getApplicationContext());
            db.open();


            db.createAlertManual(ExpenseDatabase.MANUAL_TABLE,
                    ExpenseDatabase.EXPENSE_INT, new String[] { "" + noteEditText.getText().toString(),
                            "" + dateEditText.getText().toString() ,"" + selectedSpinnerValueFrom.toString(),"" + selectedSpinnerValueTo.toString(),"" + Integer.parseInt(moneyEditText.getText().toString()),""+month,""+year,""+day});


            db.close();

            loadExpenses();
            Toast.makeText(getApplicationContext(), "Data entered in database", Toast.LENGTH_SHORT).show();
            moneyEditText.setText("");
            noteEditText.setText("");
            dateEditText.setText("");

        }
        //  mainListView.setAdapter(new UserAdapterCash(this,db_dates));

    }

    public void Cancel(View view)
    {
        Intent intent=new Intent(this, CashActivity.class);

        intent.putExtra("key",db_dates);
        startActivity(intent);
        finish();
    }

    public void onHomeClick(View v) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }





}

