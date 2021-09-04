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
        Parqueo parqueo = new Parqueo();
        Estacionamiento estacionamiento = new Estacionamiento();
        Vista vista = new Vista();

        //Saludar al usuario
        vista.bienvenida();

        int opcion = -1;
        while (opcion != 6){
            //Despliegue de las opciones del menú y su previa lectura de dicha opción
            opcion = vista.menuOpciones();

            //Leer Archivo
            if (opcion == 1){
                estacionamiento.leerArchivo(); //POner al inicio la opcion---------------------------------------- SI / NO
            }
            
            //Agregar un carro
            if (opcion == 2){
                String placa = vista.pedirPlaca();
                String marca = vista.pedirMarca();
                String modelo = vista.pedirModelo();

                String tamano = vista.pedirTamano();
                String ubicacion = vista.pedirUbicacion();
                int horaIngreso = vista.pedirHoraIngreso();

                vehiculo = new Vehiculo(marca, placa, modelo);
                parqueo = new Parqueo(tamano, vehiculo, ubicacion, horaIngreso);
                estacionamiento.agregarCarro(tamano, vehiculo, ubicacion, horaIngreso);
            }

            if (opcion == 3){
                int horaEgreso = vista.pedirHoraEgreso();
                parqueo.calculoHorasTotales(horaEgreso);
                int numero_parqueo = vista.pedirNumeroParqueo();
                estacionamiento.retirarCarro(numero_parqueo);
            }

            //Salir
            if(opcion == 6){
                estacionamiento.escribirArchivo();
                vista.despedida();
            }
        }


    }
}
