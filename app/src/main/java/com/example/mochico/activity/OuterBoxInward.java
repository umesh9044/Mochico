package com.example.mochico.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mochico.R;
import com.example.mochico.database.SQLDB;
import com.example.mochico.utils.AppSharedPreference;
import com.example.mochico.utils.CommonMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OuterBoxInward extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout relativeLayout;
    boolean doubleBackToExitPressedOnce = false;
    TextView lblOperator,lblTypeOfWork,lblOuterScanned;
    EditText txtBarcodescan;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outerbox_inward);

        relativeLayout = findViewById(R.id.outerboxinward_Layout);
        lblOperator = findViewById(R.id.lblOperator);
        lblTypeOfWork = findViewById(R.id.lblTypeOfWork);
        lblOuterScanned = findViewById(R.id.lblOuterScanned);
        txtBarcodescan = findViewById(R.id.txtBarcodescan);


        txtBarcodescan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                OuterBoxScanning(txtBarcodescan.getText().toString());
                return false;
            }
        });

        String Operator = AppSharedPreference.getUserName(OuterBoxInward.this, AppSharedPreference.PREF_KEY.UserName);
        String TypeOfWork = AppSharedPreference.getTypeOfWork(OuterBoxInward.this, AppSharedPreference.PREF_KEY.TypeOfWork);
        if(Operator==null)
        {
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            OuterBoxInward.this.finish();
        }
        else
        {
            lblOperator.setText(Operator);
            lblTypeOfWork.setText(TypeOfWork);
            txtBarcodescan.requestFocus();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting:
                startActivity(new Intent(OuterBoxInward.this, Setting.class));
                break;
        }
    }
    private void  OuterBoxScanning(String BarCode)
    {
        SQLDB c= new SQLDB();
        Connection con=c.GetCon();
        if(con!=null)
        {
            try {

                Statement smt = con.createStatement();
                ResultSet res = smt.executeQuery("Select CartonBoxQty,ScannedQty from tblInputData2 Where TYPE='Inner Box In Outer Box' And CartonBoxBarcode='"+BarCode+"';");
                if(res.next())
                {
                    String BoxQty = res.getString(1);
                    String ScannedQty = res.getString(2);
                    lblOuterScanned.setText("");
                    if(BoxQty.equals(String.valueOf(Integer.parseInt(ScannedQty))))
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        AlertDialog alert = builder.create();
                        alert.setTitle("Allready Scanned");
                        alert.setMessage(BarCode);
                        alert.show();
                    }
                    else {
                        PreparedStatement ps = con.prepareStatement("INSERT INTO tblScanLogInOut (Type, CartonBoxBarcode, CartonBoxQty, Operator, deviceSerial) VALUES (?, ?,?,?,?)");
                        ps.setString(1, lblTypeOfWork.getText().toString());
                        ps.setString(2, BarCode);
                        ps.setString(3, BoxQty);
                        ps.setString(4, lblOperator.getText().toString());
                        ps.setString(5, Build.MODEL);
                        ps.addBatch();
                        int[] results = ps.executeBatch();
                        int ScnQty = Integer.parseInt(ScannedQty) + 1;
                        lblOuterScanned.setText(String.valueOf(ScnQty) + "/" + BoxQty);

                        PreparedStatement ps2 = con.prepareStatement("Update tblInputData2 Set ScannedQty=? Where CartonBoxBarcode=?");
                        ps2.setInt(1, ScnQty);
                        ps2.setString(2, BarCode);
                        ps2.addBatch();
                        int[] results2 = ps2.executeBatch();
                        txtBarcodescan.setText("");
                        txtBarcodescan.requestFocus();
                        Toast.makeText(getApplicationContext(), "Record Added Successfully!!", Toast.LENGTH_SHORT).show();
                        con.close();

                        if (BoxQty.equals(String.valueOf(ScnQty)))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            AlertDialog alert = builder.create();
                            alert.setTitle("Scanning Completed");
                            alert.setMessage(BarCode);
                            alert.show();
                        }
                    }
                }
                else
                {
                    CommonMethod.displaySnackbarerror(relativeLayout, OuterBoxInward.this, "Barcode Not Exists !!");
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
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Setting.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        OuterBoxInward.this.finish();
    }
}
