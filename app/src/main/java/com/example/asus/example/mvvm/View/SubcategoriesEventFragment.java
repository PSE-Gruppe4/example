package com.example.asus.example.mvvm.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.example.databinding.FragmentSubcategoriesEventBinding;
import com.example.asus.example.mvvm.Model.Entities.Category;
import com.example.asus.example.mvvm.Model.Entities.User;
import com.example.asus.example.mvvm.View.Adapter.SubcategoryAdapter;
import com.example.asus.example.mvvm.ViewModel.ItemCategoryViewModel;

/**
 * Fragment for the view, to show all the subcategories that exist for a event.
 */
public class SubcategoriesEventFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        //get extra arguments from the initiating activity
        Category category = (Category) getArguments().getSerializable("category");

        //set viewmodel
        final ItemCategoryViewModel itemCategoryViewModel = ViewModelProviders.of(this).get(ItemCategoryViewModel.class);

        //set adapter
        final SubcategoryAdapter subcategoryAdapter = new SubcategoryAdapter();

        //set databinding
        final FragmentSubcategoriesEventBinding fragmentSubcategoriesEventBinding = FragmentSubcategoriesEventBinding.inflate(inflater, parent, false);


        itemCategoryViewModel.init(category, this.getContext().getApplicationContext());

        itemCategoryViewModel.getCurrentUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                subcategoryAdapter.setSubcategoryList(itemCategoryViewModel.getSubcategories());
                fragmentSubcategoriesEventBinding.subcategoriesEventSubcategoryRV.setAdapter(subcategoryAdapter);
            }
        });

        fragmentSubcategoriesEventBinding.subcategoriesEventSubcategoryRV.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return fragmentSubcategoriesEventBinding.getRoot();
    }


    public void launchFragment() {
        Navigation_Drawer_Activity navigation_drawer_activity = (Navigation_Drawer_Activity) getActivity();
        navigation_drawer_activity.launchSubcategoriesEventFragment();
    }


}
