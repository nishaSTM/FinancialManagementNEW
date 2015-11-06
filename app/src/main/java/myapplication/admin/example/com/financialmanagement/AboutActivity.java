package myapplication.admin.example.com.financialmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.artroo.R;

public class AboutActivity extends ActionBarActivity {
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
                startActivity(new Intent(AboutActivity.this, MainActivity.class));
                finish();
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
		((TextView)findViewById(R.id.data_item)).setText(intent.getStringExtra(Constant.DATA_VALUE));	
		((TextView)findViewById(R.id.dateValue)).setText(intent.getStringExtra(Constant.DATE_DATA));
	}

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
       // menu.findItem(R.id.action_delete).setVisible(true);
        //menu.findItem(R.id.action_home).setVisible(true);
        return true;
    }

}