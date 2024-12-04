package com.example.myquizapp;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    private EditText username;
    private EditText usermail;
    private EditText userpass;
    private EditText confpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        initialize();

        // Log to check if onCreate is called
        Log.d(TAG, "onCreate: SignupActivity started");

        // Set the content view
        setContentView(R.layout.signup);

        // Log to confirm layout binding
        Log.d(TAG, "onCreate: Layout activity_signup is set");
        TextView textView = findViewById(R.id.login_link); // Replace with your TextView ID

        // Set an OnClickListener on the TextView
        textView.setOnClickListener(v -> {
            // Intent to navigate to the LoginActivity
            Intent intent = new Intent(SignupActivity.this, loginActivity.class);
            startActivity(intent);
        });

    }



    public void initialize(){
        username= findViewById(R.id.username);
        usermail= findViewById(R.id.email);
        userpass= findViewById(R.id.password);
        confpass= findViewById(R.id.confirm_password);
    }
    protected void adduser() {
        // Create a data map to store book details
        Map<String, Object> data = new HashMap<>();
        data.put("username", username.getText().toString()); // Corrected to a string
        data.put("email",usermail.getText().toString());
        data.put("password",userpass.getText().toString());


        // Check if password and confirm password match
        if (!userpass.equals(confpass)) {
            // If they don't match, show a Toast message and exit the function
            Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }
        // Retains the page number as an integer

        // Get the Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Add data to the "users" collection
        db.collection("users")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(SignupActivity.this, "user added successfully",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignupActivity.this, "Erreur user can't be added",Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error adding document", e);
                    }
                });
    }
}