package com.cst3104.jsonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*

 This application decodes a JSON file
 and displays the results on a textview.

 The application reads a restaurants JSON file
 and builds a list of restaurants.

 We then display the list of the names of the restaurants
 in the TextView.

 A button is available to clear the TextView and do it again.

 The TODOs below provide step by step instructions
 to complete the implementation.


 Challenge 1:
 Convert the TextView into a ListView and display
 the result accordingly.


 Challenge 2:
 Convert the clear button into a menu item on the Options Menu.
 You can implement it as an Action item with an icon

*/

public class MainActivity extends AppCompatActivity {

    static private final String TAG = "MainActivity";

    // Declare a List of restaurants
    ArrayList<Restaurant> listOfRestaurants;

    // TextView where we display the result on the screen
    private RecyclerView descriptionTv;
    private MyAdapter theAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the view
        descriptionTv = findViewById(R.id.recyclerView);


        // When we click on btn_read_json, we call the readData
        // and decode the results
        Button btnJson = findViewById(R.id.btn_read_json);

        // When the button is presssed, call readData
        // With the results, populate the textview
        // Put on the TextView the names of the restaurants.
        btnJson.setOnClickListener(v -> {
            // Read the file
            listOfRestaurants = readData("restaurants.json");
            theAdapter = new MyAdapter();
            descriptionTv.setAdapter(theAdapter);
           descriptionTv.setLayoutManager(new LinearLayoutManager(this));
           theAdapter.restaurants = listOfRestaurants;


        });

    }


    public void clear(MenuItem item) {
        descriptionTv.swapAdapter(null, true);

    }

    // Deserialize a list of states from a file in JSON format
    public ArrayList<Restaurant> readData(String fileName){

        final ArrayList<Restaurant> mylist = new ArrayList<>();

        try {
            // load the data in an ArrayList
            String jsonString     = MyFileReader.readJson(this, fileName);
            JSONObject json       = new JSONObject(jsonString);
            JSONArray items = json.getJSONArray("restaurants");

            // Loop through the list in the json array
            for (int i = 0; i < items.length(); i++){
                JSONObject restaurant = items.getJSONObject(i);

                String name = restaurant.getString("name");
                String description = restaurant.getString("description");
                String image = restaurant.getString("image");

                Restaurant newrestaurant = new Restaurant(name, description, image);

                mylist.add(newrestaurant);
                //TODO (3): Extract the name and description

                //TODO (4) : create a Restaurant object

                //TODO (5): Add the restaurant object to the lisat

            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
        }
        return mylist;
    }

    public void addNewJSONObject(Restaurant newRest, String fileName) {
        try {
            // load the data in an ArrayList
            String jsonString = MyFileReader.readJson(this, fileName);
            JSONObject json = new JSONObject(jsonString);
            JSONArray items = json.getJSONArray("restaurants");

            JSONObject newRestaurant = new JSONObject();

            newRestaurant.put("name", newRest.getName());
            newRestaurant.put("description", newRest.getDescription());
            newRestaurant.put("image", newRest.getImage());

            items.put(newRestaurant);  // put the data in array

            JSONObject newJsonObject = new JSONObject();
            newJsonObject.put("restaurants", items);
            Log.e("MYACTION", newRestaurant.toString());
            Log.e("MYACTION", newJsonObject.toString());
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(newJsonObject));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

        private void addNewRest() {
        AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this );
        builder.setTitle("New restaurant");
        builder.setMessage("Please insert the name, the description and the image name for the new restaurant:");
        final EditText restName = new EditText(this);
        restName.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(restName);
        builder.setNegativeButton("Cancel", (dialog, click1)->{ });
        builder.setPositiveButton("Create", (dialog, click2)->{
            String nameOfRest = restName.getText().toString();
            String descOfRest = "New vegeterian restaurant at the heart of the city";
            String imageOfRest = "veg";
            Restaurant newRest = new Restaurant(nameOfRest, descOfRest,imageOfRest);

            listOfRestaurants.add(newRest);
            addNewJSONObject(newRest, "restaurants.json");
        });
        builder.create();
        builder.show();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.clear:
                clear(item);
                break;

            case R.id.add:
                if (theAdapter == null) {
                    break;
                }
                else {
                    addNewRest();
                }
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}