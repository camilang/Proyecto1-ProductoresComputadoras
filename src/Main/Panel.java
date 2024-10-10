/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Camila Garcia
 */
public class Panel extends javax.swing.JFrame {
    
    public String [] simulacion;
    public JTextField[] JTextFieldArreglo;
    public Compania dell;
    public Compania msi;

    /**
     * Creates new form Dashboard
     */
    public Panel() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Arreglo con los datos introducidos
        this.JTextFieldArreglo= new JTextField[8];
        this.JTextFieldArreglo[0] = tiempoDia;
        this.JTextFieldArreglo[1] = fechaLimiteEntrega;
        this.JTextFieldArreglo[2] = cantproductoresPB;
        this.JTextFieldArreglo[3] = cantProductoresCPUS;
        this.JTextFieldArreglo[4] = cantProductoresRAM;
        this.JTextFieldArreglo[5] = cantProductoresFuenteA;
        this.JTextFieldArreglo[6] = cantProductoresTA;
        this.JTextFieldArreglo[7] = cantEnsambladores;
        
       //Leer txt
           String ruta = "src\\Main\\Simulacion.txt";
        try {
            File archivo = new File(ruta);
            int maxTrabajadores = 0;
            try (Scanner scanner = new Scanner(archivo)) {
                String linea = scanner.nextLine();
                this.simulacion = linea.split(",");
                for (int i = 2; i < this.simulacion.length; i++) {
                    maxTrabajadores += Integer.parseInt(this.simulacion[i]);
                }
                if (maxTrabajadores > 12){
                    JOptionPane.showMessageDialog(null, "ERROR!! La cantidad de trabajadores es un máximo de 12");
                    System.exit(0);
                }                
            }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR!! " + ex.getMessage());
                System.exit(0);
            }     
            for (int i = 0; i < 8; i++) {
                try {
                    if (validacion(simulacion[i]) != -1) {
                        this.JTextFieldArreglo[i].setText(simulacion[i]);               
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR!! Introduce un número");
                        System.exit(0);
                    }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR!! Mal ingreso de Datos");
            System.exit(0);
            }
        }
         //Crear instancia de la compañia Dell y MSI
        this.dell = new Compania(Integer.parseInt(this.simulacion[0])*1000, Integer.parseInt(this.simulacion[1]), Integer.parseInt(this.simulacion[2]), Integer.parseInt(this.simulacion[3]), Integer.parseInt(this.simulacion[4]), Integer.parseInt(this.simulacion[5]), Integer.parseInt(this.simulacion[6]), Integer.parseInt(this.simulacion[7]), 0);
        this.msi = new Compania(Integer.parseInt(this.simulacion[0])*1000, Integer.parseInt(this.simulacion[1]), Integer.parseInt(this.simulacion[2]), Integer.parseInt(this.simulacion[3]), Integer.parseInt(this.simulacion[4]), Integer.parseInt(this.simulacion[5]), Integer.parseInt(this.simulacion[6]), Integer.parseInt(this.simulacion[7]), 1);
        
