/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import excepciones.TiempoExcedidoException;

/**
 * @author Garme
 */
public class ArchivoDescarga implements Runnable {
    private int tiempoEstimado;
    private int tiempoLimite;
    private DescargaListener listener;

    public interface DescargaListener {
        void onInicio();
        void onProgreso(int porcentaje);
        void onExito(String mensaje);
        void onError(String error);
    }

    public ArchivoDescarga(int tiempoEstimado, int tiempoLimite, DescargaListener listener) {
        this.tiempoEstimado = tiempoEstimado;
        this.tiempoLimite = tiempoLimite;
        this.listener = listener;
    }

    @Override
    public void run() {
        if (listener != null) {
            listener.onInicio();
        }

        try {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(tiempoEstimado * 100); 
                
                double tiempoActual = i * (tiempoEstimado / 10.0);
                if (tiempoActual > tiempoLimite) {
                    throw new TiempoExcedidoException("La descarga supero el limite de " + tiempoLimite + " segundos");
                }

                if (listener != null) {
                    listener.onProgreso(i * 10);
                }
            }

            if (listener != null) {
                listener.onExito("Descarga completada");
            }

        } catch (TiempoExcedidoException e) {
            if (listener != null) {
                listener.onError(e.getMessage());
            }
        } catch (InterruptedException e) {
            if (listener != null) {
                listener.onError("El proceso de descarga fue interrumpido.");
            }
            Thread.currentThread().interrupt();
        }
    }
}