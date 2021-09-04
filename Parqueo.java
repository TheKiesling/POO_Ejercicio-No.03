/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

public class Parqueo {
    /** 
    @author: José Pablo Kiesling Lange
    Nombre del programa: Parqueo.java
    @version: 
        - Creación: 31/08/2021
        - Última modificación: 31/08/2021

    Clase que crea espacios de parqueos junto con las características de este y el vehículo que almacenan
    */

    //---------------------------PROPIEDADES-------------------------
    private String tamano;
    private Vehiculo vehiculo;
    private String ubicacion;
    private int horaIngreso; 
    private int horasTotales;
    private int vecesUsado = 0;

    //---------------------------MÉTODOS-----------------------------

    /****************************************************************
     * instancia de los atributos del parqueo con los valores obtenidos a través de los parámetros. 
     * @param tamano
     * @param vehiculo
     * @param ubicacion
     * @param horaIngreso
     */
    public Parqueo(String tamano, Vehiculo vehiculo, String ubicacion, int horaIngreso){ //overloading
        this.tamano = tamano;
        this.vehiculo = vehiculo;
        this.ubicacion = ubicacion;
        this.horaIngreso = horaIngreso;
        vecesUsado++;
    }
    //***************************************************************

    public Parqueo(){
        
    }

    /****************************************************************
     * Cálculo de las horas que estuvo el carro en el parqueo
     * @param horaEgreso
     */
    public void calculoHorasTotales(int horaEgreso){
        horasTotales = horaEgreso - horaIngreso;
    }
    //***************************************************************

    /****************************************************************
     * devuelve un arreglo con los datos Strings del parqueo: tamano y ubicacion
     * @return datosParqueo
     */
    public String[] datosStrings(){
        String[] datosParqueo = new String[2];
        datosParqueo[0] = tamano; datosParqueo[1] = ubicacion;
        return datosParqueo;
    }
    //***************************************************************

    /****************************************************************
     * devuelve el vehículo del parqueo
     * @return vehiculo
     */
    public Vehiculo getVehiculo(){
        return vehiculo;
    }
    //***************************************************************

    /****************************************************************
     * devuelve un arreglo con los datos ints del parqueo: horaIngreso, horasTotales y vecesUsado
     * @return datosParqueo
     */
    public int[] datosInt(){
        int[] datosParqueo = new int[3];
        datosParqueo[0] = horaIngreso; datosParqueo[1] = horasTotales; datosParqueo[2] = vecesUsado;
        return datosParqueo;
    }
    //***************************************************************
}
