package com.example.asus.example.mvvm.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asus.example.databinding.FragmentNewEventInCategoryBinding;
import com.example.asus.example.mvvm.Model.Entities.Category;
import com.example.asus.example.mvvm.Model.Entities.User;
import com.example.asus.example.mvvm.ViewModel.ItemCategoryViewModel;

/**
 * Activity class for the view regarding creating a new Event in a Category.
 * Uses the ItemCategoryViewModel as its ViewModel.
 */
public class NewEventInCategoryFragment extends Fragment {

    private Category chosenCategory;


    /**
     * Method which will be called when this fragment is created.
     * Inflates the View and sets the ViewModel.
     * Observes getCurrentUser to check if there is a value or a null object.
     * @param inflater           inflates the layout on the screen
     * @param parent             of this ViewGroup
     * @param savedInstanceState state of the Application as a Bundle
     * @return the outermost View in the layout file associated with the Binding.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        final FragmentNewEventInCategoryBinding fragmentNewEventBinding = FragmentNewEventInCategoryBinding.inflate(inflater, parent, false);

        //set viewmodel
        final ItemCategoryViewModel itemCategoryViewModel = ViewModelProviders.of(this).get(ItemCategoryViewModel.class);
        itemCategoryViewModel.init(chosenCategory, this.getContext().getApplicationContext());

        itemCategoryViewModel.getCurrentUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    fragmentNewEventBinding.setItemCategoryViewModel(itemCategoryViewModel);
                } else {
                    Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT);
                }
            }
        });

        return fragmentNewEventBinding.getRoot();
    }


    /**
     * sets the category in which the User wants to create an Event in.
     * @param category chosen by User.
     */
    public void setCategory(Category category) {
        this.chosenCategory = category;
    }

}
