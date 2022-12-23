package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class inicio extends AppCompatActivity implements View.OnClickListener {
    private Button btnEvent, btnEliminar, btnPassEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        referencias();
        eventos();
    }

    private void editar(){
        Intent editar = new Intent(this, Editar.class);
        startActivity(editar);
    }

    private void eliminarUsuario(){
        Intent inicio = new Intent(this, MainActivity.class);
        startActivity(inicio);
    }
    private void crearevento(){
        Intent eventonuevo = new Intent(this, CrearEvento.class);
        startActivity(eventonuevo);
    }

    private void eventos() {
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearevento();
                Log.d("TAG_", "Click en boton aceptar");
            }
        });
        btnEliminar.setOnClickListener(view -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Eliminar")
                    .setMessage("¿Estás seguro que deseas eliminar?")
                    .setPositiveButton("CONFIRMO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            eliminarUsuario();
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
        });

        btnPassEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar();
                Log.d("TAG_", "Click en boton aceptar");
            }
        });}


    private void referencias() {
        btnEvent = findViewById(R.id.btnEvent);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnPassEdit = findViewById(R.id.btnPassEdit);
    }

    @Override
    public void onClick(View view) {

    }
}


