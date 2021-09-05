/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

import java.util.Scanner;


public class Vista {
    /**
     @author: José Pablo Kiesling Lange
    Nombre del programa: Vista.java
    @version: 
        - Creación: 02/09/2021
        - Última modificación: 05/09/2021

    Clase que tiene como fin ser un sistema I/O para la manipulación del programa
     */ 

    //---------------------------PROPIEDADES--------------------------
    private Scanner scan;

    //---------------------------MÉTODOS------------------------------

    /*****************************************************************
     * Constructor: Instancia el scanner
     */
    public Vista(){
        scan = new Scanner(System.in);
    }
    //****************************************************************

    /*****************************************************************
     * error: captura errores y muestra el mensaje que se obtuvo
     * @param e
     */
    public void error(Exception e){
        System.out.println("Ha ocurrido un error : -- " + e.getMessage()); 
    }
    //****************************************************************

    //---------------------------MENÚ---------------------------------
    /*****************************************************************
     * bienvenida: Imprime un mensaje de bienvenida
     */
    public void bienvenida(){
        System.out.println("\n\nBienvenido al sistema de parqueos de Hogares Temporales para Canes. ¿Que hara?");
    }
    //****************************************************************
    
    /*****************************************************************
     * abrirArchivo: pregunta al usuario si desea abrir el archivo con los datos
     * @return
     */
    public boolean abrirArchivo(){
        System.out.println("Desea abrir el archivo 'estacionamiento.txt' que contiene los datos actuales del parqueo? (Si/No)");
        
        boolean bandera = false;
        boolean abrir_archivo = false;

        while (!bandera){
            String abrir = scan.nextLine();
            if (abrir.equals("Si")) {
                System.out.println("Abriendo archivo...\n\n");
                bandera = true;
                abrir_archivo = true;
            }
            else if (abrir.equals("No"))
                bandera = true;
            else
                System.out.println("Ingrese Si o No");
        }
        return abrir_archivo;
    }
    //****************************************************************

    /*****************************************************************
     * menuOpciones: Despliega el menú y recibe la opción del usuario
     * @return opcion
     * @throws Exception si no se ingresa un valor entero
     */
    public int menuOpciones() throws Exception{
        int opcion = -1;
        try{  
            System.out.println("\n\nBienvenido al sistema de parqueos de Hogares Temporales para Canes. ¿Que hara?");
            System.out.println("\n\n1. Ingresar un carro");
            System.out.println("2. Retirar un carro");
            System.out.println("3. Ampliar estacionamiento");
            System.out.println("4. Mostrar estadísticas");
            System.out.println("5. Guardar y Salir\n\n");

            opcion = Integer.parseInt(scan.nextLine());
            System.out.println("\n");
            return opcion;
        } catch (Exception e){
            String s = "vista.menuOpciones: " + e.getMessage() + " -- Ingrese un número válido de las opciones";
            throw new Exception(s);
        }
    }
    //****************************************************************

    /*****************************************************************
     * despedida: Imprime un mensaje de despedida
     */
    public void despedida(){
        System.out.println("Se está guardando el archivo, espere un momento...");
        System.out.println("Archivo guardado con éxito");
        System.out.println("\nGracias por haber probado el programa, tenga un feliz día");
        scan.close();
    }
    //****************************************************************

    /*****************************************************************
     * pedirMarca: pide la marca del vehículo
     * @return marca
     */
    public String pedirMarca(){
        System.out.println("Ingrese la marca del vehículo");
        boolean bandera = false;
        String marca = "";
        while (!bandera){
            marca = scan.nextLine();
            System.out.println();
            if (marca.equals("") || marca.equals(";")) System.out.println("ERROR: Ingrese una marca valida"); 
            else bandera = true;
        }
        return marca;
    }
    //****************************************************************

    /*****************************************************************
     * pedirPlaca: pide la placa del vehículo
     * @return placa
     */
    public String pedirPlaca(){
        System.out.println("Ingrese la placa del vehículo");
        boolean bandera = false;
        String placa = "";
        while (!bandera){
            placa = scan.nextLine();
            System.out.println();
            if (placa.equals("") || placa.equals(";")) System.out.println("ERROR: Ingrese una placa valida"); 
            else bandera = true;
        }
        return placa;
    }
    //****************************************************************

