package com.example.android.cryptotrade;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.android.cryptotrade.fragments.AlertFragment;
import com.example.android.cryptotrade.pojo.Alert;
import com.example.android.cryptotrade.sql.DataHelper;

/**
 * Created by DITHA on 15/07/2017.
 */

public class AlertActivity extends AppCompatActivity {

    DataHelper dbHelper;
    EditText ET_SELECT_CRYPTO, ET_ALERT_PRICE;
    Switch SWITCH;
    Context context;
    String check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity);


        dbHelper = new DataHelper(this);
        dbHelper.open();
        context = this;

        ET_SELECT_CRYPTO = (EditText) findViewById(R.id.etSelectCrypto);
        ET_ALERT_PRICE = (EditText) findViewById(R.id.etAlertPrice);
        SWITCH = (Switch) findViewById(R.id.switch1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ET_SELECT_CRYPTO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose an crypto");

                String[] crypto = {"BTC/IDR",
                                    "BTS/BTC",
                                    "DASH/BTC",
                                    "DOGE/BTC",
                                    "ETH/BTC",
                                    "LTC/BTC",
                                    "NXT/BTC",
                                    "STR/BTC",
                                    "NEM/BTC",
                                    "XRP/BTC"};

                builder.setItems(crypto, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                ET_SELECT_CRYPTO.setText("BTC/IDR");
                                break;
                            case 1:
                                ET_SELECT_CRYPTO.setText("BTS/BTC");
                                break;
                            case 2:
                                ET_SELECT_CRYPTO.setText("DASH/BTC");
                                break;
                            case 3:
                                ET_SELECT_CRYPTO.setText("DOGE/BTC");
                                break;
                            case 4:
                                ET_SELECT_CRYPTO.setText("ETH/BTC");
                                break;
                            case 5:
                                ET_SELECT_CRYPTO.setText("LTC/BTC");
                                break;
                            case 6:
                                ET_SELECT_CRYPTO.setText("NXT/BTC");
                                break;
                            case 7:
                                ET_SELECT_CRYPTO.setText("STR/BTC");
                                break;
                            case 8:
                                ET_SELECT_CRYPTO.setText("NEM/BTC");
                                break;
                            case 9:
                                ET_SELECT_CRYPTO.setText("XRP/BTC");
                                break;
                        }
                    }
                });

// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

                SWITCH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked){
                            check = "active";
                        }else{
                            check = "inactive";
                        }
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alert, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_proses :
                Alert alert = new Alert();
                alert.setCryptoName(ET_SELECT_CRYPTO.getText().toString());
                alert.setPrice(ET_ALERT_PRICE.getText().toString());
                alert.setStatus(check);

                dbHelper.CreateAlert(alert);
                dbHelper.Close();
                /*
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into alert(id, cryptoname, price, status) values('" +
                        2 + "','" +
                        ET_SELECT_CRYPTO.getText().toString() + "','" +
                        ET_ALERT_PRICE.getText().toString() + "','" +
                        check + "')");
                        */
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                AlertFragment.alertFragment.RefreshList();
                this.finish();
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            case android.R.id.home:
                    this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
