package com.rainbow.spanishbolomain.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.rainbow.spanishbolomain.Adapters.CategoryAdapter;
import com.rainbow.spanishbolomain.Adapters.CoursesAdapter;
import com.rainbow.spanishbolomain.Models.Course;
import com.rainbow.spanishbolomain.R;

public class CourseFragment extends Fragment {

    private CourseViewModel homeViewModel;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView courseList, categoryList;
    private LinearLayoutManager layoutManager, layoutManager1;
    private CoursesAdapter courseAdapter;
    private CategoryAdapter categoryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(CourseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_course, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        courseList = root.findViewById(R.id.top_courses_list);
        categoryList = root.findViewById(R.id.categories_list);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        courseList.setLayoutManager(layoutManager);

        layoutManager1 = new LinearLayoutManager(getActivity());
        categoryList.setLayoutManager(layoutManager1);

        Query query = db.collection("Courses");

        final FirestoreRecyclerOptions<Course> options = new FirestoreRecyclerOptions.Builder<Course>()
                .setQuery(query, Course.class)
                .build();

        courseAdapter = new CoursesAdapter(getContext(), options);
        courseList.setAdapter(courseAdapter);

        categoryAdapter = new CategoryAdapter(getContext(), options);
        categoryList.setAdapter(categoryAdapter);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        courseAdapter.startListening();
        categoryAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        courseAdapter.stopListening();
        categoryAdapter.stopListening();
    }
}