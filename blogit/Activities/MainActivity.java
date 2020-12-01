    package com.example.blogit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blogit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

    public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button loginButton;
    private Button createButton;
    private EditText emailField;
    private EditText passwdField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        loginButton = (Button)findViewById(R.id.Loginbutton);
        createButton= (Button) findViewById(R.id.Createbutton);
        emailField = (EditText) findViewById(R.id.emailET);
        passwdField = (EditText) findViewById(R.id.passwdET);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if (mUser != null) {
                    Toast.makeText(MainActivity.this, "Signed-in!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, PostListActivity.class ));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Signed-out!", Toast.LENGTH_LONG).show();
                }
            }


        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(emailField.getText().toString())
                && !TextUtils.isEmpty(passwdField.getText().toString())){
                    String email = emailField.getText().toString();
                    String pwd = passwdField.getText().toString();

                    login(email,pwd);


                }else{
                    Toast.makeText(MainActivity.this, "Failed to sign-in!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.action_signout){
            mAuth.signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void login(String email, String pwd) {
        mAuth.signInWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(MainActivity.this, "Signed-in successfully!", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(MainActivity.this, PostListActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Failed to sign-in!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }


}


