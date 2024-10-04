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
    public String estado; /*Para saber cuando trabaja o vigila*/
    public int salarioAcumulado;
    public int computadora;
    public int computadoraTG;
    public int tiempoDia;
    public Compania compania;
    
    public Director(Compania compania, int tiempoDia, Semaphore luzTrafico){
        this.compania=compania;
        this.luzTrafico= luzTrafico;
        this.tiempoDia= tiempoDia;
        this.salarioAcumulado=0;
    }
    
    public void EstadoEnviandoComputadoras(){
        this.estado= "Envia computadoras";
    }
    
    public void EstadoDeTrabajo(){
        this.estado="Labores administrativas";
    }
    
    public void EstadoVigilancia(){
        this.estado="Revisa que esta haciendo el Product Manager";
    }
    
    
    public void PagoDirector(){
        this.salarioAcumulado+=60*24;
    }
    
    public void EnviaComputadoras(){
        if (Compania.tipo==0){
            this.compania.sumGanancias(this.compania.almacen.computadora*80000);
            this.compania.sumGanancias(this.compania.almacen.computadoraTG*120000);    
        }else if (Compania.tipo==1){
            this.compania.sumGanancias(this.compania.almacen.computadora*180000);
            this.compania.sumGanancias(this.compania.almacen.computadoraTG*250000);  
        }
    }
    
   
    
    public void Trabaja(){
        Random aleatorio = new Random();
        int aleatorioNum= aleatorio.nextInt(25);/*0 del marcador mas 24 dias*/
        
        try{
            if(this.compania.contador.tiempoRestante>0){
                EstadoDeTrabajo();
                Director.sleep((this.tiempoDia/24)*aleatorioNum);
                EstadoVigilancia();
                Director.sleep((long)((this.tiempoDia/24)*0.583));
                EstadoDeTrabajo();
                Director.sleep(((this.tiempoDia)-(this.tiempoDia/24))*aleatorioNum);  
            }else{
                EstadoEnviandoComputadoras();
                Director.sleep(this.tiempoDia);
                this.EnviaComputadoras();
                this.compania.almacen.reiniciarContadorComputadoras();
                this.luzTrafico.acquire();
                this.compania.contador.reiniciar();
                this.luzTrafico.release();
            }
        } catch(InterruptedException ex){
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
        
    public void PagoTrabajo(){
        while(true){
            PagoDirector();
            Trabaja();
            }
        }
        
        
    
}
