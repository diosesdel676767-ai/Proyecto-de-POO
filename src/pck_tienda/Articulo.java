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

public class Articulo implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String idArticulo;
    protected String tela;
    protected String color;
    protected int talla;
    protected float precio;

    public Articulo() {
        this.idArticulo = "";
        this.tela = "";
        this.color = "";
        this.talla = 0;
        this.precio = 0.0f;
    }

    public Articulo(String idArticulo, String tela, String color, int talla, float precio) {
        this.idArticulo = idArticulo;
        this.tela = tela;
        this.color = color;
        this.talla = talla;
        this.precio = precio;
    }

    public void setId(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public void setTela(String tela) {
        this.tela = tela;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getId() {
        return idArticulo;
    }

    public String getTela() {
        return tela;
    }

    public String getColor() {
        return color;
    }

    public int getTalla() {
        return talla;
    }

    public float getPrecio() {
        return precio;
    }

    public String getDatos() {
        return "Id Artículo: " + getId()
                + "\nTipo tela: " + getTela()
                + "\nColor: " + getColor()
                + "\nTalla: " + getTalla()
                + "\nPrecio: $ " + getPrecio();
    }
}
