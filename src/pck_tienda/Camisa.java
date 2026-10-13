/*
 * Proyecto: Tienda de Ropa
 * Desarrolladores:
 *   Francisco Javier Bautista Bautista
 *   Antonio Angeles Trejo
 *   Rodrigo Gael Contreras Corona
 *   Adair Gonzalez Martin
 */
package pck_tienda;

public class Camisa extends Articulo {
    private static final long serialVersionUID = 1L;
    private String cuello;
    private String manga;
    private boolean conBotones;
    private String ajuste;

    public Camisa() {
        super();
        this.cuello = "";
        this.manga = "";
        this.conBotones = false;
        this.ajuste = "";
    }

    public Camisa(String idArticulo, String tela, String color, int talla, float precio, String cuello, String manga, boolean conBotones, String ajuste) {
        super(idArticulo, tela, color, talla, precio);
        this.cuello = cuello;
        this.manga = manga;
        this.conBotones = conBotones;
        this.ajuste = ajuste;
    }

    public void setCuello(String cuello) {
        this.cuello = cuello;
    }

    public void setManga(String manga) {
        this.manga = manga;
    }

    public void setConBotones(boolean conBotones) {
        this.conBotones = conBotones;
    }

    public void setAjuste(String ajuste) {
        this.ajuste = ajuste;
    }

    public String getCuello() {
        return cuello;
    }

    public String getManga() {
        return manga;
    }

    public boolean isConBotones() {
        return conBotones;
    }

    public String getAjuste() {
        return ajuste;
    }

    //Sobreescritura del metodo getDatos() de la clase Articulo
    public String getDatos() {
        return super.getDatos()
                + "\nTipo de cuello: " + getCuello()
                + "\nTipo de manga: " + getManga()
                + "\nCon botones: " + (isConBotones() ? "Sí" : "No")
                + "\nAjuste: " + getAjuste();
    }
}
