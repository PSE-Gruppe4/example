package com.example.asus.example.mvvm.View;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.asus.example.R;
import com.example.asus.example.mvvm.Model.Entities.Category;
import com.example.asus.example.mvvm.Model.Entities.Event;
import com.example.asus.example.mvvm.Model.Entities.Group;
import com.example.asus.example.mvvm.Model.Entities.Post;
import com.example.asus.example.mvvm.Model.Entities.Subcategory;
import com.example.asus.example.mvvm.Model.Entities.User;

public class Navigation_Drawer_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation__drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button a = (Button) findViewById(R.id.newEventInCategoryButton);


        //launchPersonalFeedFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_navigation__drawer_drawer, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
         //Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return super.onOptionsItemSelected(item);
    }
            return true;
}*/



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_feed) {
            launchPersonalFeedFragment();
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_profile) {
            //launchUserProfileFragment();
            //TODO methode muss noch geändert werden so dass alle Posts of user angezeigt werden
        } else if (id == R.id.nav_groups) {
            launchGroupFragments();
        } else if (id == R.id.nav_events) {
            launchEventFragments();
        } else if (id == R.id.nav_search) {
            launchSearchFragments();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void launchPersonalFeedFragment() {

        PersonalFeedFragment personalFeedFragment = new PersonalFeedFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, personalFeedFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchUserProfileFragment(User user) {
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        userProfileFragment.setUser(user);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, userProfileFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchGroupFragments() {
        CategoriesGroupFragment categoriesGroupFragment = new CategoriesGroupFragment();
        MyGroupsFragment myGroupsFragment = new MyGroupsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, categoriesGroupFragment);
        transaction.add(R.id.content_frame, myGroupsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchEventFragments() {
        CategoriesEventFragment categoriesEventFragment = new CategoriesEventFragment();
        MyEventsFragment myEventsFragment = new MyEventsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, categoriesEventFragment);
        transaction.add(R.id.content_frame, myEventsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchSearchFragments() {
        SearchInputFragment searchInputFragment = new SearchInputFragment();
        // UserSearchResultsFragment userSearchResultsFragment = new UserSearchResultsFragment();
        // EventSearchResultsFragment eventSearchResultsFragment = new EventSearchResultsFragment();
        // GroupSearchResultsFragment groupSearchResultsFragment = new GroupSearchResultsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, searchInputFragment);
        //transaction.add(R.id.content_frame, userSearchResultsFragment);
        //transaction.add(R.id.content_frame, eventSearchResultsFragment);
        //transaction.add(R.id.content_frame, groupSearchResultsFragment);

        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void launchCategoriesEventFragment() {
        CategoriesEventFragment categoriesEventFragment = new CategoriesEventFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, categoriesEventFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void launchCategoriesGroupFragment() {
        CategoriesGroupFragment categoriesGroupFragment = new CategoriesGroupFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, categoriesGroupFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchEventFeedFragment(Event event) {
        EventFeedFragment eventFeedFragment = new EventFeedFragment();
        eventFeedFragment.setEvent(event);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, eventFeedFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchEventSearchResultsInFragment() {
        EventSearchResultsFragment eventSearchResultsFragment = new EventSearchResultsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, eventSearchResultsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchEventInCategoryFragment(Category category) {
        EventsInCategoryFragment eventInCategoryFragment = new EventsInCategoryFragment();
        eventInCategoryFragment.setCategory(category);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, eventInCategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchEventsInSubcategoryFragment(Subcategory subcategory) {
        EventsInSubcategoryFragment eventsInSubcategoryFragment = new EventsInSubcategoryFragment();
        eventsInSubcategoryFragment.setSubcategory(subcategory);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, eventsInSubcategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchGroupFeedFragment(Group group) {
        GroupFeedFragment groupFeedFragment = new GroupFeedFragment();
        groupFeedFragment.setGroup(group);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, groupFeedFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchGroupSearchResultsFragment() {
        GroupSearchResultsFragment groupSearchResultsFragment = new GroupSearchResultsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, groupSearchResultsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchGroupsInCategoryFragment(Category category) {
        GroupsInCategoryFragment groupsInCategoryFragment = new GroupsInCategoryFragment();
        groupsInCategoryFragment.setCategory(category);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, groupsInCategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchGroupsInSubcategoryFragment(Subcategory subcategory) {
        GroupsInSubcategoryFragment groupsInSubcategoryFragment = new GroupsInSubcategoryFragment();
        groupsInSubcategoryFragment.setSubcategory(subcategory);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, groupsInSubcategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchMyEventsFragment() {
        MyEventsFragment myEventsFragment = new MyEventsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, myEventsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchMyGroupsFragment() {
        MyGroupsFragment myGroupsFragment = new MyGroupsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, myGroupsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchNewEventInCategoryFragment(Category category) {
        NewEventInCategoryFragment newEventInCategoryFragment = new NewEventInCategoryFragment();
        newEventInCategoryFragment.setCategory(category);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newEventInCategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchNewEventInSubategoryFragment(Subcategory subcategory) {
        NewEventInSubcategoryFragment newEventInSubategoryFragment = new NewEventInSubcategoryFragment();
        newEventInSubategoryFragment.setSubcategory(subcategory);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newEventInSubategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchNewEventPostFragment(Event event) {
        NewEventPostFragment newEventPostFragment = new NewEventPostFragment();
        newEventPostFragment.setEvent(event);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newEventPostFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchNewGroupInCategoryFragment(Category category) {
        NewGroupInCategoryFragment newGroupInCategoryFragment = new NewGroupInCategoryFragment();
        newGroupInCategoryFragment.setCategory(category);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newGroupInCategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchNewGroupInSubcategoryFragment(Subcategory subcategory) {
        NewGroupInSubcategoryFragment newGroupInSubcategoryFragment = new NewGroupInSubcategoryFragment();
        newGroupInSubcategoryFragment.setSubcategory(subcategory);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newGroupInSubcategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchNewGroupPostFragment(Group group) {
        NewGroupPostFragment newGroupPostFragment = new NewGroupPostFragment();
        newGroupPostFragment.setGroup(group);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newGroupPostFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchNewUserPostFragment(User user) {
        NewUserPostFragment newUserPostFragment = new NewUserPostFragment();
        newUserPostFragment.setUser(user);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newUserPostFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchSearchInputFragment() {
        SearchInputFragment searchInputFragment = new SearchInputFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, searchInputFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchShowPostFragment(Post post) {
        ShowPostFragment showPostFragment = new ShowPostFragment();
        showPostFragment.setPost(post);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, showPostFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchSubcategoriesEventAndEventsInCategoryFragment(Category category) {
        SubcategoriesEventFragment subcategoriesEventFragment = new SubcategoriesEventFragment();
        EventsInCategoryFragment eventsInCategoryFragment = new EventsInCategoryFragment();
        eventsInCategoryFragment.setCategory(category);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, subcategoriesEventFragment);
        transaction.add(R.id.content_frame, eventsInCategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchSubcategoriesGroupAndGroupsInCategoryFragment(Category category) {
        SubcategoriesGroupFragment subcategoriesGroupFragment = new SubcategoriesGroupFragment();
        GroupsInCategoryFragment groupsInCategoryFragment = new GroupsInCategoryFragment();
        groupsInCategoryFragment.setCategory(category);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, subcategoriesGroupFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchSubscriptionsFragment(User user) {
        SubscriptionsFragment subscriptionsFragment = new SubscriptionsFragment();
        subscriptionsFragment.setUser(user);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, subscriptionsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void launchUserSearchResultsFragment() {
        UserSearchResultsFragment userSearchResultsFragment = new UserSearchResultsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, userSearchResultsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
