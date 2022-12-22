package com.example.studentsapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {
    List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        students = Model.instance().getAllStudents();

        RecyclerView list = findViewById(R.id.studentlist_list);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));
        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
//            Call Student Details screen
        });

        Intent createIntent = new Intent(this, NewStudentActivity.class);
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result ->
                    adapter.notifyDataSetChanged());

        Button addStudentBtn = findViewById(R.id.studentlist_add_btn);
        addStudentBtn.setOnClickListener(v -> {
            launcher.launch(createIntent);
        });
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
            idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
            cb = itemView.findViewById(R.id.studentlistrow_cb);

            cb.setOnClickListener(v -> {
                int pos = (int)cb.getTag();
                Student st = students.get(pos);
                st.setCb(cb.isChecked());
            });

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                listener.onItemClick(pos);
            });
        }

        public void bind(Student st, int position) {
            nameTv.setText(st.getName());
            idTv.setText(st.getId());
            cb.setChecked(st.getCb());
            cb.setTag(position);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
        OnItemClickListener listener;
        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);

            return new StudentViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = students.get(position);
            holder.bind(st, position);
        }

        @Override
        public int getItemCount() {
            return students.size();
        }
    }
}