        while (true){
            //Cambiar el texto de la interfaz en Dell
            CantPB.setText(Integer.toString(this.dell.almacen.placaBase));
            CantCPU.setText(Integer.toString(this.dell.almacen.cpu));
            CantRAM.setText(Integer.toString(this.dell.almacen.ram));
            CantFA.setText(Integer.toString(this.dell.almacen.fuenteAlimentacion));
            CantTG.setText(Integer.toString(this.dell.almacen.tarjetaGrafica));
            estadoPM.setText(this.dell.projectM.estado);
            estadoDirector.setText(this.dell.director.estado);
            diasRestantes.setText(Integer.toString(this.dell.contador.diasRestantes));
            pmFaltas.setText(Integer.toString(this.dell.projectM.faltas));
            computadorasListasEst.setText(Integer.toString(this.dell.almacen.computadora));
            computadorasListaTG.setText(Integer.toString(this.dell.almacen.computadoraTG));
            
            ganancias.setText(Integer.toString(this.dell.gananciaComputadora));
            dineroDescontadoPm.setText(Integer.toString(this.dell.projectM.salarioPerdido));
            utilidad.setText(Integer.toString(this.dell.gananciaTotal));
            costos.setText(Integer.toString(this.dell.costOperativo));
            
            CantPPB.setText(Integer.toString(this.dell.productoresPlacaBase.length));
            CantPCPU.setText(Integer.toString(this.dell.productoresCpu.length));
            CantPRAM.setText(Integer.toString(this.dell.productoresRam.length));
            CantPFA.setText(Integer.toString(this.dell.productoresFuenteA.length));
            CantPTG.setText(Integer.toString(this.dell.productoresTG.length));
            CantEnsambladores.setText(Integer.toString(this.dell.ensambladores.length));
            
           
            //Cambiar texto de interfaz en MSI
            
            CantPB1.setText(Integer.toString(this.msi.almacen.placaBase));
            CantCPU1.setText(Integer.toString(this.msi.almacen.cpu));
            CantRAM1.setText(Integer.toString(this.msi.almacen.ram));
            CantFA1.setText(Integer.toString(this.msi.almacen.fuenteAlimentacion));
            CantTG1.setText(Integer.toString(this.msi.almacen.tarjetaGrafica));
            estadoPM1.setText(this.msi.projectM.estado);
            estadoDirector1.setText(this.msi.director.estado);
            diasRestantes1.setText(Integer.toString(this.msi.contador.diasRestantes));
            pmFaltas1.setText(Integer.toString(this.msi.projectM.faltas));
            computadorasListasEst1.setText(Integer.toString(this.msi.almacen.computadora));
            computadorasListaTG1.setText(Integer.toString(this.msi.almacen.computadoraTG));
            ganancias2.setText(Integer.toString(this.msi.gananciaComputadora));
            dineroDescontadoPm1.setText(Integer.toString(this.msi.projectM.salarioPerdido));
            utilidad2.setText(Integer.toString(this.msi.gananciaTotal));
            costos2.setText(Integer.toString(this.msi.costOperativo));
            
            CantPPB1.setText(Integer.toString(this.msi.productoresPlacaBase.length));
            CantPCPU1.setText(Integer.toString(this.msi.productoresCpu.length));
            CantPRAM1.setText(Integer.toString(this.msi.productoresRam.length));
            CantPFA1.setText(Integer.toString(this.msi.productoresFuenteA.length));
            CantPTG1.setText(Integer.toString(this.msi.productoresTG.length));
            CantEnsambladores1.setText(Integer.toString(this.msi.ensambladores.length));
        }
        
        
        
        
    }
    
    //Funcion para verificar que un valor ingresado sea numero
    public static int validacion(String numString) {
        int numero;
        try {
            numero = Integer.parseInt(numString);
            return numero;

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input No numerico: " + ex.getMessage());
        }
        return -1;
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Guardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cantproductoresPB = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        cantProductoresFuenteA = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cantProductoresCPUS = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        cantProductoresRAM = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        cantProductoresTA = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cantEnsambladores = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tiempoDia = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        fechaLimiteEntrega = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        diasRestantes = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        computadorasListaTG = new javax.swing.JLabel();
        computadorasListasEst = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        ganancias = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        utilidad = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        costos = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        ganancias1 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        utilidad1 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        costos1 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        CantTG = new javax.swing.JLabel();
        CantPB = new javax.swing.JLabel();
        CantCPU = new javax.swing.JLabel();
        CantRAM = new javax.swing.JLabel();
        CantFA = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        CantPTG = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        agregarEnsamblador = new javax.swing.JButton();
        borrarEnsamblador = new javax.swing.JButton();
        agregarPPB = new javax.swing.JButton();
        agregarPCPU = new javax.swing.JButton();
        agregarPRAM = new javax.swing.JButton();
        agregarPFA = new javax.swing.JButton();
        AgregarPTA = new javax.swing.JButton();
        borrarPPB = new javax.swing.JButton();
        borrarPCPU = new javax.swing.JButton();
        borrarPRAM = new javax.swing.JButton();
        borrarPFA = new javax.swing.JButton();
        borrarPTG = new javax.swing.JButton();
        jLabel79 = new javax.swing.JLabel();
        CantPPB = new javax.swing.JLabel();
        CantPCPU = new javax.swing.JLabel();
        CantPRAM = new javax.swing.JLabel();
        CantEnsambladores = new javax.swing.JLabel();
        CantPFA = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        dineroDescontadoPm = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        estadoPM = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        pmFaltas = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        estadoDirector = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        CantTG1 = new javax.swing.JLabel();
        CantRAM1 = new javax.swing.JLabel();
        CantFA1 = new javax.swing.JLabel();
        CantPB1 = new javax.swing.JLabel();
        CantCPU1 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        estadoDirector1 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        estadoPM1 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        pmFaltas1 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        dineroDescontadoPm1 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        diasRestantes1 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        computadorasListaTG1 = new javax.swing.JLabel();
        computadorasListasEst1 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        CantPTG1 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        agregarEnsamblador1 = new javax.swing.JButton();
        borrarEnsamblador1 = new javax.swing.JButton();
        agregarPPB1 = new javax.swing.JButton();
        agregarPCPU1 = new javax.swing.JButton();
        agregarPRAM1 = new javax.swing.JButton();
        agregarPFA1 = new javax.swing.JButton();
        AgregarPTA1 = new javax.swing.JButton();
        borrarPPB1 = new javax.swing.JButton();
        borrarPCPU1 = new javax.swing.JButton();
        borrarPRAM1 = new javax.swing.JButton();
        borrarPFA1 = new javax.swing.JButton();
        borrarPTG1 = new javax.swing.JButton();
        jLabel114 = new javax.swing.JLabel();
        CantPPB1 = new javax.swing.JLabel();
        CantPCPU1 = new javax.swing.JLabel();
        CantPRAM1 = new javax.swing.JLabel();
        CantEnsambladores1 = new javax.swing.JLabel();
        CantPFA1 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        ganancias2 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        utilidad2 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        costos2 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        ganancias3 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        utilidad3 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        costos3 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInicio (2).jpg"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Leelawadee UI", 1, 40)); // NOI18N
        jLabel29.setText("VSimulator");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Green and White Modern Computer Service Repair Logo (2).png"))); // NOI18N
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 70));

        Guardar.setBackground(new java.awt.Color(0, 120, 120));
        Guardar.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        Guardar.setForeground(new java.awt.Color(255, 255, 255));
        Guardar.setText("Guardar Simulación");
        Guardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, new java.awt.Color(153, 255, 255), java.awt.Color.gray, new java.awt.Color(153, 255, 255)));
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jPanel5.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(769, 22, 170, 30));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 90));

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 13)); // NOI18N
        jLabel3.setText("El objetivo de la simulación es conocer las  ganacias potenciales ");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 380, 60));

        jLabel4.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 13)); // NOI18N
        jLabel4.setText("funcionamiento.");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 370, 60));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setText("UN VERSUS!");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 270, 60));

        jLabel5.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 13)); // NOI18N
        jLabel5.setText("con la producción y ventas de las computadoras, contrastadas con");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 370, 60));

        jLabel7.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 13)); // NOI18N
        jLabel7.setText("los gastos operativos que implican mantener las compañias en  ");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 400, 60));

        jLabel8.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        jLabel8.setText("VIABILIDAD EN ");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 290, 60));

        jLabel9.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 13)); // NOI18N
        jLabel9.setText("El objetivo de la simulación es conocer las  ganacias potenciales ");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 380, 60));

        jLabel10.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 13)); // NOI18N
        jLabel10.setText("El objetivo de la simulación es conocer las  ganacias potenciales ");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 380, 60));

        jLabel11.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 13)); // NOI18N
        jLabel11.setText("El objetivo de la simulación es conocer las  ganacias potenciales ");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 380, 60));

        jLabel12.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        jLabel12.setText("VIABILIDAD EN ");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 350, 60));

        jLabel13.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        jLabel13.setText("VIABILIDAD EN ");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 340, 60));

        jLabel15.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        jLabel15.setText("VIABILIDAD EN ");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 290, 60));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dell-brand-logo-computer-symbol-blue-design-usa-laptop-illustration-with-black-background-free-vector.jpg"))); // NOI18N
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 330, 190, 140));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/msilogoo.jpg"))); // NOI18N
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 170, 130));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ram.jpg"))); // NOI18N
        jPanel3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 286, 100, 50));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setForeground(new java.awt.Color(0, 0, 0));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setBackground(new java.awt.Color(153, 153, 153));
        jLabel24.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Productores placa base");
        jPanel13.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 170, -1));

        cantproductoresPB.setBackground(new java.awt.Color(204, 204, 204));
        cantproductoresPB.setBorder(null);
        cantproductoresPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantproductoresPBActionPerformed(evt);
            }
        });
        jPanel13.add(cantproductoresPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 70, 20));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/placa-madre4-e1534448782751 (1).jpg"))); // NOI18N
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 180, 120));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setBackground(new java.awt.Color(153, 153, 153));
        jLabel27.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("alimentación");
        jPanel14.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 220, -1));

        cantProductoresFuenteA.setBackground(new java.awt.Color(204, 204, 204));
        cantProductoresFuenteA.setBorder(null);
        cantProductoresFuenteA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantProductoresFuenteAActionPerformed(evt);
            }
        });
        jPanel14.add(cantProductoresFuenteA, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 70, 20));

        jLabel32.setBackground(new java.awt.Color(153, 153, 153));
        jLabel32.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Productores fuente ");
        jPanel14.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 220, 30));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mejores-fuentes-de-alimentacion-660x387 (1).jpg"))); // NOI18N
        jPanel14.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel3.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 180, 130));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cpu.jpg"))); // NOI18N
        jPanel16.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 50));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Productores CPUS");
        jPanel16.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 170, -1));

        cantProductoresCPUS.setBackground(new java.awt.Color(204, 204, 204));
        cantProductoresCPUS.setBorder(null);
        cantProductoresCPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantProductoresCPUSActionPerformed(evt);
            }
        });
        jPanel16.add(cantProductoresCPUS, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 70, 20));

        jPanel3.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, 180, 120));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setForeground(new java.awt.Color(0, 0, 0));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setBackground(new java.awt.Color(153, 153, 153));
        jLabel26.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Productores RAM");
        jPanel17.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 170, -1));

        cantProductoresRAM.setBackground(new java.awt.Color(204, 204, 204));
        cantProductoresRAM.setBorder(null);
        cantProductoresRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantProductoresRAMActionPerformed(evt);
            }
        });
        jPanel17.add(cantProductoresRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 70, 20));

        jPanel3.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, 180, 120));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setBackground(new java.awt.Color(153, 153, 153));
        jLabel28.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Productores tarjeta gráfica");
        jPanel19.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 220, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/c_mo_funciona_una_tarjeta_gr_fica (1).jpg"))); // NOI18N
        jPanel19.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        cantProductoresTA.setBackground(new java.awt.Color(204, 204, 204));
        cantProductoresTA.setBorder(null);
        cantProductoresTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantProductoresTAActionPerformed(evt);
            }
        });
        jPanel19.add(cantProductoresTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 70, 20));

        jPanel3.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 420, 180, 130));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setBackground(new java.awt.Color(153, 153, 153));
        jLabel21.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Ensambladores");
        jPanel20.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 100, -1));

        cantEnsambladores.setBackground(new java.awt.Color(204, 204, 204));
        cantEnsambladores.setBorder(null);
        cantEnsambladores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantEnsambladoresActionPerformed(evt);
            }
        });
        jPanel20.add(cantEnsambladores, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 70, 20));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ensamblador-de-pc (1).png"))); // NOI18N
        jPanel20.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 60));

        jPanel3.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 420, 180, 130));

        jLabel23.setBackground(new java.awt.Color(153, 153, 153));
        jLabel23.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Duración día");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 180, 100, 20));

        jLabel19.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("DATOS DEL SIMULADOR");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 140, 180, 36));

        tiempoDia.setBackground(new java.awt.Color(204, 204, 204));
        tiempoDia.setBorder(null);
        tiempoDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiempoDiaActionPerformed(evt);
            }
        });
        jPanel3.add(tiempoDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 60, 20));

        jLabel18.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Días de entrega");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, 110, 20));

        fechaLimiteEntrega.setBackground(new java.awt.Color(204, 204, 204));
        fechaLimiteEntrega.setBorder(null);
        fechaLimiteEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaLimiteEntregaActionPerformed(evt);
            }
        });
        jPanel3.add(fechaLimiteEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 220, 60, 20));

        jLabel22.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("seg");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 180, 60, 20));

        jLabel20.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("días");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 220, 60, 20));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInicio (2).jpg"))); // NOI18N
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jTabbedPane3.addTab("Inicio", jPanel3);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dell-Logo-White-Background.png"))); // NOI18N
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel35.setFont(new java.awt.Font("Leelawadee UI", 1, 40)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(50, 135, 193));
        jLabel35.setText("Technologies");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        exit.setBackground(new java.awt.Color(204, 204, 204));
        exit.setFont(new java.awt.Font("Leelawadee UI", 0, 11)); // NOI18N
        exit.setForeground(new java.awt.Color(0, 0, 102));
        exit.setText("exit");
        exit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel2.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 30, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 90));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInicio (2).png"))); // NOI18N
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 970, 230));

        jPanel15.setBackground(new java.awt.Color(0, 51, 102));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        diasRestantes.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        diasRestantes.setForeground(new java.awt.Color(255, 255, 255));
        diasRestantes.setText("0");
        jPanel15.add(diasRestantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 60, -1));

        jLabel44.setFont(new java.awt.Font("Leelawadee UI", 1, 22)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Computadoras Listas");
        jPanel15.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -10, -1, 40));

        jLabel47.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(153, 153, 153));
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInicio (3) (1).png"))); // NOI18N
        jPanel15.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 100, -1));

        computadorasListaTG.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        computadorasListaTG.setForeground(new java.awt.Color(255, 255, 255));
        computadorasListaTG.setText("0");
        jPanel15.add(computadorasListaTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 50, 30));

        computadorasListasEst.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        computadorasListasEst.setForeground(new java.awt.Color(255, 255, 255));
        computadorasListasEst.setText("0");
        jPanel15.add(computadorasListasEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 40, -1));

        jLabel50.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Con tarjeta gráfica:");
        jPanel15.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, 30));

        jLabel42.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Deadline de entrega:");
        jPanel15.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 180, -1));

        jLabel59.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Estándar: ");
        jPanel15.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 90, -1));

        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, 350, 100));

        jPanel22.setBackground(new java.awt.Color(50, 135, 193));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel22.setForeground(new java.awt.Color(204, 204, 204));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ganancias.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        ganancias.setForeground(new java.awt.Color(255, 255, 255));
        ganancias.setText("0");
        jPanel22.add(ganancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 80, 20));

        jLabel48.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Costos");
        jPanel22.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 10, 60, -1));

        utilidad.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        utilidad.setForeground(new java.awt.Color(255, 255, 255));
        utilidad.setText("0");
        jPanel22.add(utilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 70, -1));

        jLabel51.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Ganancias");
        jPanel22.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jLabel52.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("$");
        jPanel22.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 20, 20));

        costos.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        costos.setForeground(new java.awt.Color(255, 255, 255));
        costos.setText("0");
        jPanel22.add(costos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 90, 20));

        jLabel54.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("$");
        jPanel22.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 20, 20));

        jLabel46.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Utilidad total:$");
        jPanel22.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        jPanel23.setBackground(new java.awt.Color(50, 135, 193));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel23.setForeground(new java.awt.Color(204, 204, 204));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ganancias1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        ganancias1.setForeground(new java.awt.Color(255, 255, 255));
        ganancias1.setText("0");
        jPanel23.add(ganancias1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 25, 50, 30));

        jLabel53.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Costos");
        jPanel23.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, -1));

        utilidad1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        utilidad1.setForeground(new java.awt.Color(255, 255, 255));
        utilidad1.setText("0");
        jPanel23.add(utilidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 40, -1));

        jLabel55.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Ganancias");
        jPanel23.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel56.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("$");
        jPanel23.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 25, -1, 30));

        costos1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        costos1.setForeground(new java.awt.Color(255, 255, 255));
        costos1.setText("0");
        jPanel23.add(costos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 25, 40, 30));

        jLabel57.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("$");
        jPanel23.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, 20, 30));

        jLabel58.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Utilidad total:  $");
        jPanel23.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jPanel22.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 200, 90));

        jPanel1.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 460, 210, 120));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Empleados-de-Dell.jpg"))); // NOI18N
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 200, 120));

        CantTG.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantTG.setForeground(new java.awt.Color(102, 102, 102));
        CantTG.setText("0");
        jPanel1.add(CantTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, 40, -1));

        CantPB.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantPB.setForeground(new java.awt.Color(102, 102, 102));
        CantPB.setText("0");
        jPanel1.add(CantPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 30, -1));

        CantCPU.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantCPU.setForeground(new java.awt.Color(102, 102, 102));
        CantCPU.setText("0");
        jPanel1.add(CantCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 30, -1));

        CantRAM.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantRAM.setForeground(new java.awt.Color(102, 102, 102));
        CantRAM.setText("0");
        jPanel1.add(CantRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 40, -1));

        CantFA.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantFA.setForeground(new java.awt.Color(102, 102, 102));
        CantFA.setText("0");
        jPanel1.add(CantFA, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 40, -1));

        jLabel64.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(102, 102, 102));
        jLabel64.setText("Max");
        jPanel1.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 50, -1));

        jLabel69.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(102, 102, 102));
        jLabel69.setText("Disponible");
        jPanel1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 60, -1));

        jLabel71.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(102, 102, 102));
        jLabel71.setText("25");
        jPanel1.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 30, 40));

        jLabel72.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(102, 102, 102));
        jLabel72.setText("10");
        jPanel1.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 30, -1));

        jLabel73.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(102, 102, 102));
        jLabel73.setText("20");
        jPanel1.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 30, -1));

        jLabel74.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(102, 102, 102));
        jLabel74.setText("55");
        jPanel1.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 30, -1));

        jLabel75.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(102, 102, 102));
        jLabel75.setText("35");
        jPanel1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 30, -1));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel21.setBackground(new java.awt.Color(204, 204, 204));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel21.setForeground(new java.awt.Color(204, 204, 204));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CantPTG.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPTG.setForeground(new java.awt.Color(102, 102, 102));
        CantPTG.setText("0");
        jPanel21.add(CantPTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 40, -1));

        jLabel65.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(102, 102, 102));
        jLabel65.setText("Productores CPU");
        jPanel21.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, -1));

        jLabel66.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(102, 102, 102));
        jLabel66.setText("Productores RAM");
        jPanel21.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 200, -1));

        jLabel67.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(102, 102, 102));
        jLabel67.setText("Productores Fuente A");
        jPanel21.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 190, -1));

        jLabel60.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(102, 102, 102));
        jLabel60.setText("Ensamblador");
        jPanel21.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 220, -1));

        jLabel62.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(102, 102, 102));
        jLabel62.setText("Productores Tarjeta G");
        jPanel21.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 230, -1));

        agregarEnsamblador.setBackground(new java.awt.Color(0, 51, 102));
        agregarEnsamblador.setText("+");
        agregarEnsamblador.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarEnsamblador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarEnsambladorActionPerformed(evt);
            }
        });
        jPanel21.add(agregarEnsamblador, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 40, 20));

        borrarEnsamblador.setBackground(new java.awt.Color(0, 51, 102));
        borrarEnsamblador.setText("-");
        borrarEnsamblador.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarEnsamblador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarEnsambladorActionPerformed(evt);
            }
        });
        jPanel21.add(borrarEnsamblador, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 40, 20));

        agregarPPB.setBackground(new java.awt.Color(0, 51, 102));
        agregarPPB.setFont(new java.awt.Font("Leelawadee UI", 1, 11)); // NOI18N
        agregarPPB.setText("+");
        agregarPPB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarPPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPPBActionPerformed(evt);
            }
        });
        jPanel21.add(agregarPPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 40, 20));

        agregarPCPU.setBackground(new java.awt.Color(0, 51, 102));
        agregarPCPU.setText("+");
        agregarPCPU.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarPCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPCPUActionPerformed(evt);
            }
        });
        jPanel21.add(agregarPCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 40, 20));

        agregarPRAM.setBackground(new java.awt.Color(0, 51, 102));
        agregarPRAM.setText("+");
        agregarPRAM.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarPRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPRAMActionPerformed(evt);
            }
        });
        jPanel21.add(agregarPRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 40, 20));

        agregarPFA.setBackground(new java.awt.Color(0, 51, 102));
        agregarPFA.setText("+");
        agregarPFA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarPFA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPFAActionPerformed(evt);
            }
        });
        jPanel21.add(agregarPFA, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 40, 20));

        AgregarPTA.setBackground(new java.awt.Color(0, 51, 102));
        AgregarPTA.setText("+");
        AgregarPTA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AgregarPTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarPTAActionPerformed(evt);
            }
        });
        jPanel21.add(AgregarPTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 40, 20));

        borrarPPB.setBackground(new java.awt.Color(0, 51, 102));
        borrarPPB.setText("-");
        borrarPPB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPPBActionPerformed(evt);
            }
        });
        jPanel21.add(borrarPPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 40, 20));

        borrarPCPU.setBackground(new java.awt.Color(0, 51, 102));
        borrarPCPU.setText("-");
        borrarPCPU.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPCPUActionPerformed(evt);
            }
        });
        jPanel21.add(borrarPCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 40, 20));

        borrarPRAM.setBackground(new java.awt.Color(0, 51, 102));
        borrarPRAM.setText("-");
        borrarPRAM.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPRAMActionPerformed(evt);
            }
        });
        jPanel21.add(borrarPRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 40, 20));

        borrarPFA.setBackground(new java.awt.Color(0, 51, 102));
        borrarPFA.setText("-");
        borrarPFA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPFA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPFAActionPerformed(evt);
            }
        });
        jPanel21.add(borrarPFA, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 40, 20));

        borrarPTG.setBackground(new java.awt.Color(0, 51, 102));
        borrarPTG.setText("-");
        borrarPTG.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPTGActionPerformed(evt);
            }
        });
        jPanel21.add(borrarPTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 40, 20));

        jLabel79.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(102, 102, 102));
        jLabel79.setText("Productores placa base");
        jPanel21.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, -1));

        CantPPB.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPPB.setForeground(new java.awt.Color(102, 102, 102));
        CantPPB.setText("0");
        jPanel21.add(CantPPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 40, -1));

        CantPCPU.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPCPU.setForeground(new java.awt.Color(102, 102, 102));
        CantPCPU.setText("0");
        jPanel21.add(CantPCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 40, -1));

        CantPRAM.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPRAM.setForeground(new java.awt.Color(102, 102, 102));
        CantPRAM.setText("0");
        jPanel21.add(CantPRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 40, -1));

        CantEnsambladores.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantEnsambladores.setForeground(new java.awt.Color(102, 102, 102));
        CantEnsambladores.setText("0");
        jPanel21.add(CantEnsambladores, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 40, -1));

        CantPFA.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPFA.setForeground(new java.awt.Color(102, 102, 102));
        CantPFA.setText("0");
        jPanel21.add(CantPFA, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 40, -1));

        jPanel1.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, 350, -1));

        jLabel76.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(102, 102, 102));
        jLabel76.setText("Placa Base");
        jPanel1.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 200, -1));

        jLabel68.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(102, 102, 102));
        jLabel68.setText("CPU");
        jPanel1.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 200, -1));

        jLabel77.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(102, 102, 102));
        jLabel77.setText("RAM");
        jPanel1.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 200, -1));

        jLabel78.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(102, 102, 102));
        jLabel78.setText("Fuente Alimentación");
        jPanel1.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 280, -1));

        dineroDescontadoPm.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        dineroDescontadoPm.setForeground(new java.awt.Color(102, 102, 102));
        dineroDescontadoPm.setText("0");
        jPanel1.add(dineroDescontadoPm, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 80, -1));

        jLabel45.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 51, 102));
        jLabel45.setText("Partes");
        jPanel1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 120, -1));

        jLabel43.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 51, 102));
        jLabel43.setText("Datos");
        jPanel1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 420, -1, 50));

        jLabel49.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 51, 102));
        jLabel49.setText("Estados");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, -1, 50));

        jLabel63.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(102, 102, 102));
        jLabel63.setText("Tarjeta Gráfica");
        jPanel1.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 280, -1));

        estadoPM.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        estadoPM.setForeground(new java.awt.Color(102, 102, 102));
        estadoPM.setText("Trabajando / Viendo Anime");
        jPanel1.add(estadoPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 280, 30));

        jLabel80.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 51, 102));
        jLabel80.setText("Trabajadores");
        jPanel1.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, -1, 50));

        jLabel81.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(102, 102, 102));
        jLabel81.setText("Project Manager:");
        jPanel1.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 280, -1));

        pmFaltas.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        pmFaltas.setForeground(new java.awt.Color(102, 102, 102));
        pmFaltas.setText("0");
        jPanel1.add(pmFaltas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, 60, -1));

        jLabel83.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(102, 102, 102));
        jLabel83.setText("Director:");
        jPanel1.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 80, -1));

        estadoDirector.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        estadoDirector.setForeground(new java.awt.Color(102, 102, 102));
        estadoDirector.setText("Trabajando / Vigilando");
        jPanel1.add(estadoDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 280, -1));

        jLabel84.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(102, 102, 102));
        jLabel84.setText("Faltas del Product Manger: ");
        jPanel1.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 280, -1));

        jLabel70.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(102, 102, 102));
        jLabel70.setText("Dinero descontado: $");
        jPanel1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 190, -1));

        jTabbedPane3.addTab("Dell", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 958, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MSI logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(615, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 90));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Laptops MSI.png"))); // NOI18N
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 90, 1030, 230));

        jLabel82.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(102, 102, 102));
        jLabel82.setText("Placa Base");
        jPanel4.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 200, -1));

        jLabel85.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(102, 102, 102));
        jLabel85.setText("CPU");
        jPanel4.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 200, -1));

        jLabel86.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(102, 102, 102));
        jLabel86.setText("RAM");
        jPanel4.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 200, -1));

        jLabel87.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(102, 102, 102));
        jLabel87.setText("Fuente Alimentación");
        jPanel4.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 280, -1));

        jLabel88.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(102, 102, 102));
        jLabel88.setText("Tarjeta Gráfica");
        jPanel4.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 280, -1));

        jLabel89.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(102, 0, 102));
        jLabel89.setText("Estados");
        jPanel4.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 120, -1));

        jLabel90.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(102, 102, 102));
        jLabel90.setText("Disponible");
        jPanel4.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 60, -1));

        jLabel91.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(102, 102, 102));
        jLabel91.setText("Max");
        jPanel4.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 50, -1));

        jLabel92.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(102, 102, 102));
        jLabel92.setText("25");
        jPanel4.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 30, 40));

        jLabel93.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(102, 102, 102));
        jLabel93.setText("20");
        jPanel4.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 30, -1));

        jLabel94.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(102, 102, 102));
        jLabel94.setText("55");
        jPanel4.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 30, -1));

        jLabel95.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(102, 102, 102));
        jLabel95.setText("35");
        jPanel4.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 30, -1));

        jLabel96.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(102, 102, 102));
        jLabel96.setText("10");
        jPanel4.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 30, -1));

        CantTG1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantTG1.setForeground(new java.awt.Color(102, 102, 102));
        CantTG1.setText("0");
        jPanel4.add(CantTG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, 40, -1));

        CantRAM1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantRAM1.setForeground(new java.awt.Color(102, 102, 102));
        CantRAM1.setText("0");
        jPanel4.add(CantRAM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 40, -1));

        CantFA1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantFA1.setForeground(new java.awt.Color(102, 102, 102));
        CantFA1.setText("0");
        jPanel4.add(CantFA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 40, -1));

        CantPB1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantPB1.setForeground(new java.awt.Color(102, 102, 102));
        CantPB1.setText("0");
        jPanel4.add(CantPB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 30, -1));

        CantCPU1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        CantCPU1.setForeground(new java.awt.Color(102, 102, 102));
        CantCPU1.setText("0");
        jPanel4.add(CantCPU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 30, -1));

        jLabel97.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(102, 0, 102));
        jLabel97.setText("Partes");
        jPanel4.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 120, -1));

        jLabel98.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(102, 102, 102));
        jLabel98.setText("Director:");
        jPanel4.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 80, -1));

        estadoDirector1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        estadoDirector1.setForeground(new java.awt.Color(102, 102, 102));
        estadoDirector1.setText("Trabajando / Vigilando");
        jPanel4.add(estadoDirector1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 280, -1));

        jLabel99.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(102, 102, 102));
        jLabel99.setText("Project Manager:");
        jPanel4.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 280, -1));

        estadoPM1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        estadoPM1.setForeground(new java.awt.Color(102, 102, 102));
        estadoPM1.setText("Trabajando / Viendo Anime");
        jPanel4.add(estadoPM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 280, 30));

        jLabel100.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(102, 102, 102));
        jLabel100.setText("Faltas del Product Manger: ");
        jPanel4.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 280, -1));

        pmFaltas1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        pmFaltas1.setForeground(new java.awt.Color(102, 102, 102));
        pmFaltas1.setText("0");
        jPanel4.add(pmFaltas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, 60, -1));

        jLabel101.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(102, 102, 102));
        jLabel101.setText("Dinero descontado: $");
        jPanel4.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 190, -1));

        dineroDescontadoPm1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        dineroDescontadoPm1.setForeground(new java.awt.Color(102, 102, 102));
        dineroDescontadoPm1.setText("0");
        jPanel4.add(dineroDescontadoPm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 80, -1));

        jPanel24.setBackground(new java.awt.Color(51, 0, 51));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        diasRestantes1.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        diasRestantes1.setForeground(new java.awt.Color(255, 255, 255));
        diasRestantes1.setText("0");
        jPanel24.add(diasRestantes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 50, -1));

        jLabel102.setFont(new java.awt.Font("Leelawadee UI", 1, 22)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("Computadoras Listas");
        jPanel24.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -10, -1, 40));

        computadorasListaTG1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        computadorasListaTG1.setForeground(new java.awt.Color(255, 255, 255));
        computadorasListaTG1.setText("0");
        jPanel24.add(computadorasListaTG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 40, 30));

        computadorasListasEst1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        computadorasListasEst1.setForeground(new java.awt.Color(255, 255, 255));
        computadorasListasEst1.setText("0");
        jPanel24.add(computadorasListasEst1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 40, -1));

        jLabel104.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 255, 255));
        jLabel104.setText("Con tarjeta gráfica:");
        jPanel24.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, 30));

        jLabel105.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setText("Deadline de entrega:");
        jPanel24.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 180, -1));

        jLabel106.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setText("Estándar: ");
        jPanel24.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 90, -1));

        jPanel4.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, 350, 100));

        jLabel107.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(0, 51, 102));
        jLabel107.setText("Datos");
        jPanel4.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 420, -1, 50));

        jLabel108.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(102, 0, 102));
        jLabel108.setText("Trabajadores");
        jPanel4.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, -1, 50));

        jPanel25.setBackground(new java.awt.Color(204, 204, 204));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel25.setForeground(new java.awt.Color(204, 204, 204));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CantPTG1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPTG1.setForeground(new java.awt.Color(102, 102, 102));
        CantPTG1.setText("0");
        jPanel25.add(CantPTG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 40, -1));

        jLabel109.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(102, 102, 102));
        jLabel109.setText("Productores CPU");
        jPanel25.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, -1));

        jLabel110.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(102, 102, 102));
        jLabel110.setText("Productores RAM");
        jPanel25.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 200, -1));

        jLabel111.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(102, 102, 102));
        jLabel111.setText("Productores Fuente A");
        jPanel25.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 190, -1));

        jLabel112.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(102, 102, 102));
        jLabel112.setText("Ensamblador");
        jPanel25.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 220, -1));

        jLabel113.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(102, 102, 102));
        jLabel113.setText("Productores Tarjeta G");
        jPanel25.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 230, -1));

        agregarEnsamblador1.setBackground(new java.awt.Color(51, 0, 51));
        agregarEnsamblador1.setText("+");
        agregarEnsamblador1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarEnsamblador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarEnsamblador1ActionPerformed(evt);
            }
        });
        jPanel25.add(agregarEnsamblador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 40, 20));

        borrarEnsamblador1.setBackground(new java.awt.Color(51, 0, 51));
        borrarEnsamblador1.setText("-");
        borrarEnsamblador1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarEnsamblador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarEnsamblador1ActionPerformed(evt);
            }
        });
        jPanel25.add(borrarEnsamblador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 40, 20));

        agregarPPB1.setBackground(new java.awt.Color(51, 0, 51));
        agregarPPB1.setFont(new java.awt.Font("Leelawadee UI", 1, 11)); // NOI18N
        agregarPPB1.setText("+");
        agregarPPB1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarPPB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPPB1ActionPerformed(evt);
            }
        });
        jPanel25.add(agregarPPB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 40, 20));

        agregarPCPU1.setBackground(new java.awt.Color(51, 0, 51));
        agregarPCPU1.setText("+");
        agregarPCPU1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarPCPU1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPCPU1ActionPerformed(evt);
            }
        });
        jPanel25.add(agregarPCPU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 40, 20));

        agregarPRAM1.setBackground(new java.awt.Color(51, 0, 51));
        agregarPRAM1.setText("+");
        agregarPRAM1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarPRAM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPRAM1ActionPerformed(evt);
            }
        });
        jPanel25.add(agregarPRAM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 40, 20));

        agregarPFA1.setBackground(new java.awt.Color(51, 0, 51));
        agregarPFA1.setText("+");
        agregarPFA1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agregarPFA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPFA1ActionPerformed(evt);
            }
        });
        jPanel25.add(agregarPFA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 40, 20));

        AgregarPTA1.setBackground(new java.awt.Color(51, 0, 51));
        AgregarPTA1.setText("+");
        AgregarPTA1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AgregarPTA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarPTA1ActionPerformed(evt);
            }
        });
        jPanel25.add(AgregarPTA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 40, 20));

        borrarPPB1.setBackground(new java.awt.Color(51, 0, 51));
        borrarPPB1.setText("-");
        borrarPPB1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPPB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPPB1ActionPerformed(evt);
            }
        });
        jPanel25.add(borrarPPB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 40, 20));

        borrarPCPU1.setBackground(new java.awt.Color(51, 0, 51));
        borrarPCPU1.setText("-");
        borrarPCPU1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPCPU1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPCPU1ActionPerformed(evt);
            }
        });
        jPanel25.add(borrarPCPU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 40, 20));

        borrarPRAM1.setBackground(new java.awt.Color(51, 0, 51));
        borrarPRAM1.setText("-");
        borrarPRAM1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPRAM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPRAM1ActionPerformed(evt);
            }
        });
        jPanel25.add(borrarPRAM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 40, 20));

        borrarPFA1.setBackground(new java.awt.Color(51, 0, 51));
        borrarPFA1.setText("-");
        borrarPFA1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPFA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPFA1ActionPerformed(evt);
            }
        });
        jPanel25.add(borrarPFA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 40, 20));

        borrarPTG1.setBackground(new java.awt.Color(51, 0, 51));
        borrarPTG1.setText("-");
        borrarPTG1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        borrarPTG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarPTG1ActionPerformed(evt);
            }
        });
        jPanel25.add(borrarPTG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 40, 20));

        jLabel114.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(102, 102, 102));
        jLabel114.setText("Productores placa base");
        jPanel25.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, -1));

        CantPPB1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPPB1.setForeground(new java.awt.Color(102, 102, 102));
        CantPPB1.setText("0");
        jPanel25.add(CantPPB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 40, -1));

        CantPCPU1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPCPU1.setForeground(new java.awt.Color(102, 102, 102));
        CantPCPU1.setText("0");
        jPanel25.add(CantPCPU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 40, -1));

        CantPRAM1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPRAM1.setForeground(new java.awt.Color(102, 102, 102));
        CantPRAM1.setText("0");
        jPanel25.add(CantPRAM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 40, -1));

        CantEnsambladores1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantEnsambladores1.setForeground(new java.awt.Color(102, 102, 102));
        CantEnsambladores1.setText("0");
        jPanel25.add(CantEnsambladores1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 40, -1));

        CantPFA1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 16)); // NOI18N
        CantPFA1.setForeground(new java.awt.Color(102, 102, 102));
        CantPFA1.setText("0");
        jPanel25.add(CantPFA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 40, -1));

        jPanel4.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, 350, -1));

        jPanel26.setBackground(new java.awt.Color(51, 0, 51));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel26.setForeground(new java.awt.Color(204, 204, 204));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ganancias2.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        ganancias2.setForeground(new java.awt.Color(255, 255, 255));
        ganancias2.setText("0");
        jPanel26.add(ganancias2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        jLabel115.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
        jLabel115.setText("Costos");
        jPanel26.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 10, 60, -1));

        utilidad2.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        utilidad2.setForeground(new java.awt.Color(255, 255, 255));
        utilidad2.setText("0");
        jPanel26.add(utilidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 90, -1));

        jLabel116.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 255, 255));
        jLabel116.setText("Ganancias");
        jPanel26.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jLabel117.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
        jLabel117.setText("$");
        jPanel26.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 20, 20));

        costos2.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        costos2.setForeground(new java.awt.Color(255, 255, 255));
        costos2.setText("0");
        jPanel26.add(costos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 110, 20));

        jLabel118.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(255, 255, 255));
        jLabel118.setText("$");
        jPanel26.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 20, 20));

        jLabel119.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setText("Utilidad total:$");
        jPanel26.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        jPanel27.setBackground(new java.awt.Color(50, 135, 193));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel27.setForeground(new java.awt.Color(204, 204, 204));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ganancias3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        ganancias3.setForeground(new java.awt.Color(255, 255, 255));
        ganancias3.setText("0");
        jPanel27.add(ganancias3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 25, 50, 30));

        jLabel120.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 255, 255));
        jLabel120.setText("Costos");
        jPanel27.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, -1));

        utilidad3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        utilidad3.setForeground(new java.awt.Color(255, 255, 255));
        utilidad3.setText("0");
        jPanel27.add(utilidad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 40, -1));

        jLabel121.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(255, 255, 255));
        jLabel121.setText("Ganancias");
        jPanel27.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel122.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(255, 255, 255));
        jLabel122.setText("$");
        jPanel27.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 25, -1, 30));

        costos3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        costos3.setForeground(new java.awt.Color(255, 255, 255));
        costos3.setText("0");
        jPanel27.add(costos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 25, 40, 30));

        jLabel123.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(255, 255, 255));
        jLabel123.setText("$");
        jPanel27.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, 20, 30));

        jLabel124.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 18)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(255, 255, 255));
        jLabel124.setText("Utilidad total:  $");
        jPanel27.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jPanel26.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 200, 90));

        jPanel4.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 460, 220, 120));

        jTabbedPane3.addTab("MSI", jPanel4);

        getContentPane().add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tiempoDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiempoDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiempoDiaActionPerformed

    private void cantEnsambladoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantEnsambladoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantEnsambladoresActionPerformed

    private void fechaLimiteEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaLimiteEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaLimiteEntregaActionPerformed

    private void cantproductoresPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantproductoresPBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantproductoresPBActionPerformed

    private void cantProductoresCPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantProductoresCPUSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantProductoresCPUSActionPerformed

    private void cantProductoresRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantProductoresRAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantProductoresRAMActionPerformed

    private void cantProductoresFuenteAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantProductoresFuenteAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantProductoresFuenteAActionPerformed

    private void cantProductoresTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantProductoresTAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantProductoresTAActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        // Funcion que se activa cuando se presiona el botón Guardar Simulación para pasar la info a un TXT
        String info= "";
        String ruta="src\\Main\\Simulacion.txt";
        int maxTrabajadores = 0-Integer.parseInt(this.JTextFieldArreglo[0].getText())-Integer.parseInt(this.JTextFieldArreglo[1].getText());
        for (int i = 0; i < 8; i++) {
            if (validacion(this.JTextFieldArreglo[i].getText()) != -1 ) {
                if (i == 7) {
                    info += this.JTextFieldArreglo[i].getText();
                } else {
                    info += this.JTextFieldArreglo[i].getText() + ",";                
                }  
                maxTrabajadores += Integer.parseInt(this.JTextFieldArreglo[i].getText());
            } else {
                break;
            }
        }
        if (maxTrabajadores <= 12) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write(info);
            JOptionPane.showMessageDialog(null, "Simulación guardada con exito");
            } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Tu Simulación no ha sido guardada" + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR! Guardado no exitoso. Máxima cantidad de trabajadores es 12");
        }
        
        
        
    }//GEN-LAST:event_GuardarActionPerformed

    private void agregarEnsambladorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarEnsambladorActionPerformed
        this.dell.agregarTrabajador(5);
    }//GEN-LAST:event_agregarEnsambladorActionPerformed

    private void borrarEnsambladorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarEnsambladorActionPerformed
        this.dell.borrarTrabajador(5);
    }//GEN-LAST:event_borrarEnsambladorActionPerformed

    private void agregarPPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPPBActionPerformed
        this.dell.agregarTrabajador(0);
    }//GEN-LAST:event_agregarPPBActionPerformed

    private void agregarPCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPCPUActionPerformed
        this.dell.agregarTrabajador(1);
    }//GEN-LAST:event_agregarPCPUActionPerformed

    private void agregarPRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPRAMActionPerformed
       this.dell.agregarTrabajador(2);
    }//GEN-LAST:event_agregarPRAMActionPerformed

    private void agregarPFAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPFAActionPerformed
        this.dell.agregarTrabajador(3);
    }//GEN-LAST:event_agregarPFAActionPerformed

    private void AgregarPTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarPTAActionPerformed
        this.dell.agregarTrabajador(4);
    }//GEN-LAST:event_AgregarPTAActionPerformed

    private void borrarPPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPPBActionPerformed
        this.dell.borrarTrabajador(0);
    }//GEN-LAST:event_borrarPPBActionPerformed

    private void borrarPCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPCPUActionPerformed
        this.dell.borrarTrabajador(1);
    }//GEN-LAST:event_borrarPCPUActionPerformed

    private void borrarPRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPRAMActionPerformed
        this.dell.borrarTrabajador(2);
    }//GEN-LAST:event_borrarPRAMActionPerformed

    private void borrarPFAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPFAActionPerformed
        this.dell.borrarTrabajador(3);
    }//GEN-LAST:event_borrarPFAActionPerformed

    private void borrarPTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPTGActionPerformed
        this.dell.borrarTrabajador(4);
    }//GEN-LAST:event_borrarPTGActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitActionPerformed

    private void agregarEnsamblador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarEnsamblador1ActionPerformed
        this.msi.agregarTrabajador(5);
    }//GEN-LAST:event_agregarEnsamblador1ActionPerformed

    private void borrarEnsamblador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarEnsamblador1ActionPerformed
        this.msi.borrarTrabajador(5);
    }//GEN-LAST:event_borrarEnsamblador1ActionPerformed

    private void agregarPPB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPPB1ActionPerformed
        this.msi.agregarTrabajador(0);
    }//GEN-LAST:event_agregarPPB1ActionPerformed

    private void agregarPCPU1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPCPU1ActionPerformed
        this.msi.agregarTrabajador(1);
    }//GEN-LAST:event_agregarPCPU1ActionPerformed

    private void agregarPRAM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPRAM1ActionPerformed
        this.msi.agregarTrabajador(2);
    }//GEN-LAST:event_agregarPRAM1ActionPerformed

    private void agregarPFA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPFA1ActionPerformed
        this.msi.agregarTrabajador(3);
    }//GEN-LAST:event_agregarPFA1ActionPerformed

    private void AgregarPTA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarPTA1ActionPerformed
        this.msi.agregarTrabajador(4);
    }//GEN-LAST:event_AgregarPTA1ActionPerformed

    private void borrarPPB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPPB1ActionPerformed
        this.msi.borrarTrabajador(0);
    }//GEN-LAST:event_borrarPPB1ActionPerformed

    private void borrarPCPU1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPCPU1ActionPerformed
        this.msi.borrarTrabajador(1);
    }//GEN-LAST:event_borrarPCPU1ActionPerformed

    private void borrarPRAM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPRAM1ActionPerformed
        this.msi.borrarTrabajador(2);
    }//GEN-LAST:event_borrarPRAM1ActionPerformed

    private void borrarPFA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPFA1ActionPerformed
        this.msi.borrarTrabajador(3);
    }//GEN-LAST:event_borrarPFA1ActionPerformed

    private void borrarPTG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarPTG1ActionPerformed
        this.dell.borrarTrabajador(4);
    }//GEN-LAST:event_borrarPTG1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarPTA;
    private javax.swing.JButton AgregarPTA1;
    private javax.swing.JLabel CantCPU;
    private javax.swing.JLabel CantCPU1;
    private javax.swing.JLabel CantEnsambladores;
    private javax.swing.JLabel CantEnsambladores1;
    private javax.swing.JLabel CantFA;
    private javax.swing.JLabel CantFA1;
    private javax.swing.JLabel CantPB;
    private javax.swing.JLabel CantPB1;
    private javax.swing.JLabel CantPCPU;
    private javax.swing.JLabel CantPCPU1;
    private javax.swing.JLabel CantPFA;
    private javax.swing.JLabel CantPFA1;
    private javax.swing.JLabel CantPPB;
    private javax.swing.JLabel CantPPB1;
    private javax.swing.JLabel CantPRAM;
    private javax.swing.JLabel CantPRAM1;
    private javax.swing.JLabel CantPTG;
    private javax.swing.JLabel CantPTG1;
    private javax.swing.JLabel CantRAM;
    private javax.swing.JLabel CantRAM1;
    private javax.swing.JLabel CantTG;
    private javax.swing.JLabel CantTG1;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton agregarEnsamblador;
    private javax.swing.JButton agregarEnsamblador1;
    private javax.swing.JButton agregarPCPU;
    private javax.swing.JButton agregarPCPU1;
    private javax.swing.JButton agregarPFA;
    private javax.swing.JButton agregarPFA1;
    private javax.swing.JButton agregarPPB;
    private javax.swing.JButton agregarPPB1;
    private javax.swing.JButton agregarPRAM;
    private javax.swing.JButton agregarPRAM1;
    private javax.swing.JButton borrarEnsamblador;
    private javax.swing.JButton borrarEnsamblador1;
    private javax.swing.JButton borrarPCPU;
    private javax.swing.JButton borrarPCPU1;
    private javax.swing.JButton borrarPFA;
    private javax.swing.JButton borrarPFA1;
    private javax.swing.JButton borrarPPB;
    private javax.swing.JButton borrarPPB1;
    private javax.swing.JButton borrarPRAM;
    private javax.swing.JButton borrarPRAM1;
    private javax.swing.JButton borrarPTG;
    private javax.swing.JButton borrarPTG1;
    private javax.swing.JTextField cantEnsambladores;
    private javax.swing.JTextField cantProductoresCPUS;
    private javax.swing.JTextField cantProductoresFuenteA;
    private javax.swing.JTextField cantProductoresRAM;
    private javax.swing.JTextField cantProductoresTA;
    private javax.swing.JTextField cantproductoresPB;
    private javax.swing.JLabel computadorasListaTG;
    private javax.swing.JLabel computadorasListaTG1;
    private javax.swing.JLabel computadorasListasEst;
    private javax.swing.JLabel computadorasListasEst1;
    private javax.swing.JLabel costos;
    private javax.swing.JLabel costos1;
    private javax.swing.JLabel costos2;
    private javax.swing.JLabel costos3;
    private javax.swing.JLabel diasRestantes;
    private javax.swing.JLabel diasRestantes1;
    private javax.swing.JLabel dineroDescontadoPm;
    private javax.swing.JLabel dineroDescontadoPm1;
    private javax.swing.JLabel estadoDirector;
    private javax.swing.JLabel estadoDirector1;
    private javax.swing.JLabel estadoPM;
    private javax.swing.JLabel estadoPM1;
    private javax.swing.JButton exit;
    private javax.swing.JTextField fechaLimiteEntrega;
    private javax.swing.JLabel ganancias;
    private javax.swing.JLabel ganancias1;
    private javax.swing.JLabel ganancias2;
    private javax.swing.JLabel ganancias3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel pmFaltas;
    private javax.swing.JLabel pmFaltas1;
    private javax.swing.JTextField tiempoDia;
    private javax.swing.JLabel utilidad;
    private javax.swing.JLabel utilidad1;
    private javax.swing.JLabel utilidad2;
    private javax.swing.JLabel utilidad3;
    // End of variables declaration//GEN-END:variables
}
