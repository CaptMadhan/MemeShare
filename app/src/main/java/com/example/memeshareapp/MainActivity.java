package com.example.memeshareapp;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageView memeImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memeImageView = (ImageView)findViewById(R.id.memeImageView);
        Objects.requireNonNull(getSupportActionBar()).hide();
    loadmeme();
    }
    private void loadmeme(){
        // Instantiate the RequestQueue.
        String url ="https://meme-api.herokuapp.com/gimme";
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        String url1 = response.getString("url");
                        Glide.with(MainActivity.this).load(url1).into(memeImageView);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(MainActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show());
        Volley.newRequestQueue(MainActivity.this).add(jsonObjectRequest);
    }

    public void shareMeme(View view) {
    }

    public void nextMeme(View view) {
        loadmeme();
    }
}