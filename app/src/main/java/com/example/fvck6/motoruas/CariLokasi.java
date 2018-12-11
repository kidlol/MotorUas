package com.example.fvck6.motoruas;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class CariLokasi extends AppCompatActivity implements DapatkanAlamatTask.onTaskSelesai
{
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final int REQUEST_PICK_PLACE = 1;
    private Button mLocationButton;
    private Button mPickPlace;
    private TextView mLocationTextView;
    private ImageView mAndroidImageView;
    private AnimatorSet mRotateAnim;

    private boolean mTrackingLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private PlaceDetectionClient mPlacesDetectionCLinet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_lokasi);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationTextView = (TextView) findViewById(R.id.textview_location);
        mAndroidImageView = (ImageView) findViewById(R.id.imageview_android);
        mPlacesDetectionCLinet = Places.getPlaceDetectionClient(this);

        mRotateAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.rotate);
        mRotateAnim.setTarget(mAndroidImageView);

        mPickPlace = (Button) findViewById(R.id.button_pilih);
        mPickPlace.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try
                {
                    startActivityForResult(builder.build(CariLokasi.this),REQUEST_PICK_PLACE);
                }
                catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e)
                {
                    e.printStackTrace();
                }
            }
        });
        mLocationButton = (Button) findViewById(R.id.button_location);
        mLocationButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!mTrackingLocation)
                {
                    mulaiTrackingLokasi();
                }
                else
                {
                    stopTrackingLokasi();
                }
            }
        });

        mLocationCallback = new LocationCallback()
        {
            public void onLocationResult(LocationResult locationResult)
            {
                if (mTrackingLocation)
                {
                    new DapatkanAlamatTask(CariLokasi.this,CariLokasi.this).execute(locationResult.getLastLocation());
                }
            }
        };
    }

    private void mulaiTrackingLokasi()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSION);
        }
        else
        {
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(),mLocationCallback,null);
            mLocationTextView.setText(getString(R.string.alamat_text,"Sedang mencari nama tempat","Sedang mencari alamat",System.currentTimeMillis()));
            mTrackingLocation=true;
            mLocationButton.setText("Stop Traking Lokasi");
            mRotateAnim.start();
        }

    }

    private void stopTrackingLokasi()
    {
        if (mTrackingLocation)
        {
            mTrackingLocation=false;
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            mLocationButton.setText("Mulai Tracking Lokasi");
            mLocationTextView.setText("Tracking sedang dihetikan");
            mRotateAnim.end();
        }
    }

    public void onTaskCompleted(final String result) throws SecurityException
    {
        if (mTrackingLocation)
        {
            Task<PlaceLikelihoodBufferResponse> placeResult = mPlacesDetectionCLinet.getCurrentPlace(null);
            placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>()
            {
                @Override
                public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task)
                {
                    if (task.isSuccessful())
                    {
                        PlaceLikelihoodBufferResponse likelyPlace = task.getResult();
                        float maxLikelihood = 0;
                        Place currentPlace = null;
                        for (PlaceLikelihood placeLikelihood : likelyPlace)
                        {
                            if (maxLikelihood < placeLikelihood.getLikelihood())
                            {
                                maxLikelihood = placeLikelihood.getLikelihood();
                                currentPlace = placeLikelihood.getPlace();
                            }
                        }
                        if (currentPlace != null)
                        {
                            mLocationTextView.setText(getString(R.string.alamat_text,currentPlace.getName(),result,System.currentTimeMillis()));
                            setTipeLokasi(currentPlace);
                        }
                        likelyPlace.release();
                    }
                    else
                    {
                        mLocationTextView.setText(getString(R.string.alamat_text,"Nama lokasi tidak ditemukan!",result,System.currentTimeMillis()));
                    }
                }
            });
        }
    }

    private LocationRequest getLocationRequest()
    {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private void setTipeLokasi(Place currentPlace)
    {
        int drawableID = -1;
        for (Integer placeType : currentPlace.getPlaceTypes())
        {
            switch (placeType)
            {
                case Place.TYPE_CAR_DEALER:
                    drawableID = R.drawable.ic_store_black_24dp;
                    break;
            }
        }
        mAndroidImageView.setImageResource(drawableID);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == RESULT_OK)
        {
            Place place = PlacePicker.getPlace(this,data);
            setTipeLokasi(place);
            mLocationTextView.setText(getString(R.string.alamat_text,place.getName(),place.getAddress(),System.currentTimeMillis()));
        }
        else
        {
            mLocationTextView.setText("Lokasinya kenapa kok gak dipilih Ferguso?");
        }

    }
}
