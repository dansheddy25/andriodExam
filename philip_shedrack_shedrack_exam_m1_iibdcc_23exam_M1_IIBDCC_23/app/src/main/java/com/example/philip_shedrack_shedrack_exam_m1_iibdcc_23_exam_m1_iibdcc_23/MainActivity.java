package com.example.philip_shedrack_shedrack_exam_m1_iibdcc_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coulibaly_prince_baba_daouda_exam_m1_iibdcc_23.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText ipInput;

    String IP_ADDRESS;

    LinearLayout container;

    List<String> infoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ipInput = findViewById(R.id.ipInput);
        this.container = findViewById(R.id.container);

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IP_ADDRESS = ipInput.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                String url = "https://ipinfo.io/"+IP_ADDRESS+"/geo";

                StringRequest request = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {

                                    JSONObject object = new JSONObject(response);
                                    String city = object.getString("city");
                                    String region = object.getString("region");
                                    String country = object.getString("country");

                                    infoList.add("City : " + city);
                                    infoList.add("Region : " + region);
                                    infoList.add("Country : " + country);

                                    String LatLand = object.getString("loc");

                                    infoList.forEach(text -> {
                                        TextView toAdd = new TextView(MainActivity.this);
                                        toAdd.setText(text);
                                        toAdd.setTextSize(16);
                                        toAdd.setTypeface(null, Typeface.BOLD);
                                        toAdd.setTextColor(Color.WHITE);
                                        String hexColor = "#62317e";
                                        int color = Color.parseColor(hexColor);
                                        toAdd.setBackgroundColor(color);
                                        int paddingY = 20;
                                        toAdd.setPadding(0, paddingY, 0, paddingY);
                                        int paddingValue = 20;
                                        toAdd.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
                                        toAdd.setGravity(Gravity.CENTER);
                                        int marginBottom = 20;
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT
                                        );
                                        layoutParams.setMargins(0,0,0,marginBottom);
                                        toAdd.setLayoutParams(layoutParams);
                                        container.addView(toAdd);
                                    });

                                    Button mapBtn = new Button(MainActivity.this);
                                    mapBtn.setText("Show Map");
                                    int paddingY = 30;
                                    mapBtn.setPadding(0, paddingY, 0, paddingY);
                                    container.addView(mapBtn);

                                    mapBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent Map = new Intent(view.getContext(), MapIntent.class);
                                            Map.putExtra("LatLand", LatLand);
                                            startActivity(Map);
                                        }
                                    });

                                }catch (Exception e){
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("API Error", error.toString());
                    }
                });
                requestQueue.add(request);
            }
        });
    }
}