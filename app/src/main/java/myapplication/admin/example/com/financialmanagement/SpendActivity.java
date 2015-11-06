package myapplication.admin.example.com.financialmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.artroo.R;

import java.util.ArrayList;

public class SpendActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ArrayList<Operation> db_dates;
    ListView mainListView;

    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.datalayout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.action_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onBackPressed();
                startActivity(new Intent(SpendActivity.this, MainActivity.class));
                finish();
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mainListView =(ListView)findViewById(R.id.mainList2);
        db_dates = new ArrayList<Operation>();
        db_dates = (ArrayList<Operation>) getIntent().getSerializableExtra("key");
        //  db_dates=getList();
//        loadExpenses();
        //if(db_dates.size()==0)
        //{
        //mainListView.setAdapter(a);

        //}
/*        db_dates = new ArrayList<Operation>();
        Bundle b = getArguments();
        if(b!=null) {
            db_dates = (ArrayList<Operation>)b.getSerializable("key");
            // Log.d("Hello","Hello"+db_dates.size());
        }
*/
        final UsersAdapter userAdapter = new UsersAdapter(SpendActivity.this,db_dates);
        Log.d("what the hell", db_dates.size() + "hello");
        mainListView.setAdapter(userAdapter);
	}

	public void onHomeClick(View v) {			
    	startActivity(new Intent(this, MainActivity.class));
    	finish();    
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //menu.findItem(R.id.action_delete).setVisible(true);
        //menu.findItem(R.id.action_home).setVisible(true);
        return true;
    }

}
