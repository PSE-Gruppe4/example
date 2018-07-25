package com.example.asus.example.mvvm.View;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.example.databinding.FragmentSubcategoriesEventBinding;
import com.example.asus.example.mvvm.Model.Entities.Category;
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
        ItemCategoryViewModel itemCategoryViewModel = ViewModelProviders.of(this).get(ItemCategoryViewModel.class);
        itemCategoryViewModel.init(category, this.getContext().getApplicationContext());

        //set adapter
        SubcategoryAdapter subcategoryAdapter = new SubcategoryAdapter();
        subcategoryAdapter.setSubcategoryList(itemCategoryViewModel.getSubcategories());

        //set databinding
        FragmentSubcategoriesEventBinding fragmentSubcategoriesEventBinding = FragmentSubcategoriesEventBinding.inflate(inflater, parent, false);
        fragmentSubcategoriesEventBinding.subcategoriesEventSubcategoryRV.setAdapter(subcategoryAdapter);
        fragmentSubcategoriesEventBinding.subcategoriesEventSubcategoryRV.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //TODO: observe livedata somehow

        return fragmentSubcategoriesEventBinding.getRoot();

    }


    public void launchFragment() {
        Navigation_Drawer_Activity navigation_drawer_activity = (Navigation_Drawer_Activity) getActivity();
        navigation_drawer_activity.launchSubcategoriesEventFragment();
    }


}
