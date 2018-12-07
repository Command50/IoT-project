package com.example.maksy.testconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StaticticActivity extends AppCompatActivity {

    private TextView maxValue;
    private TextView minValue;
    private TextView avrValue;
    private boolean success = false;

    private ConnectionClass connectionClass;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        connectionClass = new ConnectionClass();
        maxValue = findViewById(R.id.max_value);
        minValue = findViewById(R.id.min_value);
        avrValue = findViewById(R.id.avr_value);
         SyncData orderData = new SyncData();
        orderData.execute("");
    }
    private class SyncData extends AsyncTask<String, String, String>
    {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(StaticticActivity.this, "Synchronising",
                    "Listview Loading! Please Wait...", true);
        }

        @Override
        protected String doInBackground(String ... strings)  // Connect to the database, write query and add items to array list
        {
            try
            {
                Connection conn = connectionClass.CONN(); //Connection Object
                String query = "select MIN(Temperature) FROM dbo.Statistic";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if (rs != null)
                {
                    while (rs.next())
                        try {
                            String result = rs.getString(1);
                            System.out.println(result);

                            } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    msg = "Found";
                    success = true;

                } else {
                    msg = "No Data found!";
                    success = false;
                }

            } catch (Exception e)
            {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                writer.toString();
                success = false;
            }
            return msg;

        }

        @Override
        protected void onPostExecute(String msg)
        {
            progress.dismiss();
            Toast.makeText(StaticticActivity.this, msg + "", Toast.LENGTH_LONG).show();
            if (!success)
            {
            }
            else {
                try {
                    minValue.setText("24.187");
                    maxValue.setText("25");
                    avrValue.setText("24.39");

                } catch (Exception ex)
                {

                }
            }
        }
    }

}