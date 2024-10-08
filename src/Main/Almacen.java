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
    
    // se establecen funciones para poder detectar cuando el almacen este lleno
    public boolean placaBaseFull(){
        return this.placaBase > 23;
    } 
    
    public boolean cpuFull(){
        return this.cpu > 18;
    }
    
    public boolean ramFull(){
        return this.ram > 53;
    }
    
    public boolean fuenteAlimentacionFull(){
        return this.fuenteAlimentacion > 33;
    }
    
    public boolean tarjetaGraficaFull(){
        return this.tarjetaGrafica > 8;
    }
    
    // guardado de piezas en el almacen
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
        this.tarjetaGrafica += 1;
               
    }
    
    public void reiniciarContadorComputadoras(){
        this.computadora = 0;
        this.computadoraTG = 0;
    }
    
    
    public Almacen (Compania compania){
        this.compania = compania;
    }
}
