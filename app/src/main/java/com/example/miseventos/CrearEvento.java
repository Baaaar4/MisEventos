package com.example.miseventos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



public class CrearEvento extends AppCompatActivity {
    private Spinner spnImportancia;
    private Button btnEveRegistrar, btnEveCancelar;
    private EditText etEveTitulo, etEveFecha, etEveObs, etEveLugar, etEveAviso;

    //Spinner
    /*String tipoimportancia[] = {"Alta","Media","Baja"};
    Spinner importan = (Spinner) findViewById(R.id.spnImportancia);*/

    //Eventos
    private ArrayList<evento> losEventos;
    private int indiceActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        spnImportancia = (Spinner) findViewById(R.id.spnImportancia);
        String [] opciones={"Seleccione","Alta","Media","Baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item, opciones);
        spnImportancia.setAdapter(adapter);

        /*poblar();*/
        referencias();
        eventos();

        /*obtenerDatosEvento();*/

        /*ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, tipoimportancia);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        importan.setAdapter(spinnerArrayAdapter);*/

    }

    /*private void poblar() {
        tipoImportancia = new String[] {"Selecione tipo de cliente", "Bueno", "Regular", "Malo"};

        losEventos = new ArrayList<evento>();
        losEventos.add(new evento("Bautizo", ("01/08/2021") , "Alta", "Llevar ropa formal","Los angeles","2 días antes"));
        losEventos.add(new evento();
        losEventos.add(new evento();
        losEventos.add(new evento();
    };*/

/* OBTENER LOS DATOS DEL ARRAYLIST*/
    /*private void obtenerDatosEvento() {
        String importan = getIntent().getExtras().getString("datoNombre");
        tipoimportancia[0] = "Seleccione ";
        tipoimportancia[1] = "Alta";
        tipoimportancia[2] = "Media";
        tipoimportancia[3] = "Baja";
    }
        /*if(indiceActual >= 0 && indiceActual < losEventos.size()) {*/
            /*evento eve = losEventos.get(indiceActual);*/
            /*etEveTitulo.setText(eve.getTitulo());
            etEveFecha.setText(eve.getFecha());
            etEveObs.setText(eve.getObservacion());
            etEveLugar.setText(eve.getLugar());
            etEveAviso.setText(eve.getTaviso());

            if(eve.getImportancia().equals("Alta")) spnImportancia.setSelection(1);

            if(eve.getImportancia().equals("Medio")) spnImportancia.setSelection(2);

            if(eve.getImportancia().equals("Baja")) spnImportancia.setSelection(3);

            /*tvPaginacion.setText((indiceActual + 1) + " de " + losClientes.size());*/
        /*}*/
    /*}*/

    private void limpiarPantalla(){
        etEveTitulo.setText(""); etEveFecha.setText(""); spnImportancia.setSelection(0); etEveObs.setText("");etEveLugar.setText("");etEveAviso.setText("");
        etEveTitulo.setError(null); etEveFecha.setError(null); etEveObs.setError(null);etEveLugar.setError(null);etEveAviso.setError(null);

        /*tvPaginacion.setText("" + losClientes.size());
        indiceActual = -1;*/
    }

    private void grabarEvento(){
        String titulo, fecha, importancia, observacion, lugar, aviso = "";
        /*boolean rutOK = true;*/

        titulo = etEveTitulo.getText().toString();
        fecha = etEveFecha.getText().toString();
        importancia = spnImportancia.getSelectedItem().toString();
        observacion = etEveObs.getText().toString();
        lugar = etEveLugar.getText().toString();
        aviso = etEveAviso.getText().toString();


        /*for(evento c : losEventos){
            if(c.getRut().equals(rut)) {
                rutOK = false;
                break;
            }
        }*/

        if(titulo.isEmpty() || fecha.isEmpty() || spnImportancia.getSelectedItemPosition() == 0 || observacion.isEmpty() || lugar.isEmpty() || aviso.isEmpty() ){
            etEveTitulo.setError("Tiene errores de validación");
        }else {
            evento eve = new evento(titulo, fecha, importancia, observacion, lugar, aviso);
            losEventos.add(eve);

            /*grabarBaseDatos(eve);*/

            /*tvPaginacion.setText((indiceActual + 1) + " de " + losClientes.size());*/
            Toast.makeText(CrearEvento.this, "Grabado exitosamente", Toast.LENGTH_SHORT).show();
            limpiarPantalla();

        }
    }

