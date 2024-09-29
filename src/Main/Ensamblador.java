/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.concurrent.Semaphore;

/**
 *
 * @author MAURICIO MENDEZ
 */
public class Ensamblador extends Thread{
    
    public int tipo;
    public int tiempo;
    public int salario;
    public int diasTrabajados= 0;
    public Almacen almacen;
    public Semaphore semaforo;
    public int contador;
    public boolean[] partesComputadoras= new boolean[5]; 
    public boolean running = false;
    
    public Ensamblador(int tipo, Almacen almacen, Semaphore semaforo, int tiempo){
        this.tipo = tipo;
        this.almacen = almacen;
        this.semaforo = semaforo;
        this.tiempo = tiempo;
    }
    
    public void salarioTotal(){
    this.salario += 50*24;
    }
    
//    tipo 0 Msi tipo 1 Dell 
    public void vericarPartesComputadora(){
    
        if (this.tipo == 1) {
            if (this.almacen.placaBase > 0) {               
                this.partesComputadoras[0] = true;
            }
            if (this.almacen.cpu > 4){               
                this.partesComputadoras[1] = true;     
            }
            if (this.almacen.ram > 5){               
                this.partesComputadoras[2] = true;     
            }
            if (this.almacen.fuenteAlimentacion > 4){                
                this.partesComputadoras[3] = true;     
            }    
            if (this.contador > 2) {
                if (this.almacen.tarjetaGrafica > 0) {
                    this.partesComputadoras[4] = true;
                    this.contador = 0;
                }
            }
        } else if (this.tipo == 0) {
            if (this.almacen.placaBase > 1) {               
                this.partesComputadoras[0] = true;
            }
            if (this.almacen.cpu > 2){               
                this.partesComputadoras[1] = true;     
            }
            if (this.almacen.ram > 3){               
                this.partesComputadoras[2] = true;     
            }
            if (this.almacen.fuenteAlimentacion > 5){                
                this.partesComputadoras[3] = true;     
            }    
            if (this.contador > 5) {
                if (this.almacen.tarjetaGrafica > 4) {
                    this.partesComputadoras[4] = true;
                    this.contador = 0;
                }
            }
           
        }
      
    }
}
    


    

