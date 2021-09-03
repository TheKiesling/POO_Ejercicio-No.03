/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

public class controlador {
    /** 
    @author: José Pablo Kiesling Lange
    Nombre del programa: Controlador.java
    @version: 
        - Creación: 02/09/2021
        - Última modificación: 02/09/2021

    Clase que comunica el modelo con la vista y controla sus acciones
    */

    //---------------------------MÉTODOS-----------------------------

    public static void main(String[] args) {
        //Instancia de objetos
        Vehiculo vehiculo;
        Parqueo parqueo;
        Estacionamiento estacionamiento;
        Vista vista = new Vista();

        //Saludar al usuario
        vista.bienvenida();

        int opcion = -1;
        while (opcion != 5){
            //Despliegue de las opciones del menú y su previa lectura de dicha opción
            opcion = vista.menuOpciones();

            //Leer Archivo
            if (opcion == 1){

            }
            
            //Agregar un carro
            if (opcion == 2){
                String placa = vista.pedirPlaca;
                String marca = vista.pedirMarca;
                String modelo = vista.pedirModelo;

                String tamano = vista.pedirTamano;
                String ubicacion = vista.pedirUbicacion;
                int horaIngreso = vista.pedirHoraIngreso;
            }
        }


    }
}
