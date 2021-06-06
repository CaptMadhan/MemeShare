package com.example.memeshareapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ImageView memeImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memeImageView = (ImageView)findViewById(R.id.memeImageView);
        getSupportActionBar().hide();
    loadmeme();
    }
    private void loadmeme(){
        // Instantiate the RequestQueue.
        String url ="https://meme-api.herokuapp.com/gimme";
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String url = response.getString("url");
                            Glide.with(MainActivity.this).load(url).into(memeImageView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(MainActivity.this).add(jsonObjectRequest);
    }

    public void shareMeme(View view) {
    }

    public void nextMeme(View view) {
        loadmeme();
    }
}