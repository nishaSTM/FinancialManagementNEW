package myapplication.admin.example.com.financialmanagement;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.artroo.R;

import android.content.DialogInterface.OnClickListener;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class CashListFragment extends Fragment {
    ListView mainListView;
    ArrayList<User> db_dates;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


        db_dates = new ArrayList<User>();
        Bundle b = getArguments();
        if(b!=null) {
            db_dates = (ArrayList<User>) b.getSerializable("key");
         //   Log.d("Hello","Hello");
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.cashlistfragment, container, false);
        mainListView =(ListView)rootView.findViewById(R.id.cashList);
        //  db_dates=getList();
        loadExpenses();

        final UserAdapterCash[] userAdapter = {new UserAdapterCash(getActivity(), db_dates)};
          Log.d("what the hell",db_dates.size()+"hello");
        mainListView.setAdapter(userAdapter[0]);

        AdapterView.OnItemLongClickListener itemClickListener = new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View container,
                                           int position, long id) {

                removeItemFromList(position);

                return false;
            }
            private void removeItemFromList(int position) {
                // TODO Auto-generated method stub
                //	Log.d("Hello",position+"==");
                final int deletePosition = position;

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        getActivity());

                alert.setTitle("Delete");
                alert.setMessage("Do you want delete this item?");
                alert.setPositiveButton("YES", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TOD O Auto-generated method stub

                        // main code on after clicking yes


                       delete(db_dates.get(deletePosition));
                        db_dates.remove(deletePosition);
                        userAdapter[0] =new UserAdapterCash(getActivity(),db_dates);
                       // Log.d("what the hell",db_dates.size()+"hello");
                        mainListView.setAdapter(userAdapter[0]);
                      //  mainListView.setAdapter(userAdapter);
                        userAdapter[0].notifyDataSetChanged();
                        userAdapter[0].notifyDataSetInvalidated();

                    }
                });
                alert.setNegativeButton("CANCEL", new OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });

                alert.show();

            }


        };

        // Setting the item click listener for the listview
        mainListView.setOnItemLongClickListener(itemClickListener);

return rootView;
    }


    private void delete(User user)
    {
        ExpenseDatabase dbase = new ExpenseDatabase(getActivity().getApplicationContext());
        String whereClause="transaction_date" + "= '" + user.transaction_date + "'and money"
                + "= '" + user.money + "'and typeOfItem"
                + "= '" + user.typeOfItem + "'and sourceOfPayment"
                + "= '" + user.sourceOfPayment + "'and month"
                + "= '" + user.month + "'and year"
                + "= '" + user.year + "'and day"
                + "= '" + user.day + "'and note"
                + "= '" + user.note + "'";


        dbase.open();
        dbase.deleteManual(ExpenseDatabase.MANUAL_TABLE,ExpenseDatabase.EXPENSE_INT,whereClause);

    }

    private void loadExpenses() {
        db_dates = new ArrayList<User>();
        //db_amnt = new ArrayList<String>();

        ExpenseDatabase dbase = new ExpenseDatabase(getActivity().getApplicationContext());
        dbase.open();
        Cursor db_cur = dbase.fetchAllManualAlerts(ExpenseDatabase.MANUAL_TABLE,
                ExpenseDatabase.EXPENSE_INT);
        if (db_cur != null && db_cur.getCount() > 0) {
            db_cur.moveToFirst();
            do {
                User data=new User(db_cur.getString(1),db_cur.getString(2),db_cur.getString(3),db_cur.getString(4),db_cur.getInt(5),db_cur.getString(6),db_cur.getString(7),db_cur.getString(8));

                //data.setAmount(db_cur.getLong(1)+"");data.setName(db_cur.getString(3));
                	Log.d("datemonths======",db_cur.getString(6));

                db_dates.add(data);

            } while (db_cur.moveToNext());
        }
        dbase.close();
    }

}