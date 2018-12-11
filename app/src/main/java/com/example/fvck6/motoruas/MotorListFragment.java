package com.example.fvck6.motoruas;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MotorListFragment extends ListFragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String[] names = new String[Motor.listmotor.length];

        for (int i = 0; i < names.length; i++)
        {
            names[i] = Motor.listmotor[i].getNama();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),android.R.layout.simple_list_item_1,names);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    public void onListItemClick(ListView listView, View view, int position, long id)
    {
        super.onListItemClick(listView,view,position,id);
        Toast.makeText(getActivity(),"item"+position+"was clicked",Toast.LENGTH_LONG);
        Intent intent = new Intent(view.getContext(),DetailActivity.class);
        Bundle b = new Bundle();
        String[] names = new String[Motor.listmotor.length];
        b.putInt("id",position);
        intent.putExtras(b);
        startActivity(intent);
    }


}
