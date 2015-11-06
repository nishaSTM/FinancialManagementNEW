package myapplication.admin.example.com.financialmanagement;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
//import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.example.readsmsfromphone.Operation;
//import com.example.readsmsfromphone.Sms;

//import com.example.readsmsfromphone.Operation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.artroo.R;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	// Within which the entire activity is enclosed
	DrawerLayout mDrawerLayout;
	String curDate;

	ListView mDrawerList;
	ListView mainList;
    int expenditureSum=0;

	String mTitle="";


    String TITLES[] = {"Account","Bill","Cash","Spend Summary","Share","LikeUsonFb","follow on twitter","About",};
    int ICONS[] = {R.drawable.facebook,R.drawable.facebook,R.drawable.facebook,R.drawable.facebook,R.drawable.facebook,R.drawable.facebook,R.drawable.facebook,R.drawable.facebook};
    HashSet<String> bankNameset=new HashSet<String>(Arrays.asList("HDFCBK", "ICICIB","CANBNK","AxisBk" ,"FROMSC", "OBCBNK", "BOBTXN", "CENTBK", "CBSSBI"));
    //And we also create a int resource for profile picture in the header view

    //And we also create a int resource for profile picture in the header view

    String NAME = "Nisha Aggarwal";
    String EMAIL = "nishaagg1994@gmail.com";
    int PROFILE = R.drawable.app_icon;

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    //DrawerLayout Drawer;                                  // Declaring DrawerLayout

    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle



    private ArrayList<DataForMain> db_dates;
	Operation operation;
	HashMap<String,String> set;
	ArrayList<Operation> operationList;
    ArrayList<Operation> incomeList;
	private ArrayList<User> manualList;
	private int max;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

    /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     */
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView


		mainList =(ListView)findViewById(R.id.mainList);

		//getting current date
		Date dt = new Date();
		int date = dt.getDate();
		int month = dt.getMonth()+1;
		int year = dt.getYear();
		year += 1900;
		int day = dt.getDay();
		String[] days = { "Sunday", "Monday", "Tuesday","WednesDay", "Thursday", "Friday", "Saterday" };
		curDate = date + "/" + month + "/" + year + " " + days[day];
		// Getting reference to the ActionBarDrawerToggle



		//get the operation object which contains the latest account details

		List<Sms> smsList = readAllSms();
		operationList = new ArrayList<Operation>();
        incomeList=new ArrayList<Operation>();
		//ArrayList<Operation> operations = new ArrayList<Operation>();
		set=new HashMap<String,String>();

		for (Sms sms : smsList) {
            String bankName=(sms.from).substring(3,sms.from.length());
            String smsBody=sms.body;
            if(bankNameset.contains(bankName)||(((smsBody.contains("a/c")||smsBody.contains("A/c") ||smsBody.contains("Account")||smsBody.contains("account")) &&((sms.from).endsWith("BK")||(sms.from).endsWith("BNK")||(sms.from).endsWith("Bnk")||(sms.from).endsWith("bnk")||(sms.from).endsWith("bk")||(sms.from).endsWith("Bk")))))
            {
				operation= parseOperation(sms.body, sms.date, sms.from);
				if(operation.getDebited()!=null)
				{
					operationList.add(operation);
				}

			}
		}



        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    mDrawerLayout.closeDrawers();
