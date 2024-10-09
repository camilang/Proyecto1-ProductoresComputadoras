/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;

/**
 *
 * @author MAURICIO MENDEZ
 */
public class Trabajadores extends Thread{
    public int salario;
    public int tipo;
    public int salarioAcumulado = 0;
    public int diasTrabajados = 0;
    public Semaphore semaforo;
    public Almacen almacen;
    public boolean running = false;
    
    public Trabajadores(int tipo, Almacen almacen, Semaphore semaforo){
        this.tipo = tipo;
        this.almacen = almacen;
        this.semaforo = semaforo;
        
        
        switch (this.tipo) {
            
            case 0:
                this.salario = 20;
                break;
            
            case 1:
                this.salario = 26;
                break;
            
            case 2:
                this.salario = 40;
                break;
            
            case 3:
                this.salario = 16;
                break;
            
            case 4:
                this.salario = 34;
                break;
            default:
                break;
        }
    }
    
    
    public void pagoTrabajadores(){
        this.salarioAcumulado += this.salario*24;
    }
    
    
    
    public void trabaja(){
        this.diasTrabajados += 1;
        
        switch (this.tipo) {

            case 0 -> { 
                
                if (this.diasTrabajados > 2) {                                        
                    if (!this.almacen.placaBaseFull()) {
                        try {
                            this.semaforo.acquire();
                            this.almacen.guardarPlacaBase();
                            this.semaforo.release();
                            this.diasTrabajados = 0;
                        } catch (InterruptedException e) {
                            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
                        } 
                    }
                     
                }
            }
            case 1 -> {
                if (this.diasTrabajados > 2) {
                    if (!this.almacen.cpuFull()) {
                        try {
                            this.semaforo.acquire();
                            this.almacen.guardarCpu();
                            this.semaforo.release();
                            this.diasTrabajados = 0;
                        } catch (InterruptedException e) {
                            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
                        } 
                    }
                     
                }
            }
            case 2 -> {
                if (this.diasTrabajados > 1) {
                    if (!this.almacen.ramFull()) {
                        try {
                            this.semaforo.acquire();
                            this.almacen.guardarRam();
                            this.semaforo.release();
                            this.diasTrabajados = 0;
                        } catch (InterruptedException e) {
                            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
                        } 
                    }
                }
            }
            case 3 -> {
                if (this.diasTrabajados > 1) {
                    if (!this.almacen.fuenteAlimentacionFull()) {
                        try {
                            this.semaforo.acquire();
                            this.almacen.guardarFuenteAlimentacion();
                            this.semaforo.release();
                            this.diasTrabajados = 0;
                        } catch (InterruptedException e) {
                            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
                        }  
                    }                    
                }
            }
            case 4 -> {
                if (this.diasTrabajados > 3) {
                    if (!this.almacen.tarjetaGraficaFull()) {
                        try {
                            this.semaforo.acquire();
                            this.almacen.guardarTarjetaGrafica();
                            this.semaforo.release();
                            this.diasTrabajados = 0;
                        } catch (InterruptedException e) {
                            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
                        }  
                    }                   
                }
            }
            default -> {
            }
        }
        // 0 para Guionista 1 guión cada 4 días
            }
    
    public void detenerHilo(){
        this.running = false;
    }
    
    
    @Override
    public void run(){
        this.running = true;
        while(this.running == true){
            // Se suma salario, trabaja, y pasa un día.
            try {             
                pagoTrabajadores();
                trabaja();
                sleep(Compania.tiempoDia);
            } catch (InterruptedException e) {
                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
}
