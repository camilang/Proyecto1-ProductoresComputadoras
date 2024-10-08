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
    // reiniciar el contador de dias
    public void reiniciar() {
        this.diasRestantes = this.fechaLimite;
    }
    
    @Override
    public void run(){
        while(true){
            // calcular el tiempo transcurrido
            long tiempoTrasncurrido = System.currentTimeMillis();
            
            // ver el tiempo actual
            
            long tiempoActual = tiempoTrasncurrido - this.tiempoInicio;
            
            // actualizar el tiempo restante
            this.tiempoRestante = Compania.tiempoDia - tiempoActual;

            //Altualizar el dia
            if (Compania.tiempoDia < tiempoActual) {
                this.tiempoInicio = System.currentTimeMillis();
                this.tiempoRestante = Compania.tiempoDia;
            }
        }
    }
         
}
