/*
 * Proyecto: Tienda de Ropa
 * Desarrolladores:
 *   Francisco Javier Bautista Bautista
 *   Antonio Angeles Trejo
 *   Rodrigo Gael Contreras Corona
 *   Adair Gonzalez Martin
 */
package pck_tienda;

import java.io.Serializable;
import pck_fecha.Fecha;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String RFC;
    private String nombre;
    public String direccion;
    private String telefono;
    private String tipo;
    private Fecha fechaNacimiento;
    private String correoElectronico;

    public Cliente() {
        this.RFC = "";
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.tipo = "";
        this.fechaNacimiento = new Fecha();
        this.correoElectronico = "";
    }

    public Cliente(String RFC, String nombre, String direccion, String telefono, String tipo, Fecha fechaNacimiento, String correoElectronico) {
        this.RFC = RFC;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }

    public Cliente(String RFC, String nombre, String direccion, String telefono, String tipo, int dia, int mes, int anio, String correoElectronico) {
        this.RFC = RFC;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.fechaNacimiento = new Fecha(dia, mes, anio);
        this.correoElectronico = correoElectronico;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean setFechaNacimiento(int dia, int mes, int anio) {
        fechaNacimiento.setFecha(dia, mes, anio);
        return fechaNacimiento.fechaCorrecta();
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getRFC() {
        return RFC;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.getFecha();
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getDatos() {
        return "RFC: " + getRFC()
                + "\nNombre: " + getNombre()
                + "\nDirección: " + getDireccion()
                + "\nTeléfono: " + getTelefono()
                + "\nTipo: " + getTipo()
                + "\nFecha de nacimiento: " + getFechaNacimiento()
                + "\nCorreo electrónico: " + getCorreoElectronico();
    }
}
