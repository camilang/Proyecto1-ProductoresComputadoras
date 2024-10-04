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
    public String estado; /*Para saber cuando ve One piece y cuando trabaja*/
    public Director director;
    public Compania compania;
    public int salarioAcumulado;
    public int salarioPerdido;
    public int falla=0;
    public int tiempoDia;
 
    
    public ProjectM(int tiempoDia, Semaphore luzTrafico,Director director, Compania compania){
        this.tiempoDia= tiempoDia;
        this.luzTrafico = luzTrafico;
        this.compania= compania;
        this.director=director;
    }
    
    public void PagoProjectM(){
        this.salarioAcumulado+=40*24;
    }
    
   
    
    public void VeAnime() {
     int aux = 0;
     int mediahora = (tiempoDia/24)/2;

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
    
   public void Trabaja(){
        try{
            VeAnime();
            this.luzTrafico.acquire();
            this.estado="Cambia contador";
            Thread.sleep((tiempoDia/24)*8);
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
   
   public void PagoTrabajo(){
       while(true){
           PagoProjectM();
           Trabaja();
       }
   }
    
  
    
}
