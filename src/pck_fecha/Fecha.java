/*
 * Proyecto: Tienda de Ropa
 * Desarrolladores:
 *   Francisco Javier Bautista Bautista
 *   Antonio Angeles Trejo
 *   Rodrigo Gael Contreras Corona
 *   Jose Pastrana Segundo
 */
package pck_fecha;

import java.io.Serializable;

public class Fecha implements Serializable {
    private static final long serialVersionUID = 1L;
    private int dia;
    private int mes;
    private int anio;

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public Fecha() {
        this(1, 2, 1900);
    }

    public void setFecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    protected boolean bisiesto() {
        return ((anio % 100 != 0 && anio % 4 == 0) || (anio % 400 == 0));
    }

    public boolean fechaCorrecta() {
        boolean diaCorrecto, mesCorrecto, anioCorrecto;
        anioCorrecto = (anio >= 1900);
        mesCorrecto = (mes >= 1 && mes <= 12);
        switch (mes) {
            case 2:
                if (bisiesto()) {
                    diaCorrecto = (dia >= 1 && dia <= 29);
                } else {
                    diaCorrecto = (dia >= 1 && dia <= 28);
                }
                break;
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                diaCorrecto = (dia >= 1 && dia <= 31);
                break;
            default:
                diaCorrecto = (dia >= 1 && dia <= 30);
        }
        return (diaCorrecto && mesCorrecto && anioCorrecto);
    }

    public String getFecha() {
        return dia + "/" + mes + "/" + anio;
    }
}
