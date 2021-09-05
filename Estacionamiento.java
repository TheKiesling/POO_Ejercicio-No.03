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
        - Última modificación: 05/09/2021

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

    /****************************************************************
     * Estacionamiento: constructor que crea un espacio de parqueos con un espacio específico
     * @param espacio
     */
    public Estacionamiento(int espacio){
        parqueos = new ArrayList<Parqueo>(espacio);
        for (int i = 0; i < espacio; i++)
            parqueos.add(null);
    }
    //***************************************************************

    /****************************************************************
     * Estacionamiento: constructor que crea un espacio de parqueos de 5 lugares
     */
    public Estacionamiento(){
        parqueos = new ArrayList<Parqueo>(espacio);
        for (int i = 0; i < espacio; i++)
            parqueos.add(null);
    }
    //***************************************************************

    /****************************************************************
     * agregarCarro: agrega un vehiculo en el estacionamiento y retorna el puesto de parqueo
     * @param tamano
     * @param vehiculo
     * @param ubicacion
     * @param horaIngreso
     * @return puesto
     */
    public int agregarCarro(String tamano, Vehiculo vehiculo, String ubicacion, int horaIngreso){
        int puesto = -1;
        boolean bandera = false;
        parqueo = new Parqueo(tamano, vehiculo, ubicacion, horaIngreso); //Crear parqueo
        for(int i = 0; i < parqueos.size() && bandera == false ; i++)
            if (parqueos.get(i) == null){
                puesto = i; //Primer puesto disponible
                bandera = true;
            }
        for (int i = 0; i < parqueos.size(); i++) 
            frecuencia_parqueos.add(0);       
        if (puesto >= 0){ //Verificar si hay espacio en el parqueo
            int puesto_ocupado = frecuencia_parqueos.get(puesto) + 1;
            frecuencia_parqueos.set(puesto, puesto_ocupado);
            parqueos.set(puesto, parqueo);
        }
        else vehiculos_rechazados++;
        return puesto;
    }
    //***************************************************************

    /****************************************************************
     * retirarCarro: Retira un vehiculo del estacionamiento e indica que puesto de parqueo se vacio
     * @param hora_egreso
     * @param numero_parqueo
     * @return puesto
     * @throws Exception
     */
    public int retirarCarro(int hora_egreso, int numero_parqueo) throws Exception{
        try {
            vehiculos_retirados++;
            int hora_ingreso = parqueos.get(numero_parqueo).datosInt()[0]; 
            horas_vehiculos += calculoHorasTotales(hora_egreso, hora_ingreso);
            for (int i = hora_ingreso; i <= hora_egreso; i++){
                horas[i]++;  
            }
            parqueos.set(numero_parqueo, null);
        }
        catch (Exception e){
            String s = "Estacionamiento.retirarCarro:" + e.getMessage();
			throw new Exception(s);
        }
        return numero_parqueo;
    }
    //***************************************************************

    /****************************************************************
     * calculoHorasTotales: calcula las horas que estuvo el vehiculo en el parqueo
     * @param hora_egreso
     * @param hora_ingreso
     * @return horasTotales
     */
    private int calculoHorasTotales (int hora_egreso, int hora_ingreso){
        return Math.abs(hora_egreso - hora_ingreso);
    }
    //***************************************************************

    /****************************************************************
     * aumentarEspacio: aumenta el espacio disponible del parqueo
     * @param espacio
     */
    public void aumentarEspacio(int espacio){
        int espacio_nuevo = espacio + parqueos.size();
        for (int i = parqueos.size(); i < espacio_nuevo; i++){
            parqueos.add(i, null);
        }
    }
    //***************************************************************

    /****************************************************************
     * intervalosHorario: obtiene la hora mínima mas frecuente y la hora maxima mas frecuente y las concatena como rango
     * @return rango
     * @throws Exception
     */
    public String intervalosHorario() throws Exception{
        try {
            int hora_usada = Arrays.stream(horas).max().getAsInt(); //Obtener hora mas frecuente
            boolean bandera = false;
            int hora_minima = 0; int hora_maxima = 23;
            for (int i = 0; i < horas.length && bandera == false ; i++){ //Busqueda de hora mas frecuente minima
                if (hora_usada == horas[i]){
                    hora_minima = i; 
                    bandera = true;
                }
            }
            for (int i = 0; i < horas.length; i++){ //Busqueda de hora mas frecuente maxima
                if (hora_usada == horas[i])
                    hora_maxima = i;
            }
            String rango = hora_minima + "-" + hora_maxima;
            return rango;  
        }  catch (Exception e) {
            String s = "estacionamiento.intervalosHorario: " + e.getMessage();
            throw new Exception(s);
        }
    }
    //***************************************************************

    /****************************************************************
     * tiempoPromedio: calcula el tiempo promedio por vehiculo que usa el parqueo
     * @return promedio
     * @throws Exception
     */
    public int tiempoPromedio() throws Exception{
        int promedio = 0;
        try{
            promedio = horas_vehiculos/vehiculos_retirados;
        } catch (ArithmeticException e){
            String s = "estacionamiento.tiempoPromedio: " + e.getMessage() + " --Tiene que retirar vehiculos para saber cual es el tiempo promedio";
            throw new Exception(s);
        }
        return promedio;
    }
    //***************************************************************

    /****************************************************************
     * parqueoMasUsado: obtiene el maximo de la frecuencia de parqueos y verifica el indice que corresponde a dicha frecuencia
     * @return parqueo
     * @throws Exception
     */
    public int parqueoMasUsado() throws Exception{
        int parqueo = 0;
        try{
            int veces_usado = Collections.max(frecuencia_parqueos); //Obtener maxima frecuencia
            boolean bandera = false;
            for (int i = 0; i < frecuencia_parqueos.size() && bandera == false; i++) //Indice al que corresponde la frecuencia
                if (veces_usado == frecuencia_parqueos.get(i)){
                    parqueo = i;
                    bandera = true;
                }
        } catch (Exception e) {
            String s = "estacionamiento.parqueoMasUsado: " + e.getMessage() + " -- Tiene que ingresar vehiculos para saber cual es el parqueo más usado";
            throw new Exception(s);
        }
        return parqueo;
    }
    //***************************************************************

    /****************************************************************
     * vehiculosRechazados: getter de la variable vehiculos_rechazados
     * @return vehiculos_rechazados
     */
    public int vehiculosRechazados(){
        return vehiculos_rechazados;
    }
    //****************************************************************

    /*****************************************************************
     * marcaMasUsada: creacion de arreglo dinamico con todas las marcas, para luego obtener la maxima frecuencia siendo este valor el indice del arreglo de marcas
     * @return marca_usada
     */
    public String marcaMasUsada(){
        ArrayList <String> marcas = new ArrayList<>();
        int frecuencia = 0;
        String marca_usada ="";
        for (int i = 0; i < parqueos.size();i++){
            if (parqueos.get(i) != null){
                Vehiculo vehiculo_actual = parqueos.get(i).getVehiculo();
                String marca = vehiculo_actual.getDatos()[0];
                marcas.add(marca); //Obtener todas las marcas de todos los vehiculos en el estacionamiento
            }
        }
        for (int i = 0; i < marcas.size(); i++){ //Proceso de verificacion de la frecuencia mas grande
            int frecuencia_actual = 0;
            for (int j = 0; j <marcas.size(); j++){
                if(marcas.get(i).equals(marcas.get(j)))
                    frecuencia_actual++;
                if(frecuencia_actual > frecuencia){ //mira si la frecuencia es menor que la frecuencia del elemento a evaluar
                    marca_usada = marcas.get(i);
                    frecuencia = frecuencia_actual;
                }
            }
        }
        return marca_usada;     
    }
    //***************************************************************

    /****************************************************************
     * caracteristicasParqueo: creacion de arreglo con datos para evaluar su frecuencia y evaluar cual es mayor
     * @return datos
     */
    public String[] caracteristicasParqueo(){
        ArrayList <String> tamanos = new ArrayList<>(); ArrayList <String> ubicaciones = new ArrayList<>();
        int frecuencia_tamano = 0; int frecuencia_ubicaciones = 0;
        String tamano_usado = ""; String ubicacion_usada = "";
        for (int i = 0; i < parqueos.size(); i++){ //Ver todos los tamanos
            if (parqueos.get(i) != null){
                String tamano = parqueos.get(i).datosStrings()[0];
                String ubicacion = parqueos.get(i).datosStrings()[1];
                tamanos.add(tamano); ubicaciones.add(ubicacion);
            }
        }
        for (int i = 0; i < tamanos.size(); i++){//Tamano mas frecuente
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
        for (int i = 0; i <ubicaciones.size(); i++){//Ubicacion mas frecuente
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
        return datos;
    }
    //***************************************************************

    /****************************************************************
     * escribirArchivo: al salir, guarda todos los datos llenos y vacios en estacionamiento.txt
     * @throws Exception
     */
    public void escribirArchivo() throws Exception{
        try{
            for (int i = 0; i < parqueos.size(); i++){
                if(parqueos.get(i) != null){
                    Parqueo parqueo = parqueos.get(i);
                    int horaIngreso = parqueo.datosInt()[0];
                    String tamano = parqueo.datosStrings()[0]; String ubicacion = parqueo.datosStrings()[1];
                    Vehiculo vehiculo = parqueo.getVehiculo();
                    String marca = vehiculo.getDatos()[0]; String placa = vehiculo.getDatos()[1]; String modelo = vehiculo.getDatos()[2];

                    texto += i +";" + placa +";" + marca +";" + modelo +";" + tamano +";" + ubicacion +";" + horaIngreso +"\n"; //Parqueos llenos
                }

                if(parqueos.get(i) == null){ 
                    texto += i +";" + "" +";" + "" +";" + "" +";" + "" +";" + "" +";" + "0" +"\n"; //Parqueos vacios
                }
            }
            FileWriter f = new FileWriter("estacionamiento.txt"); //Escribir
            f.write(texto);
            f.close();
        } 
        catch (IOException e){
            String s = "Estacionamiento.escribirArchivo:" + e.getMessage() + " -- Algo ha pasado mal";
			throw new Exception(s);
        }
    }
    //***************************************************************

    /****************************************************************
     * leerArchivo: lectura del archivo estacionamiento.txt y asignacion de valores al arreglo dinamico de estacionamientos
     * @param espacios
     * @return
     * @throws Exception
     */
    public boolean leerArchivo(int espacios) throws Exception{
        boolean leerArchivo = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String datos; String[] datos_separados = new String[7]; 
            int espacio_actual = parqueos.size();
            for (int i = espacio_actual; i < espacios; i++) { //Parqueos extras a los 5
                    parqueos.add(i, null); 
            }
            while ((datos = br.readLine()) != null){ //Lectura de archivo
                datos_separados = datos.split(";");
                int posicion = Integer.parseInt(datos_separados[0]);
                String placa = datos_separados[1]; String marca = datos_separados[2]; String modelo = datos_separados[3];
                Vehiculo vehiculo = new Vehiculo(marca, placa, modelo);
                String tamano = datos_separados[4]; String ubicacion = datos_separados[5]; int horaIngreso = Integer.parseInt(datos_separados[6]);
                Parqueo parqueo = new Parqueo(tamano, vehiculo, ubicacion, horaIngreso);

                if (placa.equals(""))
                    parqueos.set(posicion, null); //Parqueos vacios
                else
                    parqueos.set(posicion, parqueo); //Parqueos con vehiculos
            }
            leerArchivo = true;
        }
        catch (Exception e){
            String s = "Estacionamiento.leerArchivo:" + e.getMessage() + " -- Ingrese correctamente el número de parqueos";
			throw new Exception(s);
        }
        return leerArchivo;
    }
    //***************************************************************
}
