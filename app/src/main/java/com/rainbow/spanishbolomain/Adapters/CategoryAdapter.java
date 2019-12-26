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
import androidx.constraintlayout.widget.ConstraintLayout;
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


public class CategoryAdapter extends FirestoreRecyclerAdapter<Course, CategoryAdapter.ViewHolder> {

    private Context mContext;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    List<String> colors;

    OnItemClick onItemClick;

    public CategoryAdapter(Context mContext, FirestoreRecyclerOptions<Course> options) {
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
        holder.courseCount.setText("$"+String.valueOf(model.getMoney()));

        holder.wallCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.getPosition(model.getUid(), "Home", model.getImage(), position);
            }
        });

        colors=new ArrayList<String>();
        colors.add("#d50000");
        colors.add("#c51162");
        colors.add("#aa00ff");
        colors.add("#6200ea");

        colors.add("#304ffe");
        colors.add("#0091ea");
        colors.add("#00c853");
        colors.add("#ff6d00");

        Random r = new Random();
        int i1 = r.nextInt(7- 0) + 0;

        GradientDrawable draw = new GradientDrawable();
        draw.setColor(Color.parseColor(colors.get(i1)));
        holder.colorBack.setBackground(draw);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_single_item, viewGroup, false);

        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView preview;
        private CardView wallCard;
        private TextView title, courseCount;
        private ConstraintLayout colorBack;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            preview = itemView.findViewById(R.id.preview_category_image);
            wallCard = itemView.findViewById(R.id.category_card);
            courseCount = itemView.findViewById(R.id.course_count);
            title = itemView.findViewById(R.id.category_title);
            colorBack = itemView.findViewById(R.id.category_constrained);
        }

    }
}
