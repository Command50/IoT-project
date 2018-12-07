package com.example.maksy.testconnection;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass
{
    @SuppressLint("NewApi")
    public Connection CONN()
    {
        Connection connection = null;
        String connectionString = "";
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
        }
        catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        }
        catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return connection;
    }
}


