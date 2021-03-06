package com.example.blogit.Data;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogit.Model.Blog;
import com.example.blogit.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Blog> blogList;


    public BlogRecyclerAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Blog blog = blogList.get(position);
        String imageURL = null;
        holder.title.setText(blog.getTitle());
        holder.desc.setText(blog.getDesc());

        DateFormat dateFormat = DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.parseLong(blog.getTimestamp())).getTime());

        holder.timestamp.setText(formattedDate);
//        String imageURL = "https://s.clipartkey.com/mpngs/s/219-2199468_astronaut-spacesuit-space-planet-astronaut-tattoo-png.png";
        imageURL =  blog.getImage();
    Picasso.get().load(imageURL).into(holder.image);

        

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public TextView timestamp;
        public ImageView image;
        String userid;

        public ViewHolder(@NonNull View view, Context ctx) {
            super(view);
            context=ctx;

            title= (TextView) view.findViewById(R.id.postTitileList);
            desc= (TextView) view.findViewById(R.id.postTextList);
            image= (ImageView) view.findViewById(R.id.postImageList);
            timestamp= (TextView) view.findViewById(R.id.timestampList);
            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //next activity
                }
            });




        }
    }
}
