package com.example.asus.example.mvvm.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.example.mvvm.Model.Entities.Event;
import com.example.asus.example.mvvm.Model.Entities.Group;
import com.example.asus.example.mvvm.Model.Entities.Post;
import com.example.asus.example.mvvm.Model.Entities.User;
import com.example.asus.example.mvvm.Model.Repository.PostRepository;
import com.example.asus.example.mvvm.Model.Repository.UserRepository;
import com.example.asus.example.mvvm.View.Navigation_Drawer_Activity;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;


/**
 * ViewModel class for one specific user, and is responsible for preparing and managing the data for Views,
 * which need the information of this particular user, by handling the communication of the View with the
 * UserRepository class, which has the user business logic of the application.
 * Objects received from repositories will be stored as MutableLiveData Objects.
 */
public class ItemUserViewModel extends ViewModel {

    private MutableLiveData<User> chosenUser = new MutableLiveData<>();
    private MutableLiveData<User> currentUser;
    private UserRepository userRepository;
    private PostRepository postRepository;
    private Context context;


    /**
     * Text of the post which will be created. Typed in by user.
     */
    public final ObservableField<String> textValue = new ObservableField<>("");

    /**
     * loads a image into a view via an url.
     *
     * @param view                in which the image will be loaded.
     * @param currentUserImageUrl url of the image that will be loaded.
     */
    @BindingAdapter({"currentUserImageUrl"})
    public static void loadCurrentUserImage(ImageView view, String currentUserImageUrl) {
        Picasso.get().load(currentUserImageUrl)
                .placeholder(android.R.drawable.ic_menu_help)
                .error(android.R.drawable.ic_menu_camera)
                .resize(100, 100)
                .into(view);
    }

    /**
     * loads image into a view via an url.
     *
     * @param view     in which the image will be loaded.
     * @param imageUrl of the image which will be loaded.
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get().load(imageUrl)
                .placeholder(android.R.drawable.ic_menu_help)
                .error(android.R.drawable.ic_menu_camera)
                .resize(100, 100)
                .into(view);
    }

    /**
     * Initializes repository variables. Also sets the value of chosen User.
     * Retrieves currently logged in user from storage via SharedPreference and the
     * UserRepository.
     *
     * @param user    which will be handled in this class,
     * @param context of the Application.
     */
    public void init(User user, Context context) {
        this.chosenUser.setValue(user);
        userRepository = new UserRepository();
        postRepository = new PostRepository();
        this.context = context;

        SharedPreferences myPrefs = context.getSharedPreferences("CurrentUser", 0);
        currentUser = userRepository.getUserByID(myPrefs.getInt("CurrentUserId", 0));

    }

    /**
     * Initializes repository variables.
     * Retrieves currently logged in user from storage via SharedPreference and the
     * UserRepository.
     * @param context of the Application.
     */
    public void init(Context context) {
        userRepository = new UserRepository();
        postRepository = new PostRepository();
        this.context = context;

        SharedPreferences myPrefs = context.getSharedPreferences("CurrentUser", 0);
        currentUser = userRepository.getUserByID(myPrefs.getInt("CurrentUserId", 0));
    }

    /**
     * method to get name of the current User.
     * @return current User as String.
     */
    public String getCurrentUserName() {
        return currentUser.getValue().getName();
    }

    /**
     * Gets the chosen user.
     * @return observable user.
     */
    public MutableLiveData<User> getChosenUser() {
        return chosenUser;
    }

    /**
     * method to get the email of the current user.
     *
     * @return email as String.
     */
    public String getCurrentUserEmail() {
        return currentUser.getValue().getEmail();
    }

    /**
     * method to get the image url of the current user.
     * @return url of the current user image as String.
     */
    public String getCurrentUserImageUrl() {
        return currentUser.getValue().getImageUrl();
    }


    /**
     * Sets the chosen user.
     *
     * @param user to set.
     */
    public void setChosenUser(User user) {
        this.chosenUser.setValue(user);
    }

    /**
     * method to get the name of the User
     * @return the name of the User
     */
    public String getName() {
        return chosenUser.getValue().getName();
    }

