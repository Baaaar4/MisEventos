package com.example.miseventos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daousuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd="BDUsuarios";
    String tabla="create table if not exists usuario(id integer primary key autoincrement, usuario text, pass text, nombre text, ap text)";
        /*esa leyenda hace que no se cree la tabla si ya esta creada*/
    public daousuario(Context c){
        this.c=c;
        /*Comando nos sirve para abrir o crear la base de datos*/
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        /*Comando nos sirve para crear una tabla*/
        sql.execSQL(tabla);
        u=new Usuario();
    }
 /*Insertando datos en la tabla usuario */
    public boolean insertUsuario(Usuario u)
    {
        if(buscar(u.getUsuarios())==0) {
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUsuarios());
            cv.put("pass", u.getPassword());
            cv.put("nombre", u.getNombre());
            cv.put("ap", u.getApellidos());
            return (sql.insert("usuario", null, cv) > 0);
        }else{
            return false;

        }
    }
    /*Aquí vamos a comprobar que si no hay 2 usuarios iguales, si regresa 0 es porque no hay un usuario igual*/
    public int buscar(String u){
        int x=0;
        lista=selectUsuario();
        for (Usuario us:lista ){
            if(us.getUsuarios().equals(u)){
                x++;
            }
        }
        return x;
    }
 /*Nos retorna todos los usuarios que hay en la BBD */
    public ArrayList<Usuario> selectUsuario() {
        ArrayList<Usuario> lista=new ArrayList<Usuario>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                Usuario u=new Usuario();
                u.setId(cr.getInt( 0));
                u.setUsuarios(cr.getString(  1));
                u.setPassword(cr.getString(  2));
                u.setNombre(cr.getString(  3));
                u.setApellidos(cr.getString(  4));
                lista.add(u);
            }while(cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p){
        int a=0;
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while(cr.moveToNext());
        }
        return a;
    }

    public Usuario getUsuario(String u, String p){
        lista=selectUsuario();
        for (Usuario us:lista) {
            if (us.getUsuarios().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }
    public Usuario getUsuarioById(int id){
        lista=selectUsuario();
        for (Usuario us:lista) {
            if (us.getId()==id){
                return us;
            }
        }
        return null;
    }


}
