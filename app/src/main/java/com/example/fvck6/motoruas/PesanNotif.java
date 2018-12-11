package com.example.fvck6.motoruas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PesanNotif extends AppCompatActivity implements View.OnClickListener
{
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_notif);
        findAllViewId();
        text.setOnClickListener(this);
    }

    private void findAllViewId()
    {
        text = (TextView)findViewById(R.id.link);
    }

    public void onResume()
    {
        super.onResume();
        TextView atas = (TextView)findViewById(R.id.atas);
        TextView bawah = (TextView)findViewById(R.id.bawah);

        Intent intent = getIntent();
        String pesan = intent.getStringExtra("bawah");
        String pengirim = intent.getStringExtra("atas");

        bawah.setText(pesan);
        atas.setText(pengirim);
    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(getApplicationContext(),ProfilMotor.class);
        Bundle b = new Bundle();
        intent.putExtras(b);
        startActivity(intent);
    }
}