//                    Toast.makeText(MainActivity.this,"The Item Clicked is: "+recyclerView.getChildPosition(child),Toast.LENGTH_SHORT).show();


                    String[] details = getResources().getStringArray(R.array.details);

                    Intent intent = null;
                    int position = recyclerView.getChildPosition(child);
                    Log.d("positio####",position+"@@@");
                    //starting another activity corrsponding to each list item in drawerItem
                    switch (position) {

                        case 1:
                            intent = new Intent(MainActivity.this, BankBalanceActivity.class);

                            intent.putExtra("key", operationList);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(MainActivity.this, IncomeActivity.class);
                            //loadIncome();
                            intent.putExtra("key", set);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(MainActivity.this, CashMainActivity.class);
                            intent.putExtra("key", details[position-1]);
                            intent.putExtra("dateValue", curDate);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(MainActivity.this, SpendSumarryActivity.class);
                            intent.putExtra("key", details[position-1]);
                            intent.putExtra("dateValue", curDate);
                            startActivity(intent);
                            break;
                        case 5:

                            PackageManager pm = getPackageManager();
                            try {

                                intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                String text = "link for application";

                                PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);

                                intent.setPackage("com.whatsapp");

                                intent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(intent, "Share with"));

                            } catch (NameNotFoundException e) {
                                Toast.makeText(MainActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                            }

                            break;
                        case 6:

                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(Constant.fbURL));
                            //startActivity(browserIntent);
                            startActivity(intent);
                            break;
                        case 7:
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(Constant.twitterURL));
                            //startActivity(browserIntent1);
                            startActivity(intent);
                            break;
                        case 8:
                            intent = new Intent(MainActivity.this, AboutActivity.class);
                            intent.putExtra("key", details[position-1]);
                            intent.putExtra("dateValue", curDate);
                            startActivity(intent);
                            break;

                        default:
                            break;
                    }




                   return true;
                }


                           return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });



        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        // Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);


        mTitle = (String) getTitle();
        // Getting reference to the DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        //mDrawerList = (ListView) findViewById(R.id.drawer_list);

        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

        };
        // Setting DrawerToggle on DrawerLayout
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
		ArrayList<DataForMain> list=getList(); 
		mainList.setAdapter(new StableArrayAdapter(this,list));
		mainList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,
					View view,
					int position,
					long id) {


				String[] details = getResources().getStringArray(R.array.details);

				Intent intent = null;
				//starting another activity corrsponding to each list item in drawerItem
				switch (position) {
				case 0:
					intent=new Intent(MainActivity.this,BankBalanceActivity.class);
					//loadExpenses();
					intent.putExtra("key",operationList);
					startActivity(intent);
					break;
				case 1:
					intent=new Intent(MainActivity.this,IncomeActivity.class);
					//loadIncome();
					intent.putExtra("key",set);
					startActivity(intent);
					break;
				case 2:
					intent=new Intent(MainActivity.this,SpendActivity.class);
					intent.putExtra("key",incomeList);
					startActivity(intent);
					break;
				case 3:
					intent=new Intent(MainActivity.this, TopSpendAreas.class);
					intent.putExtra("key","top Spend Areas");
					startActivity(intent);
					break;
				case 4:
					intent=new Intent(MainActivity.this,CashMainActivity.class);
					intent.putExtra("key","Cash");
					startActivity(intent);
					break;
				case 5:
					intent=new Intent(MainActivity.this,LatestTransactionActivity.class);

					intent.putExtra("key",operationList.get(0).getDebited());
					startActivity(intent);
					break;
				case 6:
					intent=new Intent(MainActivity.this,RelaxBillActivity.class);
					intent.putExtra("key","Relax Bill");
					startActivity(intent);
					break;


				default:
					break;
				}
			}
		});

	}

	private ArrayList<DataForMain> getList() {
		// TODO Auto-generated method stub
		ArrayList<DataForMain> list=new ArrayList<DataForMain>();
		DataForMain main=new DataForMain();
		main.setName("Expenditure");
		main.setImageName("expenditure");

				main.setAmount("Rs"+expenditureSum);

		list.add(main);

		main=new DataForMain();
		main.setName("Bank Balance");
		main.setImageName("bank_balance");
		if(set.size()!=0)
		{
			main.setAmount(set.get(set.keySet().toArray()[0]));
		}
		else
		{
			main.setAmount("No Income details");
		}
		list.add(main);

		main=new DataForMain();
		main.setName("Income/Deposits");
		main.setImageName("income");
		if(incomeList.size()!=0)
		{main.setAmount(incomeList.get(0).getDebited()+"");}
		else
		{
			main.setAmount("No Income details");
		}
		list.add(main);

		main=new DataForMain();
		//max amount spend
		main.setName("Top Spend Areas");
		main.setImageName("top_spend_areas");
		max=-9999;
		//int latestTransaction;
		for(int i=0;i<operationList.size();i++)
		{


			String str=operationList.get(i).getDebited();
			str=str.replace("INR ","");
			str=str.replaceAll("Rs","");

			str=str.replaceAll(",","");
            str=str.replace(".","");
			str=str.replaceAll(" ","");
			if( Integer.parseInt(str)>max)
			{
				max= Integer.parseInt(str);
			}
		}
		//String str=operationList.get(i).getDebited();

		if(operationList.size()!=0)
		{
			main.setAmount("Rs"+ max);
		}
		else
		{
			main.setAmount("No details");
		}


		list.add(main);

		main=new DataForMain();
		main.setName("Cash");
		main.setImageName("cash");
		String cashMoney=loadManualExpense();
		if(cashMoney==null)
		{
			main.setAmount("No cash activity yet");
		}
		else
		{
			main.setAmount(cashMoney);	
		}
		list.add(main);

		main=new DataForMain();
		main.setName("Latest Transactions");
		main.setImageName("latest_transactions");
		//	Pattern r = Pattern.compile("//d*");
		//
		//Log.d("HELLO",);
		//Matcher m = r.matcher(operationList.get(0).getDebited());
		if(operationList.size()!=0)
		{
			main.setAmount(operationList.get(0).getDebited());
		}
		else
		{
			main.setAmount("No transaction");
		}
		list.add(main);

		main=new DataForMain();
		main.setName("Relax,No Bill Due");
		main.setImageName("emi");
		main.setAmount(100+"");
		list.add(main);

		return list;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	/** Handling the touch event of app icon */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	private List<Sms> readAllSms() {
		List<Sms> list = new ArrayList<Sms>();

		Uri request = Uri.parse("content://sms/inbox");

		Cursor cur = getContentResolver().query(request,
				new String[]{"address", "body", "date"}, null, null, null);

		if (cur == null) {
			return list;
		}

		while (cur.moveToNext()) {
			final Date date = new Date(Long.parseLong(cur.getString(2)));
			final String from = cur.getString(0);
			final String body = cur.getString(1);
			//Log.d("====",date+"===");

			list.add(new Sms(date, from, body));
		}

		cur.close();

		return list;
	}



	@SuppressWarnings("deprecation")
	private Operation parseOperation(String text,Date date,String bankName) {
		bankName=bankName.substring(3,bankName.length());
		Operation op=new Operation();
		String pattern = "(?:INR|Rs)\\.?[\\s]*[\\d]*\\,?[\\d]*\\,?[\\d]*";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(text);
		//Log.d("Hello22222==",text);
        String datePattern = "[\\D]*[\\s]*[\\D]*[\\s]*[\\d]*";
        Pattern r1 = Pattern.compile(datePattern);
        Matcher m1 = r1.matcher(date+"");
        String dateVar="";
        if(m1.find())
        {
            dateVar=m1.group();
            op.setDate(m1.group()+"");
        }
		if(text.contains("debit")|| text.contains("withdraw") || text.contains(" Dr ")|| text.contains(" dr "))
		{

			if(m.find())
			{
                String str=m.group(0);
				op.setDebited(m.group(0));
                //str=str.replace("INR ","");
                //str=str.replaceAll("Rs","");

                str=str.replaceAll(",","");
                str=str.replace(".","");
                str=str.replaceAll(" ","");

                Pattern intsOnly = Pattern.compile("[\\d]+");
                Matcher makeMatch = intsOnly.matcher(str+"");
                makeMatch.find();
               // Log.d("inside debit",bankName);
                String inputInt = makeMatch.group();
                Log.d("inside nisha",inputInt);
                System.out.println(inputInt);
                //if(m2.find()) {
                  expenditureSum+=  Integer.parseInt(inputInt);
                //}

				if(m.find())
				{
					if(!set.containsKey(bankName))
						//	Log.d("inside debit",bankName);
						set.put(bankName,m.group());
				}
			}
            if(text.contains("Info"))
            {
                String pattern2="Info[\\s]*[:|.]*[\\s]*.+";
                r = Pattern.compile(pattern2);
                m = r.matcher(text);
                if(m.find())
                {
                    op.setThrough(m.group(0));

                    Iterator it = set.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        Log.d(pair.getKey() + " = ",  (String) pair.getValue());
                        // avoids a ConcurrentModificationException
                    }
                    //Log.d("Hello",m.group());
                }


            }
            else if(text.contains("toward")) {

                String pattern2 = "(toward|TOWARD)[s]?[\\s]*[:|.]*[\\s]*.+";
                r = Pattern.compile(pattern2);
                m = r.matcher(text);
                if (m.find()) {
                    op.setThrough(m.group(0));

                    Iterator it = set.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        Log.d(pair.getKey() + " = ", (String) pair.getValue());
                        // avoids a ConcurrentModificationException
                    }
                    //Log.d("Hello",m.group());
                }
            }
            else
            {
                op.setThrough("Details Not Available");
            }
		}
		else
		{
			if(m.find())
			{
                String money=m.group();
                if(text.contains("salary")||text.contains("SALARY")||text.contains("Salary")||text.contains("Sal"))
                {
                 Operation income=new Operation();
                    income.setBankName(bankName);
                    income.setDate(dateVar);
                    income.setDebited(money);
                    income.setThrough("Salary");
                    incomeList.add(income);
                }
				//Log.d("Hello",text);
				if(!set.containsKey(bankName))
                    if(!m.find()) {
                        set.put(bankName, money);
                    }
                else
                    {
                        set.put(bankName,m.group());
                    }
			}
		}
		op.setBankName(bankName);

		return op;

	}



	//databse storage for all the expenses made in one year/one month/one day
	private void createRecord(Date date, BigDecimal amount, String type,String bankName) {
		//Log.d("date",date+"");
		ExpenseDatabase db = new ExpenseDatabase(getApplicationContext());
		db.open();
		if(type.equals("debited"))
		{
			db.createAlert(ExpenseDatabase.EXPENSE_TABLE,
					ExpenseDatabase.EXPENSE_INT, new String[] { "" + amount,
					"" + date,"" + bankName });
		}
		else
		{

			db.createAlertIncome(ExpenseDatabase.INCOME_TABLE,
					ExpenseDatabase.EXPENSE_INT, new String[] { "" + amount,
					"" + date ,"" + bankName });
		}
		db.close();
	}




	private String loadManualExpense() {
		String money = null;
		ExpenseDatabase dbase = new ExpenseDatabase(getApplicationContext());
		dbase.open();
		Cursor db_cur = dbase.fetchAllManualAlerts(ExpenseDatabase.MANUAL_TABLE,
				ExpenseDatabase.EXPENSE_INT);
		if (db_cur != null && db_cur.getCount() > 0) {
			db_cur.moveToFirst();
			money=	db_cur.getString(5);
		}
		dbase.close();
		return money;
	}
}
