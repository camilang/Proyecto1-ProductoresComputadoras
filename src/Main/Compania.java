/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Camila Garcia
 */
public class Compania {
    public static int tiempoDia;    
    public Contador contador;
    
    public int costOperativo = 0;
    public int gananciaComputadora = 0;
    public int gananciaTotal = 0;
    
    public static int tipo;
    
    public Semaphore placaBase = new Semaphore(1);
    public Semaphore cpu = new Semaphore(1);
    public Semaphore ram = new Semaphore(1);
    public Semaphore fuenteAlimentacion = new Semaphore(1);
    public Semaphore tarjetaGrafica = new Semaphore(1);
    
    public Semaphore partes = new Semaphore(1);
    
    public Semaphore accesoContador = new Semaphore(1);
    
    public Almacen almacen;
    
    public Trabajadores[] productoresPlacaBase;
    public Trabajadores[] productoresCpu;
    public Trabajadores[] productoresRam;
    public Trabajadores[] productoresFuenteA;
    public Trabajadores[] productoresTG;
    
    public Ensamblador[] ensambladores;
    
    public ProjectM projectM;
    
    public Director director;
    
    public Compania( int tiempoDia, int fechaLimite, int productoresPlacaBase, int productoresCpu, int productoresRam, int productoresFuenteA, int productoresTG, int ensambladores, int tipo){
        Compania.tiempoDia = tiempoDia;
        Compania.tipo = tipo;
        this.contador = new Contador(fechaLimite);
        this.almacen = new Almacen(this);  
        
      
        this.productoresPlacaBase = new Trabajadores[productoresPlacaBase];
        this.productoresCpu = new Trabajadores[productoresCpu];
        this.productoresRam = new Trabajadores[productoresRam];
        this.productoresFuenteA = new Trabajadores[productoresFuenteA];
        this.productoresTG = new Trabajadores[productoresTG];
        this.ensambladores = new Ensamblador[ensambladores];
        
        
        for (int i = 0; i < this.productoresPlacaBase.length; i++) {
            Trabajadores trabajador = new Trabajadores(0, this.almacen, this.placaBase);
            this.productoresPlacaBase[i] = trabajador;
            this.productoresPlacaBase[i].start();
        }
        
        for (int i = 0; i < this.productoresCpu.length; i++) {
            Trabajadores trabajador = new Trabajadores(1, this.almacen, this.cpu);
            this.productoresCpu[i] = trabajador;
            this.productoresCpu[i].start();
        }
       
        for (int i = 0; i < this.productoresRam.length; i++) {
            Trabajadores trabajador = new Trabajadores(2, this.almacen, this.ram);
            this.productoresRam[i] = trabajador;
            this.productoresRam[i].start();
        }
        
        for (int i = 0; i < this.productoresFuenteA.length; i++) {
            Trabajadores trabajador = new Trabajadores(3, this.almacen, this.fuenteAlimentacion);
            this.productoresFuenteA[i] = trabajador;
            this.productoresFuenteA[i].start();
        }
        
        for (int i = 0; i < this.productoresTG.length; i++) {
            Trabajadores trabajador = new Trabajadores(4, this.almacen, this.tarjetaGrafica);
            this.productoresTG[i] = trabajador;
            this.productoresTG[i].start();
        }
        
        for (int i = 0; i < this.ensambladores.length; i++) {
            Ensamblador ensamblador = new Ensamblador(Compania.tipo, this.almacen, this.partes, Compania.tiempoDia);
            this.ensambladores[i] = ensamblador;
            this.ensambladores[i].start();
        }
        
        this.director = new Director(this, tiempoDia, this.accesoContador);
        this.projectM = new ProjectM(tiempoDia, this.accesoContador, this.director, this);
        
        this.contador.start();
        this.director.start();
        this.projectM.start();
    }     
    
    public void sumaGanancias(int numero){
        this.gananciaComputadora += numero;
        this.sumaUtilidades();
        this.sumaGananciasTotales();
    }
    
    public void sumaUtilidades(){
        int costoSalario = 0;
        for (Trabajadores productoresPlacaBase : this.productoresPlacaBase) {
            costoSalario += productoresPlacaBase.salarioAcumulado;
        }
        
        for (Trabajadores productoresCpu : this.productoresCpu) {
            costoSalario += productoresCpu.salarioAcumulado;
        }
        
        for (Trabajadores productoresRam : this.productoresRam) {
            costoSalario += productoresRam.salarioAcumulado;
        }
        
        for (Trabajadores productoresFuenteA : this.productoresFuenteA) {
            costoSalario += productoresFuenteA.salarioAcumulado;
        }
        
        for (Trabajadores productoresTG : this.productoresTG) {
            costoSalario += productoresTG.salarioAcumulado;
        }
        
        for (Ensamblador ensambladores : this.ensambladores) {
            costoSalario += ensambladores.salarioAcumulado;
        }
        
        costoSalario += this.director.salarioAcumulado;
        costoSalario += this.projectM.salarioAcumulado;
        
        this.costOperativo = costoSalario;
    }
    
    public void sumaGananciasTotales(){
        this.gananciaTotal = this.gananciaComputadora - this.costOperativo;
    }
    
    public boolean maxTrabajadores(){
        int maxTrabajadores = 0; 
        maxTrabajadores = this.productoresPlacaBase.length + this.productoresCpu.length + this.productoresRam.length + this.productoresFuenteA.length + this.productoresTG.length + this.ensambladores.length;
        return 12 > maxTrabajadores;
    }
    
