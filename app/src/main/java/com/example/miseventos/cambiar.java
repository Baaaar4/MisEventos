package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cambiar extends AppCompatActivity implements View.OnClickListener {
    EditText etEditPass;
    Button btnPassEdit, btnCancelarEdit;
    int id=0;
    Usuario u;
    daousuario dao;
    Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar);
        etEditPass =(EditText)findViewById(R.id.etEditPass);
        btnPassEdit = (Button) findViewById(R.id.btnPassEdit);
        btnCancelarEdit = (Button) findViewById(R.id.btnCancelarEdit);
        btnPassEdit.setOnClickListener(this);
        btnCancelarEdit.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");
        dao=new daousuario(this);
        u=dao.getUsuarioById(id);
        etEditPass.setText(u.getPassword());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPassEdit:
                u.setPassword(etEditPass.getText().toString());
                if(!u.isNull()){
                    Toast.makeText(this,"Error: Campos vacios", Toast.LENGTH_LONG ).show();
                }else if(dao.updateUsuario(u)) {
                    Toast.makeText(this, "Cambio sin problemas", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(cambiar.this, inicio.class);
                    i2.putExtra("id",u.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "No se puede actualizar", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnCancelarEdit:
                Intent i2=new Intent(cambiar.this, inicio.class);
                startActivity(i2);

        }

    }
}