/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Camila Garcia
 */
public class ProjectM extends Thread{
    public Semaphore luzTrafico;
    public String estado; //Para saber cuando ve One piece y cuando trabaja
    public Director director;
    public Compania compania;
    public int salarioAcumulado;
    public int salarioPerdido;
    public int falla=0;
    public int tiempoDia;
 
    //Constructor
    public ProjectM(int tiempoDia, Semaphore luzTrafico,Director director, Compania compania){
        this.tiempoDia= tiempoDia;
        this.luzTrafico = luzTrafico;
        this.compania= compania;
        this.director=director;
    }
    
    //Funcion que define el pago del pm
    public void PagoProjectM(){
        this.salarioAcumulado+=40*24;
    }
    
   
    //Funcion que cambia su estado a ver anime y pierde dinero por su falla
    public void VeAnime() {
     int aux = 0;
     int mediahora = hora()/2;

     while (aux < 16){
         try {
             this.estado = "Ve One Piece";
             ProjectM.sleep(mediahora);
             if ("Revisa que esta haciendo el Product Manager".equals(this.director.estado)) {
                 this.falla += 1;
                 this.salarioAcumulado -= 100;
                 this.salarioPerdido += 100;
             }
             this.estado = "Trabajando";
             ProjectM.sleep(mediahora);
             aux += 1;
            }catch (InterruptedException ex) {
             Logger.getLogger(ProjectM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }         
   
    //Funcion de estado de trabajo
   public void Trabaja(){
        try{
            VeAnime();
            this.luzTrafico.acquire();
            this.estado="Cambia el contador";
            Thread.sleep(hora()*8); //8 horas que invierte cambiando el contador
            if(this.compania.contador.diasRestantes>0){
                this.compania.contador.estado+=1;
                this.compania.contador.tiempoRestante-=1;
                this.compania.contador.estado-=1; 
            }
            this.luzTrafico.release();
                
        }catch(InterruptedException ex){
            Logger.getLogger(ProjectM.class.getName()).log(Level.SEVERE,null,ex);
        }       
    }
   
   
   public int hora(){
       return tiempoDia/24;
   }
   //Funcion para ejecutar el trabajo y el pago
   @Override
   public void run(){
       while(true){
           PagoProjectM();
           Trabaja();
       }
   }
   
   
    
  
    
}
