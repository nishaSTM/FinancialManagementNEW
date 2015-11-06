package myapplication.admin.example.com.financialmanagement;

import java.util.HashMap;
import java.util.Hashtable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

public class HashTableAdapter extends BaseAdapter {

	private HashMap<String, String> mData = new HashMap<String, String>();
    private String[] mKeys;
	private Context context;
    public HashTableAdapter(Context context,HashMap<String, String> data){
        mData  = data;
        mKeys = mData.keySet().toArray(new String[data.size()]);
        this.context=context;
    }

    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		 return mData.get(mKeys[position]);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		 return arg0;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		  String key = mKeys[pos];
	        String Value = getItem(pos).toString();
	        
	        TwoLineListItem twoLineListItem;
	    	if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            twoLineListItem = (TwoLineListItem) inflater.inflate(
	                    android.R.layout.simple_list_item_2, null);
	        } else {
	            twoLineListItem = (TwoLineListItem) convertView;
	        }

	        TextView text1 = twoLineListItem.getText1();
	        TextView text2 = twoLineListItem.getText2();

	        text1.setText(key);
	        text2.setText("" +Value);

	        return twoLineListItem;
		
	}

	
}
