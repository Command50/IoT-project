package com.example.maksy.testconnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ClassListItems> itemArrayList;
    private MyAppAdapter myAppAdapter;
    private ListView listView;
    private boolean success = false;
    private ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemArrayList = new ArrayList<>();

        listView = findViewById(R.id.listView);
        connectionClass = new ConnectionClass();

        SyncData orderData = new SyncData();
        orderData.execute("");
    }


    private class SyncData extends AsyncTask<String, String, String>
    {
        String msg = getString(R.string.error_connection);
        ProgressDialog progress;

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(MainActivity.this, "Synchronising",
                    getString(R.string.loading_list_sensors), true);
        }
        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            try
            {
                Connection conn = connectionClass.CONN(); //Connection Object
                String query = "select SensorName, Temperature from dbo.Sensor";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if (rs != null)
                {
                    while (rs.next())
                    {
                        try {
                            itemArrayList.add(new ClassListItems(rs.getString("SensorName"),
                                                            rs.getString("Temperature")));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    msg = getString(R.string.data_found);
                    success = true;
                } else {
                    msg = getString(R.string.no_data_found);
                    success = false;
                }

            } catch (Exception e)
            {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg)
        {
            progress.dismiss();
            Toast.makeText(MainActivity.this, msg + "", Toast.LENGTH_LONG).show();
            if(success) {
                try {
                    myAppAdapter = new MyAppAdapter(itemArrayList, MainActivity.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(MainActivity.this, StatisticActivity.class);
                            startActivity(intent);
                        }
                    });
                } catch (Exception ex)
                {

                }
            }
        }
    }
    public class MyAppAdapter extends BaseAdapter
    {
        public class ViewHolder
        {
            TextView textName;
            TextView textTemperature;
        }

        public List<ClassListItems> sensorList;

        public Context context;
        ArrayList<ClassListItems> arraylist;

        private MyAppAdapter(List<ClassListItems> sensorList, Context context)
        {
            this.sensorList = sensorList;
            this.context = context;

            arraylist = new ArrayList<>();
            arraylist.addAll(sensorList);
        }

        @Override
        public int getCount() {
            return sensorList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {

            View rowView = convertView;
            ViewHolder viewHolder;
            if (rowView == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_content, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = rowView.findViewById(R.id.textName);
                viewHolder.textTemperature = rowView.findViewById(R.id.textTemperature);

                rowView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textName.setText(sensorList.get(position).getSensorName() + ":");
            viewHolder.textTemperature.setText(sensorList.get(position).getTemperature());
            return rowView;
        }
    }
}
