package com.yipl.architecturecomponenttest.db;

import android.content.Context;

import com.yipl.architecturecomponenttest.db.entity.Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by umesh on 11/15/17.
 */

public class Seeder {

    public static void insertProjects(Context context) {
        List<Project> projectList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Project project = new Project();
            project.setName("Project  " + i);
            project.setDeadline(new Date());
            projectList.add(project);
        }
        AppDatabase.getInstance(context)
                .getProjectDao()
                .insertAll(projectList);
    }
}
