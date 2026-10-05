/*
 * Proyecto: Tienda de Ropa
 * Desarrolladores:
 *   Francisco Javier Bautista Bautista
 *   Antonio Angeles Trejo
 *   Rodrigo Gael Contreras Corona
 *   Jose Pastrana Segundo
 */
package pck_tienda;

public class Pantalon extends Articulo {
    private static final long serialVersionUID = 1L;
    private String genero;
    private String largo;
    private String corte;

    public Pantalon() {
        super();
        this.genero = "";
        this.largo = "";
        this.corte = "";
    }

    public Pantalon(String idArticulo, String tela, String color, int talla, float precio, String genero, String largo, String corte) {
        super(idArticulo, tela, color, talla, precio);
        this.genero = genero;
        this.largo = largo;
        this.corte = corte;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }

    public String getGenero() {
        return genero;
    }

    public String getLargo() {
        return largo;
    }

    public String getCorte() {
        return corte;
    }

    //Sobreescritura del metodo getDatos() de la clase Articulo
    public String getDatos() {
        return super.getDatos()
                + "\nGénero: " + getGenero()
                + "\nLargo: " + getLargo()
                + "\nCorte: " + getCorte();
    }
}
