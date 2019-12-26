package com.rainbow.spanishbolomain.ui.account;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rainbow.spanishbolomain.R;
import com.white.progressview.CircleProgressView;
import com.white.progressview.HorizontalProgressView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private AccountViewModel dashboardViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        dashboardViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        final TextView textView = root.findViewById(R.id.Name);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;

    }
}
