package com.example.studentsapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        Student curr_st = Model.instance().getAllStudents().get(position);

        TextView nameTv = findViewById(R.id.studentdeatils_name_tv);
        TextView idTv = findViewById(R.id.studentdeatils_id_tv);
        TextView phoneTv = findViewById(R.id.studentdeatils_phone_tv);
        TextView addressTv = findViewById(R.id.studentdeatils_address_tv);
        CheckBox cb = findViewById(R.id.studentdeatils_cb);

        nameTv.setText(curr_st.getName());
        idTv.setText(curr_st.getId());
        phoneTv.setText(curr_st.getPhone());
        addressTv.setText(curr_st.getAddress());
        cb.setChecked(curr_st.getCb());

        Button editBtn = findViewById(R.id.studentdeatils_edit_btn);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                   if (result.getResultCode() == 1) {               // DELETE
                       finish();
                   } else if (result.getResultCode() == 2) {        // EDIT
                        newStudentView(result.getData(), "new_student");
                   }
                });

        editBtn.setOnClickListener(v -> {
            Intent editStudentIntent = new Intent(this, EditStudentActivity.class);
            editStudentIntent.putExtra("position", position);
            launcher.launch(editStudentIntent);
        });
    }

    private void newStudentView(Intent intent, String bundleKey) {
        Bundle newSt = intent.getBundleExtra(bundleKey);

        TextView nameTv = findViewById(R.id.studentdeatils_name_tv);
        TextView idTv = findViewById(R.id.studentdeatils_id_tv);
        TextView phoneTv = findViewById(R.id.studentdeatils_phone_tv);
        TextView addressTv = findViewById(R.id.studentdeatils_address_tv);
        CheckBox cb = findViewById(R.id.studentdeatils_cb);

        nameTv.setText(newSt.getString("name"));
        idTv.setText(newSt.getString("id"));
        phoneTv.setText(newSt.getString("phone"));
        addressTv.setText(newSt.getString("address"));
        cb.setChecked(newSt.getBoolean("checked"));
    }
}