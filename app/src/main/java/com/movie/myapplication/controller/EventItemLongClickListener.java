package com.movie.myapplication.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.movie.myapplication.model.AsyncTask.RemoveEventbyTitleAsyncTask;
import com.movie.myapplication.model.Item;
import com.movie.myapplication.view.MainActivity;

public class EventItemLongClickListener implements AdapterView.OnItemLongClickListener {
    private Activity a;
    private  Item[] items;

    public EventItemLongClickListener(MainActivity mainActivity, Item[] inItems) {
        a=mainActivity;
        items=inItems;
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(a);
        normalDialog.setTitle("Warning");
        normalDialog.setMessage("Are you sure to remove this even?");
        normalDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String title = items[position].getTitle();

                        new RemoveEventbyTitleAsyncTask(a).execute(title);

                        Toast.makeText(a, "Event Remooved", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(a, MainActivity.class);
                        a.startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("No", null);
        normalDialog.show();
        return true;


    }
}
