package com.example.fvck6.motoruas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EditGambarList extends ListFragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String[] names = new String[Warna.listwarna.length];

        for (int i = 0; i < names.length; i++)
        {
            names[i] = Warna.listwarna[i].getNama();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),android.R.layout.simple_list_item_1,names);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onListItemClick(ListView listView, View view, int position, long id)
    {
        super.onListItemClick(listView,view,position,id);
        Toast.makeText(getActivity(),"item"+position+"was clicked",Toast.LENGTH_LONG);
        Intent intent = new Intent(view.getContext(),EditGambar.class);
        Bundle b = new Bundle();
        String[] names = new String[Warna.listwarna.length];
        b.putInt("id",position);
        intent.putExtras(b);
        startActivity(intent);
    }
}
