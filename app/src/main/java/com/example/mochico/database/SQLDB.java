package com.example.mochico.database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLDB {
    Connection con;

    @SuppressLint("DBApi")
    public Connection GetCon()
    {
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //con= DriverManager.getConnection("jdbc:jtds:sqlserver://182.74.67.181:49442;databasename=dbmochico;user=sa;password=star@123;");
            con= DriverManager.getConnection("jdbc:jtds:sqlserver://arise.Mochiko-group.in:1433;databasename=dbmochico;user=sa;password=star@123;");
        }
        catch (Exception ex)
        {
            Log.e("Error:",ex.getMessage());
        }
        return  con;
    }
}
