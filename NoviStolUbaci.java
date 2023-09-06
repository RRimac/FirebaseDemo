package com.java.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NoviStolUbaci extends AppCompatActivity {
    Button btnInsert;
    EditText ulog;
    DatabaseReference databaseUsers;

    String id_adminSobe;
    String ime_adimnaSobe;
    String ulaganje;
    private FirebaseAuth auth;
    String broj_igraca = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novi_stol_ubaci);

        btnInsert =findViewById(R.id.btninsert);
        ulog = findViewById(R.id.edtulog);
        databaseUsers = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        id_adminSobe = auth.getCurrentUser().getUid();

        databaseUsers.child("Players").child(id_adminSobe).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(NoviStolUbaci.this, "Uspješno čitanje", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        ime_adimnaSobe = String.valueOf(dataSnapshot.child("name").getValue());
                        Log.d("Document", ime_adimnaSobe);
                    }else {
                        Toast.makeText(NoviStolUbaci.this, "User ne postoji", Toast.LENGTH_SHORT).show();
                    }
                }else {
                }
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UbaciStol();
            }
        });
    }


    private void UbaciStol() {
        ulaganje = ulog.getText().toString();         //Kad prebacim ovo u onCreate onda neradi

        Stol stol = new Stol(ime_adimnaSobe, ulaganje, broj_igraca);

        databaseUsers.child("Stol").child(auth.getCurrentUser().getUid()).setValue(stol)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){

                        }

                }
        });
        startActivity(new Intent(NoviStolUbaci.this, StoloviLista.class));
        finish();
    }

}