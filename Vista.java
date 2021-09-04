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
        - Última modificación: 18/08/2021

    Clase que tiene como fin ser un sistema I/O para la manipulación del programa
     */ 

     //---------------------------PROPIEDADES-------------------------
    private Scanner scan;

    //---------------------------MÉTODOS-----------------------------

    /****************************************************************
     * Constructor: Instancia el scanner
     */
    public Vista(){
        scan = new Scanner(System.in);
    }
    //****************************************************************

    //---------------------------MENÚ--------------------------------
    /*****************************************************************
     * bienvenida: Imprime un mensaje de bienvenida
     */
    public void bienvenida(){
        System.out.println("\n\nBienvenido al sistema Hogares Temporales para Canes (HTC) ¿Que hara?");
    }
    //****************************************************************
    
    /*****************************************************************
     * menuOpciones: Despliega el menú y recibe la opción del usuario
     * @return opcion
     */
    public int menuOpciones(){
        System.out.println("\n\n1. Abrir archivo con datos previos");
		System.out.println("2. Ingresar un carro");
        System.out.println("3. Retirar un carro");
		System.out.println("4. Ampliar estacionamiento");
        System.out.println("5. Mostrar estadísticas");
		System.out.println("6. Salir\n\n");

        int opcion = Integer.parseInt(scan.nextLine());
        System.out.println("\n");
        return opcion;
    }
    //****************************************************************

    /*****************************************************************
     * despedida: Imprime un mensaje de despedida
     */
    public void despedida(){
        System.out.println("\n\nGracias por haber probado el programa, tenga un feliz día");
        scan.close();
    }
    //****************************************************************

    /*****************************************************************
     * pedirMarca: pide la marca del vehículo
     * @return marca
     */
    public String pedirMarca(){
        System.out.println("Ingrese la marca del vehículo");
        String marca = scan.nextLine();
        return marca;
    }
    //****************************************************************

    /*****************************************************************
     * pedirPlaca: pide la placa del vehículo
     * @return placa
     */
    public String pedirPlaca(){
        System.out.println("Ingrese la placa del vehículo");
        String placa = scan.nextLine();
        return placa;
    }
    //****************************************************************

    /*****************************************************************
     * pedirModelo: pide el modelo del vehículo
     * @return modelo
     */
    public String pedirModelo(){
        System.out.println("Ingrese el modelo del vehículo");
        String modelo = scan.nextLine();
        return modelo;
    }
    //****************************************************************

    /*****************************************************************
     * pedirTamano: pide el tamano del parqueo
     * @return tamano
     */
    public String pedirTamano(){
        System.out.println("Ingrese el tamaño del vehículo");
        String tamano = scan.nextLine();
        return tamano;
    }
    //****************************************************************

    /*****************************************************************
     * pedirUbicacion: pide la ubicacion del parqueo 
     * @return nombre
     */
    public String pedirUbicacion(){
        System.out.println("Ingrese la ubicacion del parqueo");
        String ubicacion = scan.nextLine();
        return ubicacion;
    }
    //****************************************************************

    /*****************************************************************
     * pedirHoraIngreso: pide la hora de ingreso del vehículo
     * @return horaIngreso
     */
    public int pedirHoraIngreso(){
        System.out.println("Ingrese la hora de ingreso del vehículo");
        int horaIngreso = Integer.parseInt(scan.nextLine());
        return horaIngreso;
    }
    //****************************************************************

    /*****************************************************************
     * pedirHoraEgreso: pide la hora de egreso del vehículo
     * @return horaEgreso
     */
    public int pedirHoraEgreso(){
        System.out.println("Ingrese la hora de egreseo del vehículo");
        int horaEgreso = Integer.parseInt(scan.nextLine());
        return horaEgreso;
    }
    //****************************************************************

    /*****************************************************************
     * pedirEspacio: pide el espacio de parqueos en el estacionamiento
     * @return edad
     */
    public int pedirEspacio(){
        System.out.println("Ingrese el espacio de parqueos del estacionamiento");
        int espacio = Integer.parseInt(scan.nextLine());
        return espacio;
    }
    //****************************************************************

    /*****************************************************************
     * pedirNumeroParqueo: pide el número de parqueo donde está asignado la persona
     * @return numero_parqueo
     */
    public int pedirNumeroParqueo(){
        System.out.println("Ingrese su número de parqueo asignado");
        int numero_parqueo = Integer.parseInt(scan.nextLine());
        return numero_parqueo;
    }
    //****************************************************************
}
