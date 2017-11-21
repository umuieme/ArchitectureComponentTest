package com.yipl.architecturecomponenttest.project.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.yipl.architecturecomponenttest.db.entity.Project;
import com.yipl.architecturecomponenttest.utilities.AppDateUtils;

/**
 * Created by umesh on 11/16/17.
 */

public class ProjectViewModel extends BaseObservable {

    private Project project;

    public ProjectViewModel(Project project) {
        this.project = project;
    }

    @Bindable
    public String getProjectName() {
        return project.getName();
    }

    @Bindable
    public String getDeadline() {
        return "Deadline: " + AppDateUtils.convertDate(project.getDeadline());
    }
}