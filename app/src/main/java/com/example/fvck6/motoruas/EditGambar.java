package com.example.fvck6.motoruas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditGambar extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gambar);
        Bundle extras = getIntent().getExtras();
        int a = extras.getInt("id");
        WarnaDetail frag = (WarnaDetail) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        frag.setWarnaId(a);
    }
}
