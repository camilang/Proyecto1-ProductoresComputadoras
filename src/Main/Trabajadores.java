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
    public int cobroxHora;
    public int tipo;
    public int salario = 0;
    public int diasTrabajados= 0;
    public Almacen almacen;
    public Semaphore semaforo;
    public boolean running = false;
    
    public Trabajadores(int tipo, Almacen almacen,Semaphore semaforo){
        this.tipo = tipo;
        this.almacen = almacen;
        this.semaforo = semaforo;
        
        if (this.tipo == 0) {
    // 0 para Productor de placa base 20$ la hora
                this.cobroxHora = 20;
        } else if (this.tipo == 1) {
    // 1 para Productor  de cpu 26$ la hora
            this.cobroxHora = 26;
        } else if (this.tipo == 2) {
    // 2 para Productor de memoria ram 40$ la hora
         this.cobroxHora = 40;
        } else if (this.tipo == 3) {
    // 3 para Productor de fuente de alimentacion 16$ la hora
            this.cobroxHora = 16;
    } else if (this.tipo == 4) {
    // 4 para Productor de tarjeta graficas 34$ la hora
    this.cobroxHora = 34;
        } else {
    
        }
        
    }
    
    public void Trabajo() {
    this.diasTrabajados += 1;
    
    switch (this.tipo) {
            case 0 -> { 
                // Si alcanza los 3 dias trabajados solicita permiso al drive para guardar el guión
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
    
//    if (this.tipo == 0) { 
//    // Guarda en el almacen la placa base si alcanza los 2 dias de trabajo
//        if (this.diasTrabajados > 2 && !this.almacen.placaBaseFull()) {
//            try {
//                this.semaforo.acquire();
//                this.almacen.guardarPlacaBase();
//                this.semaforo.release();
//                this.diasTrabajados = 0;
//            } catch (InterruptedException e) {
//                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
//    } else if (this.tipo == 1) {
//        if (this.diasTrabajados > 2 && !this.almacen.cpuFull()) {
//            try {
//                this.semaforo.acquire();
//                this.almacen.guardarCpu();
//                this.semaforo.release();
//                this.diasTrabajados = 0;
//            } catch (InterruptedException e) {
//                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
//    } else if (this.tipo == 2) {
//        if (this.diasTrabajados > 1 && !this.almacen.ramFull()) {
//            try {
//                this.semaforo.acquire();
//                this.almacen.guardarRam();
//                this.semaforo.release();
//                this.diasTrabajados = 0;
//            } catch (InterruptedException e) {
//                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
//    } else if (this.tipo == 3) {
//        if (this.diasTrabajados > 1 && !this.almacen.fuenteAlimentacionFull()) {
//            try {
//                this.semaforo.acquire();
//                this.almacen.guardarFuenteAlimentacion();
//                this.semaforo.release();
//                this.diasTrabajados = 0;
//            } catch (InterruptedException e) {
//                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
//    } else if (this.tipo == 4) {
//        if (this.diasTrabajados > 3 && !this.almacen.tarjetaGraficaFull()) {
//            try {
//                this.semaforo.acquire();
//                this.almacen.guardarTarjetaGrafica();
//                this.semaforo.release();
//                this.diasTrabajados = 0;
//            } catch (InterruptedException e) {
//                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
//    }
}
    
public void salarioTotal(){
    this.salario += this.cobroxHora*24;
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
                salarioTotal();
                Trabajo();
                sleep(Compania.tiempoDia);
            } catch (InterruptedException e) {
                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }    
 
    
}