    /**
     * method to get the Email of the User.
     * @return the Email of the User.
     */
    public String getEmail() {
        return chosenUser.getValue().getEmail();
    }

    /**
     * method to get the imageURl of the Users profile picture
     * @return the imageUrl
     */
    public String getImageUrl() {
        return chosenUser.getValue().getImageUrl() != null ? chosenUser.getValue().getImageUrl() : "http://www.skywardimaging.com/wp-content/uploads/2015/11/default-user-image.png";
    }

    /**
     * method to get the a List of the Users, this User subscribes to
     * @return List of Subscriptions
     */
    public List<User> getSubscriptions() {
        return this.chosenUser.getValue().getSubscriptions();
    }

    /**
     * method to get the number of users who subscribe to the chosen user
     *
     * @return size of the list of subscribers
     */
    public String getNumberOfSubscribers() {
        return Integer.toString(chosenUser.getValue().getSubscribers().size());
    }

    /**
     * method to get the number of users who the chosen user is subscribed to
     *
     * @return size of list of subscriptions of the user
     */
    public String getNumberOfSubscriptions() {
        return Integer.toString(chosenUser.getValue().getSubscriptions().size());
    }
    /**
     * method to get the List of Users, who subscribe to this User
     * @return List of Subscribers
     */
    public List<User> getSubscribers() {
        return this.chosenUser.getValue().getSubscribers();
    }

    /**
     * method to get the list of joined groups.
     * @return list of groups
     */
    public List<Group> getJoinedGroups() {
        return this.chosenUser.getValue().getJoinedGroups();

    }

    /**
     * method to get the list of event, in which the user is participating.
     * @return list of events
     */
    public List<Event> getParticipatedEvents() {
        return this.chosenUser.getValue().getParticipatedEvents();
    }

    /**
     * Gets all posts created by the current user.
     * @return
     */
    public MutableLiveData<List<Post>> getUserProfile() {
        return userRepository.getUserProfile(chosenUser.getValue());
    }


    /**
     * Checks if the current profile is the profile of the current users own profile.
     * @return boolean result
     */
    public boolean isCurrentUsersProfile() {
        return chosenUser.getValue().getId() == currentUser.getValue().getId();
    }

    /**
     * Subscribes the chosenUser by the current user.
     */
    public void subscribeUser() {
        userRepository.subscribeUser(currentUser.getValue(), chosenUser.getValue());
    }

    /**
     * Unsubscribes the chosenUser by the current user.
     */
    public void unsubscribeUser() {
        userRepository.unsubscribeUser(currentUser.getValue(), chosenUser.getValue());
    }

    /**
     * Creates a new post for the current user. This will only be available from the
     * user profile view of the current user.
     *
     */
    public void createPost(View view) {

        if (textValue.get().length() != 0) {

            Post postToCreate = new Post();
            postToCreate.setText(textValue.get());
            postToCreate.setCreator(currentUser.getValue());
            postToCreate.setDate(new Date());
            postToCreate.setOwnerUser(currentUser.getValue());
            postToCreate.setOwnedBy(currentUser.getValue().getId());
            postRepository.createPost(postToCreate);

            Navigation_Drawer_Activity navigation_drawer_activity = (Navigation_Drawer_Activity) view.getContext();
            navigation_drawer_activity.launchPersonalFeedFragment();
        } else {
            Toast.makeText(context, "Der Beitrag darf keinen leeren Text enthalten!", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * method to set the chosen user.
     * @param chosenUser which will be set.
     */
    public void setChosenUser(MutableLiveData<User> chosenUser) {
        this.chosenUser = chosenUser;
    }

    /**
     * method to get the current User.
     * @return current user as a MutableLiveData object.
     */
    public MutableLiveData<User> getCurrentUser() {
        return currentUser;
    }

    /**
     * method which sets the currently logged in user.
     * @param currentUser which will be set.
     */
    public void setCurrentUser(MutableLiveData<User> currentUser) {
        this.currentUser = currentUser;
    }


}

