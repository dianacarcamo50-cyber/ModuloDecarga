/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Garme
 */

public class TiempoExcedidoException extends Exception {
    private double tiempoExcedido; 
    
    public TiempoExcedidoException(String mensaje) {
        super(mensaje);
    }
}