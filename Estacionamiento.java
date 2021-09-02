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
import java.io.BufferedWriter;
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
    String texto;

    public Estacionamiento(int espacio){
        parqueos = new ArrayList<Parqueo>(espacio);
    }

    public Estacionamiento(){
        parqueos = new ArrayList<Parqueo>(espacio);
    }

    public void agregarCarro(String tamano, Vehiculo vehiculo, String ubicacion, int horaIngreso){
        int puesto = -1;
        boolean bandera = false;

        parqueo = new Parqueo(tamano, vehiculo, ubicacion, horaIngreso);

        for(int i = 0; i < parqueos.size() && bandera == false ; i++)
            if (parqueos.get(i) == null){
                puesto = i;
                bandera = true;
            }

        parqueos.add(puesto, parqueo);
    }

    public void escribirArchivo(){
        try{
            for (int i = 0; i < parqueos.size(); i++){
                Parqueo parqueo = parqueos.get(i);
                int horaIngreso = parqueo.datosInt()[0]; int horasTotales = parqueo.datosInt()[1];
                String tamano = parqueo.datosStrings()[0]; String ubicacion = parqueo.datosStrings()[1];

                Vehiculo vehiculo = parqueo.getVehiculo();
                String marca = vehiculo.getDatos()[0]; String placa = vehiculo.getDatos()[1]; String modelo = vehiculo.getDatos()[2];

                texto = i + placa + marca + modelo + tamano + ubicacion + horaIngreso;
            }

            FileWriter f = new FileWriter("estacionamiento.txt");
            f.write(texto);
            f.close();
        } catch (IOException e){
            
        }
    }
}
