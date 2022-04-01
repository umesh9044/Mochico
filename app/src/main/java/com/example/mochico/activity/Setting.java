package com.example.mochico.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mochico.R;
import com.example.mochico.database.SQLDB;
import com.example.mochico.utils.AppSharedPreference;
import com.example.mochico.utils.CommonMethod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Setting extends AppCompatActivity implements View.OnClickListener{
    boolean doubleBackToExitPressedOnce = false;
    RelativeLayout relativeLayout;
    private EditText txtUserBarCode;
    private Button btnSubmit;
    private TextView title,lblUserName,lblTypeOfWork;
    String Id="",UserName="",BarCode="",TypeOfWork="",DescOfWork="";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        title = findViewById(R.id.title);
        txtUserBarCode = findViewById(R.id.txtUserBarCode);
        lblUserName = findViewById(R.id.lblUserName);
        lblTypeOfWork = findViewById(R.id.lblTypeOfWork);
        btnSubmit = findViewById(R.id.btn_submit);
        relativeLayout = findViewById(R.id.inout_Layout);

        title.setText("Settings");
        btnSubmit.setOnClickListener(this);
        String Name = AppSharedPreference.getUserName(Setting.this, AppSharedPreference.PREF_KEY.UserName);
        String Type = AppSharedPreference.getTypeOfWork(Setting.this, AppSharedPreference.PREF_KEY.TypeOfWork);
        if(Name!=null)
        {
            lblUserName.setText(Name);
            lblTypeOfWork.setText(Type);
        }
        txtUserBarCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(!GetData(txtUserBarCode.getText().toString()))
                {
                    Toast.makeText(Setting.this, "No User Found!!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        txtUserBarCode.requestFocus();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit)
        {
            if(UserName.length()>0 && TypeOfWork.length()>0)
            {
                AppSharedPreference.putId(Setting.this, AppSharedPreference.PREF_KEY.Id, Id);
                AppSharedPreference.putUserName(Setting.this, AppSharedPreference.PREF_KEY.UserName, UserName);
                AppSharedPreference.putBarCode(Setting.this, AppSharedPreference.PREF_KEY.BarCode, BarCode);
                AppSharedPreference.putTypeOfWork(Setting.this, AppSharedPreference.PREF_KEY.TypeOfWork, TypeOfWork);
                AppSharedPreference.putDescOfWork(Setting.this, AppSharedPreference.PREF_KEY.DescOfWork, DescOfWork);

                if(TypeOfWork.equals("Inner Box In Outer Box"))
                {
                    Intent intent = new Intent(getApplicationContext(), InnerBoxInOuterBox.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Setting.this.finish();
                }
                else if(TypeOfWork.equals("Outer Box Inward"))
                {
                    Intent intent = new Intent(getApplicationContext(), OuterBoxInward.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Setting.this.finish();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Enter User Barcode First!!",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean  GetData(String Barcode)
    {
        SQLDB c= new SQLDB();
        Connection con=c.GetCon();
        if(con!=null)
        {
            try {
                Statement smt = con.createStatement();
                ResultSet res = smt.executeQuery("Select * from UserMaster Where BarCode='"+Barcode+"';");
                if(res.next())
                {
                    Id = res.getString(1);
                    UserName = res.getString(2);
                    BarCode = res.getString(3);
                    TypeOfWork = res.getString(4);
                    DescOfWork = res.getString(5);

                    lblUserName.setText(UserName);
                    lblTypeOfWork.setText(TypeOfWork);

                    //Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                    con.close();
                    return  true;
                }
                else
                {
                    return  false;
                }
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(),"Error:"+ex.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("Error:",ex.getMessage());
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No Connection!!",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce)
        {
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        //Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show();
        CommonMethod.displaySnackbarLogin(relativeLayout, Setting.this, "Please click BACK again to exit.");
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}