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
 * @author MAURICIO MENDEZ
 */
public class Ensamblador extends Thread{
    
    public int contador;
    public boolean[] partesComputadoras= new boolean[5]; 
    public int tipo;
    public int tiempo;
    public int salario;
    public int diasTrabajados= 0;
    public Almacen almacen;
    public Semaphore semaforo;
    public boolean running = false;
    
    public Ensamblador(int tipo, Almacen almacen, Semaphore semaforo, int tiempo){
        this.tipo = tipo;
        this.almacen = almacen;
        this.semaforo = semaforo;
        this.tiempo = tiempo;
    }
    //salario del ensamblador por dia
    public void salarioTotal(){
    this.salario += 50*24;
    }
 // 0 placa base 1 cpu 2 ram 3 fuenteAlimentacion 4Tarjeta   

//    tipo 0 Dell Tipo 1 Msi
    public void vericarPartesComputadora(){
    // se marca verdadero solo si esta disponible la catidad de piezas necesarias en el almacen
        if (this.tipo == 1) {
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
            
            
            
        } else if (this.tipo == 0) {
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
           
        }
      
    }
    
    public void detenerHilo(){
        this.running = false;
    }    
    //verificar el si estan todas las partes para ensamblar la computadora
    //retormanos 1 si es una pc normla y 2 para una pc con tarjeta grafica
    
    public int ensamblarComputadora(){
        vericarPartesComputadora();
        // aux cuenta si todas las partes estan disponibles 
        int aux = 0;
        for (int i = 0; i < 4; i++) {
            if (this.partesComputadoras[i] == true){
                aux += 1;
            }
        }
        if (aux == 4 && this.partesComputadoras[4] == true){
            if (this.tipo == 1){
                this.almacen.placaBase -= 2;
                this.almacen.cpu -= 3;
                this.almacen.ram -= 4;
                this.almacen.fuenteAlimentacion -= 6;
                this.almacen.tarjetaGrafica -= 5; 
            } else {
                
                
                this.almacen.placaBase -= 1;
                this.almacen.cpu -= 5;
                this.almacen.ram -= 6;
                this.almacen.fuenteAlimentacion -= 5;
                this.almacen.tarjetaGrafica -= 1; 
                
            }
            for (int i = 0; i < this.partesComputadoras.length; i++) {
                this.partesComputadoras[i] = false;
            }
            return 2; 
        } 
        else if (aux == 4){
            if (this.tipo == 1){
                
                this.almacen.placaBase -= 2;
                this.almacen.cpu -= 3;
                this.almacen.ram -= 4;
                this.almacen.fuenteAlimentacion -= 6;
            } else {
                
                
                this.almacen.placaBase -= 1;
                this.almacen.cpu -= 5;
                this.almacen.ram -= 6;
                this.almacen.fuenteAlimentacion -= 5;
                
            }
            for (int i = 0; i < this.partesComputadoras.length; i++) {
                this.partesComputadoras[i] = false;
            }
            return 1;
        }
        for (int i = 0; i < this.partesComputadoras.length; i++) {
            this.partesComputadoras[i] = false;
        }
        return 0;        
    }
    
    // se gestiona el acceso al almacen 
    public int revisarAlmacen(){
        int respuesta = 0;
        try {
            // se adquiere el semaforo
            this.semaforo.acquire();
            switch (ensamblarComputadora()) {
                case 1 -> respuesta = 1;
                case 2 -> respuesta = 2;
                default -> respuesta = 0;
            }
            // se libera el semaforo
            this.semaforo.release();
        } catch(InterruptedException e){
            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
        }
        return respuesta;
    }
    
    // se guarda en el almacen las computadoras ensambladas
    public void trabajo(int tipo){
        boolean trabajando = true;
                
        while (trabajando){
            this.diasTrabajados += 1;
            if (this.diasTrabajados > 2){
                switch (tipo) {
                    case 1 -> {
                        try {
                            //
                            this.semaforo.acquire();
                            this.almacen.computadora += 1;
                            this.contador += 1;
                            this.semaforo.release();
                            this.diasTrabajados = 0;
                            trabajando = false;
                        } catch(InterruptedException e){
                            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                    case 2 -> {
                        try {
                            this.semaforo.acquire();
                            this.almacen.computadoraTG += 1;
                            this.contador += 1;
                            this.semaforo.release();
                            this.diasTrabajados = 0;
                            trabajando = false;
                        } catch(InterruptedException e){
                            Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                }
            }
            if (trabajando){
                try {
                    sleep(this.tiempo);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }     
             
        }
    }
    @Override
    public void run(){
        this.running = true;
        while (this.running == true){
            try{
                //sumamos el salario del dia 
                salarioTotal();
                //verifica si se puede ensamblar
                int ensamblar = revisarAlmacen();
                if (ensamblar != 0){
                    trabajo(ensamblar); //se ensambla la pc
                }
                sleep(this.tiempo); 
            } catch (InterruptedException e) {
                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, e);
            }           
        }
    }  
    
}
    


    

