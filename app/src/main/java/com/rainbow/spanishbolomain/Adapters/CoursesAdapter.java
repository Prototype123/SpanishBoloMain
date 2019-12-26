package com.rainbow.spanishbolomain.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rainbow.spanishbolomain.Models.Course;
import com.rainbow.spanishbolomain.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CoursesAdapter extends FirestoreRecyclerAdapter<Course, CoursesAdapter.ViewHolder> {

    private Context mContext;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    List<String> colors;

    OnItemClick onItemClick;

    public CoursesAdapter(Context mContext, FirestoreRecyclerOptions<Course> options) {
        super(options);
        this.mContext = mContext;
        this.notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {

        void getPosition(String userId, String cat, String ImageUrl, int pos);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, final int position, @NonNull final Course model) {

        Glide.with(mContext).load(model.getThumb())
                .thumbnail(0.1f)
                .into(holder.preview);
        holder.title.setText(model.getTitle());
        holder.price.setText("$"+String.valueOf(model.getMoney()));

        holder.wallCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.getPosition(model.getUid(), "Home", model.getImage(), position);
            }
        });

        colors=new ArrayList<String>();
        colors.add("#b0bec5");
        colors.add("#90a4ae");
        colors.add("#78909c");
        colors.add("#a7c0cd");
        colors.add("#607d8b");
        colors.add("#8eacbb");
        colors.add("#b0bec5");
        colors.add("#90a4ae");
        colors.add("#78909c");
        colors.add("#a7c0cd");
        colors.add("#607d8b");
        colors.add("#8eacbb");


        Random r = new Random();
        int i1 = r.nextInt(11- 0) + 0;

        GradientDrawable draw = new GradientDrawable();
        draw.setColor(Color.parseColor(colors.get(i1)));
        holder.preview.setBackground(draw);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_single_item, viewGroup, false);

        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView preview;
        private CardView wallCard;
        private TextView title, price;
        private ImageView first, second, third, fourth, fifth;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            preview = itemView.findViewById(R.id.preview_course_image);
            wallCard = itemView.findViewById(R.id.course_card);
            title = itemView.findViewById(R.id.course_title);
            price = itemView.findViewById(R.id.course_price);
            first = itemView.findViewById(R.id.first_star);
            second = itemView.findViewById(R.id.second_star);
            third = itemView.findViewById(R.id.third_star);
            fourth = itemView.findViewById(R.id.fourth_star);
            fifth = itemView.findViewById(R.id.fifth_star);

        }

    }
}
