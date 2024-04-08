package com.example.frontend;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleMeeting extends AppCompatActivity {

    Button btnDatePicker, btnTimePicker, sendInviteButton;
    EditText txtDate, txtTime, participantsEditText, meetingLinkEditText;
    public int mYear, mMonth, mDay, mHour, mMinute;

    // Declare meetingsList as a class-level variable
    public ArrayList<MeetingInfo> meetingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule_meeting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnDatePicker = findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time);
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        participantsEditText = findViewById(R.id.in_participants);
        meetingLinkEditText = findViewById(R.id.in_meeting_link);
        sendInviteButton = findViewById(R.id.in_createMeeting);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ScheduleMeeting.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ScheduleMeeting.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        sendInviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInvite();
            }
        });
    }

    public void sendInvite() {
        String meetingLink = getMeetingLink();
        ArrayList<String> participants = getMeetingParticipants();
        String date = getMeetingDate();
        String time = getMeetingTime();

        // Create an object to hold the meeting information
        MeetingInfo meetingInfo = new MeetingInfo(participants, meetingLink, date, time);

        // Add the meeting to the meetings list
        meetingsList.add(meetingInfo);
    }

    public String getMeetingLink() {
        return meetingLinkEditText.getText().toString();
    }

    public ArrayList<String> getMeetingParticipants() {
        String participantsInput = participantsEditText.getText().toString();
        String[] participantsArray = participantsInput.split(",");

        // Trim whitespace from each participant and add to list
        ArrayList<String> participantsList = new ArrayList<>();
        for (String participant : participantsArray) {
            participantsList.add(participant.trim());
        }

        return participantsList;
    }

    public String getMeetingDate() {
        return txtDate.getText().toString();
    }

    public String getMeetingTime() {
        return txtTime.getText().toString();
    }

    public static class MeetingInfo {
        ArrayList<String> participants;
        String meetingLink;
        String date;
        String time;

        MeetingInfo(ArrayList<String> participants, String meetingLink, String date, String time) {
            this.participants = participants;
            this.meetingLink = meetingLink;
            this.date = date;
            this.time = time;
        }
    }

    public void viewMeetings() {
        // Print out all the meetings in the terminal
        for (MeetingInfo meeting : meetingsList) {
            System.out.println("Meeting:");
            System.out.println("Participants: " + meeting.participants);
            System.out.println("Meeting Link: " + meeting.meetingLink);
            System.out.println("Date: " + meeting.date);
            System.out.println("Time: " + meeting.time);
            System.out.println();
        }
    }

}
