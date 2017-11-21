package com.yipl.architecturecomponenttest.project.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yipl.architecturecomponenttest.db.AppDatabase;
import com.yipl.architecturecomponenttest.db.entity.Project;

import java.util.List;

/**
 * Created by umesh on 11/18/17.
 */

public class ProjectListViewModel extends AndroidViewModel {

    private final AppDatabase databaseInstance;
    LiveData<List<Project>> projectList;

    public ProjectListViewModel(@NonNull Application application) {
        super(application);
        databaseInstance = AppDatabase.getInstance(application);
        projectList = databaseInstance
                .getProjectDao()
                .getAllProjects();
    }

    public LiveData<List<Project>> getProjects() {
        return projectList;
    }

}
