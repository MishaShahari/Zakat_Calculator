/*
 *
 *   Created Hamisha Husna Shahari on 14/12/2023, 10:52 am
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://freefuninfo.com/
 *   Last modified: 12/12/2023, 3:00 am
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 *   except in compliance with the License. You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENS... Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *    either express or implied. See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 *
 */

package com.example.zakatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class CalculateActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar calcToolbar;
    EditText weight, goldValue;
    TextView tvGold,goldType, tvZakatPayable, tZakat;
    RadioButton radioKeep, radioWear;
    Button calculate, reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        // Enable the back button in the action bar

        // Initialize all components
        calcToolbar = (Toolbar) findViewById(R.id.calc_toolbar);
        setSupportActionBar(calcToolbar);
        getSupportActionBar().setTitle(R.string.app_name);//call title from strings.xml
        calcToolbar.setSubtitle("This is zakat application");// subtitle

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        calculate = (Button) findViewById(R.id.calculate);
        reset = (Button) findViewById(R.id.reset);
        radioKeep = (RadioButton) findViewById(R.id.radioKeep);
        radioWear = (RadioButton) findViewById(R.id.radioWear);
        goldType = (TextView) findViewById(R.id.goldType);
        tvGold = (TextView) findViewById(R.id.tvGold);
        tvZakatPayable = (TextView) findViewById(R.id.tvZakatPayable);
        tZakat = (TextView) findViewById(R.id.tZakat);
        weight = (EditText) findViewById(R.id.weight);
        goldValue = (EditText) findViewById(R.id.goldValue);

        calculate.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calculate) {
            try {
                // Check for valid input
                double weightValue = Double.parseDouble(weight.getText().toString());
                double goldValuePerGram = Double.parseDouble(goldValue.getText().toString());

                // Determine X value based on radio button selection
                double xValue = 0;
                if (radioKeep.isChecked()) {
                    xValue = 85.0;
                    goldType.setText("Gold Type: Keep");
                } else if (radioWear.isChecked()) {
                    xValue = 200.0;
                    goldType.setText("Gold Type: Wear");

                }

                // Calculate the total value of the gold
                double totalGoldValue = weightValue * goldValuePerGram;

                // Calculate the total gold value that is zakat payable
                double totalZakatPayable = (weightValue - xValue) * goldValuePerGram;

                // Calculate the total zakat
                double totalZakat = totalZakatPayable * 0.025;

                // Set the results in TextViews
                tvGold.setText("Total Gold Value: RM " + String.format("%.2f", totalGoldValue));
                tvZakatPayable.setText("Total Gold Value that is Zakat Payable: RM " + String.format("%.2f", totalZakatPayable));
                tZakat.setText("Total Zakat: RM " + String.format("%.2f", totalZakat));

            } catch (NumberFormatException e) {
                // Handle the case where input values are not valid numbers
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.reset) {
            // Reset button clicked, clear all input fields
            weight.setText("");
            goldValue.setText("");
            radioKeep.setChecked(false);
            radioWear.setChecked(false);
            goldType.setText("Type of Gold");
            tvGold.setText("");
            tvZakatPayable.setText("");
            tZakat.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item_share){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Please use my application - https://t.co/app");
            //something to promote the application
            startActivity(Intent.createChooser(shareIntent, null));

            return true;

        } else if(item.getItemId() == R.id.item_about){
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;

        } else if (item.getItemId() == android.R.id.home) {
            // Handle the back button click
            onBackPressed();
            return true;
        }

        return false;

    }

}