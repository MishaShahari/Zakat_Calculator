/*
 *
 *   Created Hamisha Husna Shahari on 14/12/2023, 10:52 am
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://freefuninfo.com/
 *   Last modified: 12/12/2023, 3:19 am
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
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar myToolbar;
    Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(this);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);//call title from strings.xml
        //myToolbar.setSubtitle("This is a subtitle of application");// subtitle

    }


    @Override
    public void onClick(View v) {
        if(v == btnStart){
            Intent intent = new Intent(this, CalculateActivity.class);

            startActivity(intent);

        }
    }
}
