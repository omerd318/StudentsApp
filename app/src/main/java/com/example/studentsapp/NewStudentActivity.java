package com.example.studentsapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class NewStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        EditText nameEt = findViewById(R.id.newstudent_name_et);
        EditText idEt = findViewById(R.id.newstudent_id_et);
        EditText phoneEt = findViewById(R.id.newstudent_phone_et);
        EditText addressEt = findViewById(R.id.newstudent_address_et);

        //ImageView avatarImg = findViewById(R.id.newstudent_avatar_img);

        Button saveBtn = findViewById(R.id.newstudent_save_btn);
        Button cancelBtn = findViewById(R.id.newstudent_cancel_btn);
        CheckBox cb = findViewById(R.id.newstudent_cb);

        saveBtn.setOnClickListener(v -> {
            if((nameEt.getText().toString().trim().length() > 0)
                    && (idEt.getText().toString().trim().length() > 0)) {
                Student st = new Student(nameEt.getText().toString(),
                        idEt.getText().toString(),
                        "",
                        phoneEt.getText().toString(),
                        addressEt.getText().toString(),
                        cb.isChecked());
                Model.instance().addStudent(st);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}