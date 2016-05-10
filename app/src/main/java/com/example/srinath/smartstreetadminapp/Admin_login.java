package com.example.srinath.smartstreetadminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.srinath.smartstreetadminapp.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Admin_login extends AppCompatActivity {
EditText edit,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Firebase.setAndroidContext(this);
       edit= (EditText)findViewById(R.id.email_text);
        pass = (EditText)findViewById(R.id.password_text);
    }

    public void submit(View view){
        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {


            @Override
            public void onAuthenticated(AuthData authData) {

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {

            }
        };
        String email = edit.getText().toString();
        String password = pass.getText().toString();
        Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
        rootRef.authWithPassword(email, password, authResultHandler);
        AuthData authData = rootRef.getAuth();
Log.v("User",email);
        String str1 = "Jerry.Gao@gmail.com";
        String str2="12345";
        Log.v("Pass",password);
      /*  Toast.makeText(this,
                "auth" + authData, Toast.LENGTH_LONG).show();*/
          //  if (authData != null)
        if(email.equals(str1) && password.equals(str2))
            {

                Intent home_intent = new Intent(this, MainActivity.class);
                home_intent.putExtra("UserId", email);

                startActivity(home_intent);
                this.finish();
            }
            else
            {
                Toast.makeText(this,
                        "Please enter the correct password && Username", Toast.LENGTH_LONG).show();
            }

    }
}
