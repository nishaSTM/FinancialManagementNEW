package myapplication.admin.example.com.financialmanagement;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.artroo.R;

import android.content.DialogInterface.OnClickListener;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ExpenditureFragment extends Fragment {
    ListView mainListView;
    ArrayList<Operation> db_dates;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


        db_dates = new ArrayList<Operation>();
        Bundle b = getArguments();
        if(b!=null) {
            db_dates = (ArrayList<Operation>)b.getSerializable("key");
            Log.d("Hello","Hello"+db_dates.size());
        }

        Log.d("Hello","Hello"+db_dates.size());
       /* CityListViewAdapter adapter = new CityListViewAdapter(getActivity(), R.layout.row_items, pois);
        /**Collections.sort(pois, new Comparator<RowItem>() {
         public int compare(RowItem s1, RowItem s2) {
         return s1.getID().compareTo(s2.getID());
         }
         });*/
        //   listView.setAdapter(adapter);
        // listView.setOnItemClickListener(this);*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.cashfragmenttab, container, false);
        mainListView =(ListView)rootView.findViewById(R.id.cashList);
        //  db_dates=getList();
//        loadExpenses();
        //if(db_dates.size()==0)
        //{
        //mainListView.setAdapter(a);

        //}
        db_dates = new ArrayList<Operation>();
        Bundle b = getArguments();
        if(b!=null) {
            db_dates = (ArrayList<Operation>)b.getSerializable("key");
           // Log.d("Hello","Hello"+db_dates.size());
        }

        final UsersAdapter userAdapter = new UsersAdapter(getActivity(),db_dates);
        Log.d("what the hell",db_dates.size()+"hello");
        mainListView.setAdapter(userAdapter);
        //mainListView.setEmptyView(findViewById(android.R.id.empty));
        // Item Click Listener for the listview


        return rootView;
    }





}