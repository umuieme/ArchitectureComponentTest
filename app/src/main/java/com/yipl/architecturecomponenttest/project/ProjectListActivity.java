package com.yipl.architecturecomponenttest.project;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.DatePicker;

import com.yipl.architecturecomponenttest.R;
import com.yipl.architecturecomponenttest.databinding.ActivityProjectListBinding;
import com.yipl.architecturecomponenttest.db.AppDatabase;
import com.yipl.architecturecomponenttest.db.entity.Project;
import com.yipl.architecturecomponenttest.project.adapter.ProjectListAdapter;
import com.yipl.architecturecomponenttest.project.viewmodel.ProjectListViewModel;
import com.yipl.architecturecomponenttest.utilities.AppDateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProjectListActivity extends AppCompatActivity {
    public static final String TAG = "ProjectListActivity";
    ProjectListViewModel viewModel;
    private ActivityProjectListBinding binding;
    private ProjectListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Seeder.insertProjects(this);
        binding.fab.setOnClickListener(view -> {

            String name = binding.content.containerAddProject.editName.getText().toString();
            if (name.isEmpty()) return;
            String date = binding.content.containerAddProject.textDeadline.getText().toString();
            insert(name, AppDateUtils.convertDate(date));
            binding.content.containerAddProject.editName.setText("");
            binding.content.containerAddProject.textDeadline.setText(R.string.action_pick_date);

        });
        setUpAdapter();
        fetchProjectList();
        Calendar calendar = Calendar.getInstance();
        binding.content.containerAddProject.textDeadline.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    binding.content.containerAddProject.textDeadline.setText(AppDateUtils.convertDate(calendar.getTime()));
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });
    }

    private void setUpAdapter() {
        adapter = new ProjectListAdapter();
        binding.content.listProject.setLayoutManager(new LinearLayoutManager(this));
        binding.content.listProject.setAdapter(adapter);
    }

    private void fetchProjectList() {
        // in your  activity
        ProjectListViewModel viewModel = ViewModelProviders.of(this).get(ProjectListViewModel.class);
        viewModel.getProjects()
                .observe(this, new Observer<List<Project>>() {
                    @Override
                    public void onChanged(@Nullable List<Project> projectList) {
                        adapter.setProjectList(projectList);
                    }
                });
    }

    private void insert(String name, Date date) {
        Project project = new Project();
        project.setName(name);
        project.setDeadline(date);
        AppDatabase.getInstance(getBaseContext())
                .getProjectDao()
                .insert(project);
    }
}
