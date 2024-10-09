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
   public Semaphore semaforo;
    public String estado;
    public int salarioAcumulado = 0; 
    public int salarioPerdido = 0;
    public int faltas = 0; 
    public int tiempoDia;
    public Director director;
    public Compania compania;
    
    public ProjectM (int tiempoDia, Semaphore semaforo, Director director, Compania compania) {
        this.director = director;
        this.tiempoDia = tiempoDia;
        this.semaforo = semaforo;
        this.compania = compania;
    }
    
    public void pagoTrabajadores(){
        this.salarioAcumulado += 40*24;
    }
    
<<<<<<< HEAD
    
    public void veAnime() {
        int aux = 0;
        int mediahora = hora()/2;
        
        while (aux < 16){
            try {
                this.estado = "Viendo One Piece";
                ProjectM.sleep(mediahora); 
                if ("Vigilando PM".equals(this.director.estado)) {
                    this.faltas += 1;
                    this.salarioAcumulado -= 100;
                    this.salarioPerdido += 100;
                }
                this.estado = "Trabajando";
                ProjectM.sleep(mediahora);
                aux += 1;
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }         
    
    public void trabaja(){
        try {
            veAnime();
            this.semaforo.acquire();
            this.estado = "Cambiando contador";
            Thread.sleep(hora()*8); 
            if (this.compania.contador.diasRestantes > 0) {
                this.compania.contador.estado += 1;
                this.compania.contador.diasRestantes -= 1; 
                this.compania.contador.estado -= 1;
            }               
            this.semaforo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProjectM.class.getName()).log(Level.SEVERE, null, ex);
        }
=======
   
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
>>>>>>> 1e812416a71107908be302f0cb042a38aed7177f
    }
    
    public int hora(){
        return tiempoDia/24;
    }
          
    
    
    @Override
    public void run(){
        while(true){
            pagoTrabajadores();
            trabaja();
        }
    } 

   
<<<<<<< HEAD
=======
   
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
   
   
    
  
    
>>>>>>> 1e812416a71107908be302f0cb042a38aed7177f
}
