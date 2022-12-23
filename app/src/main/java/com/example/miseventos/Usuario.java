package com.example.miseventos;

public class Usuario {
    int Id;
    String Nombre, Apellidos, Usuarios, Password;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String usuarios, String password) {
        Nombre = nombre;
        Apellidos = apellidos;
        Usuarios = usuarios;
        Password = password;
    }

    /* Aqui validamos que los campos no estén vacíos*/
    public boolean isNull() {
        if (Nombre.equals("") && Apellidos.equals("") && Usuarios.equals("") && Password.equals("")) {
            return false;
        }else{
            return true;
        }
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Usuarios='" + Usuarios + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getUsuarios() {
        return Usuarios;
    }

    public void setUsuarios(String usuarios) {
        Usuarios = usuarios;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
