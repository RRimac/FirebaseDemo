package com.java.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText userName;
    private Button register;
    private FirebaseAuth auth;
    private DatabaseReference mRootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        userName =findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_userName = userName.getText().toString();
                String txt_password = password.getText().toString();

                //Check if this values is empty
                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_userName)){                                        //Ako su polja prazna
                    Toast.makeText(RegisterActivity.this, "Neka polja su prazna", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length()<6) {
                    Toast.makeText(RegisterActivity.this, "Password pre kratak", Toast.LENGTH_SHORT).show();           //if the password is less then 6 digits
                }else {
                    registerUser(txt_email, txt_password, txt_userName);
                }


            }
        });

    }

    private void registerUser(String email, String password, String username) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this , new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", username);
                    map.put("email", email);
                    map.put("id", auth.getCurrentUser().getUid());
                    mRootRef.child("Players").child(auth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RegisterActivity.this, "Igrač ubačen", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, StoloviLista.class));
                            finish();
                        }
                    });
                    Toast.makeText(RegisterActivity.this, "Korisnik uspješno registriran", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this, "Registracija nije uspijela", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, StartActivity.class));
    }
}