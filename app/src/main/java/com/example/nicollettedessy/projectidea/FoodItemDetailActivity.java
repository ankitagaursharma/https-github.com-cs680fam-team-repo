package com.example.nicollettedessy.projectidea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.example.nicollettedessy.projectidea.data.entities.FoodEntity;
import com.example.nicollettedessy.projectidea.services.AsyncDatabaseTask;
import com.example.nicollettedessy.projectidea.services.IAsyncDatabaseListener;

import java.util.List;

/**
 * An activity representing a single FoodItem detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link FoodItemListActivity}.
 */
public class FoodItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooditem_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        final String nbdno = getIntent().getStringExtra(FoodItemDetailFragment.ARG_ITEM_ID);

        //TODO: Check DB for ndbno

        new AsyncDatabaseTask<Void, List<FoodEntity>>(getApplicationContext(), new IAsyncDatabaseListener<List<FoodEntity>>() {
            @Override
            public void onDatabaseResponse(List<FoodEntity> response) {
                Log.d("OnDBResponse", response.toString());
            }
        }).execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*                AsyncDatabaseTask task = new AsyncDatabaseTask(getApplicationContext(), new IAsyncDatabaseListener<Void>() {
                    @Override
                    public void onDatabaseResponse(Void response) {
                        //Do stuff
                    }
                });

                task.execute(nbdno);*/

                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(FoodItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(FoodItemDetailFragment.ARG_ITEM_ID));
            FoodItemDetailFragment fragment = new FoodItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fooditem_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, FoodItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
