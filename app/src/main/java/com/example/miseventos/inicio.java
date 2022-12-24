package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class inicio<id> extends AppCompatActivity implements View.OnClickListener {
    Button btnEvent, btnEliminar, btnEditar, btnSalir;
    TextView nombre;
    int id=0;
    Usuario u;
    daousuario dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        nombre = (TextView) findViewById(R.id.tvNombreUsuario);
        btnEvent = findViewById(R.id.btnEvent);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnEditar = findViewById(R.id.btnEditar);
        btnSalir = findViewById(R.id.btnSalir);
        btnEditar.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");
        dao=new daousuario(this);
        u=dao.getUsuarioById(id);
        nombre.setText(u.getNombre()+" "+u.getApellidos());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditar:
                Intent a = new Intent(inicio.this, cambiar.class);
                a.putExtra("Id", id);
                startActivity(a);
                break;
            case R.id.btnEliminar:
                AlertDialog.Builder b=new AlertDialog.Builder(this);
                b.setTitle("Eliminar");
                b.setMessage("¿Estás seguro que deseas eliminar?");
                b.setPositiveButton("CONFIRMO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (dao.eliminarUsuario(id)) {
                                        Toast.makeText(inicio.this, "Cambio sin problemas", Toast.LENGTH_LONG).show();
                                        Intent a = new Intent(inicio.this, MainActivity.class);
                                        startActivity(a);
                                        finish();
                                    }else{
                                        Toast.makeText(inicio.this, "Error al eliminar", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            b.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            b.show();
                break;


            case R.id.btnEvent:
                Intent c = new Intent(inicio.this, CrearEvento.class);
                startActivity(c);
                break;
            case R.id.btnSalir:
                Intent i2 = new Intent(inicio.this, MainActivity.class);
                startActivity(i2);
                finish();
                break;
        }
    }
}




