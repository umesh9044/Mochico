package com.example.mochico.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mochico.R;
import com.example.mochico.database.SQLDB;
import com.example.mochico.utils.AppSharedPreference;
import com.example.mochico.utils.CommonMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InnerBoxInOuterBox extends AppCompatActivity implements View.OnClickListener{
    boolean doubleBackToExitPressedOnce = false;
     RelativeLayout relativeLayout;
     TextView lblOperator,lblTypeOfWork,lblArticle,lblInnerScanned;
     EditText txtOuterBarcodescan,txtscanningqty,txtInnerBarcodescan;
     Button btn_Ok, btn_Exit, btn_Clear;
     LinearLayout layoutOuterBox,layoutInnerBox,layoutBtn;
     ImageView settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.innerbox_in_outerbox);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        mTitle.setText("Scan Barcode");

        relativeLayout = findViewById(R.id.inout_Layout);
        layoutOuterBox = findViewById(R.id.layoutOuterBox);
        layoutInnerBox = findViewById(R.id.layoutInnerBox);
        layoutBtn = findViewById(R.id.Btnlayout);

        lblOperator = findViewById(R.id.lblOperator);
        lblTypeOfWork = findViewById(R.id.lblTypeOfWork);
        txtOuterBarcodescan = findViewById(R.id.txtOuterBarcodescan);
        lblArticle = findViewById(R.id.lblArticle);
        txtscanningqty=findViewById(R.id.txtscanningqty);

        lblInnerScanned=findViewById(R.id.lblInnerScanned);
        txtInnerBarcodescan=findViewById(R.id.txtInnerBarcodescan);

        settingBtn=findViewById(R.id.setting);

        btn_Ok = findViewById(R.id.btn_ok);
        btn_Exit = findViewById(R.id.btn_exit);
        btn_Clear = findViewById(R.id.btn_cleardata);
        settingBtn.setVisibility(View.VISIBLE);

        btn_Clear.setOnClickListener(this);
        btn_Exit.setOnClickListener(this);
        btn_Ok.setOnClickListener(this);
        settingBtn.setOnClickListener(this);



        txtOuterBarcodescan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                OuterScan(txtOuterBarcodescan.getText().toString());
                return false;
            }
        });
        txtInnerBarcodescan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                InnerScanning();
                return false;
            }
        });

        String Operator = AppSharedPreference.getUserName(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.UserName);
        String TypeOfWork = AppSharedPreference.getTypeOfWork(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.TypeOfWork);
        if(Operator==null)
        {
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            InnerBoxInOuterBox.this.finish();
        }
        else
        {
            lblOperator.setText(Operator);
            lblTypeOfWork.setText(TypeOfWork);
            String Article = AppSharedPreference.getUserName(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.Article);
            String OuterBarCode = AppSharedPreference.getUserName(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.OuterBarCode);
            String ScannedQty = AppSharedPreference.getUserName(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.ScannedQty);
            String OuterQty = AppSharedPreference.getTypeOfWork(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.OuterQty);
            if (OuterBarCode == null || OuterBarCode == "")
            {
                StartOuterScanning();
            } else {
                lblArticle.setText(Article);
                txtOuterBarcodescan.setText(OuterBarCode);
                txtscanningqty.setText(OuterQty);
                StartInnerScanning();
                lblInnerScanned.setText(ScannedQty + "/" + OuterQty);
            }
        }
    }
    @Override
        public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                    if(TextUtils.isEmpty(txtOuterBarcodescan.getText().toString().trim()))
                    {
                        CommonMethod.displaySnackbarerror(relativeLayout, InnerBoxInOuterBox.this, "Please Scan Outer Box Barcode.");
                        txtOuterBarcodescan.getText().clear();
                        txtOuterBarcodescan.requestFocus();
                    }
                    else
                    {
                        int ScnQty = Integer.parseInt(txtscanningqty.getText().toString());
                        if(ScnQty>0 && lblArticle.getText().length()>0)
                        {
                            StartInnerScanning();
                        }
                        else
                        {
                            CommonMethod.displaySnackbarerror(relativeLayout, InnerBoxInOuterBox.this, "Pls Enter Correct Values!!");
                        }
                    }
                    break;
                    case R.id.btn_exit:
                        finish();
                        break;
                    case R.id.btn_cleardata:
                        txtOuterBarcodescan.getText().clear();
                        lblArticle.setText("");
                        //lblSize.setText("");
                        //lblcartonboxbarcode.setText("");
                        txtscanningqty.setText("");
                        txtOuterBarcodescan.requestFocus();
                        break;
                    case R.id.setting:
                        startActivity(new Intent(InnerBoxInOuterBox.this, Setting.class));
                        break;
                }
    }
    private void StartInnerScanning()
    {
        AppSharedPreference.putBarCode(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.Article, lblArticle.getText().toString());
        AppSharedPreference.putBarCode(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.OuterBarCode, txtOuterBarcodescan.getText().toString());
        AppSharedPreference.putBarCode(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.OuterQty, txtscanningqty.getText().toString());
      //  layoutOuterBox.setVisibility(View.GONE);

        lblInnerScanned.setText("0/"+txtscanningqty.getText().toString());
        layoutBtn.setVisibility(View.GONE);
        settingBtn.setVisibility(View.GONE);
        layoutInnerBox.setVisibility(View.VISIBLE);
        txtOuterBarcodescan.setEnabled(false);
        txtscanningqty.setEnabled(false);
        txtInnerBarcodescan.requestFocus();

    }
    private void StartOuterScanning()
    {
        AppSharedPreference.putBarCode(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.Article,"");
        AppSharedPreference.putBarCode(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.OuterBarCode,"");
        AppSharedPreference.putBarCode(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.OuterQty,"");
        AppSharedPreference.putBarCode(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.ScannedQty,"0");
        layoutOuterBox.setVisibility(View.VISIBLE);
        layoutBtn.setVisibility(View.VISIBLE);
        settingBtn.setVisibility(View.VISIBLE);
        layoutInnerBox.setVisibility(View.INVISIBLE);

        lblArticle.setText("");
        //txtscanningqty.setEnabled(true);
        txtscanningqty.setText("");
        txtOuterBarcodescan.setEnabled(true);
        txtOuterBarcodescan.setText("");
        txtOuterBarcodescan.requestFocus();
    }
    private void  OuterScan(String Barcode)
    {
        SQLDB c= new SQLDB();
        Connection con=c.GetCon();
        if(con!=null)
        {
            try
            {
                Statement smt = con.createStatement();
                ResultSet res = smt.executeQuery("Select Article,CartonBoxQty from tblInputData Where CartonBoxBarCode='"+Barcode+"';");
                if(res.next())
                {
                    String Article = res.getString(1);
                    String CartonBoxQty = res.getString(2);
                    lblArticle.setText(Article);
                    txtscanningqty.setText(CartonBoxQty);
                    txtscanningqty.setEnabled(true);
                }
                else
                {
                    CommonMethod.displaySnackbarerror(relativeLayout, InnerBoxInOuterBox.this, "No Record Found!!");
                }
                con.close();
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

    private void  InnerScanning()
    {
        SQLDB c= new SQLDB();
        Connection con=c.GetCon();
        if(con!=null)
        {
            try {
                String OuterBarCode = AppSharedPreference.getUserName(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.OuterBarCode);
                String CustomerSize="";
                String CartonBoxBarcode="";
                Statement smt = con.createStatement();
                ResultSet res = smt.executeQuery("Select CustomerSize,CartonBoxBarcode from tblInputData Where Upc='"+txtInnerBarcodescan.getText().toString()+"' And CartonBoxBarcode='"+OuterBarCode+"' ;");
                if(res.next())
                {
                    CustomerSize = res.getString(1);
                    CartonBoxBarcode = res.getString(2);
                }

                //String OuterBarCode = AppSharedPreference.getUserName(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.OuterBarCode);
                String OuterQty = AppSharedPreference.getTypeOfWork(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.OuterQty);
                String ScannedQty = AppSharedPreference.getTypeOfWork(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.ScannedQty);

                if(CartonBoxBarcode.equals(OuterBarCode))
                {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO tblScanLogPkgUnPkg (Type,Article,CustomerSize,Upc,CartonBoxBarcode,Operator,boxsize,deviceSerial) VALUES (?, ?,?,?,?,?,?,?)");
                    ps.setString(1, lblTypeOfWork.getText().toString());
                    ps.setString(2, lblArticle.getText().toString());
                    ps.setString(3, CustomerSize);
                    ps.setString(4, txtInnerBarcodescan.getText().toString());
                    ps.setString(5, CartonBoxBarcode);
                    ps.setString(6, lblOperator.getText().toString());
                    ps.setString(7, txtscanningqty.getText().toString());
                    ps.setString(8, Build.MODEL);
                    ps.addBatch();
                    int[] results = ps.executeBatch();
                    Toast.makeText(getApplicationContext(), "Record Added Successfully!!", Toast.LENGTH_SHORT).show();
                    con.close();

                    ScannedQty = String.valueOf(Integer.parseInt(ScannedQty)+1);
                    lblInnerScanned.setText(ScannedQty+"/"+OuterQty);
                    AppSharedPreference.putBarCode(InnerBoxInOuterBox.this, AppSharedPreference.PREF_KEY.ScannedQty,ScannedQty);
                    txtInnerBarcodescan.setText("");
                    if(OuterQty.equals(ScannedQty))
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        AlertDialog alert = builder.create();
                        alert.setTitle("Scanning Completed");
                        alert.setMessage(lblArticle.getText().toString());
                        alert.show();
                        StartOuterScanning();
                    }
                }
                else
                {
                    CommonMethod.displaySnackbarerror(relativeLayout, InnerBoxInOuterBox.this, "Diffrent Article !!");
                }
            }
            catch (Exception ex)
            {
                if(ex.getMessage().toString().contains("UK_tblScanLogPkgUnPkg"))
                {
                    CommonMethod.displaySnackbarerror(relativeLayout, InnerBoxInOuterBox.this, "This Barcode allready Scanned!!");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error:"+ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
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
        InnerBoxInOuterBox.this.finish();
    }
}
