package main;

import exceptions.ErrorMenuException;
import lombok.SneakyThrows;
import model.Biblioteca;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final Biblioteca biblioteca = Biblioteca.getInstance();

    public static void main(String[] args) {



        try {
            hacerMenu();
        } catch (ErrorMenuException e) {
            throw new RuntimeException(e);
        }

    }

    @SneakyThrows
    public static void hacerMenu() throws ErrorMenuException
    {
        int opcion=0;
        do {
                opcion =Integer.parseInt( JOptionPane.showInputDialog(null , "Elija la opcion que desee. " +
                        "\n 1) Añadir cliente" +
                        "\n 2) Añadir libro" +
                        "\n 3) Prestar libro" +
                        "\n 4) Actualizar informacion cliente" +
                        "\n 5) Devolver libro" +
                        "\n 6) imp" +
                        "\n 7) Salir del sistema"));

                if(opcion== 1)
                {
                    biblioteca.aniadirCliente();
                }
                if(opcion==6){
                    biblioteca.imprimirCliente("1042");
                }
            }while(opcion!=7);
        }
    }

