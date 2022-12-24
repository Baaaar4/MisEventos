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

public class inicio<id> extends AppCompatActivity implements View.OnClickListener {
    Button btnEvent, btnEliminar, btnEditar, btnSalir;
    TextView nombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        nombre = (TextView) findViewById(R.id.nombreUsuario);
        btnEvent = findViewById(R.id.btnEvent);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnEditar = findViewById(R.id.btnEditar);
        btnSalir.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
    }


    @Override
    protected void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditar:
                Intent a = new Intent(this, cambiar.class);
                cambiar.putExtra("Id", id);
                startActivity(a);
                break;
            case R.id.btnEliminar:
                break;
            case R.id.btnEvent:
                break;
            case R.id.btnSalir:
                break;
        }
    }
    private void editar(){

        Intent cambiar = new Intent(this, cambiar.class);
        cambiar.putExtra("Id", id);
        startActivity(cambiar);
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
        Bundle b=getIntent().getExtras();
        id= b.getInt("Id");

    }

    @Override
    public void onClick(View view) {

    }
}


