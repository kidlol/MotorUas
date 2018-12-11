package com.example.fvck6.motoruas;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class ProfilMotor extends AppCompatActivity
{
    VideoView videoView;
    ListView listView;

    ArrayList<String> listVideo;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_motor);

        videoView = (VideoView) findViewById(R.id.videoView);
        listView = (ListView) findViewById(R.id.listView);

        listVideo = new ArrayList<>();
        listVideo.add("Guns N' Roses - Sweet Child O' Mine (Official Music Video)");
        listVideo.add("Nirvana - Smells Like Teen Spirit");

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, Profil.profil);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
//                switch (position)
//                {
//                    case 0:
//                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video_1));
//                    break;
//                    case 1:
//                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video_2));
//                    break;
//                }
//
//                videoView.setMediaController(new MediaController(MainActivity.this));
//                videoView.requestFocus();
//                videoView.start();
                Profil profilModel = Profil.profil[(int)id];
                Uri videoUri = dapatkanMedia(profilModel.getVideoRawId());
                buatPlayer(videoUri);

            }
        });
    }

    private Uri dapatkanMedia(String videoRawId)
    {
        return Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoRawId);
    }

    private void buatPlayer(Uri videoUri)
    {
        videoView.setVideoURI(videoUri);
        videoView.setMediaController(new MediaController(ProfilMotor.this));
        videoView.requestFocus();
        videoView.start();
    }
}
