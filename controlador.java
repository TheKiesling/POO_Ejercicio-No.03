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
        - Última modificación: 05/09/2021

    Clase que comunica el modelo con la vista y controla sus acciones
     * @throws Exception
    */

    //---------------------------MÉTODOS-----------------------------

    public static void main(String[] args) {
        //Instancia de objetos
        Vehiculo vehiculo;
        Parqueo parqueo = new Parqueo();
        Estacionamiento estacionamiento = new Estacionamiento();
        Vista vista = new Vista();

        try{
            //Saludar al usuario
            vista.bienvenida();

            //Verificar si se quiere abrir el archivo o no
            boolean abrir = vista.abrirArchivo();
                
            //Leer Archivo
            if (abrir){
                int espacios = vista.pedirNumeroParqueos();
                boolean leerArchivo = estacionamiento.leerArchivo(espacios); 
                vista.lecturaArchivo(leerArchivo);
            } 

            int opcion = -1;
            while (opcion != 5){
                //Despliegue de las opciones del menú y su previa lectura de dicha opción
                opcion = vista.menuOpciones();
                
                //Agregar un carro
                if (opcion == 1){
                    String placa = vista.pedirPlaca();
                    String marca = vista.pedirMarca();
                    String modelo = vista.pedirModelo();

                    String tamano = vista.pedirTamano();
                    String ubicacion = vista.pedirUbicacion();
                    int horaIngreso = vista.pedirHoraIngreso();

                    vehiculo = new Vehiculo(marca, placa, modelo);
                    parqueo = new Parqueo(tamano, vehiculo, ubicacion, horaIngreso);
                    int puesto = estacionamiento.agregarCarro(tamano, vehiculo, ubicacion, horaIngreso);
                    vista.agregarCarro(puesto);
                }

                //Retirar un carro
                if (opcion == 2){
                    int horaEgreso = vista.pedirHoraEgreso();
                    int numero_parqueo = vista.pedirNumeroParqueo();
                    int puesto = estacionamiento.retirarCarro(horaEgreso, numero_parqueo);
                    vista.retirarCarro(puesto);
                }
                
                //Ampliar estacionamiento
                if (opcion == 3){
                    int espacio = vista.pedirEspacio();
                    estacionamiento.aumentarEspacio(espacio);
                }

                //Mostrar estadísticas
                if (opcion == 4){
                    String rango = estacionamiento.intervalosHorario();
                    vista.intervalosHorario(rango);

                    int tiempo_promedio = estacionamiento.tiempoPromedio();
                    vista.tiempoPromedio(tiempo_promedio);

                    int parqueo_usado = estacionamiento.parqueoMasUsado();
                    vista.parqueoMasUsado(parqueo_usado);

                    int vehiculos_rechazados = estacionamiento.vehiculosRechazados();
                    vista.vehiculosRechazados(vehiculos_rechazados);

                    String marca_usada = estacionamiento.marcaMasUsada();
                    vista.marcaMasUsada(marca_usada);

                    String[] datos = estacionamiento.caracteristicasParqueo();
                    vista.caracteristicasParqueo(datos);
                }

                //Salir
                if(opcion == 5){
                    estacionamiento.escribirArchivo();
                    vista.despedida();
                }
            }
        } catch (Exception e){
            vista.error(e);
        }
    }
}
