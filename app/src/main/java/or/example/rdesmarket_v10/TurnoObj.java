package or.example.rdesmarket_v10;

import android.widget.ImageView;

import java.io.Serializable;

public class TurnoObj implements Serializable {
    private String tipo;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private String telefono;
    private String oficina;

    public TurnoObj(String tipo, String nombre, String apellido, String dni, String direccion, String telefono, String oficina) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.oficina = oficina;
    }



    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getOficina() {
        return oficina;
    }


}