    /*****************************************************************
     * pedirModelo: pide el modelo del vehículo
     * @return modelo
     */
    public String pedirModelo(){
        System.out.println("Ingrese el modelo del vehículo");
        boolean bandera = false;
        String modelo = "";
        while (!bandera){
            modelo = scan.nextLine();
            System.out.println();
            if (modelo.equals("") || modelo.equals(";")) System.out.println("ERROR: Ingrese un modelo valido"); 
            else bandera = true;
        }
        return modelo;
    }
    //****************************************************************

    /*****************************************************************
     * pedirTamano: pide el tamano del parqueo
     * @return tamano
     */
    public String pedirTamano(){
        System.out.println("Ingrese el tamaño del vehículo (Compacto/Estandar/Largo)");
        boolean bandera = false;
        String tamano = "";
        while (!bandera){
            tamano = scan.nextLine();
            System.out.println();
            if (tamano.equals("Compacto") || tamano.equals("Estandar") || tamano.equals("Largo")) bandera = true;
            else System.out.println("ERROR: Ingrese un tamano valido"); 
        }
        return tamano;
    }
    //****************************************************************

    /*****************************************************************
     * pedirUbicacion: pide la ubicacion del parqueo 
     * @return nombre
     */
    public String pedirUbicacion(){
        System.out.println("Ingrese la ubicacion del parqueo (Techado/Aereo)");
        boolean bandera = false;
        String ubicacion = "";
        while (!bandera){
            ubicacion = scan.nextLine();
            System.out.println();
            if (ubicacion.equals("Techado") || ubicacion.equals("Aereo")) bandera = true;
            else System.out.println("ERROR: Ingrese una ubicacion valida"); 
        }
        return ubicacion;
    }
    //****************************************************************

    /*****************************************************************
     * pedirHoraIngreso: pide la hora de ingreso del vehículo
     * @return horaIngreso
     * @throws Exception si no se ingresa un valor entero
     */
    public int pedirHoraIngreso() throws Exception{
        int horaIngreso = 0;
        try{
            System.out.println("Ingrese la Hora de Ingreso del vehículo en valores enteros y en formato de 24 horas (0-23)");
            boolean bandera = false;
            while (!bandera){
                horaIngreso = Integer.parseInt(scan.nextLine());
                System.out.println();
                if (horaIngreso >= 0 && horaIngreso < 24) bandera = true;
                else System.out.println("ERROR: Ingrese una hora valida"); 
            }
        } catch (Exception e){
            String s = "vista.pedirHoraIngreso: " + e.getMessage() + " -- Ingrese un número entero";
            throw new Exception(s);
        }
        return horaIngreso;
    }
    //****************************************************************

    /*****************************************************************
     * pedirHoraEgreso: pide la hora de egreso del vehículo
     * @return horaEgreso
     * @throws Exception si no se ingresa un valor entero
     */
    public int pedirHoraEgreso() throws Exception{
        int horaEgreso = 0;
        try{
            System.out.println("Ingrese la Hora de Egreso del vehículo en valores enteros y en formato de 24 horas (0-23)");
            boolean bandera = false;
            while (!bandera){
                horaEgreso = Integer.parseInt(scan.nextLine());
                System.out.println();
                if (horaEgreso >= 0 && horaEgreso < 24) bandera = true;
                else System.out.println("ERROR: Ingrese una hora valida"); 
            }
        } catch (Exception e){
            String s = "vista.pedirHoraEgreso: " + e.getMessage() + " -- Ingrese un número entero";
            throw new Exception(s);
        }
        return horaEgreso;
    }
    //****************************************************************

    /*****************************************************************
     * pedirEspacio: pide el espacio de parqueos en el estacionamiento
     * @return edad
     * @throws Exception si no se ingresa un valor entero
     */
    public int pedirEspacio() throws Exception{
        int espacio = 0;
        try{
            System.out.println("Ingrese cuantos espacios de parqueos quiere agregar al estacionamiento");
            boolean bandera = false;
            while (!bandera){
                espacio = Integer.parseInt(scan.nextLine());
                System.out.println();
                if (espacio > 0) bandera = true;
                else System.out.println("ERROR: Ingrese un número positivo"); 
            }
        } catch (Exception e){
            String s = "vista.pedirEspacio: " + e.getMessage() + " -- Ingrese un número entero positivo";
            throw new Exception(s);
        }
        return espacio;
    }
    //****************************************************************

