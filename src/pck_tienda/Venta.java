/*
 * Proyecto: Tienda de Ropa
 * Desarrolladores:
 *   Francisco Javier Bautista Bautista
 *   Antonio Angeles Trejo
 *   Rodrigo Gael Contreras Corona
 *   Jose Pastrana Segundo
 */
package pck_tienda;

import java.io.Serializable;
import pck_fecha.Fecha;

public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idVenta;
    private String idArticulo;
    private String RFC;
    private Fecha fechaPedido;
    private Fecha fechaEntrega;

    public Venta() {
        this.idVenta = 0;
        this.idArticulo = "";
        this.RFC = "";
        this.fechaPedido = new Fecha();
        this.fechaEntrega = new Fecha();
    }

    public Venta(int idVenta, String idArticulo, String RFC, Fecha fechaPedido, Fecha fechaEntrega) {
        this.idVenta = idVenta;
        this.idArticulo = idArticulo;
        this.RFC = RFC;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
    }

    public Venta(int idVenta, String idArticulo, String RFC, int diaPedido, int mesPedido, int anioPedido, int diaEntrega, int mesEntrega, int anioEntrega) {
        this.idVenta = idVenta;
        this.idArticulo = idArticulo;
        this.RFC = RFC;
        this.fechaPedido = new Fecha(diaPedido, mesPedido, anioPedido);
        this.fechaEntrega = new Fecha(diaEntrega, mesEntrega, anioEntrega);
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public boolean setFechaPedido(int dia, int mes, int anio) {
        fechaPedido.setFecha(dia, mes, anio);
        return fechaPedido.fechaCorrecta();
    }

    public void setFechaPedido(Fecha fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public boolean setFechaEntrega(int dia, int mes, int anio) {
        fechaEntrega.setFecha(dia, mes, anio);
        return fechaEntrega.fechaCorrecta();
    }

    public void setFechaEntrega(Fecha fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public String getRFC() {
        return RFC;
    }

    public String getFechaPedido() {
        return fechaPedido.getFecha();
    }

    public String getFechaEntrega() {
        return fechaEntrega.getFecha();
    }

    public String getDatos() {
        return "ID venta: " + getIdVenta()
                + "\nId Artículo: " + getIdArticulo()
                + "\nRFC: " + getRFC()
                + "\nFecha de Pedido: " + getFechaPedido()
                + "\nFecha de Entrega: " + getFechaEntrega();
    }
}
