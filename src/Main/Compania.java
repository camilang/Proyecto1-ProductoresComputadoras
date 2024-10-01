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
    public static int tipo;
    public int CostosO=0;
    public int ganaciasComputadoras=0;
    public int ganaciasTotales =0;
    public Contador contador;
    
    public Semaphore placaBase = new Semaphore(1);
    public Semaphore cpu = new Semaphore(1);
    public Semaphore ram = new Semaphore(1);
    public Semaphore fuenteAlimentacion = new Semaphore(1);
    public Semaphore tarjetaGrafica = new Semaphore(1);
    public Semaphore partes = new Semaphore(1);
    public Semaphore contadorAcceso = new Semaphore(1);
  
    public Almacen almacen;
    
    public Trabajadores[] productoresPB;
    public Trabajadores[] productoresCPUS;
    public Trabajadores[] productoresRAM;
    public Trabajadores[] productoresFuenteA;
    public Trabajadores[] productoresTG;
    
    public Ensamblador[] ensamblador;
    
    public ProjectM [] projectM;
    
    public Director director;
    
    
    public Compania (int tiempoDia, int fechaLimite,int productoresPB, int productoresCPUS, int productoresRAM, int productoresFuenteA, int productoresTG, int ensamblador, int tipo){
        Compania.tiempoDia = tiempoDia;
        Compania.tipo=tipo;
        this.contador= new Contador (fechaLimite);
        /*this.almacen= new Almacen(this);*/
        
        
        this.productoresPB= new Trabajadores [productoresPB];
        this.productoresCPUS = new Trabajadores [productoresCPUS];
        this.productoresRAM = new Trabajadores [productoresRAM];
        this.productoresFuenteA= new Trabajadores [productoresFuenteA];
        this.productoresTG= new Trabajadores [productoresTG];
        this.ensamblador = new Ensamblador[ensamblador];
        
        
        for(int i=0;i<this.productoresPB.length;i++){
            Trabajadores trabajador = new Trabajadores (0,this.almacen,this.placaBase);
            this.productoresPB[i]= trabajador;
            this.productoresPB[i].start();
        }
        
        for(int i=0;i<this.productoresCPUS.length;i++){
            Trabajadores trabajador = new Trabajadores (1,this.almacen,this.cpu);
            this.productoresCPUS[i]= trabajador;
            this.productoresCPUS[i].start();
        }
         
        for(int i=0;i<this.productoresRAM.length;i++){
            Trabajadores trabajador = new Trabajadores (2,this.almacen,this.ram);
            this.productoresRAM[i]= trabajador;
            this.productoresRAM[i].start();
        }
        
        for(int i=0;i<this.productoresFuenteA.length;i++){
            Trabajadores trabajador = new Trabajadores (3,this.almacen,this.fuenteAlimentacion);
            this.productoresFuenteA[i]= trabajador;
            this.productoresFuenteA[i].start();
        }
        
        for(int i=0;i<this.productoresTG.length;i++){
            Trabajadores trabajador = new Trabajadores (4,this.almacen,this.tarjetaGrafica);
            this.productoresTG[i]= trabajador;
            this.productoresTG[i].start();
        }

        for(int i=0;i<this.ensamblador.length;i++){
            Ensamblador ensambladores = new Ensamblador (Compania.tipo, this.almacen, this.partes,Compania.tiempoDia);
            this.ensamblador[i]= ensambladores;
            this.ensamblador[i].start();
        }
        /*
        this.director= new Director (this, tiempoDia, this.contadorAcceso);
        this.projectM =  new ProjectM(tiempoDia, this.contadorAcceso, this.director,this);*/
        
        
    }
    
    
    
    public void sumUtilidad(){
        int Costosalario=0;
        for (Trabajadores productoresPB: this.productoresPB){
            Costosalario+=productoresPB.salario;
        }
        
        for (Trabajadores productoresCPUS: this.productoresCPUS){
            Costosalario+=productoresCPUS.salario;
        }
        
        for (Trabajadores productoresRAM: this.productoresRAM){
            Costosalario+=productoresRAM.salario;
        }
        
        for (Trabajadores productoresFuenteA: this.productoresFuenteA){
            Costosalario+=productoresFuenteA.salario;
        }
        
        for (Trabajadores productoresTG: this.productoresTG){
            Costosalario+=productoresTG.salario;
        }
        
        for (Ensamblador ensamblador: this.ensamblador){
            Costosalario+=ensamblador.salario;
        }
        /*
        Costosalario+=this.director.salarioAcumulado;
        Costosalario+= this.projectM.salarioAcumulado;*/
        
        this.CostosO=Costosalario;
    }
    
    public void GananciasTotales(){
        this.ganaciasTotales=this.ganaciasComputadoras-this.CostosO;
    }
    
    public void sumGanancias(int num){
        this.ganaciasComputadoras+=num;
        this.sumUtilidad();
        this.GananciasTotales();
    }
    
    public boolean TrabajadoresMax(){
        int trabajadoresMax =0;
        trabajadoresMax= this.productoresPB.length+this.productoresCPUS.length+this.productoresRAM.length+this.productoresFuenteA.length+this.productoresTG.length+this.ensamblador.length;
        return 13> trabajadoresMax; /*Ultimo numero del carnet 1*/
    }
    
    public void agregarTrabajador(int type){
        if (!this.TrabajadoresMax()) {
            JOptionPane.showMessageDialog(null, "ERROR! Solo se pueden hasta 13 trabajadores");
        } else {
            try {
                switch (type) {
                    case 0 -> {
                        Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresPB.length+1];
                        System.arraycopy(this.productoresPB, 0, nuevoTrabajador, 0, this.productoresPB.length);
                        nuevoTrabajador[this.productoresPB.length] = new Trabajadores(0, this.almacen, this.placaBase);
                        nuevoTrabajador[this.productoresPB.length].start();
                        Trabajadores.sleep(this.contador.tiempoRestante);
                        this.productoresPB = nuevoTrabajador;
                    }
                    case 1 ->                     {
                        Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresCPUS.length+1];
                        System.arraycopy(this.productoresCPUS, 0, nuevoTrabajador, 0, this.productoresCPUS.length);
                        nuevoTrabajador[this.productoresCPUS.length] = new Trabajadores(1, this.almacen, this.cpu);
                        nuevoTrabajador[this.productoresCPUS.length].start();
                        Trabajadores.sleep(this.contador.tiempoRestante);
                        this.productoresCPUS = nuevoTrabajador;
                    }
                    case 2 ->                     {
                        Trabajadores[] nuevoTrabajador = new Trabajadores[this.productoresRAM.length+1];
                        System.arraycopy(this.productoresRAM, 0, nuevoTrabajador, 0, this.productoresRAM.length);
                        nuevoTrabajador[this.productoresRAM.length] = new Trabajadores(2, this.almacen, this.ram);
                        nuevoTrabajador[this.productoresRAM.length].start();
                        Trabajadores.sleep(this.contador.tiempoRestante);
                        this.productoresRAM = nuevoTrabajador;
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
                        Ensamblador[] nuevoEnsamblador = new Ensamblador[this.ensamblador.length+1];
                        System.arraycopy(this.ensamblador, 0, nuevoEnsamblador, 0, this.ensamblador.length);
                        nuevoEnsamblador[this.ensamblador.length] = new Ensamblador(Compania.tipo, this.almacen, this.partes, Compania.tiempoDia);
                        nuevoEnsamblador[this.ensamblador.length].start();
                        Ensamblador.sleep(this.contador.tiempoRestante);
                        this.ensamblador = nuevoEnsamblador;
                    }
                    default -> {
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Compania.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    
    
    
}
