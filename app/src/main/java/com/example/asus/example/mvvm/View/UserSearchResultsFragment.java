package com.example.asus.example.mvvm.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.asus.example.mvvm.ViewModel.UserViewModel;

/**
 * Fragment for the view, to show all users which match the search query.
 */
public class UserSearchResultsFragment extends Fragment {

    UserViewModel viewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
