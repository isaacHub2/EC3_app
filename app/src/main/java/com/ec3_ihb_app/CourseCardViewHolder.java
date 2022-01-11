package com.ec3_ihb_app;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

public class CourseCardViewHolder extends RecyclerView.ViewHolder {
    public NetworkImageView courseImage;
    public TextView courseTitle;

    public CourseCardViewHolder(@NonNull View itemView){
        super(itemView);
        courseImage = itemView.findViewById(R.id.course_image);
        courseTitle = itemView.findViewById(R.id.course_title);
    }
}
