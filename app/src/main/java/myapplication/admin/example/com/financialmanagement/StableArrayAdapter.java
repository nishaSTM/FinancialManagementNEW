package myapplication.admin.example.com.financialmanagement;

import java.util.ArrayList;


//import java.util.HashMap;
//import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
//import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artroo.R;

public class StableArrayAdapter extends ArrayAdapter<DataForMain> {
    public StableArrayAdapter(Context context, ArrayList<DataForMain> users) {
       super(context, 0, users);
    }

   
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		 String[] srr={"expenditure","bank_balance","income","top_spend_areas","cash","latest_transactions","emi"};
		   int[] arr={R.drawable.expenditure,R.drawable.bank_balance,R.drawable.income,R.drawable.top_spend_areas,R.drawable.cash,R.drawable.latest_transactions,R.drawable.emi};
		 DataForMain user = getItem(position);  
		  if (convertView == null) {
	          convertView = LayoutInflater.from(getContext()).inflate(R.layout.simpleadaptermainactivity, parent, false);
	       }
	       // Lookup view for data population
	       ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
	       TextView nameView = (TextView)convertView.findViewById(R.id.name);
	       TextView amountView = (TextView)convertView.findViewById(R.id.amount);

       // TextView text1 = twoLineListItem.getText1();
       // TextView text2 = twoLineListItem.getText2();
	       for(int i=0;i<srr.length;i++)
			{
				
				if(String.valueOf(srr[i]).equals(user.getImageName()))
				{
					
					//ImageView image=(ImageView)convertView.findViewById(arr[i]);
					
					imageView.setImageResource(arr[i]);
					break;
				}
			}
       // String imageName=user.getImageName();
        //imageView.setImageResource(R.drawable.imageName);
        
        nameView.setText(user.getName());
        amountView.setText(user.getAmount());

        return convertView;
    }
}