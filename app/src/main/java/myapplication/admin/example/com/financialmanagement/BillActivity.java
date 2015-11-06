package myapplication.admin.example.com.financialmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.artroo.R;

public class BillActivity extends ActionBarActivity {
    private Toolbar toolbar;
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent=getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datalayout2);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.action_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onBackPressed();
                startActivity(new Intent(BillActivity.this, MainActivity.class));
                finish();
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
		((TextView)findViewById(R.id.data_item)).setText(intent.getStringExtra(Constant.DATA_VALUE));
		((TextView)findViewById(R.id.dateValue)).setText(intent.getStringExtra(Constant.DATE_DATA));

	}

	public void onHomeClick(View v) {			
    	startActivity(new Intent(this, MainActivity.class));
    	finish();    
	}



}