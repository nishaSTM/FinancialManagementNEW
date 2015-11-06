package myapplication.admin.example.com.financialmanagement;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.artroo.R;

public class UsersAdapter extends ArrayAdapter<Operation> {
    public UsersAdapter(Context context, ArrayList<Operation> users) {
       super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       Operation user = getItem(position);    
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
       }
       // Lookup view for data population
       TextView bankName = (TextView) convertView.findViewById(R.id.bankName);
       TextView date = (TextView) convertView.findViewById(R.id.date);
       TextView debited = (TextView) convertView.findViewById(R.id.debited);
       TextView through = (TextView) convertView.findViewById(R.id.through);
       // Populate the data into the template view using the data object
       bankName.setText(user.getBankName());
       date.setText(user.getDate());
       debited.setText(user.getDebited());
       through.setText(user.getThrough());
       // Return the completed view to render on screen
       return convertView;
   }
}