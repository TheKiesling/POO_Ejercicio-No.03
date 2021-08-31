/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

public class Vehiculo {
    /** 
    @author: José Pablo Kiesling Lange
    Nombre del programa: Vehiculo.java
    @version: 
        - Creación: 31/08/2021
        - Última modificación: 31/08/2021

    Clase que crea vehículos junto con sus datos
    */

    //---------------------------PROPIEDADES-------------------------
    private String marca;
    private String placa;
    private String modelo;

    //---------------------------MÉTODOS-----------------------------
    /**
     * instancia de los atributos del vehículo con sus valores iniciales obtenidos como parámetros del método
     * @param marca
     * @param placa
     * @param modelo
     */
    public Vehiculo(String marca, String placa, String modelo){
        this.marca = marca;
        this.placa = placa;
        this.modelo = modelo;
    }

    /**
     * devuelve un arreglo con los datos del vehículo
     * @return datosVehiculo
     */
    public String[] getDatos(){
        String[] datosVehiculo = new String[3];
        datosVehiculo[0] = this.marca; datosVehiculo[1] = this.placa; datosVehiculo[2] = this.modelo;
        return datosVehiculo;
    }
}