    /*****************************************************************
     * pedirNumeroParqueo: pide el número de parqueo donde está asignado el vehiculo
     * @return numero_parqueo
     * @throws Exception si no se ingresa un valor entero
     */
    public int pedirNumeroParqueo() throws Exception{
        int numero_parqueo = 0;
        try{
            System.out.println("Ingrese su número de parqueo asignado");
            boolean bandera = false;
            while (!bandera){
                numero_parqueo = Integer.parseInt(scan.nextLine());
                System.out.println();
                if (numero_parqueo >= 0) bandera = true;
                else System.out.println("ERROR: Ingrese un número de parqueo valido"); 
            }
        } catch (Exception e){
            String s = "vista.pedirNumeroParqueo: " + e.getMessage() + " -- Ingrese un número entero";
            throw new Exception(s);
        }
        return numero_parqueo;
    }
    //****************************************************************

    /*****************************************************************
     * pedirNumeroParqueos: Pide el número de parqueos en el archivo
     * @return
     * @throws Exception
     */
    public int pedirNumeroParqueos() throws Exception{
        int numero_parqueos = 0;
        try{
            System.out.println("Ingrese el número de parqueos actuales en el archivo 'estacionamiento.txt'");
            boolean bandera = false;
            while (!bandera){
                numero_parqueos = Integer.parseInt(scan.nextLine());
                System.out.println();
                if (numero_parqueos >= 5) bandera = true;
                else System.out.println("ERROR: Ingrese un número de parqueos valido"); 
            }
        } catch (Exception e){
            String s = "vista.pedirNumeroParqueo: " + e.getMessage() + " -- Ingrese un número entero";
            throw new Exception(s);
        }
        return numero_parqueos;
    }
    //****************************************************************

    /*****************************************************************
     * leerArchivo: verifica si se pudo leer el archivo
     * @param leerArchivo
     */
    public void lecturaArchivo (boolean leerArchivo){
        if (leerArchivo)
            System.out.println("Archivo abierto con éxito");
        else
            System.out.println("Verifique el espacio de parqueos e ingreselo correctamente");
    }
    //***************************************************************

    /****************************************************************
     * agregarCarro: indica si se pudo agregar un vehiculo o no. De hacerlo, indica el puesto donde se ocupa el vehiculo.
     * @param puesto
     */
    public void agregarCarro(int puesto){
        if (puesto >= 0)
            System.out.println("Ingreso de vehiculo exitoso. El puesto asignado es: " + puesto);
        else
            System.out.println("Espacio de parqueo lleno - VEHICULO RECHAZADO - ");
    }
    //***************************************************************

    /****************************************************************
     * retirarCarro: indica de donde se retiro el carro
     * @param puesto
     */
    public void retirarCarro(int puesto){
        if (puesto >= 0)
            System.out.println("Egreso de vehiculo exitoso. El puesto del parqueo libre es: " + puesto);
    }
    //***************************************************************

    /****************************************************************
     * intervalosHorario: imprime el rango de horas más frecuentes
     * @param rango
     */
    public void intervalosHorario(String rango){
        System.out.println("El intervalo de horas que es más usual es: " + rango);
    }
    //***************************************************************

    /****************************************************************
     * tiempoPromedio: imprime el tiempo promedio por vehiculo
     * @param tiempo_promedio
     */
    public void tiempoPromedio(int tiempo_promedio){
        System.out.println("El tiempo promedio por vehículo es: " + tiempo_promedio);
    }
    //***************************************************************

    /****************************************************************
     * parqueoMasUsado: imprime el parqueo mas usado
     * @param parqueo_usado
     */
    public void parqueoMasUsado(int parqueo_usado){
        System.out.println("El parqueo más usado es: " + parqueo_usado);
    }
    //***************************************************************

    /****************************************************************
     * vehiculosRechazados: imprime la cantidad de vehiculos rechazados
     * @param vehiculos_rechazados
     */
    public void vehiculosRechazados(int vehiculos_rechazados){
        System.out.println("Se han rechazado: " + vehiculos_rechazados + " vehiculos");
    }
    //***************************************************************

    /****************************************************************
     * marcaMasUsada: imprime cual es la marca que mas se usa en el parqueo
     * @param marca_usada
     */
    public void marcaMasUsada(String marca_usada){
        System.out.println("La marca mas usada en el parqueo es: " + marca_usada);
    }
    //***************************************************************

    /****************************************************************
     * caracteristicasParqueo: imprime las caracteristicas de los parqueos mas usados
     * @param datos
     */
    public void caracteristicasParqueo(String[] datos){
        System.out.println("Las características de los parqueos más usados son de tamano: " + datos[0] + " y de ubicacion: " + datos[1]);
    }
    //***************************************************************
}
