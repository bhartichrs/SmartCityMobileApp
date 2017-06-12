package com.example.sravg.bargraph;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private Button opt1,opt2;
    final String api1 = "http://api.data.sanjoseca.gov/api/v2/datastreams/MONTH-UNEMP-RATE/data.ajson/?auth_key=336a180975a9127b0290273d40958fd6ae4a2892&limit=150&";
    final String api2 = "http://api.data.sanjoseca.gov/api/v2/datastreams/SAN-JOSE-JOBS-BY-SECTO/data.ajson/?auth_key=336a180975a9127b0290273d40958fd6ae4a2892&limit=50&";
    int  set_choice;
    private ArrayList<MainActivity> bList = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        opt1 = (Button) findViewById(R.id.butt1);
        opt1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Option 1 selected",
                        Toast.LENGTH_LONG).show();
                        set_choice=1;
                makeGithubSearchQuery(set_choice);
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        }));
        opt2 = (Button) findViewById(R.id.butt2);
        opt2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Option 2 selected",
                        Toast.LENGTH_LONG).show();
                        set_choice=2;
                makeGithubSearchQuery(set_choice);
                Intent  intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        }));
    }
    private void makeGithubSearchQuery(int set_choice) {
        GithubQueryTask task = new GithubQueryTask();

        if(set_choice==1){
            task.execute(api1);
            Toast.makeText(MainActivity3.this, "Inside choice", Toast.LENGTH_SHORT).show();
            Log.i(TAG, api1);
        }
        else if (set_choice==2){
            task.execute(api2);
            Log.i(TAG,api2);
        }
    }
    public class GithubQueryTask extends AsyncTask<String, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(String... urls)
        {
            URL searchUrl = createUrl(urls[0]);
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.makeHttpRequest(searchUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(String githubSearchResults) {

            Log.d(TAG, "Response from server = " + githubSearchResults);

            ArrayList<String> data=new ArrayList<>();
            bList = new ArrayList<>();
            try {
                // build up a list of Booklist objects with the corresponding data.
                JSONObject baseJsonResponse = new JSONObject(githubSearchResults);
                JSONArray earthquakeArray = baseJsonResponse.getJSONArray("results");
                for (int i = 180; i < earthquakeArray.length(); i++) {
                    String currentEarthquake = earthquakeArray.getString(i);

                    data.add(currentEarthquake);
                    Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                   // intent.putExtra("mylist", data);

                    //ArrayList<String> mylist = new ArrayList<String>();
                    //Intent intent = new Intent(ActivityName.this, Second.class);
                    intent.putStringArrayListExtra("key", data);
                    startActivity(intent);

                }
            } catch (JSONException e) {
                // If an error is thrown when executing any of the above statements in the "try" block,
                // catch the exception here, so the app doesn't crash. Print a log message
                // with the message from the exception.
                Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
                e.printStackTrace();

            }
        }


        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                //  Log.e(LOG_TAG, "Error with creating URL", exception);
                Toast.makeText(MainActivity3.this, "Inside the ASY TASK", Toast.LENGTH_SHORT).show();
                return null;
            }
            return url;
        }

    }
}
