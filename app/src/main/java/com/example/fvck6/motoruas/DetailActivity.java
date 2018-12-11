package com.example.fvck6.motoruas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        int a = extras.getInt("id");
        MotorDetailFragment frag = (MotorDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        frag.setMotor(a);
    }
}
