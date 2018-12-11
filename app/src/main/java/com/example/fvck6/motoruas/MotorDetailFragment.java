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
public class MotorDetailFragment extends Fragment
{
    private long motorId;

    public MotorDetailFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motor_detail, container, false);
    }

    public void setMotor(long motorId)
    {
        this.motorId = motorId;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view = getView();

        if (view != null)
        {
            TextView nama = (TextView)view.findViewById(R.id.textNama);
            Motor listmotor = Motor.listmotor[(int) motorId];
            nama.setText(listmotor.getNama());

            TextView harga = (TextView) view.findViewById(R.id.textMerk);
            harga.setText(listmotor.getMerk());

            TextView description = (TextView) view.findViewById(R.id.textHarga);
            description.setText(listmotor.getHarga());

            ImageView gambar =(ImageView) view.findViewById(R.id.imgMotor);
            gambar.setImageResource(listmotor.getGambar());
        }
    }
}
