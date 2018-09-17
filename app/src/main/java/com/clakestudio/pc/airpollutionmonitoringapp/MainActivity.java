package com.clakestudio.pc.airpollutionmonitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.clakestudio.pc.airpollutionmonitoringapp.stations.StationsListActivity;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //@BindView(R.id.btStartStationsListActivity)
    /// Button btStartStationsListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btStartStationsListActivity = (Button) findViewById(R.id.btStartStationsListActivity);

        btStartStationsListActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startStationsListActivity();
    }

    public void startStationsListActivity() {
        startActivity(new Intent(this, StationsListActivity.class));
        finish();
    }
}
