package com.ec3_ihb_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ec3_ihb_app.network.CourseEntry;
import com.ec3_ihb_app.network.ImageRequester;

import java.util.List;

public class CourseCardRecyclerViewAdapter extends RecyclerView.Adapter<CourseCardViewHolder> {
    private List<CourseEntry> courseList;
    private ImageRequester imageRequester;

    CourseCardRecyclerViewAdapter(List<CourseEntry> courseList){
        this.courseList = courseList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public CourseCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        return new CourseCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseCardViewHolder holder, int position) {
        if (courseList != null & position < courseList.size()){
            CourseEntry course = courseList.get(position);
            holder.courseTitle.setText(course.title);
            imageRequester.setImageFromUrl(holder.courseImage, course.url);
        }
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}
