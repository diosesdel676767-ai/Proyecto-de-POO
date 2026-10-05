# Proyecto: Tienda de Ropa

Proyecto del parcial 2 de Programación Orientada a Objetos.

Desarrolladores:
- Francisco Javier Bautista Bautista
- Antonio Angeles Trejo
- Rodrigo Gael Contreras Corona
- Jose Pastrana Segundo

## Estructura

```
src/
├── pck_fecha/
│   └── Fecha.java          (composición en Cliente y Venta)
└── pck_tienda/
    ├── Articulo.java       (clase padre)
    ├── Camisa.java         (hereda de Articulo)
    ├── Pantalon.java       (hereda de Articulo)
    ├── Cliente.java
    ├── Venta.java
    └── TiendaDeRopa.java   (main con el menú de 17 opciones)
```

## Cómo usarlo en NetBeans

1. Crear un proyecto nuevo de Java (sin clase principal).
2. Copiar las carpetas `pck_fecha` y `pck_tienda` dentro de la carpeta `src` del proyecto
   (en Maven: `src/main/java`).
3. Ejecutar `pck_tienda.TiendaDeRopa`.

Al elegir la opción 17 (Salir) se guardan los archivos `Articulos.txt`, `Clientes.txt` y
`Ventas.txt` en la carpeta del proyecto; al volver a ejecutar el programa se leen de ahí.
