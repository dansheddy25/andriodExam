package com.example.philip_shedrack_shedrack_exam_m1_iibdcc_23_exam_m1_iibdcc_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    SharedPreferences sharedPreferences;
   // public static final String sharedPrefs = "myprefs";

    EditText editUsername;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void connect(View view){
        sharedPreferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        String usernameS = sharedPreferences.getString("username", "");
        String passwordS = sharedPreferences.getString("password", "");

        editUsername = findViewById(R.id.usernameEditText);
        editPassword = findViewById(R.id.passwordEditText);

        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        if (usernameS.equals(username) && passwordS.equals(password)){
            Intent main = new Intent(view.getContext(), MainActivity.class);
            startActivity(main);
        }
        else {
            Toast.makeText(this, "Username ou Mot de passe Incorrect", Toast.LENGTH_LONG).show();
        }
    }

    public void goToCreateAccount(View view){
        Intent createAccount = new Intent(view.getContext(), create_account.class);
        startActivity(createAccount);
    }
}