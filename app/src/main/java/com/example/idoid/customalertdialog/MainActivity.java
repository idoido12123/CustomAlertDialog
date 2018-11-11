package com.example.idoid.customalertdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import static com.example.idoid.customalertdialog.R.*;
import static com.example.idoid.customalertdialog.R.layout.support_simple_spinner_dropdown_item;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    LinearLayout dialog;
    RadioButton rb1, rb2;
    EditText firstnum, multordif;
    String[] numbers = new String[20];
    double [] nums = new double[20];
    ListView lv;
    TextView tv1,tv2,tv3,tv4;
    String d;
    Intent t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        lv = (ListView) findViewById(R.id.listview);
        tv1 = (TextView) findViewById(id.textView2);
        tv2 = (TextView) findViewById(id.textView3);
        tv3 = (TextView) findViewById(id.textView4);
        tv4 = (TextView) findViewById(id.textView5);


        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    public void start(View view) {
        AlertDialog.Builder bul;
        dialog = (LinearLayout) getLayoutInflater().inflate(layout.dialog, null);
        rb1 = (RadioButton) dialog.findViewById(id.aritProg);
        rb2 = (RadioButton) dialog.findViewById(id.geoProg);
        firstnum = (EditText) dialog.findViewById(id.firstNum);
        multordif = (EditText) dialog.findViewById(id.MultOrDif);
        bul = new AlertDialog.Builder(this);
        bul.setView(dialog);
        bul.setNegativeButton("cancle", myclick);
        bul.setPositiveButton("OK", myclick);
        bul.setNeutralButton("reset",myclick);
        bul.show();
    }

    DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                dialog.cancel();
            }
            if (which == DialogInterface.BUTTON_POSITIVE) {
                String num = firstnum.getText().toString();
                d = multordif.getText().toString();
                double num1 = Double.parseDouble(num);
                double d2 = Double.parseDouble(d);
                numbers[0] = "" + num1;
                nums[0] = num1;
                if (rb1.isChecked()) {
                    for (int i = 1; i < 20; i++) {
                        nums[i] = nums[i - 1] + d2;
                        numbers[i] = "" + nums[i];
                    }
                }
                if (rb2.isChecked()) {
                    for (int i = 1; i < 20; i++) {
                        nums[i] = nums[i - 1] * d2;
                        numbers[i] = "" + nums[i];
                    }
                }
                Adapter();

            }
            if(which==DialogInterface.BUTTON_NEUTRAL){
                for(int i=0;i<20;i++){
                    nums[i]=0;
                    numbers[i]=""+nums[i];
                }
                Adapter();
            }
        }

    };
    public void Adapter(){
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,numbers);
        lv.setAdapter(adp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        double sum=0;
        tv1.setText("x1= "+numbers[0]);
        tv2.setText("d= "+d);
        tv3.setText("n= "+position);
        for (int i=0;i<=position;i++){
            sum=sum+nums[i];
        }
        tv4.setText("Sn= "+sum);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st = item.getTitle().toString();
        if (st.equals("credits")) {
            t=new Intent(this,Main2Activity.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }
}





