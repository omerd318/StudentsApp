package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class EditStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        Student curr_st = Model.instance().getAllStudents().get(position);

        EditText nameEt = findViewById(R.id.editstudent_name_et);
        TextView idEt = findViewById(R.id.editstudent_id_et);
        TextView phoneEt = findViewById(R.id.editstudent_phone_et);
        TextView addressEt = findViewById(R.id.editstudent_address_et);
        CheckBox cb = findViewById(R.id.editstudent_cb);
        Button saveBtn = findViewById(R.id.editstudent_save_btn);
        Button cancelBtn = findViewById(R.id.editstudent_cancel_btn);
        Button deleteBtn = findViewById(R.id.editstudent_delete_btn);

        nameEt.setText(curr_st.getName());
        idEt.setText(curr_st.getId());
        phoneEt.setText(curr_st.getPhone());
        addressEt.setText(curr_st.getAddress());
        cb.setChecked(curr_st.getCb());

        cancelBtn.setOnClickListener(v -> {
            finish();
        });

        deleteBtn.setOnClickListener(v -> {
            Model.instance().deleteStudent(position);
            setResult(1);
            finish();
        });

        saveBtn.setOnClickListener(v -> {
            Student newSt = new Student(nameEt.getText().toString(),
                                        idEt.getText().toString(),
                                        "",
                                        phoneEt.getText().toString(),
                                        addressEt.getText().toString(),
                                        cb.isChecked());
            Model.instance().editStudent(newSt, position);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("new_student", buildStBundle(newSt));
            setResult(2, returnIntent);
            finish();
        });
    }

    private Bundle buildStBundle(Student st) {
        Bundle bundle = new Bundle();

        bundle.putString("name", st.getName());
        bundle.putString("id", st.getId());
        bundle.putString("phone", st.getPhone());
        bundle.putString("address", st.getAddress());
        bundle.putBoolean("checked", st.getCb());

        return bundle;
    }
}