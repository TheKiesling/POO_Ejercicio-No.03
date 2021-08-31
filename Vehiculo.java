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

    public String[] getDatos(){
        String[] datos = new String[3];
        datos[0] = this.marca; datos[1] = this.placa; datos[2] = this.modelo;
        return datos;
    }
}
