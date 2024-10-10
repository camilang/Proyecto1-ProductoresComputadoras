/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Camila Garcia
 */
public class Director extends Thread{
    //Definicion de variables
   public Semaphore semaforo;
    public String estado;
    public int salarioAcumulado; 
    public int computadora; 
    public int computadoraTG;
    public int tiempoDia;
    public Compania compania;
    
    //Constructor de la clase
    public Director (Compania compania, int tiempoDia, Semaphore semaforo) {
        this.compania = compania;
        this.semaforo = semaforo;
        this.tiempoDia = tiempoDia;
        this.salarioAcumulado = 0;
        
    }
    
    //Estados de trabajo en el que se puede encontrar el Director
    public void estadoTrabajo(){
        this.estado = "Labores administrativas";
    }
    
    public void estadoVigilancia(){
        this.estado = "Vigilando PM";
    }
    
    public void estadoEnviando(){
        this.estado = "Enviando computadoras";
    }
    
    //Funcion para determinar el salario acumulado del Director
    public void pagoTrabajadores(){
        this.salarioAcumulado += 60*24;
    }
    
    //Funcion para determinar la ganancia de acuerdo a las computadoras enviadas
    public void enviaCompu(){
        if (Compania.tipo == 0) {
            this.compania.sumaGanancias(this.compania.almacen.computadora * 80000);
            this.compania.sumaGanancias(this.compania.almacen.computadoraTG * 120000);
        } else if (Compania.tipo == 1) {
            this.compania.sumaGanancias(this.compania.almacen.computadora * 180000);
            this.compania.sumaGanancias(this.compania.almacen.computadoraTG * 250000);
        }
    }
    
    //Funcion para realizar todas las labores del trabajo
    public void trabaja(){
        Random random = new Random();
        int aleatorio = random.nextInt(25);

        try {    
            if (this.compania.contador.diasRestantes > 0) {          
                estadoTrabajo();
                Director.sleep(hora()*aleatorio);
                estadoVigilancia();                   
                Director.sleep((long)(hora()*0.58333333));
                estadoTrabajo();
                Director.sleep(this.tiempoDia-hora()*aleatorio);
            } else {
                estadoEnviando();
                Director.sleep(this.tiempoDia);
                this.enviaCompu();
                this.compania.almacen.reiniciarComputadoras();
                this.semaforo.acquire();
                    this.compania.contador.reiniciar();
                this.semaforo.release();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Funcion para sacar la duracion de la hora de acuerdo al parametro
    public int hora(){
        return this.tiempoDia/24;
    }
    
    
    @Override
    public void run(){
        while (true){
            pagoTrabajadores();
            trabaja();
        }
        
        
        
    }
    
 
}