    public void grabarBaseDatos(evento eve){
        try{
            AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
            SQLiteDatabase miBD = adbd.getWritableDatabase();

            //Forma android
            ContentValues reg = new ContentValues();
            reg.put("titulo", eve.getTitulo());
            reg.put("fecha", eve.getFecha());
            reg.put("importancia", eve.getImportancia());
            reg.put("observacion", eve.getObservacion());
            reg.put("lugar", eve.getLugar());
            reg.put("aviso", eve.getTaviso());


            miBD.insert("eventos", null, reg);

            //Forma clásica - nombre dado por Luis Madriaga
           /* String[] parametros = {cli.getRut(), cli.getCorreo(), "34"};
            miBD.execSQL(
                    "insert into clientes (rut, correo, edad) values(?,?,?)"
            , parametros);*/


            miBD.close();
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }

        consultaSQL();

    }

    private void consultaSQL(){
        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();
        try {
            Cursor c = miBD.rawQuery("Select * from eventos order by fecha desc", null);
            if(c.moveToFirst()){
                Log.d("TAG_","Registros recuperados " + c.getCount());
                do{
                    Log.d("TAG_", "Titulo" + c.getString(0) +
                            ", fecha " + c.getString(1) +
                            ", importancia " + c.getInt(2));
                }while(c.moveToNext());
            }
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }finally {
            miBD.close();
        }
    }

    /*private void eliminarCliente(){
        if(indiceActual >= 0 && indiceActual < losClientes.size()) {
            losClientes.remove(indiceActual);
            limpiarPantalla();
        }
    }*/

    private void referencias() {
        etEveTitulo = findViewById(R.id.etEveTitulo);
        etEveFecha = findViewById(R.id.etEveFecha);
        spnImportancia = findViewById(R.id.spnImportancia);
        etEveObs = findViewById(R.id.etEveObs);
        etEveLugar = findViewById(R.id.etEveLugar);
        etEveAviso = findViewById(R.id.etEveAviso);
        btnEveCancelar = findViewById(R.id.btnEveCancelar);
        btnEveRegistrar = findViewById(R.id.btnEveRegistrar);
        /*tvPaginacion = findViewById(R.id.tvPag);
        btnAvanzar = findViewById(R.id.btnAvanzar);*/

        /*adapterImport = new ArrayAdapter(this, android.R.layout.simple_spinner_item );
        spnImportancia.setAdapter(adapterImport);*/

    }



    private void eventos() {
        btnEveRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarEvento();
            }
        });

        /*btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarCliente();
            }
        });*/

        /*AQUI HACER BOTON PARA CANCELAR RETROCEDER*/
        /*btnEveCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indiceActual = indiceActual - 1;

                if(indiceActual == -1)
                    indiceActual = losClientes.size() - 1;

                obtenerDatosCliente();
            }
        });*/

        /*btnAvanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indiceActual = indiceActual + 1;

                if(indiceActual == losClientes.size()) {
                    //btnAvanzar.setEnabled(false);
                    //btnAvanzar.setVisibility(View.INVISIBLE);
                    indiceActual = 0;
                }

                obtenerDatosCliente();
            }
        });*/


    }
}




        /*importancia=(Spinner) findViewById(R.id.spImportancia);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource
                (this, R.array.importancia, android.R.layout.simple_spinner_item);
        importancia.setAdapter(adapter);
    }*/