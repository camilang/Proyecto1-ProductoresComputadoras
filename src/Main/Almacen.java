/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author MAURICIO MENDEZ
 */
public class Almacen {
    public Compania compania;
    public int placaBase = 0;
    public int cpu = 0;
    public int ram =0;
    public int fuenteAlimentacion= 0;
    public int tarjetaGrafica= 0;
    
    public int computadora = 0;
    public int computadoraTG= 0;
    
    
    public boolean placaBaseFull(){
        return this.placaBase >= 25;
    } 
    
    public boolean cpuFull(){
        return this.cpu >= 20;
    }
    
    public boolean ramFull(){
        return this.ram >= 55;
    }
    
    public boolean fuenteAlimentacionFull(){
        return this.fuenteAlimentacion >= 35;
    }
    
    public boolean tarjetaGraficaFull(){
        return this.tarjetaGrafica >= 10;
    }
    
    public void guardarPlacaBase(){
        this.placaBase += 1;
               
    }
    
    public void guardarCpu(){
        this.cpu += 1;
               
    }
    
    public void guardarRam(){
        this.ram += 3;
               
    }
    
    public void guardarFuenteAlimentacion(){
        this.fuenteAlimentacion += 3;
               
    }
    
    public void guardarTarjetaGrafica(){
        this.placaBase += 1;
               
    }
    
    public void reiniciarContadorComputadoras(){
        this.computadora = 0;
        this.computadoraTG = 0;
    }
    
    
    public Almacen (Compania compania){
        this.compania = compania;
    }
}
