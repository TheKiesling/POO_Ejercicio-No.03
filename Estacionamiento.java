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
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

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
    private ArrayList<Integer> frecuencia_parqueos = new ArrayList<>();
    private int vehiculos_rechazados = 0;
    private int horas_vehiculos = 0;
    private int vehiculos_retirados = 0;


    public Estacionamiento(int espacio){
        parqueos = new ArrayList<Parqueo>(espacio);
        for (int i = 0; i < espacio; i++)
            parqueos.add(null);
    }

    public Estacionamiento(){
        parqueos = new ArrayList<Parqueo>(espacio);
        for (int i = 0; i < espacio; i++)
            parqueos.add(null);
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
        for (int i = 0; i < parqueos.size(); i++)
            frecuencia_parqueos.add(0);       
        if (puesto >= 0){
            int puesto_ocupado = frecuencia_parqueos.get(puesto) + 1;
            frecuencia_parqueos.set(puesto, puesto_ocupado);
            System.out.println(puesto+ ":"+frecuencia_parqueos.get(puesto));
            parqueos.set(puesto, parqueo);
        }
        else vehiculos_rechazados++;
    }

    public void retirarCarro(int hora_egreso, int numero_parqueo){
        vehiculos_retirados++;
        int hora_ingreso = parqueos.get(numero_parqueo).datosInt()[0]; 
        horas_vehiculos += calculoHorasTotales(hora_egreso, hora_ingreso);
        for (int i = hora_ingreso; i <= hora_egreso; i++){
            horas[i]++;
            System.out.println(horas[i]);
        }
        
        parqueos.set(numero_parqueo, null);
    }

    public int calculoHorasTotales (int hora_egreso, int hora_ingreso){
        return hora_egreso - hora_ingreso;
    }

    public void aumentarEspacio(int espacio){
        int espacio_nuevo = espacio + parqueos.size();
        for (int i = parqueos.size(); i < espacio_nuevo; i++){
            parqueos.add(i, null);
            System.out.println(i);
        }
        System.out.println(parqueos.size());
        System.out.println(espacio_nuevo);
    }

    public String intervalosHorario(){
        int hora_usada = Arrays.stream(horas).max().getAsInt();
        boolean bandera = false;
        int hora_minima = 0; int hora_maxima = 23;

        for (int i = 0; i < horas.length && bandera == false ; i++){
            if (hora_usada == horas[i]){
                hora_minima = i;
                bandera = true;
            }
        }

        for (int i = 0; i < horas.length; i++){
            if (hora_usada == horas[i])
                hora_maxima = i;
        }

        System.out.println("El intervalo de horas más usadas es: " + hora_minima + "-" + hora_maxima);
        String rango = hora_minima + "-" + hora_maxima;
        return rango;     
    }

    public int tiempoPromedio(){
        int promedio = horas_vehiculos/vehiculos_retirados;
        System.out.println("El tiempo promedio por vehículo es: " + promedio);
        return promedio;
    }

    public int parqueoMasUsado(){
        int veces_usado = Collections.max(frecuencia_parqueos);
        int parqueo = 0;
        boolean bandera = false;
        for (int i = 0; i < frecuencia_parqueos.size() && bandera == false; i++)
            if (veces_usado == frecuencia_parqueos.get(i)){
                parqueo = i;
                bandera = true;
            }
        
        System.out.println(parqueo);
        return parqueo;
    }

    public int vehiculosRechazados(){
        System.out.println("jkjk " + vehiculos_rechazados);
        return vehiculos_rechazados;
    }

    public String marcaMasUsada(){
        ArrayList <String> marcas = new ArrayList<>();
        int frecuencia = 0;
        String marca_usada ="";

        for (int i = 0; i < parqueos.size();i++){
            if (parqueos.get(i) != null){
                Vehiculo vehiculo_actual = parqueos.get(i).getVehiculo();
                String marca = vehiculo_actual.getDatos()[0];
                marcas.add(marca);
            }
        }
        for (int i = 0; i < marcas.size(); i++){
            int frecuencia_actual = 0;
            for (int j = 0; j <marcas.size(); j++){
                if(marcas.get(i).equals(marcas.get(j)))
                    frecuencia_actual++;
                if(frecuencia_actual > frecuencia){
                    marca_usada = marcas.get(i);
                    frecuencia = frecuencia_actual;
                }
            }
        }
        System.out.println(marca_usada);
        return marca_usada;
        
    }

    public String[] caracteristicasParqueo(){
        ArrayList <String> tamanos = new ArrayList<>(); ArrayList <String> ubicaciones = new ArrayList<>();
        int frecuencia_tamano = 0; int frecuencia_ubicaciones = 0;
        String tamano_usado = ""; String ubicacion_usada = "";

        for (int i = 0; i < parqueos.size(); i++){
            if (parqueos.get(i) != null){
                String tamano = parqueos.get(i).datosStrings()[0];
                String ubicacion = parqueos.get(i).datosStrings()[1];
                tamanos.add(tamano); ubicaciones.add(ubicacion);
            }
        }

        for (int i = 0; i < tamanos.size(); i++){
            int frecuencia_actual = 0;
            for (int j = 0; j <tamanos.size(); j++){
                if(tamanos.get(i).equals(tamanos.get(j)))
                    frecuencia_actual++;
                if(frecuencia_actual > frecuencia_tamano){
                    tamano_usado = tamanos.get(i);
                    frecuencia_tamano = frecuencia_actual;
                }
            }
        }

        for (int i = 0; i <ubicaciones.size(); i++){
            int frecuencia_actual = 0;
            for (int j = 0; j < ubicaciones.size(); j++){
                if(ubicaciones.get(i).equals(ubicaciones.get(j)))
                    frecuencia_actual++;
                if(frecuencia_actual > frecuencia_ubicaciones){
                    ubicacion_usada = ubicaciones.get(i);
                    frecuencia_ubicaciones = frecuencia_actual;
                }
            }
        }
        String[] datos = new String[2];
        datos[0] = tamano_usado; datos[1] = ubicacion_usada;
        System.out.println(tamano_usado); System.out.println(ubicacion_usada);
        return datos;
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

    public void leerArchivo(int espacios){
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String datos; String[] datos_separados = new String[7]; 
            int espacio_actual = parqueos.size();
            for (int i = espacio_actual; i < espacios; i++) {
                    parqueos.add(i, null); 
                    System.out.println(i);
            }
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
