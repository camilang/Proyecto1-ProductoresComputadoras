/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author MAURICIO MENDEZ
 */
public class Contador extends Thread {
    public int estado = 0;
    public int fechaLimite;
    public int diasRestantes;
    public long tiempoInicio = System.currentTimeMillis();
    public long tiempoRestante;
    
    
    public Contador(int diasRestantes) {
        this.diasRestantes = diasRestantes;
        this.fechaLimite = diasRestantes;
    }

    public void reiniciar() {
        this.diasRestantes = this.fechaLimite;
    }
    
    @Override
    public void run(){
        while(true){
            long tiempoTrasncurrido = System.currentTimeMillis();
            long tiempoActual = tiempoTrasncurrido - this.tiempoInicio;
            this.tiempoRestante = Compania.tiempoDia - tiempoActual;

            if (Compania.tiempoDia < tiempoActual) {
                this.tiempoInicio = System.currentTimeMillis();
                this.tiempoRestante = Compania.tiempoDia;
            }
        }
    }
         
}
