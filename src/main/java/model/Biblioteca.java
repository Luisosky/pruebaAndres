package model;

import enums.Categorias;
import exceptions.ClienteExistente;
import exceptions.ClienteVacio;
import exceptions.LibroExistente;
import exceptions.LibroVacio;
import lombok.Getter;
import lombok.extern.java.Log;
import utils.ArchivoUtils;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.ArrayList;
@Log
@Getter

public class Biblioteca {

    //Rutas
    private final String RUTA_CLIENTE = "src/main/resources/persistencia/clientes.ser";
    private final String RUTA_LIBROS = "src/main/resources/persistencia/libros.ser";
    private final String RUTA_ALQUILADOS= "src/main/resources/persistencia/alquilados.ser";

    //Listas
    private ArrayList<Cliente> clientes;
    private final ArrayList<Libro> libros;
    private final ArrayList<Alquilados> alquilados;
    private static Biblioteca biblioteca;

    private Biblioteca(){
        inicializarLogger();
        log.info("Se crea una nueva instancia de la biblioteca");

        this.clientes =new ArrayList<>();
        leerClientes();

        this.libros= new ArrayList<>();

        this.alquilados= new ArrayList<>();
    }

    private void inicializarLogger(){
        try{
            FileHandler fh = new FileHandler("logs.log", true);
            fh.setFormatter(new SimpleFormatter());
            log.addHandler(fh);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    public static Biblioteca getInstance()
    {
        if(biblioteca==null)
        {
            biblioteca= new Biblioteca();
        }
        return biblioteca;
    }

    public Cliente estaRegistradoCliente(String cedula){
        try{
            for(Cliente c : clientes ){
                if(c.getCedula().equals(cedula)){
                    return c;
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Libro estaRegistradoLibro(int idLibro) throws LibroVacio {

            for (Libro l: libros
                 ) {
                if(l.getIdLibro()==(idLibro)){
                    return l;
                }
            }
            return null;

    }


    public float calcularValorAlquilada(int cantHojas , int cantidadDias)
    {
        return (float) (2000 * cantidadDias + (cantHojas*0.5));
    }

    public void aniadirCliente() throws ClienteExistente, ClienteVacio
    {
        String nombre= JOptionPane.showInputDialog("Escriba el nombre del cliente: ");
        String cedula= JOptionPane.showInputDialog("Escriba la cedula del cliente: ");




        if(nombre==null || nombre.isBlank() || cedula==null || cedula.isBlank()){
            throw new ClienteVacio("Ta vacio");
        }

        if(estaRegistradoCliente(cedula)!=null)
        {
            throw new ClienteExistente("El cliente ya esta registrado");
        }

            Cliente cliente = Cliente.builder()
                    .nombre(nombre)
                    .cedula(cedula)
                    .librosAlquilados(libros)
                    .build();


            clientes.add(cliente);
            escribirCliente();
            log.info("Se ha creado el cliente con exito: " + cedula + "    " + nombre);
    }


    public void aniadirLibro() throws LibroVacio, LibroExistente
    {
         int idLibro;
         int cantidadPaginas;
         String nombre;
         Categorias categoria;
    }

    private void escribirCliente(){
        try{
            ArchivoUtils.serializarObjeto(RUTA_CLIENTE, clientes);
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
    }

    private void leerClientes(){
        try
        {
            ArrayList<Cliente> lista= (ArrayList<Cliente>) ArchivoUtils.deserializarObjeto(RUTA_CLIENTE);
            if(lista!=null){
                this.clientes=lista;
            }
        } catch (IOException e) {
            log.severe(e.getMessage());
        } catch (ClassNotFoundException e) {
            log.severe(e.getMessage());
        }
    }

    public void imprimirCliente(String cedula){
        Cliente c= estaRegistradoCliente(cedula);
        System.out.println(c.getCedula() + " " + c.getNombre());
    }

}
