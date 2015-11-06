    package myapplication.admin.example.com.financialmanagement;
    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.v4.app.Fragment;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ListView;

    import com.example.artroo.R;
    import com.github.mikephil.charting.charts.BarChart;
    import com.github.mikephil.charting.data.BarData;
    import com.github.mikephil.charting.data.BarDataSet;
    import com.github.mikephil.charting.data.BarEntry;
    import com.github.mikephil.charting.utils.ColorTemplate;

    import java.util.ArrayList;

    /**
     * Created by hp1 on 21-01-2015.
     */
    public class ExpenditureReportFragment extends Fragment {

        ListView mainListView;
        ArrayList<Operation> db_dates;
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);


            db_dates = new ArrayList<Operation>();
            Bundle b = getArguments();
            if(b!=null) {
                db_dates = (ArrayList<Operation>) b.getSerializable("key");

            }
//           Log.d("date",db_dates.get(0).getDate()+"");


       }


        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.cashreportfragment,container,false);


    //        mainListView =(ListView)v.findViewById(R.id.cashList);
            //  db_dates=getList();
    //        loadExpenses();
            //if(db_dates.size()==0)
            //{
            //mainListView.setAdapter(a);

            //}
            db_dates = new ArrayList<Operation>();
            Bundle b = getArguments();
            if(b!=null) {
                db_dates = (ArrayList<Operation>)b.getSerializable("key");

            }
           //Log.d("deteeditor===",db_dates.get(0).getDate());



            BarChart chart = (BarChart)v.findViewById(R.id.chart);

            BarData data = new BarData(getXAxisValues(), getDataSet());
            chart.setData(data);
            chart.setDescription("My Chart");
            chart.animateXY(10, 10);
            chart.invalidate();
            return v;
        }

        private ArrayList<BarDataSet> getDataSet() {
            ArrayList<BarDataSet> dataSets = null;

            ArrayList<BarEntry> valueSet1 = new ArrayList<>();
            BarEntry v1e1 = new BarEntry(50.000f, 0); // Jan
            valueSet1.add(v1e1);
            BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
            valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
            valueSet1.add(v1e3);
            BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
            valueSet1.add(v1e4);
            BarEntry v1e5 = new BarEntry(30.000f, 4); // May
            valueSet1.add(v1e5);
            BarEntry v1e6 = new BarEntry(30.000f, 5); // Jun
            valueSet1.add(v1e6);


            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
            barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

            dataSets = new ArrayList<>();
            dataSets.add(barDataSet1);
            //     dataSets.add(barDataSet2);
            return dataSets;
        }

        private ArrayList<String> getXAxisValues() {
            ArrayList<String> xAxis = new ArrayList<>();
            xAxis.add("JAN");
            xAxis.add("FEB");
            xAxis.add("MAR");
            xAxis.add("APR");
            xAxis.add("MAY");
            xAxis.add("JUN");
            xAxis.add("JUL");

            return xAxis;
        }
    }