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
    public Semaphore luzTrafico;
    public String estado; //Para saber cuando trabaja o vigila
    public int salarioAcumulado;
    public int computadora;
    public int computadoraTG;
    public int tiempoDia;
    public Compania compania;
    
    //Constructor con la información del director
    public Director(Compania compania, int tiempoDia, Semaphore luzTrafico){
        this.compania=compania;
        this.luzTrafico= luzTrafico;
        this.tiempoDia= tiempoDia;
        this.salarioAcumulado=0;
    }
    
    //Función cuando el director envia computadoras
    public void EstadoEnviandoComputadoras(){
        this.estado= "Envia computadoras";
    }
    
    //Funcion cuando el director realiza labores administrativas
    public void EstadoDeTrabajo(){
        this.estado="Labores administrativas";
    }
    
    //Funcion cuando vigila al pm
    public void EstadoVigilancia(){
        this.estado="Revisa que esta haciendo el Product Manager";
    }
    
    //Funcion que determina el pago del director 
    public void PagoDirector(){
        this.salarioAcumulado+=60*24;
    }
    
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
            }
        } catch(InterruptedException ex){
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    //Funcion para ejecutar el trabajo y pago
    public void PagoTrabajo(){
        while(true){
            PagoDirector();
            Trabaja();
            }
        }
    
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
}
