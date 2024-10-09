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
   public Semaphore semaforo;
    public String estado;
    public int salarioAcumulado; 
    public int computadora; 
    public int computadoraTG;
    public int tiempoDia;
    public Compania compania;
    
    public Director (Compania compania, int tiempoDia, Semaphore semaforo) {
        this.compania = compania;
        this.semaforo = semaforo;
        this.tiempoDia = tiempoDia;
        this.salarioAcumulado = 0;
        
    }
    
    public void estadoTrabajo(){
        this.estado = "Labores administrativas";
    }
    
    public void estadoVigilancia(){
        this.estado = "Vigilando PM";
    }
    
    public void estadoEnviando(){
        this.estado = "Enviando computadoras";
    }
    
    public void pagoTrabajadores(){
        this.salarioAcumulado += 60*24;
    }
    
<<<<<<< HEAD
    public void enviaCompu(){
        if (Compania.tipo == 0) {
            this.compania.sumaGanancias(this.compania.almacen.computadora * 80000);
            this.compania.sumaGanancias(this.compania.almacen.computadoraTG * 120000);
        } else if (Compania.tipo == 1) {
            this.compania.sumaGanancias(this.compania.almacen.computadora * 180000);
            this.compania.sumaGanancias(this.compania.almacen.computadoraTG * 250000);
        }
    }
    
    public void work(){
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
=======
    //Funcion que registra la nueva ganancia de los  computadores enviados 
    public void EnviaComputadoras(){
        if (Compania.tipo==0){
            this.compania.sumGanancias(this.compania.almacen.computadora * 80000);
            this.compania.sumGanancias(this.compania.almacen.computadoraTG * 120000);    
        }else if (Compania.tipo==1){
            this.compania.sumGanancias(this.compania.almacen.computadora * 180000);
            this.compania.sumGanancias(this.compania.almacen.computadoraTG * 250000);  
        }
    }
    
   
    //Funcion de director se encarga de enviar  todos los computadoras ya creados a las distribuidoras y de sus estados cambiantes
    public void Trabaja(){
        Random aleatorio = new Random();
        int aleatorioNum= aleatorio.nextInt(25);//0 del marcador mas 24 dias
        
        try{
            if(this.compania.contador.tiempoRestante>0){
                EstadoDeTrabajo();
                Director.sleep(hora()*aleatorioNum);
                EstadoVigilancia();
                Director.sleep((long)(hora()*0.583333));
                EstadoDeTrabajo();
                Director.sleep(this.tiempoDia-hora()*aleatorioNum);  
            }else{
                EstadoEnviandoComputadoras();
                Director.sleep(this.tiempoDia);
                this.EnviaComputadoras();
                this.compania.almacen.reiniciarComputadoras();
                this.luzTrafico.acquire();
                this.compania.contador.reiniciar();
                this.luzTrafico.release();
>>>>>>> 1e812416a71107908be302f0cb042a38aed7177f
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int hora(){
        return this.tiempoDia/24;
    }
    
    
    @Override
    public void run(){
        while (true){
            pagoTrabajadores();
            work();
        }
<<<<<<< HEAD
        
        
        
    }
    
 
=======
    
    public int hora(){
        return this.tiempoDia/24;
    }
        
    @Override
    public void run(){
        while (true){
            PagoDirector();
            Trabaja();
        }
        
    }
>>>>>>> 1e812416a71107908be302f0cb042a38aed7177f
}
