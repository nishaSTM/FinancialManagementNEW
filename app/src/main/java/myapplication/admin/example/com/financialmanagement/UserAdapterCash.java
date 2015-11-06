package myapplication.admin.example.com.financialmanagement;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.artroo.R;

public class UserAdapterCash extends ArrayAdapter<User> {
	public UserAdapterCash(Context context, ArrayList<User> users) {
	       super(context, 0, users);
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	       // Get the data item for this position
	       User user = getItem(position);    
	       // Check if an existing view is being reused, otherwise inflate the view
	       if (convertView == null) {
	          convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usercash, parent, false);
	       }
	       // Lookup view for data population
	       TextView note = (TextView) convertView.findViewById(R.id.note);
	       TextView transaction_date = (TextView) convertView.findViewById(R.id.transaction_date);
	       TextView typeOfItem = (TextView) convertView.findViewById(R.id.typeOfItem);
	       TextView sourceOfPayment = (TextView) convertView.findViewById(R.id.sourceOfPayment);
	       TextView money = (TextView) convertView.findViewById(R.id.money);
	       Log.d("money",money.getText().toString()+"==");
	       // Populate the data into the template view using the data object
	       note.setText(user.note);
	       transaction_date.setText(user.transaction_date);
	       typeOfItem.setText(user.typeOfItem);
	       sourceOfPayment.setText(user.sourceOfPayment);
	       money.setText("Rs"+user.money+"");
	       // Return the completed view to render on screen
	       return convertView;
	   }
	}