package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class inicio extends AppCompatActivity implements View.OnClickListener{
    private Button btnEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        referencias();
        eventos();
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
           }



        private void referencias(){
            btnEvent = findViewById(R.id.btnEvent);
        }

    @Override
    public void onClick(View view) {

    }
}

