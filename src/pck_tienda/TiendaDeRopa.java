/*
 * Proyecto: Tienda de Ropa
 * Desarrolladores:
 *   Francisco Javier Bautista Bautista
 *   Antonio Angeles Trejo
 *   Rodrigo Gael Contreras Corona
 *   Jose Pastrana Segundo
 */
package pck_tienda;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import pck_fecha.Fecha;

public class TiendaDeRopa {

    public static void main(String[] args) {

        //Coleccion heterogenea: guarda camisas y pantalones
        ArrayList<Articulo> articulos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();

        String idArticulo, tela, color, cuello, manga, ajuste, genero, largo, corte;
        String rfc, nombre, direccion, telefono, tipo, correoElectronico;
        String idBuscado, listaCamisas, listaPantalones, listaClientes, listaVentas, listaRegistrados, detalleVenta;
        int op, p, posArticulo, posCliente, talla, idVenta, idVentaBuscada, respuesta, confirmacion, ventasRelacionadas;
        int diaNacimiento, mesNacimiento, anioNacimiento;
        int diaPedido, mesPedido, anioPedido, diaEntrega, mesEntrega, anioEntrega;
        float precio;
        boolean cadenaCorrecta, fechaValida, conBotones, entregaPosterior, hayCamisas, hayPantalones;
        Fecha fechaNacimiento, fechaPedido, fechaEntrega;

        String menu = "------ TIENDA DE ROPA ------\n"
                + "1) Alta de una camisa\n"
                + "2) Alta de un pantalón\n"
                + "3) Alta de un cliente\n"
                + "4) Alta de una venta\n"
                + "5) Listar camisas\n"
                + "6) Listar pantalones\n"
                + "7) Listar clientes\n"
                + "8) Listar ventas\n"
                + "9) Ver detalle de una camisa\n"
                + "10) Ver detalle de un pantalón\n"
                + "11) Ver detalle de un cliente\n"
                + "12) Ver detalle de una venta\n"
                + "13) Eliminar una camisa\n"
                + "14) Eliminar un pantalón\n"
                + "15) Eliminar un cliente\n"
                + "16) Eliminar una venta\n"
                + "17) Salir\n"
                + "Elige una opción";

        //LECTURA DEL ARCHIVO DE ARTICULOS
        FileInputStream fin = null;
        try {
            fin = new FileInputStream("Articulos.txt");
            ObjectInputStream entrada = new ObjectInputStream(fin);
            articulos = (ArrayList) entrada.readObject();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada al leer Articulos.txt", "Error", 2);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El archivo Articulos.txt no se encuentra.\nSe creará al salir del programa.", "Aviso", 1);
        } catch (EOFException ex) {
            JOptionPane.showMessageDialog(null, "Error de fin de archivo en Articulos.txt", "Error", 2);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de E/S al leer Articulos.txt", "Error", 2);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo Articulos.txt", "Error", 2);
                }
            }
        }

        //LECTURA DEL ARCHIVO DE CLIENTES
        fin = null;
        try {
            fin = new FileInputStream("Clientes.txt");
            ObjectInputStream entrada = new ObjectInputStream(fin);
            clientes = (ArrayList) entrada.readObject();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada al leer Clientes.txt", "Error", 2);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El archivo Clientes.txt no se encuentra.\nSe creará al salir del programa.", "Aviso", 1);
        } catch (EOFException ex) {
            JOptionPane.showMessageDialog(null, "Error de fin de archivo en Clientes.txt", "Error", 2);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de E/S al leer Clientes.txt", "Error", 2);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo Clientes.txt", "Error", 2);
                }
            }
        }

        //LECTURA DEL ARCHIVO DE VENTAS
        fin = null;
        try {
            fin = new FileInputStream("Ventas.txt");
            ObjectInputStream entrada = new ObjectInputStream(fin);
            ventas = (ArrayList) entrada.readObject();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada al leer Ventas.txt", "Error", 2);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El archivo Ventas.txt no se encuentra.\nSe creará al salir del programa.", "Aviso", 1);
        } catch (EOFException ex) {
            JOptionPane.showMessageDialog(null, "Error de fin de archivo en Ventas.txt", "Error", 2);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de E/S al leer Ventas.txt", "Error", 2);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo Ventas.txt", "Error", 2);
                }
            }
        }

        do {
            do {
                op = 0;
                op = getEntero(menu, "Opciones");

                if (op < 1 || op > 17) {
                    JOptionPane.showMessageDialog(null, "La opción es incorrecta.\nDebe ser entre 1 y 17", "Error de entrada", 2);
                }

            } while (op < 1 || op > 17);

            switch (op) {

                case 1:
                    //ID DEL ARTICULO
                    do {
                        idArticulo = "";
                        cadenaCorrecta = true;
                        try {
                            idArticulo = JOptionPane.showInputDialog(null, "Id. Artículo:", "Alta de una camisa", 3);

                            if (idArticulo.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El Id. Artículo es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!idArticulo.matches("^[A-Za-z0-9]+$")) {
                                JOptionPane.showMessageDialog(null, "El Id. Artículo solo puede tener letras y números,\nsin espacios ni caracteres especiales", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else {
                                //Se busca en toda la coleccion para que no se repita el id con una camisa ni con un pantalon
                                p = buscaElemento(articulos, idArticulo, 1);

                                if (p != -1) {
                                    JOptionPane.showMessageDialog(null, "El Id. Artículo ya está registrado", "Entrada no válida", 2);
                                    cadenaCorrecta = false;
                                }
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl Id. Artículo es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //TELA
                    do {
                        tela = "";
                        cadenaCorrecta = true;
                        try {
                            tela = JOptionPane.showInputDialog(null, "Tipo de tela:", "Alta de una camisa", 3);

                            if (tela.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El tipo de tela es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!tela.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl tipo de tela es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //COLOR
                    do {
                        color = "";
                        cadenaCorrecta = true;
                        try {
                            color = JOptionPane.showInputDialog(null, "Color:", "Alta de una camisa", 3);

                            if (color.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El color es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!color.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl color es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //TALLA
                    do {
                        talla = 0;
                        try {
                            talla = Integer.parseInt(JOptionPane.showInputDialog(null, "Talla:", "Alta de una camisa", 3));

                            if (talla < 2 || talla > 50) {
                                JOptionPane.showMessageDialog(null, "La talla es incorrecta.\nDebe ser de 2 a 50.", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Debes escribir un número entero.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                        }
                    } while (talla < 2 || talla > 50);

                    //PRECIO
                    do {
                        precio = 0.0f;
                        try {
                            precio = Float.parseFloat(JOptionPane.showInputDialog(null, "Precio:", "Alta de una camisa", 3));

                            if (precio <= 0 || precio > 10000) {
                                JOptionPane.showMessageDialog(null, "El precio es incorrecto.\nDebe ser mayor a 0 y máximo 10000.", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Debes escribir un número.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl precio es un dato requerido", "Entrada no válida", 2);
                        }
                    } while (precio <= 0 || precio > 10000);

                    //CUELLO
                    do {
                        cuello = "";
                        cadenaCorrecta = true;
                        try {
                            cuello = JOptionPane.showInputDialog(null, "Tipo de cuello:", "Alta de una camisa", 3);

                            if (cuello.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El tipo de cuello es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!cuello.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl tipo de cuello es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //MANGA
                    do {
                        manga = "";
                        cadenaCorrecta = true;
                        try {
                            manga = JOptionPane.showInputDialog(null, "Tipo de manga:", "Alta de una camisa", 3);

                            if (manga.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El tipo de manga es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!manga.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl tipo de manga es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //CON BOTONES
                    do {
                        respuesta = JOptionPane.showConfirmDialog(null, "¿La camisa tiene botones?", "Alta de una camisa", YES_NO_OPTION, 3);

                        if (respuesta != JOptionPane.YES_OPTION && respuesta != JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "Debes elegir Sí o No", "Entrada no válida", 2);
                        }
                    } while (respuesta != JOptionPane.YES_OPTION && respuesta != JOptionPane.NO_OPTION);

                    conBotones = (respuesta == JOptionPane.YES_OPTION);

                    //AJUSTE
                    do {
                        ajuste = "";
                        cadenaCorrecta = true;
                        try {
                            ajuste = JOptionPane.showInputDialog(null, "Ajuste:", "Alta de una camisa", 3);

                            if (ajuste.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El ajuste es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!ajuste.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl ajuste es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    articulos.add(new Camisa(idArticulo, tela, color, talla, precio, cuello, manga, conBotones, ajuste));
                    JOptionPane.showMessageDialog(null, "La camisa se registró correctamente", "Alta de una camisa", 1);
                    break;

                case 2:
                    //ID DEL ARTICULO
                    do {
                        idArticulo = "";
                        cadenaCorrecta = true;
                        try {
                            idArticulo = JOptionPane.showInputDialog(null, "Id. Artículo:", "Alta de un pantalón", 3);

                            if (idArticulo.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El Id. Artículo es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!idArticulo.matches("^[A-Za-z0-9]+$")) {
                                JOptionPane.showMessageDialog(null, "El Id. Artículo solo puede tener letras y números,\nsin espacios ni caracteres especiales", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else {
                                //Se busca en toda la coleccion para que no se repita el id con una camisa ni con un pantalon
                                p = buscaElemento(articulos, idArticulo, 2);

                                if (p != -1) {
                                    JOptionPane.showMessageDialog(null, "El Id. Artículo ya está registrado", "Entrada no válida", 2);
                                    cadenaCorrecta = false;
                                }
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl Id. Artículo es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //TELA
                    do {
                        tela = "";
                        cadenaCorrecta = true;
                        try {
                            tela = JOptionPane.showInputDialog(null, "Tipo de tela:", "Alta de un pantalón", 3);

                            if (tela.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El tipo de tela es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!tela.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl tipo de tela es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //COLOR
                    do {
                        color = "";
                        cadenaCorrecta = true;
                        try {
                            color = JOptionPane.showInputDialog(null, "Color:", "Alta de un pantalón", 3);

                            if (color.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El color es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!color.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl color es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //TALLA
                    do {
                        talla = 0;
                        try {
                            talla = Integer.parseInt(JOptionPane.showInputDialog(null, "Talla:", "Alta de un pantalón", 3));

                            if (talla < 2 || talla > 50) {
                                JOptionPane.showMessageDialog(null, "La talla es incorrecta.\nDebe ser de 2 a 50.", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Debes escribir un número entero.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                        }
                    } while (talla < 2 || talla > 50);

                    //PRECIO
                    do {
                        precio = 0.0f;
                        try {
                            precio = Float.parseFloat(JOptionPane.showInputDialog(null, "Precio:", "Alta de un pantalón", 3));

                            if (precio <= 0 || precio > 10000) {
                                JOptionPane.showMessageDialog(null, "El precio es incorrecto.\nDebe ser mayor a 0 y máximo 10000.", "Error de entrada", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Debes escribir un número.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl precio es un dato requerido", "Entrada no válida", 2);
                        }
                    } while (precio <= 0 || precio > 10000);

                    //GENERO
                    do {
                        genero = "";
                        cadenaCorrecta = true;
                        try {
                            genero = JOptionPane.showInputDialog(null, "Género (ej. Hombre, Mujer, Unisex):", "Alta de un pantalón", 3);

                            if (genero.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El género es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!genero.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl género es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //LARGO
                    do {
                        largo = "";
                        cadenaCorrecta = true;
                        try {
                            largo = JOptionPane.showInputDialog(null, "Largo:", "Alta de un pantalón", 3);

                            if (largo.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El largo es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!largo.matches("^[A-Za-z0-9áéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-z0-9áéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras, números y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl largo es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //CORTE
                    do {
                        corte = "";
                        cadenaCorrecta = true;
                        try {
                            corte = JOptionPane.showInputDialog(null, "Corte:", "Alta de un pantalón", 3);

                            if (corte.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El corte es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!corte.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl corte es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    articulos.add(new Pantalon(idArticulo, tela, color, talla, precio, genero, largo, corte));
                    JOptionPane.showMessageDialog(null, "El pantalón se registró correctamente", "Alta de un pantalón", 1);
                    break;

                case 3:
                    //RFC
                    do {
                        rfc = "";
                        cadenaCorrecta = true;
                        try {
                            rfc = JOptionPane.showInputDialog(null, "RFC:", "Alta de un cliente", 3);

                            if (rfc.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El RFC es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!rfc.matches("^[A-ZÑ]{4}[0-9]{6}[A-Z0-9]{3}$")) {
                                JOptionPane.showMessageDialog(null, "El RFC no tiene el formato correcto.\nDebe tener 13 caracteres en mayúsculas:\n4 letras, 6 números y 3 letras o números (ej. LUMR950923F58)", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else {
                                p = buscaCliente(clientes, rfc);

                                if (p != -1) {
                                    JOptionPane.showMessageDialog(null, "El RFC ya está registrado", "Entrada no válida", 2);
                                    cadenaCorrecta = false;
                                }
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl RFC es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //NOMBRE
                    do {
                        nombre = "";
                        cadenaCorrecta = true;
                        try {
                            nombre = JOptionPane.showInputDialog(null, "Nombre completo:", "Alta de un cliente", 3);

                            if (nombre.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El nombre es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!nombre.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl nombre es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //DIRECCION
                    do {
                        direccion = "";
                        cadenaCorrecta = true;
                        try {
                            direccion = JOptionPane.showInputDialog(null, "Dirección:", "Alta de un cliente", 3);

                            if (direccion.isBlank()) {
                                JOptionPane.showMessageDialog(null, "La dirección es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!direccion.matches("^[A-Za-z0-9áéíóúÁÉÍÓÚüÜñÑ.,#-]+(\\s[A-Za-z0-9áéíóúÁÉÍÓÚüÜñÑ.,#-]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras, números, un espacio entre palabras\ny los signos  .  ,  #  -", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nLa dirección es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //TELEFONO
                    do {
                        telefono = "";
                        cadenaCorrecta = true;
                        try {
                            telefono = JOptionPane.showInputDialog(null, "Teléfono (10 dígitos):", "Alta de un cliente", 3);

                            if (telefono.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El teléfono es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!telefono.matches("^[0-9]{10}$")) {
                                JOptionPane.showMessageDialog(null, "El teléfono debe tener exactamente 10 dígitos,\nsin espacios ni otros caracteres", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl teléfono es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //TIPO
                    do {
                        tipo = "";
                        cadenaCorrecta = true;
                        try {
                            tipo = JOptionPane.showInputDialog(null, "Tipo de cliente (ej. Premium, Regular):", "Alta de un cliente", 3);

                            if (tipo.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El tipo de cliente es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!tipo.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+(\\s[A-Za-záéíóúÁÉÍÓÚüÜñÑ]+)*$")) {
                                JOptionPane.showMessageDialog(null, "Solo se permiten letras y un espacio entre palabras", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl tipo de cliente es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    //FECHA DE NACIMIENTO (el cliente debe ser mayor de edad: 2026 - 18 = 2008)
                    fechaNacimiento = new Fecha();
                    do {
                        fechaValida = false;
                        try {
                            diaNacimiento = Integer.parseInt(JOptionPane.showInputDialog(null, "Día de nacimiento:", "Alta de un cliente", 3));
                            mesNacimiento = Integer.parseInt(JOptionPane.showInputDialog(null, "Mes de nacimiento:", "Alta de un cliente", 3));
                            anioNacimiento = Integer.parseInt(JOptionPane.showInputDialog(null, "Año de nacimiento:", "Alta de un cliente", 3));

                            if (anioNacimiento < 1900 || anioNacimiento > 2008) {
                                fechaValida = false;
                            } else {
                                fechaNacimiento.setFecha(diaNacimiento, mesNacimiento, anioNacimiento);
                                fechaValida = fechaNacimiento.fechaCorrecta();
                            }

                            if (!fechaValida) {
                                JOptionPane.showMessageDialog(null, "La fecha no es válida. Revisa el día, mes y año.\nEl año debe ser de 1900 a 2008 (el cliente debe ser mayor de edad)", "Fecha incorrecta", 2);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Debes escribir un número entero.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                        }
                    } while (!fechaValida);

                    //CORREO ELECTRONICO
                    do {
                        correoElectronico = "";
                        cadenaCorrecta = true;
                        try {
                            correoElectronico = JOptionPane.showInputDialog(null, "Correo electrónico:", "Alta de un cliente", 3);

                            if (correoElectronico.isBlank()) {
                                JOptionPane.showMessageDialog(null, "El correo electrónico es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            } else if (!correoElectronico.matches("^[A-Za-z0-9._-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$")) {
                                JOptionPane.showMessageDialog(null, "El correo electrónico no tiene un formato válido\n(ej. usuario@correo.com)", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl correo electrónico es un dato requerido", "Entrada no válida", 2);
                            cadenaCorrecta = false;
                        }
                    } while (!cadenaCorrecta);

                    clientes.add(new Cliente(rfc, nombre, direccion, telefono, tipo, fechaNacimiento, correoElectronico));
                    JOptionPane.showMessageDialog(null, "El cliente se registró correctamente", "Alta de un cliente", 1);
                    break;

                case 4:
                    //Para registrar una venta deben existir articulos y clientes
                    if (articulos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay artículos registrados.\nPrimero da de alta una camisa o un pantalón.", "Alta de una venta", 2);
                    } else if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay clientes registrados.\nPrimero da de alta un cliente.", "Alta de una venta", 2);
                    } else {
                        //ID DE LA VENTA
                        do {
                            idVenta = 0;
                            try {
                                idVenta = Integer.parseInt(JOptionPane.showInputDialog(null, "Id. Venta:", "Alta de una venta", 3));

                                if (idVenta <= 0) {
                                    JOptionPane.showMessageDialog(null, "El Id. Venta debe ser mayor a cero", "Error de entrada", 2);
                                } else {
                                    p = buscaVenta(ventas, idVenta);

                                    if (p != -1) {
                                        JOptionPane.showMessageDialog(null, "El Id. Venta ya está registrado", "Error de entrada", 2);
                                        idVenta = 0;
                                    }
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Debes escribir un número entero.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                            }
                        } while (idVenta <= 0);

                        //ID DEL ARTICULO (debe estar registrado)
                        listaRegistrados = "";
                        for (int i = 0; i < articulos.size(); i++) {
                            if (articulos.get(i) instanceof Camisa) {
                                listaRegistrados += articulos.get(i).getId() + " (Camisa)\n";
                            } else if (articulos.get(i) instanceof Pantalon) {
                                listaRegistrados += articulos.get(i).getId() + " (Pantalón)\n";
                            }
                        }

                        do {
                            idArticulo = "";
                            cadenaCorrecta = true;
                            try {
                                idArticulo = JOptionPane.showInputDialog(null, "Artículos registrados:\n" + listaRegistrados + "\nId. Artículo:", "Alta de una venta", 3);

                                if (idArticulo.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El Id. Artículo es un dato requerido", "Entrada no válida", 2);
                                    cadenaCorrecta = false;
                                } else {
                                    //-1 = no existe, -2 = existe pero es pantalon, otro valor = es camisa
                                    p = buscaElemento(articulos, idArticulo, 1);

                                    if (p == -1) {
                                        JOptionPane.showMessageDialog(null, "El artículo no está registrado.\nEscribe el Id de una camisa o un pantalón registrado.", "Entrada no válida", 2);
                                        cadenaCorrecta = false;
                                    }
                                }
                            } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl Id. Artículo es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } while (!cadenaCorrecta);

                        //RFC DEL CLIENTE (debe estar registrado)
                        listaRegistrados = "";
                        for (int i = 0; i < clientes.size(); i++) {
                            listaRegistrados += clientes.get(i).getRFC() + " - " + clientes.get(i).getNombre() + "\n";
                        }

                        do {
                            rfc = "";
                            cadenaCorrecta = true;
                            try {
                                rfc = JOptionPane.showInputDialog(null, "Clientes registrados:\n" + listaRegistrados + "\nRFC del cliente:", "Alta de una venta", 3);

                                if (rfc.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "El RFC es un dato requerido", "Entrada no válida", 2);
                                    cadenaCorrecta = false;
                                } else {
                                    p = buscaCliente(clientes, rfc);

                                    if (p == -1) {
                                        JOptionPane.showMessageDialog(null, "El cliente no está registrado.\nEscribe el RFC de un cliente registrado.", "Entrada no válida", 2);
                                        cadenaCorrecta = false;
                                    }
                                }
                            } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "No se puede cancelar.\nEl RFC es un dato requerido", "Entrada no válida", 2);
                                cadenaCorrecta = false;
                            }
                        } while (!cadenaCorrecta);

                        //FECHA DE PEDIDO (no muy anterior ni posterior a la actual: 2025 o 2026)
                        fechaPedido = new Fecha();
                        do {
                            fechaValida = false;
                            diaPedido = 0;
                            mesPedido = 0;
                            anioPedido = 0;
                            try {
                                diaPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Día del pedido:", "Alta de una venta", 3));
                                mesPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Mes del pedido:", "Alta de una venta", 3));
                                anioPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Año del pedido:", "Alta de una venta", 3));

                                if (anioPedido < 2025 || anioPedido > 2026) {
                                    fechaValida = false;
                                } else {
                                    fechaPedido.setFecha(diaPedido, mesPedido, anioPedido);
                                    fechaValida = fechaPedido.fechaCorrecta();
                                }

                                if (!fechaValida) {
                                    JOptionPane.showMessageDialog(null, "La fecha no es válida. Revisa el día, mes y año.\nEl año del pedido debe ser 2025 o 2026", "Fecha incorrecta", 2);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Debes escribir un número entero.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                            }
                        } while (!fechaValida);

                        //FECHA DE ENTREGA (debe ser posterior a la fecha de pedido)
                        fechaEntrega = new Fecha();
                        do {
                            fechaValida = false;
                            try {
                                diaEntrega = Integer.parseInt(JOptionPane.showInputDialog(null, "Día de entrega:", "Alta de una venta", 3));
                                mesEntrega = Integer.parseInt(JOptionPane.showInputDialog(null, "Mes de entrega:", "Alta de una venta", 3));
                                anioEntrega = Integer.parseInt(JOptionPane.showInputDialog(null, "Año de entrega:", "Alta de una venta", 3));

                                if (anioEntrega < 2025 || anioEntrega > 2027) {
                                    JOptionPane.showMessageDialog(null, "La fecha no es válida.\nEl año de entrega debe ser de 2025 a 2027", "Fecha incorrecta", 2);
                                } else {
                                    fechaEntrega.setFecha(diaEntrega, mesEntrega, anioEntrega);
                                    fechaValida = fechaEntrega.fechaCorrecta();

                                    if (!fechaValida) {
                                        JOptionPane.showMessageDialog(null, "La fecha no es válida. Revisa el día, mes y año.", "Fecha incorrecta", 2);
                                    } else {
                                        entregaPosterior = false;

                                        if (anioEntrega > anioPedido) {
                                            entregaPosterior = true;
                                        } else if (anioEntrega == anioPedido && mesEntrega > mesPedido) {
                                            entregaPosterior = true;
                                        } else if (anioEntrega == anioPedido && mesEntrega == mesPedido && diaEntrega > diaPedido) {
                                            entregaPosterior = true;
                                        }

                                        if (!entregaPosterior) {
                                            JOptionPane.showMessageDialog(null, "La fecha de entrega debe ser posterior a la fecha de pedido (" + fechaPedido.getFecha() + ")", "Fecha incorrecta", 2);
                                            fechaValida = false;
                                        }
                                    }
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Debes escribir un número entero.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                            }
                        } while (!fechaValida);

                        ventas.add(new Venta(idVenta, idArticulo, rfc, fechaPedido, fechaEntrega));
                        JOptionPane.showMessageDialog(null, "La venta se registró correctamente", "Alta de una venta", 1);
                    }
                    break;

                case 5:
                    hayCamisas = false;
                    listaCamisas = "LISTA DE CAMISAS\n"
                            + "Id. Artículo        Color        Talla        Cuello        Mangas        Ajuste        Precio\n"
                            + "------------------------------------------------------------------------------------------------------------------\n";

                    for (int i = 0; i < articulos.size(); i++) {
                        if (articulos.get(i) instanceof Camisa) {
                            Camisa camisa = (Camisa) articulos.get(i);
                            listaCamisas += camisa.getId() + "        "
                                    + camisa.getColor() + "        "
                                    + camisa.getTalla() + "        "
                                    + camisa.getCuello() + "        "
                                    + camisa.getManga() + "        "
                                    + camisa.getAjuste() + "        $ "
                                    + camisa.getPrecio() + "\n";
                            hayCamisas = true;
                        }
                    }

                    if (!hayCamisas) {
                        JOptionPane.showMessageDialog(null, "Aún no hay camisas registradas", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, listaCamisas, "Lista de camisas", 1);
                    }
                    break;

                case 6:
                    hayPantalones = false;
                    listaPantalones = "LISTA DE PANTALONES\n"
                            + "Id. Artículo        Talla        Color        Género        Corte        Precio\n"
                            + "-----------------------------------------------------------------------------------------------\n";

                    for (int i = 0; i < articulos.size(); i++) {
                        if (articulos.get(i) instanceof Pantalon) {
                            Pantalon pantalon = (Pantalon) articulos.get(i);
                            listaPantalones += pantalon.getId() + "        "
                                    + pantalon.getTalla() + "        "
                                    + pantalon.getColor() + "        "
                                    + pantalon.getGenero() + "        "
                                    + pantalon.getCorte() + "        $ "
                                    + pantalon.getPrecio() + "\n";
                            hayPantalones = true;
                        }
                    }

                    if (!hayPantalones) {
                        JOptionPane.showMessageDialog(null, "Aún no hay pantalones registrados", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, listaPantalones, "Lista de pantalones", 1);
                    }
                    break;

                case 7:
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay clientes registrados", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        listaClientes = "LISTADO DE CLIENTES\n"
                                + "RFC                Nombre                Tipo                Fecha de nacimiento\n"
                                + "-------------------------------------------------------------------------------------------\n";

                        for (int i = 0; i < clientes.size(); i++) {
                            listaClientes += clientes.get(i).getRFC() + "        "
                                    + clientes.get(i).getNombre() + "        "
                                    + clientes.get(i).getTipo() + "        "
                                    + clientes.get(i).getFechaNacimiento() + "\n";
                        }

                        JOptionPane.showMessageDialog(null, listaClientes, "Listado de clientes", 1);
                    }
                    break;

                case 8:
                    if (ventas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay ventas registradas", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        listaVentas = "LISTADO DE VENTAS\n"
                                + "Id. Venta        Id. Artículo        RFC        Fecha Pedido\n"
                                + "-----------------------------------------------------------------------------\n";

                        for (int i = 0; i < ventas.size(); i++) {
                            listaVentas += ventas.get(i).getIdVenta() + "        "
                                    + ventas.get(i).getIdArticulo() + "        "
                                    + ventas.get(i).getRFC() + "        "
                                    + ventas.get(i).getFechaPedido() + "\n";
                        }

                        JOptionPane.showMessageDialog(null, listaVentas, "Listado de ventas", 1);
                    }
                    break;

                case 9:
                    if (articulos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay artículos registrados", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        idBuscado = JOptionPane.showInputDialog(null, "Ingrese el Id del artículo a consultar:", "Ver detalle de una camisa", 3);
                        p = buscaElemento(articulos, idBuscado, 1);

                        if (p == -1) {
                            JOptionPane.showMessageDialog(null, "El Id del artículo no está registrado", "Error", 2);
                        } else if (p == -2) {
                            JOptionPane.showMessageDialog(null, "El Id del artículo no corresponde a una camisa", "Error", 2);
                        } else {
                            JOptionPane.showMessageDialog(null, "DETALLE DE UNA CAMISA\n" + articulos.get(p).getDatos(), "Ver detalle de una camisa", 1);
                        }
                    }
                    break;

                case 10:
                    if (articulos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay artículos registrados", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        idBuscado = JOptionPane.showInputDialog(null, "Ingrese el Id del artículo a consultar:", "Ver detalle de un pantalón", 3);
                        p = buscaElemento(articulos, idBuscado, 2);

                        if (p == -1) {
                            JOptionPane.showMessageDialog(null, "El Id del artículo no está registrado", "Error", 2);
                        } else if (p == -2) {
                            JOptionPane.showMessageDialog(null, "El Id del artículo no corresponde a un pantalón", "Error", 2);
                        } else {
                            JOptionPane.showMessageDialog(null, "DETALLE DE UN PANTALÓN\n" + articulos.get(p).getDatos(), "Ver detalle de un pantalón", 1);
                        }
                    }
                    break;

                case 11:
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay clientes registrados", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        idBuscado = JOptionPane.showInputDialog(null, "Ingrese el RFC del cliente a consultar:", "Ver detalle de un cliente", 3);
                        p = buscaCliente(clientes, idBuscado);

                        if (p == -1) {
                            JOptionPane.showMessageDialog(null, "El RFC no está registrado", "Error", 2);
                        } else {
                            JOptionPane.showMessageDialog(null, "DETALLE DE UN CLIENTE\n" + clientes.get(p).getDatos(), "Ver detalle de un cliente", 1);
                        }
                    }
                    break;

                case 12:
                    if (ventas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay ventas registradas", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        do {
                            idVentaBuscada = 0;
                            try {
                                idVentaBuscada = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id. Venta a consultar:", "Ver detalle de una venta", 3));

                                if (idVentaBuscada <= 0) {
                                    JOptionPane.showMessageDialog(null, "El Id. Venta debe ser mayor a cero", "Error de entrada", 2);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Debes escribir un número entero.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                            }
                        } while (idVentaBuscada <= 0);

                        p = buscaVenta(ventas, idVentaBuscada);

                        if (p == -1) {
                            JOptionPane.showMessageDialog(null, "El Id. Venta no está registrado", "Error", 2);
                        } else {
                            detalleVenta = "DETALLE DE UNA VENTA\n"
                                    + "ID venta: " + ventas.get(p).getIdVenta() + "\n"
                                    + "ARTÍCULO\n";

                            //Se busca el articulo de la venta en la coleccion de articulos
                            posArticulo = buscaElemento(articulos, ventas.get(p).getIdArticulo(), 1);
                            if (posArticulo == -2) {
                                posArticulo = buscaElemento(articulos, ventas.get(p).getIdArticulo(), 2);
                            }

                            if (posArticulo == -1) {
                                detalleVenta += "El artículo " + ventas.get(p).getIdArticulo() + " ya no está registrado\n";
                            } else {
                                detalleVenta += articulos.get(posArticulo).getDatos() + "\n";
                            }

                            //Se busca el cliente de la venta en la coleccion de clientes
                            detalleVenta += "CLIENTE\n";
                            posCliente = buscaCliente(clientes, ventas.get(p).getRFC());

                            if (posCliente == -1) {
                                detalleVenta += "El cliente " + ventas.get(p).getRFC() + " ya no está registrado\n";
                            } else {
                                detalleVenta += clientes.get(posCliente).getDatos() + "\n";
                            }

                            detalleVenta += "\nFecha de Pedido: " + ventas.get(p).getFechaPedido()
                                    + "\nFecha de Entrega: " + ventas.get(p).getFechaEntrega();

                            JOptionPane.showMessageDialog(null, detalleVenta, "Ver detalle de una venta", 1);
                        }
                    }
                    break;

                case 13:
                    if (articulos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay artículos registrados", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        idBuscado = JOptionPane.showInputDialog(null, "Ingrese el Id del artículo a eliminar:", "Eliminar una camisa", 3);
                        p = buscaElemento(articulos, idBuscado, 1);

                        if (p == -1) {
                            JOptionPane.showMessageDialog(null, "El Id del artículo no está registrado", "Error", 2);
                        } else if (p == -2) {
                            JOptionPane.showMessageDialog(null, "El Id del artículo no corresponde a una camisa", "Error", 2);
                        } else {
                            //Se cuentan las ventas de este articulo (eliminacion en cascada)
                            ventasRelacionadas = 0;
                            for (int i = 0; i < ventas.size(); i++) {
                                if (ventas.get(i).getIdArticulo().equals(idBuscado)) {
                                    ventasRelacionadas++;
                                }
                            }

                            confirmacion = JOptionPane.showConfirmDialog(null, "DETALLE DE UNA CAMISA\n" + articulos.get(p).getDatos()
                                    + "\n\nVentas relacionadas que también se eliminarán: " + ventasRelacionadas
                                    + "\n¿Desea eliminar esta camisa?", "Confirmación", YES_NO_OPTION, 2);

                            if (confirmacion == JOptionPane.YES_OPTION) {
                                //Se recorre de atras hacia adelante para no saltar posiciones al eliminar
                                for (int i = ventas.size() - 1; i >= 0; i--) {
                                    if (ventas.get(i).getIdArticulo().equals(idBuscado)) {
                                        ventas.remove(i);
                                    }
                                }
                                articulos.remove(p);
                                JOptionPane.showMessageDialog(null, "Camisa " + idBuscado + " eliminada.\nVentas eliminadas: " + ventasRelacionadas, "Eliminar una camisa", 1);
                            } else {
                                JOptionPane.showMessageDialog(null, "Proceso cancelado", "Eliminar una camisa", 1);
                            }
                        }
                    }
                    break;

                case 14:
                    if (articulos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay artículos registrados", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        idBuscado = JOptionPane.showInputDialog(null, "Ingrese el Id del artículo a eliminar:", "Eliminar un pantalón", 3);
                        p = buscaElemento(articulos, idBuscado, 2);

                        if (p == -1) {
                            JOptionPane.showMessageDialog(null, "El Id del artículo no está registrado", "Error", 2);
                        } else if (p == -2) {
                            JOptionPane.showMessageDialog(null, "El Id del artículo no corresponde a un pantalón", "Error", 2);
                        } else {
                            //Se cuentan las ventas de este articulo (eliminacion en cascada)
                            ventasRelacionadas = 0;
                            for (int i = 0; i < ventas.size(); i++) {
                                if (ventas.get(i).getIdArticulo().equals(idBuscado)) {
                                    ventasRelacionadas++;
                                }
                            }

                            confirmacion = JOptionPane.showConfirmDialog(null, "DETALLE DE UN PANTALÓN\n" + articulos.get(p).getDatos()
                                    + "\n\nVentas relacionadas que también se eliminarán: " + ventasRelacionadas
                                    + "\n¿Desea eliminar este pantalón?", "Confirmación", YES_NO_OPTION, 2);

                            if (confirmacion == JOptionPane.YES_OPTION) {
                                //Se recorre de atras hacia adelante para no saltar posiciones al eliminar
                                for (int i = ventas.size() - 1; i >= 0; i--) {
                                    if (ventas.get(i).getIdArticulo().equals(idBuscado)) {
                                        ventas.remove(i);
                                    }
                                }
                                articulos.remove(p);
                                JOptionPane.showMessageDialog(null, "Pantalón " + idBuscado + " eliminado.\nVentas eliminadas: " + ventasRelacionadas, "Eliminar un pantalón", 1);
                            } else {
                                JOptionPane.showMessageDialog(null, "Proceso cancelado", "Eliminar un pantalón", 1);
                            }
                        }
                    }
                    break;

                case 15:
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay clientes registrados", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        idBuscado = JOptionPane.showInputDialog(null, "Ingrese el RFC del cliente a eliminar:", "Eliminar un cliente", 3);
                        p = buscaCliente(clientes, idBuscado);

                        if (p == -1) {
                            JOptionPane.showMessageDialog(null, "El RFC no está registrado", "Error", 2);
                        } else {
                            //Se cuentan las ventas de este cliente (eliminacion en cascada)
                            ventasRelacionadas = 0;
                            for (int i = 0; i < ventas.size(); i++) {
                                if (ventas.get(i).getRFC().equals(idBuscado)) {
                                    ventasRelacionadas++;
                                }
                            }

                            confirmacion = JOptionPane.showConfirmDialog(null, "DETALLE DE UN CLIENTE\n" + clientes.get(p).getDatos()
                                    + "\n\nVentas relacionadas que también se eliminarán: " + ventasRelacionadas
                                    + "\n¿Desea eliminar este cliente?", "Confirmación", YES_NO_OPTION, 2);

                            if (confirmacion == JOptionPane.YES_OPTION) {
                                //Se recorre de atras hacia adelante para no saltar posiciones al eliminar
                                for (int i = ventas.size() - 1; i >= 0; i--) {
                                    if (ventas.get(i).getRFC().equals(idBuscado)) {
                                        ventas.remove(i);
                                    }
                                }
                                JOptionPane.showMessageDialog(null, "Cliente " + clientes.get(p).getNombre() + " eliminado.\nVentas eliminadas: " + ventasRelacionadas, "Eliminar un cliente", 1);
                                clientes.remove(p);
                            } else {
                                JOptionPane.showMessageDialog(null, "Proceso cancelado", "Eliminar un cliente", 1);
                            }
                        }
                    }
                    break;

                case 16:
                    if (ventas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aún no hay ventas registradas", "Sin registros", JOptionPane.WARNING_MESSAGE);
                    } else {
                        do {
                            idVentaBuscada = 0;
                            try {
                                idVentaBuscada = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id. Venta a eliminar:", "Eliminar una venta", 3));

                                if (idVentaBuscada <= 0) {
                                    JOptionPane.showMessageDialog(null, "El Id. Venta debe ser mayor a cero", "Error de entrada", 2);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Debes escribir un número entero.\nNo dejes el campo vacío.", "Entrada no válida", 2);
                            }
                        } while (idVentaBuscada <= 0);

                        p = buscaVenta(ventas, idVentaBuscada);

                        if (p == -1) {
                            JOptionPane.showMessageDialog(null, "El Id. Venta no está registrado", "Error", 2);
                        } else {
                            confirmacion = JOptionPane.showConfirmDialog(null, "DETALLE DE UNA VENTA\n" + ventas.get(p).getDatos()
                                    + "\n\n¿Desea eliminar esta venta?", "Confirmación", YES_NO_OPTION, 2);

                            if (confirmacion == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Venta " + ventas.get(p).getIdVenta() + " eliminada", "Eliminar una venta", 1);
                                ventas.remove(p);
                            } else {
                                JOptionPane.showMessageDialog(null, "Proceso cancelado", "Eliminar una venta", 1);
                            }
                        }
                    }
                    break;

                case 17:
                    //ESCRITURA DEL ARCHIVO DE ARTICULOS
                    FileOutputStream fout = null;
                    try {
                        fout = new FileOutputStream("Articulos.txt");
                        ObjectOutputStream salida = new ObjectOutputStream(fout);
                        salida.writeObject(articulos);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "No se encontró el archivo Articulos.txt", "Error", 2);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error de E/S al guardar Articulos.txt", "Error", 2);
                    } finally {
                        if (fout != null) {
                            try {
                                fout.close();
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo Articulos.txt", "Error", 2);
                            }
                        }
                    }

                    //ESCRITURA DEL ARCHIVO DE CLIENTES
                    fout = null;
                    try {
                        fout = new FileOutputStream("Clientes.txt");
                        ObjectOutputStream salida = new ObjectOutputStream(fout);
                        salida.writeObject(clientes);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "No se encontró el archivo Clientes.txt", "Error", 2);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error de E/S al guardar Clientes.txt", "Error", 2);
                    } finally {
                        if (fout != null) {
                            try {
                                fout.close();
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo Clientes.txt", "Error", 2);
                            }
                        }
                    }

                    //ESCRITURA DEL ARCHIVO DE VENTAS
                    fout = null;
                    try {
                        fout = new FileOutputStream("Ventas.txt");
                        ObjectOutputStream salida = new ObjectOutputStream(fout);
                        salida.writeObject(ventas);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "No se encontró el archivo Ventas.txt", "Error", 2);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error de E/S al guardar Ventas.txt", "Error", 2);
                    } finally {
                        if (fout != null) {
                            try {
                                fout.close();
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo Ventas.txt", "Error", 2);
                            }
                        }
                    }
                    break;
            }

        } while (op != 17);

        JOptionPane.showMessageDialog(null, "TIENDA DE ROPA\nCopyright © 2026\n\nDesarrolladores:\n"
                + "Francisco Javier Bautista Bautista\n"
                + "Antonio Angeles Trejo\n"
                + "Rodrigo Gael Contreras Corona\n"
                + "Jose Pastrana Segundo", "Copyright", 1);
    }

    public static int getEntero(String mensaje, String titulo) {

        int entValido, check;

        do {
            entValido = 0;
            check = 0;

            try {
                entValido = Integer.parseInt(JOptionPane.showInputDialog(null, mensaje, titulo, 3));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La opción debe ser numérica.\nIntroduzca una de las opciones disponibles", "Error de entrada", 2);
                check++;
            }

        } while (check != 0);

        return entValido;
    }

    //tipo 1 = Camisa, tipo 2 = Pantalon
    //Regresa la posicion si lo encuentra, -1 si no existe y -2 si existe pero no es del tipo buscado
    public static int buscaElemento(ArrayList<Articulo> e, String id, int tipo) {

        int pos = -1;

        if (id == null) {
            return -1;
        }

        for (int i = 0; i < e.size(); i++) {
            if (id.equals(e.get(i).getId())) {
                if ((tipo == 1 && e.get(i) instanceof Camisa) || (tipo == 2 && e.get(i) instanceof Pantalon)) {
                    pos = i;
                    break;
                } else {
                    pos = -2;
                }
            }
        }

        return pos;
    }

    //Regresa la posicion del cliente o -1 si no existe
    public static int buscaCliente(ArrayList<Cliente> c, String rfc) {

        int pos = -1;

        if (rfc == null) {
            return -1;
        }

        for (int i = 0; i < c.size(); i++) {
            if (rfc.equals(c.get(i).getRFC())) {
                pos = i;
                break;
            }
        }

        return pos;
    }

    //Regresa la posicion de la venta o -1 si no existe
    public static int buscaVenta(ArrayList<Venta> v, int idVenta) {

        int pos = -1;

        for (int i = 0; i < v.size(); i++) {
            if (idVenta == v.get(i).getIdVenta()) {
                pos = i;
                break;
            }
        }

        return pos;
    }
}
