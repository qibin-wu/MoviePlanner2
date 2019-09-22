package com.movie.myapplication.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.movie.myapplication.model.AsyncTask.cleanAttendeeAsyncTask;
import com.movie.myapplication.model.Attendees;
import com.movie.myapplication.model.Event;
import com.movie.myapplication.model.Item;
import com.movie.myapplication.view.attendeeList;

public class attendeeOnItemLongClick implements AdapterView.OnItemLongClickListener{
    private Activity a;
    private Attendees[] attendees;
    private Event thisEvent;

    public attendeeOnItemLongClick(attendeeList a, Attendees[] attendees,Event thisEvent) {
        this.a = a;
        this.attendees=attendees;
        this.thisEvent=thisEvent;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(a);
        normalDialog.setTitle("Warning");
        normalDialog.setMessage("Are you sure to remove this attendee?");
        normalDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        new cleanAttendeeAsyncTask(a).execute(attendees[position].getName());

                        Intent attendIntent = new Intent();
                        attendIntent.setClass(a, attendeeList.class);
                        attendIntent.putExtra("eventID", thisEvent.getId());
                       a.startActivity(attendIntent);

                    }
                });
        normalDialog.setNegativeButton("No",null);
        normalDialog.show();

        return false;
    }
}