    public void agregarTrabajador(int tipo){
        if (!this.maxTrabajadores()) {
            JOptionPane.showMessageDialog(null, "No se pueden tener más de 21 trabajadores.");
        } else {
            try {
                switch (tipo) {
                    case 0 ->                     {
                        Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresPlacaBase.length+1];
                        System.arraycopy(this.productoresPlacaBase, 0, nuevoTrabajador, 0, this.productoresPlacaBase.length);
                        nuevoTrabajador[this.productoresPlacaBase.length] = new Trabajadores(0, this.almacen, this.placaBase);
                        nuevoTrabajador[this.productoresPlacaBase.length].start();
                        Trabajadores.sleep(this.contador.tiempoRestante);
                        this.productoresPlacaBase = nuevoTrabajador;
                    }
                    case 1 ->                     {
                        Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresCpu.length+1];
                        System.arraycopy(this.productoresCpu, 0, nuevoTrabajador, 0, this.productoresCpu.length);
                        nuevoTrabajador[this.productoresCpu.length] = new Trabajadores(1, this.almacen, this.cpu);
                        nuevoTrabajador[this.productoresCpu.length].start();
                        Trabajadores.sleep(this.contador.tiempoRestante);
                        this.productoresCpu = nuevoTrabajador;
                    }
                    case 2 ->                     {
                        Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresRam.length+1];
                        System.arraycopy(this.productoresRam, 0, nuevoTrabajador, 0, this.productoresRam.length);
                        nuevoTrabajador[this.productoresRam.length] = new Trabajadores(2, this.almacen, this.ram);
                        nuevoTrabajador[this.productoresRam.length].start();
                        Trabajadores.sleep(this.contador.tiempoRestante);
                        this.productoresRam = nuevoTrabajador;
                    }
                    case 3 ->                     {
                        Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresFuenteA.length+1];
                        System.arraycopy(this.productoresFuenteA, 0, nuevoTrabajador, 0, this.productoresFuenteA.length);
                        nuevoTrabajador[this.productoresFuenteA.length] = new Trabajadores(3, this.almacen, this.fuenteAlimentacion);
                        nuevoTrabajador[this.productoresFuenteA.length].start();
                        Trabajadores.sleep(this.contador.tiempoRestante);
                        this.productoresFuenteA = nuevoTrabajador;
                    }
                    case 4 ->                     {
                        Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresTG.length+1];
                        System.arraycopy(this.productoresTG, 0, nuevoTrabajador, 0, this.productoresTG.length);
                        nuevoTrabajador[this.productoresTG.length] = new Trabajadores(4, this.almacen, this.tarjetaGrafica);
                        nuevoTrabajador[this.productoresTG.length].start();
                        Trabajadores.sleep(this.contador.tiempoRestante);
                        this.productoresTG = nuevoTrabajador;
                    }
                    case 5 -> {
                        Ensamblador[] nuevoEnsamblador = new Ensamblador[this.ensambladores.length+1];
                        System.arraycopy(this.ensambladores, 0, nuevoEnsamblador, 0, this.ensambladores.length);
                        nuevoEnsamblador[this.ensambladores.length] = new Ensamblador(Compania.tipo, this.almacen, this.partes, Compania.tiempoDia);
                        nuevoEnsamblador[this.ensambladores.length].start();
                        Ensamblador.sleep(this.contador.tiempoRestante);
                        this.ensambladores = nuevoEnsamblador;
                    }
                    default -> {
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Compania.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void borrarTrabajador(int type){
        switch (type) {
            case 0 ->                 {
                    Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresPlacaBase.length-1];
                    this.productoresPlacaBase[this.productoresPlacaBase.length-1].detenerHilo();
                    System.arraycopy(this.productoresPlacaBase, 0, nuevoTrabajador, 0, this.productoresPlacaBase.length-1);
                    this.productoresPlacaBase = nuevoTrabajador;
                }
            case 1 ->                 {
                    Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresCpu.length-1];
                    this.productoresCpu[this.productoresCpu.length-1].detenerHilo();
                    System.arraycopy(this.productoresCpu, 0, nuevoTrabajador, 0, this.productoresCpu.length-1);
                    this.productoresCpu = nuevoTrabajador;
                }
            case 2 ->                 {
                    Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresRam.length-1];
                    this.productoresRam[this.productoresRam.length-1].detenerHilo();
                    System.arraycopy(this.productoresRam, 0, nuevoTrabajador, 0, this.productoresRam.length-1);
                    this.productoresRam = nuevoTrabajador;
                }
            case 3 ->                 {
                    Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresFuenteA.length-1];
                    this.productoresFuenteA[this.productoresFuenteA.length-1].detenerHilo();
                    System.arraycopy(this.productoresFuenteA, 0, nuevoTrabajador, 0, this.productoresFuenteA.length-1);
                    this.productoresFuenteA = nuevoTrabajador;
                }
            case 4 ->                 {
                    Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresTG.length-1];
                    this.productoresTG[this.productoresTG.length-1].detenerHilo();
                    System.arraycopy(this.productoresTG, 0, nuevoTrabajador, 0, this.productoresTG.length-1);
                    this.productoresTG = nuevoTrabajador;
                }
            case 5 -> {
                Ensamblador[] nuevoEnsamblador = new Ensamblador[this.ensambladores.length-1];
                this.ensambladores[this.ensambladores.length-1].detenerHilo();
                System.arraycopy(this.ensambladores, 0, nuevoEnsamblador, 0, this.ensambladores.length-1);
                this.ensambladores = nuevoEnsamblador;
            }
            default -> {
            }
        }
        
    }
    
}
