package myapplication.admin.example.com.financialmanagement;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.example.artroo.R;


public class IncomeActivity extends ActionBarActivity {
	ListView mainList;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
		Intent intent=getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datalayout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.action_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onBackPressed();
                startActivity(new Intent(IncomeActivity.this, MainActivity.class));
                finish();
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
		HashMap<String,String> questions;
		  questions = (HashMap<String,String>)intent.getSerializableExtra("key");
		//HashMap<long,String> hp=intent.getStringExtra(Constant.DATA_VALUE);
		//((TextView)findViewById(R.id.data_item)).setText();	
		
		mainList =(ListView)findViewById(R.id.mainList2);
		
		//ArrayList<DataForMain> list=getList(); 
		mainList.setAdapter(new HashTableAdapter(this,questions));
	}

	public void onHomeClick(View v) {			
    	startActivity(new Intent(this, MainActivity.class));
    	finish();    
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
       // menu.findItem(R.id.action_delete).setVisible(true);
        //menu.findItem(R.id.action_home).setVisible(true);
        return true;
    }



}
