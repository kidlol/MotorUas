package com.example.fvck6.motoruas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WarnaDetail extends Fragment
{
    private long warnaId;

    public WarnaDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_warna_detail, container, false);
    }

    public void setWarnaId(long warnaId)
    {
        this.warnaId = warnaId;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view = getView();

        if (view != null)
        {
            TextView nama = (TextView) view.findViewById(R.id.textNama);
            Warna listwarna = Warna.listwarna[(int) warnaId];
            nama.setText(listwarna.getNama());

            ImageView gambar = (ImageView) view.findViewById(R.id.imgWarna);
            gambar.setImageResource(listwarna.getGambar());

            ImageView gambar1 =(ImageView) view.findViewById(R.id.imgWarna1);
            gambar.setImageResource(listwarna.getGambar1());
        }
    }
}
