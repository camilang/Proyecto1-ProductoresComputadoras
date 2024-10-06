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
    //Variables de uso para definir valores
    public static int tiempoDia;
    public static int tipo;
    public int CostosO=0;
    public int ganaciasComputadoras=0;
    public int ganaciasTotales =0;
    public Contador contador;
    //Creación de semaforos
    public Semaphore placaBase = new Semaphore(1);
    public Semaphore cpu = new Semaphore(1);
    public Semaphore ram = new Semaphore(1);
    public Semaphore fuenteAlimentacion = new Semaphore(1);
    public Semaphore tarjetaGrafica = new Semaphore(1);
    public Semaphore partes = new Semaphore(1);
    public Semaphore contadorAcceso = new Semaphore(1);
  
    //Creacion de clases y arrays para guardar la lista de productores de cada tipo
    public Almacen almacen;
    
    public Trabajadores[] productoresPB;
    public Trabajadores[] productoresCPUS;
    public Trabajadores[] productoresRAM;
    public Trabajadores[] productoresFuenteA;
    public Trabajadores[] productoresTG;
    
    //Creacion de los demas trabajadores, ProjectM y director no es array ya que solo hay uno
    public Ensamblador[] ensamblador;
    
    public ProjectM  projectM;
    
    public Director director;
    
    //Constructor
    public Compania (int tiempoDia, int fechaLimite,int productoresPB, int productoresCPUS, int productoresRAM, int productoresFuenteA, int productoresTG, int ensamblador, int tipo){
        Compania.tiempoDia = tiempoDia;
        Compania.tipo=tipo; //Tipo es la variable que define si es Dell o MSI
        this.contador= new Contador (fechaLimite); //Contador para que llegue la fecha limite
        this.almacen= new Almacen(this);
        
        //Se crea un array con los productores
        this.productoresPB= new Trabajadores [productoresPB];
        this.productoresCPUS = new Trabajadores [productoresCPUS];
        this.productoresRAM = new Trabajadores [productoresRAM];
        this.productoresFuenteA= new Trabajadores [productoresFuenteA];
        this.productoresTG= new Trabajadores [productoresTG];
        this.ensamblador = new Ensamblador[ensamblador];
        
        //Se realiza varios for para que los productores empiecen a trabajar con .start()
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
        
        //Se instacia el director y el pm
        this.director= new Director (this, tiempoDia, this.contadorAcceso);
        this.projectM =  new ProjectM(tiempoDia, this.contadorAcceso, this.director,this);
        
        
    }
    
    
    //Funcion para determinar cuanto dinero se da en la compañia de acuerdo a los salarios
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
        
        Costosalario+=this.director.salarioAcumulado;
        Costosalario+= this.projectM.salarioAcumulado;
        
        this.CostosO=Costosalario;
    }
    
    //Funcion para saber cuanto es el dinero de ganancia de una computadora ya considerando el costo
    public void GananciasTotales(){
        this.ganaciasTotales=this.ganaciasComputadoras-this.CostosO;
    }
    
    //Funcion para tener las ganancias totales
    
    public void sumGanancias(int num){
        this.ganaciasComputadoras+=num;
        this.sumUtilidad();
        this.GananciasTotales();
    }
    
    //Funcion para definir el maximo de trabajadores
    public boolean TrabajadoresMax(){
        int trabajadoresMax =0;
        trabajadoresMax= this.productoresPB.length+this.productoresCPUS.length+this.productoresRAM.length+this.productoresFuenteA.length+this.productoresTG.length+this.ensamblador.length;
        return 12> trabajadoresMax; /*Ultimo numero del carnet 0*/
    }
    
    //Funcion para añadir un trabajador de acuerdo a su que tipo de productor es 
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
    
    //Funcion para borrar un trabajador de acuerdo a su tipo
    public void borrarTrabajador(int type){
        switch (type) {
            case 0 ->                 {
                    Trabajadores[] trabajadorEliminado = new Trabajadores[this.productoresPB.length-1];
                    this.productoresPB[this.productoresPB.length-1].detenerHilo();
                    System.arraycopy(this.productoresPB, 0, trabajadorEliminado, 0, this.productoresPB.length-1);
                    this.productoresPB = trabajadorEliminado;
                }
            case 1 ->                 {
                    Trabajadores[] trabajadorEliminado = new Trabajadores[this.productoresCPUS.length-1];
                    this.productoresCPUS[this.productoresCPUS.length-1].detenerHilo();
                    System.arraycopy(this.productoresCPUS, 0, trabajadorEliminado, 0, this.productoresCPUS.length-1);
                    this.productoresCPUS = trabajadorEliminado;
                }
            case 2 ->                 {
                    Trabajadores[] trabajadorEliminado = new Trabajadores[this.productoresRAM.length-1];
                    this.productoresRAM[this.productoresRAM.length-1].detenerHilo();
                    System.arraycopy(this.productoresRAM, 0, trabajadorEliminado, 0, this.productoresRAM.length-1);
                    this.productoresRAM = trabajadorEliminado;
                }
            case 3 ->                 {
                    Trabajadores[] trabajadorEliminado = new Trabajadores[this.productoresFuenteA.length-1];
                    this.productoresFuenteA[this.productoresFuenteA.length-1].detenerHilo();
                    System.arraycopy(this.productoresFuenteA, 0, trabajadorEliminado, 0, this.productoresFuenteA.length-1);
                    this.productoresFuenteA = trabajadorEliminado;
                }
            case 4 ->                 {
                    Trabajadores[] trabajadorEliminado = new Trabajadores[this.productoresTG.length-1];
                    this.productoresTG[this.productoresTG.length-1].detenerHilo();
                    System.arraycopy(this.productoresTG, 0, trabajadorEliminado, 0, this.productoresTG.length-1);
                    this.productoresTG = trabajadorEliminado;
                }
            case 5 -> {
                Ensamblador[] newAssemblers = new Ensamblador[this.ensamblador.length-1];
                this.ensamblador[this.ensamblador.length-1].detenerHilo();
                System.arraycopy(this.ensamblador, 0, newAssemblers, 0, this.ensamblador.length-1);
                this.ensamblador = newAssemblers;
            }
            default -> {
            }
        }
    }
    
        
    
    
    
}
