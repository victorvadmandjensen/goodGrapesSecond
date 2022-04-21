package com.example.goodgrapessecond;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<User> userList;
    public static User currentUser;
    public static ArrayList<Wine> wineList;
    public static ArrayList<UserLibrary> userLibList;
    public static UserLibrary currentLibrary;

    /** go through all wines like for Wine Collection interface component
    * create a new TextView for every iteration and set the text here to the name and ID to be the id of i
     * then add the View to the LinearLayout given
    * @param linearLayout a LinearLayout to which the generated TextViews should be added
     */
    public void browseAllWines(LinearLayout linearLayout) {
    for (int i = 0; i < wineList.size(); i++) {
        TextView newText = new TextView(this);
        newText.setText("Name:" + wineList.get(i).name + "\r\n" + "Type: " + wineList.get(i).type
                + "\r\n" + "Grape: " + wineList.get(i).grape + "\r\n" + "Year: " + String.valueOf(wineList.get(i).year) + "\r\n");
        newText.setId(i);
        linearLayout.addView(newText);

        // this should open the Wine Display interface component, and send the id to that component so when we press add to library the id can be added
        newText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newText.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        }

        );

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we create a list of users available throughout the app's fragments
        userList = User.createUsers();
        // we create a currentUser, which will be the one that is logged in
        currentUser = userList.get(1);
        // we create a our list of Wines
        wineList = Wine.createWines();
        // we set up the libraries for our userList
        userLibList = User.createLibraries(userList);
        // we get the userLibrary for our current user
        currentLibrary = userLibList.get(currentUser.userID);

        currentLibrary.addWineToLibrary(wineList.get(0));

        TextView tv = (TextView) findViewById(R.id.text);
        tv.setText(currentUser.name);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linLayout);

        browseAllWines(linearLayout);




    }
}