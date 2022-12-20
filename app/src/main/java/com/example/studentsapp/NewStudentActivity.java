package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class NewStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<Student> data = Model.instance().getAllStudents();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        EditText nameEt = findViewById(R.id.newstudent_name_et);
        EditText idEt = findViewById(R.id.newstudent_id_et);
        EditText phoneEt = findViewById(R.id.newstudent_phone_et);
        EditText addressEt = findViewById(R.id.newstudent_address_et);

        Button saveBtn = findViewById(R.id.newstudent_save_btn);
        Button cancelBtn = findViewById(R.id.newstudent_cancel_btn);
        CheckBox cb = findViewById(R.id.newstudent_cb);

        saveBtn.setOnClickListener(v -> {
            //data.addStudent(new Student(nameEt, idEt, phoneEt, addressEt));
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}