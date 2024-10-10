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
    public int diasRestantes;
    public int fechaLimite;
    public long tiempoInicio = System.currentTimeMillis();
    public long tiempoRestante;
    public int estado = 0;
    
    public Contador(int diasRestantes){
        this.diasRestantes = diasRestantes;
        this.fechaLimite = diasRestantes;
    }
    // reinicia los dias restantes
    public void reiniciar(){
        this.diasRestantes = this.fechaLimite;
    }
    
    @Override
    public void run(){
        while(true){
            long tiempoTranscurrido = System.currentTimeMillis(); //devuelve el tiempo en milisegundo
            long tiempoActual = tiempoTranscurrido - this.tiempoInicio;
            this.tiempoRestante = Compania.tiempoDia - tiempoActual;

            if (Compania.tiempoDia < tiempoActual) {
                this.tiempoInicio = System.currentTimeMillis();
                this.tiempoRestante = Compania.tiempoDia;
            }
        }
    }
         
}
