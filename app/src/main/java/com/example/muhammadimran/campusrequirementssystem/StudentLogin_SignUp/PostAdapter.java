package com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp.PostModel;
import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.UserActivity;

import java.util.List;

/**
 * Created by muhammad imran on 1/26/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {


    private List<PostModel> postModelList;
    Context context;

    public PostAdapter(List<PostModel> postModelList, Context context) {
        this.postModelList = postModelList;
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view_layout, parent, false);
        return new PostViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        PostModel post = postModelList.get(position);
        holder.description.setText(post.getDescription());
        Glide.with(context).load(post.getImageUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private ImageView image;

        public PostViewHolder(View view) {
            super(view);
            description = (TextView) view.findViewById(R.id.PostDescription);
            image = (ImageView) view.findViewById(R.id.PostImage);
        }
    }



}
