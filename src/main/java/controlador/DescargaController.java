/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.ArchivoDescarga;
import vista.DescargaForm;
import javax.swing.SwingUtilities;

/**
 *
 * @author Garme
 */
public class DescargaController {
    private DescargaForm vista;

    public DescargaController(DescargaForm vista) {
        this.vista = vista;
    }

    public void iniciarSimulacion(int tiempoEstimado, int tiempoLimite) {
        ArchivoDescarga.DescargaListener listener = new ArchivoDescarga.DescargaListener() {
            @Override
            public void onInicio() {
                System.out.println("Estado: Proceso iniciado...");
            }

            @Override
            public void onProgreso(int porcentaje) {
                System.out.println("Progreso: " + porcentaje + "%");
            }

            @Override
            public void onExito(String mensaje) {
                System.out.println("Resultado: " + mensaje);
            }

            @Override
            public void onError(String error) {
                System.err.println("Error: " + error);
            }
        };

        ArchivoDescarga tarea = new ArchivoDescarga(tiempoEstimado, tiempoLimite, listener);
        new Thread(tarea).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DescargaForm form = new DescargaForm();
            DescargaController controlador = new DescargaController(form);
            
            controlador.iniciarSimulacion(5, 3);
            
            form.setVisible(true);
        });
    }
}