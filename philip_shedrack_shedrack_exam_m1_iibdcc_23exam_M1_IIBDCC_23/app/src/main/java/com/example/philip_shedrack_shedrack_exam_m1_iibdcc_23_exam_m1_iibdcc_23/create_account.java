package com.example.philip_shedrack_shedrack_exam_m1_iibdcc_23_exam_m1_iibdcc_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class create_account extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String sharedPrefs = "myprefs";
    public static final String Username = "username";
    public static final String Password = "password";

    EditText editUsername;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void createAccount(View view){
        sharedPreferences = getSharedPreferences(sharedPrefs, MODE_PRIVATE);
        editUsername = findViewById(R.id.newUsernameEditText);
        editPassword = findViewById(R.id.newPasswordEditText);
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Username, username);
        editor.putString(Password, password);
        editor.commit();
        Intent login = new Intent(view.getContext(), login.class);
        startActivity(login);
    }
}