package com.yipl.architecturecomponenttest.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.yipl.architecturecomponenttest.db.entity.Project;

import java.util.List;

/**
 * Created by umesh on 11/15/17.
 */

@Dao
public interface ProjectDao {

    @Insert
    void insert(Project project);

    @Query("SELECT * FROM project")
    LiveData<List<Project>> getAllProjects();

    @Insert
    void insertAll(List<Project> projectList);

}
