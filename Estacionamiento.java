/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Estacionamiento {
    /** 
    @author: José Pablo Kiesling Lange
    Nombre del programa: Vehiculo.java
    @version: 
        - Creación: 02/09/2021
        - Última modificación: 02/09/2021

    Clase que tendrá todos los parqueos y será el gestionador de las clases de modelo
    */

    //---------------------------PROPIEDADES-------------------------
    private Parqueo parqueo;
    private int espacio = 5;
    private ArrayList<Parqueo> parqueos;
    private File archivo = new File(".\\estacionamiento.txt");
    private String texto = "";
    private int[] horas = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    public Estacionamiento(int espacio){
        parqueos = new ArrayList<Parqueo>(espacio);
    }

    public Estacionamiento(){
        parqueos = new ArrayList<Parqueo>(espacio);
        for (int i = 0; i < espacio; i++)
            parqueos.add(null);
    }

    public void agregarCarro(String tamano, Vehiculo vehiculo, String ubicacion, int horaIngreso){
        int puesto = 0;
        boolean bandera = false;

        parqueo = new Parqueo(tamano, vehiculo, ubicacion, horaIngreso);
        for(int i = 0; i < parqueos.size() && bandera == false ; i++)
            if (parqueos.get(i) == null){
                puesto = i;
                bandera = true;
            }
        System.out.println(puesto);
        parqueos.set(puesto, parqueo);
    }

    public void retirarCarro(int numero_parqueo){
        int hora_ingreso = parqueos.get(numero_parqueo).datosInt()[0]; int hora_egreso = parqueos.get(numero_parqueo).datosInt()[1];
        for (int i = hora_ingreso; i <= hora_egreso; i++){
            horas[i]++;
        }
        System.out.println(horas);
        parqueos.set(numero_parqueo, null);
    }

    public void escribirArchivo(){
        try{
            for (int i = 0; i < parqueos.size(); i++){
                if(parqueos.get(i) != null){
                    System.out.println(i);
                    Parqueo parqueo = parqueos.get(i);
                    int horaIngreso = parqueo.datosInt()[0];
                    String tamano = parqueo.datosStrings()[0]; String ubicacion = parqueo.datosStrings()[1];
                    Vehiculo vehiculo = parqueo.getVehiculo();
                    String marca = vehiculo.getDatos()[0]; String placa = vehiculo.getDatos()[1]; String modelo = vehiculo.getDatos()[2];

                    texto += i +";" + placa +";" + marca +";" + modelo +";" + tamano +";" + ubicacion +";" + horaIngreso +"\n";
                }

                if(parqueos.get(i) == null){ 
                    texto += i +";" + "" +";" + "" +";" + "" +";" + "" +";" + "" +";" + "0" +"\n";
                }
            }
            FileWriter f = new FileWriter("estacionamiento.txt");
            f.write(texto);
            f.close();
        } 
        catch (IOException e){

        }
    }

    public void leerArchivo(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String datos; String[] datos_separados = new String[7];
            while ((datos = br.readLine()) != null){
                datos_separados = datos.split(";");
                int posicion = Integer.parseInt(datos_separados[0]);
                String placa = datos_separados[1]; String marca = datos_separados[2]; String modelo = datos_separados[3];
                Vehiculo vehiculo = new Vehiculo(marca, placa, modelo);
                String tamano = datos_separados[4]; String ubicacion = datos_separados[5]; int horaIngreso = Integer.parseInt(datos_separados[6]);
                Parqueo parqueo = new Parqueo(tamano, vehiculo, ubicacion, horaIngreso);
                if (placa.equals(""))
                    parqueos.set(posicion, null);
                else
                    parqueos.set(posicion, parqueo);
            }
        }
        catch (IOException e){

        }
    }
}
