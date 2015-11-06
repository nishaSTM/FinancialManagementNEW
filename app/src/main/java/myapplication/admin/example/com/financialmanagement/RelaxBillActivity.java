package myapplication.admin.example.com.financialmanagement;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.artroo.R;

import java.util.ArrayList;
import java.util.List;

public class RelaxBillActivity extends ActionBarActivity {

	public void onCreate(Bundle savedInstanceState) {
		Intent intent=getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billemisfragment_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.action_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onBackPressed();
                startActivity(new Intent(RelaxBillActivity.this, MainActivity.class));
                finish();
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();
		

		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Bills");
		

		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("EMIs");
		spec2.setContent(R.id.tab2);

		

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.getTabWidget().setBackgroundColor(getResources().getColor(R.color.green));
		}
}