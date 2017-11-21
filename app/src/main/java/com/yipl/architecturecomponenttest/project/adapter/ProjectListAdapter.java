package com.yipl.architecturecomponenttest.project.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yipl.architecturecomponenttest.R;
import com.yipl.architecturecomponenttest.databinding.ItemProjectListBinding;
import com.yipl.architecturecomponenttest.db.entity.Project;
import com.yipl.architecturecomponenttest.project.viewmodel.ProjectViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umesh on 11/15/17.
 */

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder> {

    List<Project> projectList = new ArrayList<>();

    @Override
    public ProjectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProjectListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_project_list, parent, false);
        return new ProjectListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProjectListViewHolder holder, int position) {
        holder.setProject(projectList.get(position));
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ProjectListViewHolder extends RecyclerView.ViewHolder {

        private ItemProjectListBinding binding;

        public ProjectListViewHolder(ItemProjectListBinding itemProjectListBinding) {
            super(itemProjectListBinding.getRoot());
            this.binding = itemProjectListBinding;
        }

        public void setProject(Project project) {
            binding.setViewModel(new ProjectViewModel(project));
        }

    }
}
