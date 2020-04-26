package clases;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public final class Interfaz extends javax.swing.JFrame {

    int opcion = 5;
    int posicion;
    int codIniciador=1;
    
    //tablas
    DefaultTableModel tablaInsti, tablaClase, tablaProfe, tablaAlumno;
    
    //arrays
    ArrayList<Instituto> arrayInsti;
    ArrayList<Clase> arrayClase;
    ArrayList <Profesor> arrayProfesor;
    ArrayList <Alumno> arrayAlumno;
    
    //Variables Instituto    
    int id;
    String striNombre, striProvincia, striCalle;
    DefaultComboBoxModel dm;
    
    //Variables clase
    String strcAsignatura;
    int strcNumeroAlumnos;
    int intcCodClase;
    int intcInstituto;
    
    //Variables profesor
    int intpCodProfesor;
    String strpNombre;
    String strpApellido;
    String strpDni;
    int intpClase;
    
    //Variables alumno
    int intaCod;
    String straNombre;
    String straApellido;
    String straDni;
    int straProfesor;
    
    Controlador controlador;
    

    public Interfaz() throws SAXException, IOException {
        

        
            //dm = new DefaultComboBoxModel(arrayInsti.toArray());
            initComponents();
            arrayInsti = new ArrayList();
            arrayClase = new ArrayList();
            arrayProfesor = new ArrayList();
            arrayAlumno= new ArrayList();
            
            controlador = new Controlador(arrayInsti, arrayClase, arrayProfesor, arrayAlumno);
            
            tablaInsti = new DefaultTableModel();
            tablaClase = new DefaultTableModel();
            tablaProfe = new DefaultTableModel();
            tablaAlumno = new DefaultTableModel();
            
            instiTabla.setModel(tablaInsti);
            claseTabla.setModel(tablaClase);
            profeTabla.setModel(tablaProfe);
            alumnoTabla.setModel(tablaAlumno);
            
           
            

            
            desactivarCamposInsti();
            desactivarCamposClase();
            desactivarCamposProfe();
            desactivarCamposAlumno();
            

            llenarArrayInsti();
            leerArrayInsti();//Muestra el array por consola
            actualizarTablaInsti();
            
            llenarArrayClase();
            leerArrayClase();
            actualizarTablaClase();
            
            
            llenarArrayProfe();
            leerArrayProfe();
            actualizarTablaProfe();
            

            llenarArrayAlumno();
            leerArrayAlumnos();
            actualizarTablaAlumno();
            
            
            llenarComboBoxes();
            
            
        
    }
    
    
    public void llenarComboBoxes(){

          //vaciar
        
        int itemCount = claseInstituto.getItemCount();
        for(int i=0;i<itemCount;i++){
            claseInstituto.removeItemAt(0);
         }
        
        itemCount = profesorClase.getItemCount();
        for(int i=0;i<itemCount;i++){
            profesorClase.removeItemAt(0);
         }
        
        itemCount = alumnoProfesor.getItemCount();
        for(int i=0;i<itemCount;i++){
            alumnoProfesor.removeItemAt(0);
         }
        
        
        //llenar
        for (int i = 0; i < arrayInsti.size(); ++i){
            claseInstituto.addItem(arrayInsti.get(i).getNombre());
        }
        
        for (int i = 0; i < arrayClase.size(); ++i){
            profesorClase.addItem(arrayClase.get(i).getAsignatura());
        }
        
        for (int i = 0; i < arrayProfesor.size(); ++i){
            alumnoProfesor.addItem(arrayProfesor.get(i).getNombre());
        }
        
        
    }
      
        
    /*----------------------iNSTITUTO--------------------------------------- */
    
    public void llenarArrayInsti(){
        //llenamos el array del fichero 
        arrayInsti.clear();
        controlador.leerInstituto();
    }
    
    
    public void añadirInsti() throws SQLException{
        striNombre = instiNombre.getText();
        striProvincia = instiProvincia.getText();
        striCalle= instiCalle.getText();
        
        Instituto instiAux = new Instituto(striNombre, striProvincia, striCalle);
   
        controlador.insertarInstituto(instiAux);  
        arrayInsti.add(instiAux);
    }
    
    public void eliminarInsti()throws SQLException{
        int idBorrar = 2000;

       idBorrar = arrayInsti.get(posicion).getCodInstituto();
        System.out.println(idBorrar);
        llenarArrayInsti();
    }
    
    
    
    public void actualizarTablaInsti(){
        int tamano = tablaInsti.getRowCount();
            for (int i=0; i < tamano; i++){
                tablaInsti.removeRow(0);
            }    
        
        String matriz [][] = new String[arrayInsti.size()][4];
        for(int i = 0; i < arrayInsti.size(); ++i){
            matriz[i][0] = Integer.toString(arrayInsti.get(i).getCodInstituto());
            matriz[i][1] = arrayInsti.get(i).getNombre();
            matriz[i][2] = arrayInsti.get(i).getProvincia();
            matriz[i][3] = arrayInsti.get(i).getCalle();
        }
        
        instiTabla.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]{"codInsti", "nombre", "provincia", "calle"}));
        
        
    } 
 
    public void vaciarCamposInsti(){
        instiNombre.setText("");
        instiProvincia.setText("");
        instiCalle.setText("");

    }
    public void activarCamposInsti(){
        instiNombre.setEditable(true);
        instiProvincia.setEditable(true);
        instiCalle.setEditable(true);
        
        claseInstituto.removeAll(); 
        
    }
    public void desactivarCamposInsti(){
        instiNombre.setEditable(false);
        instiProvincia.setEditable(false);
        instiCalle.setEditable(false);
    }
    
    public void desactivarBotonesInsti(){
    instiAñadir.setEnabled(false);
    instiModificar.setEnabled(false);
    instiBorrar.setEnabled(false);
    }
    
    public void activarBotonesInsti(){    
    instiAñadir.setEnabled(true);
    instiModificar.setEnabled(true);
    instiBorrar.setEnabled(true);
    }
    
    
    public void leerArrayInsti(){
        for(int i = 0; i < arrayInsti.size(); ++i){
            System.out.println("********************************************");
            System.out.println(arrayInsti.get(i).toString());
            System.out.println("********************************************");
        }
    }
    
    
    
    public void borrarInsti()throws SQLException{
        controlador.borrarInstituto(arrayInsti.get(posicion));
    }
    
/*----------------------CLASE--------------------------------------- */
    
    public void llenarArrayClase(){
        arrayClase.clear();
        controlador.leerClase();
    }
    
    
     public void actualizarTablaClase(){
         llenarArrayClase();
        int tamano = tablaClase.getRowCount();
        String nombre_instituto = "";
        for (int i=0; i < tamano; i++){
            tablaClase.removeRow(0);
        } 
        
        
           
            
        String matriz [][] = new String[arrayClase.size()][4];
        for(int i = 0; i < arrayClase.size(); ++i){
            matriz[i][0] = Integer.toString(arrayClase.get(i).getCodClase());
            matriz[i][1] = Integer.toString(arrayClase.get(i).getNumeroAlumnos());
            matriz[i][2] = arrayClase.get(i).getAsignatura();
            for(Instituto instituto : arrayInsti){
                if(instituto.getCodInstituto() == arrayClase.get(i).getInstituto().getCodInstituto() ){
                    nombre_instituto = instituto.getNombre();
                }
            }
            matriz[i][3] = nombre_instituto;
           
            
        }
        claseTabla.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]{"codClase", "numeroAlumnos", "asignatura", "instituto"}));
    } 
    
    public void vaciarCamposClase(){
        claseAlumnos.setText("");
        claseAsignaturas.setText("");
    }
    public void activarCamposClase(){
        claseAlumnos.setEditable(true);
        claseAsignaturas.setEditable(true);  
    }
    public void desactivarCamposClase(){
       claseAlumnos.setEditable(false);
       claseAsignaturas.setEditable(false);  
    }
    
    public void desactivarBotonesClase(){
        claseAñadir.setEnabled(false);
        claseModificar.setEnabled(false);
        claseBorrar.setEnabled(false);
    }
    
    public void activarBotonesClase(){    
        claseAñadir.setEnabled(true);
        claseModificar.setEnabled(true);
        claseBorrar.setEnabled(true);
    }
    

    
    public void leerArrayClase(){
        for(int i = 0; i < arrayClase.size(); ++i){
            System.out.println(arrayClase.get(i).toString());
        }
    }
    
    public void añadirClase() throws SQLException{

        strcAsignatura = claseAsignaturas.getText();
        strcNumeroAlumnos = Integer.parseInt(claseAlumnos.getText());
        intcInstituto = claseInstituto.getSelectedIndex();
        Clase claseAux = new Clase(arrayInsti.get(intcInstituto), strcNumeroAlumnos, strcAsignatura);
        controlador.insertarClase(claseAux);
        arrayClase.add(claseAux);
    }
    
    public void borrarClase()throws SQLException{
     controlador.borrarClase(arrayClase.get(posicion));
    }
    
    /*----------------------PROFESOR--------------------------------------- */

    
    public void llenarArrayProfe(){
    arrayProfesor.clear();
    controlador.leerProfesores();
    }
    
    public void actualizarTablaProfe(){
        llenarArrayProfe();
        String nombre_materia="";
        int tamano = tablaProfe.getRowCount();
            for (int i=0; i < tamano; i++){
                tablaProfe.removeRow(0);
            }    
        
        String matriz [][] = new String[arrayProfesor.size()][5];
        for(int i = 0; i < arrayProfesor.size(); ++i){
            
            matriz[i][0] = Integer.toString(arrayProfesor.get(i).getCodProfe());
            matriz[i][1] = arrayProfesor.get(i).getNombre();
            matriz[i][2] = arrayProfesor.get(i).getApellido();
            matriz[i][3] = arrayProfesor.get(i).getDni();
            for(Clase clase : arrayClase){
                if(clase.getCodClase() == arrayProfesor.get(i).getClase().getCodClase() ){
                    nombre_materia = clase.getAsignatura();
                }
            }
            matriz[i][4] = nombre_materia;
            //int profeClaseAux = arrayProfesor.get(i).getClase();
            //matriz[i][4] = (arrayClase.get(i).getInstituto()).getNombre();
        }
        
        profeTabla.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]{"codProfe", "nombre", "apellidos", "dni", "clase"}));
        
        
    } 
    
    
    public void añadirProfe() throws SQLException{
        strpNombre = profeNombre.getText();
        strpApellido = profeApellido.getText();
        strpDni = profeDni.getText();
        intpClase = profesorClase.getSelectedIndex();
        
        Profesor profeAux = new Profesor(arrayClase.get(intpClase), strpNombre, strpApellido, strpDni);
        System.out.println("prueba" +profeAux.toString());
        controlador.insertarProfe(profeAux);
        arrayProfesor.add(profeAux);
        
    }

    
    public void vaciarCamposProfe(){
        profeNombre.setText("");
        profeApellido.setText("");
        profeDni.setText("");
        

    }
    public void activarCamposProfe(){
        profeNombre.setEditable(true);
        profeApellido.setEditable(true);
        profeDni.setEditable(true);
        profesorClase.setEditable(true);
           
    }
    public void desactivarCamposProfe(){
        profeNombre.setEditable(false);
        profeApellido.setEditable(false);
        profeDni.setEditable(false);
        profesorClase.setEditable(false);
        
    }
    
    public void desactivarBotonesProfe(){
    profeAñadir.setEnabled(false);
    profeModificar.setEnabled(false);
    profeBorrar.setEnabled(false);
    }
    
    public void activarBotonesProfe(){    
    profeAñadir.setEnabled(true);
    profeModificar.setEnabled(true);
    profeBorrar.setEnabled(true);
    }
    
    public void leerArrayProfe(){
        for(int i = 0; i < arrayProfesor.size(); ++i){
            System.out.println("********************************************");
            System.out.println(arrayProfesor.get(i).toString());
        }
    }
    
    public void borrarProfe()throws SQLException{
        controlador.borrarProfe(arrayProfesor.get(posicion));
        actualizarTablaProfe();
    }
    
     
    
    
    public void llenarArrayAlumno(){
    arrayAlumno.clear();
    controlador.leerAlumnos();
    
        
    }
    
    public void actualizarTablaAlumno(){
        llenarArrayAlumno();
        String nombre_profesor="";
        int tamano = tablaAlumno.getRowCount();
            for (int i=0; i < tamano; i++){
                tablaAlumno.removeRow(0);
            }    
        
        String matriz [][] = new String[arrayAlumno.size()][5];
        for(int i = 0; i < arrayAlumno.size(); ++i){
            
            matriz[i][0] = Integer.toString(arrayAlumno.get(i).getCodAlumno());
            matriz[i][1] = arrayAlumno.get(i).getNombre();
            matriz[i][2] = arrayAlumno.get(i).getApellido();
            matriz[i][3] = arrayAlumno.get(i).getDni();
            for(Profesor profesor : arrayProfesor){
                if(profesor.getCodProfe() == arrayAlumno.get(i).getProfesor().getCodProfe() ){
                    nombre_profesor = profesor.getNombre();
                }
            }
            matriz[i][4] = nombre_profesor;

            
        }
        
        alumnoTabla.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]{"codAlumno", "nombre", "apellidos", "dni", "profesor"}));
        
        
    }
    
    public void añadirAlumno() throws SQLException{
        
        straNombre = alumnoNombre.getText();
        straApellido = alumnoApellido.getText();
        straDni = alumnoDni.getText();
        straProfesor = alumnoProfesor.getSelectedIndex();
        
        Alumno alumnx = new Alumno(arrayProfesor.get(straProfesor), straNombre, straApellido, straDni);
        controlador.insertarAlumno(alumnx);
        arrayAlumno.add(alumnx);

                

    }

    
    public void vaciarCamposAlumno(){
        alumnoNombre.setText("");
        alumnoApellido.setText("");
        alumnoDni.setText("");
        

    }
    public void activarCamposAlumno(){
        alumnoNombre.setEditable(true);
        alumnoApellido.setEditable(true);
        alumnoDni.setEditable(true);
        profesorClase.setEditable(true);
           
    }
    public void desactivarCamposAlumno(){
        alumnoNombre.setEditable(false);
        alumnoApellido.setEditable(false);
        alumnoDni.setEditable(false);
        profesorClase.setEditable(false);
        
    }
    
    public void desactivarBotonesAlumno(){
    alumnoAñadir.setEnabled(false);
    alumnoModificar.setEnabled(false);
    alumnoBorrar.setEnabled(false);
    }
    
    public void activarBotonesAlumno(){    
    alumnoAñadir.setEnabled(true);
    alumnoModificar.setEnabled(true);
    alumnoBorrar.setEnabled(true);
    }
    
    
    public void leerArrayAlumnos(){
        for(int i = 0; i < arrayAlumno.size(); ++i){
            System.out.println("********************************************");
            System.out.println(arrayAlumno.get(i).toString());
            System.out.println("********************************************");
        }
        
    }
    
    public void borrarAlumno()throws SQLException{
        controlador.borrarAlumno(arrayAlumno.get(posicion));
    }

    

    
    /*------------------Metodos Autogenerados de las clases---------------- */
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        instiTabla = new javax.swing.JTable();
        instiAñadir = new javax.swing.JButton();
        instiBorrar = new javax.swing.JButton();
        instiModificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        instiNombre = new javax.swing.JTextField();
        instiProvincia = new javax.swing.JTextField();
        instiCalle = new javax.swing.JTextField();
        instiGuardar = new javax.swing.JButton();
        instiCancelar = new javax.swing.JButton();
        instiSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        claseAlumnos = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        claseTabla = new javax.swing.JTable();
        claseAsignaturas = new javax.swing.JTextField();
        claseAñadir = new javax.swing.JButton();
        claseBorrar = new javax.swing.JButton();
        claseGuardar = new javax.swing.JButton();
        claseCancelar = new javax.swing.JButton();
        claseModificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        claseInstituto = new javax.swing.JComboBox<>();
        claseSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        profeNombre = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        profeTabla = new javax.swing.JTable();
        profeApellido = new javax.swing.JTextField();
        profeAñadir = new javax.swing.JButton();
        profeDni = new javax.swing.JTextField();
        profeBorrar = new javax.swing.JButton();
        profeGuardar = new javax.swing.JButton();
        profeCancelar = new javax.swing.JButton();
        profeModificar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        profesorClase = new javax.swing.JComboBox<>();
        profeSalir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        alumnoAñadir = new javax.swing.JButton();
        alumnoDni = new javax.swing.JTextField();
        alumnoBorrar = new javax.swing.JButton();
        alumnoGuardar = new javax.swing.JButton();
        alumnoCancelar = new javax.swing.JButton();
        alumnoModificar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        alumnoNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        alumnoTabla = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        alumnoApellido = new javax.swing.JTextField();
        alumnoProfesor = new javax.swing.JComboBox<>();
        claseSalir1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        instiTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "COD", "NOMBRE", "PROVINCIA", "CALLE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        instiTabla.getTableHeader().setReorderingAllowed(false);
        instiTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instiTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(instiTabla);

        instiAñadir.setText("Añadir");
        instiAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instiAñadirActionPerformed(evt);
            }
        });

        instiBorrar.setText("Borrar");
        instiBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instiBorrarActionPerformed(evt);
            }
        });

        instiModificar.setText("Modificar");
        instiModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instiModificarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre: ");

        jLabel2.setText("Calle: ");

        jLabel3.setText("Provincia: ");

        instiNombre.setEditable(false);
        instiNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instiNombreActionPerformed(evt);
            }
        });

        instiProvincia.setEditable(false);

        instiCalle.setEditable(false);

        instiGuardar.setText("Guardar");
        instiGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instiGuardarActionPerformed(evt);
            }
        });

        instiCancelar.setText("Cancelar");
        instiCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instiCancelarActionPerformed(evt);
            }
        });

        instiSalir.setText("Salir");
        instiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instiSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(instiAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(instiBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(instiModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(instiNombre)
                            .addComponent(instiCalle, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                        .addGap(126, 126, 126)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(instiProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(instiSalir)
                .addGap(28, 28, 28)
                .addComponent(instiCancelar)
                .addGap(56, 56, 56)
                .addComponent(instiGuardar)
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(instiAñadir)
                        .addGap(52, 52, 52)
                        .addComponent(instiBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(instiModificar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(instiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instiProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(instiCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(instiGuardar)
                    .addComponent(instiCancelar)
                    .addComponent(instiSalir))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("Instituto", jPanel1);

        claseAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseAlumnosActionPerformed(evt);
            }
        });

        claseTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "COD", "Nº ALUMNOS", "ASIGNATURAS", "Instituto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        claseTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                claseTablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(claseTabla);

        claseAñadir.setText("Añadir");
        claseAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseAñadirActionPerformed(evt);
            }
        });

        claseBorrar.setText("Borrar");
        claseBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseBorrarActionPerformed(evt);
            }
        });

        claseGuardar.setText("Guardar");
        claseGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseGuardarActionPerformed(evt);
            }
        });

        claseCancelar.setText("Cancelar");
        claseCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseCancelarActionPerformed(evt);
            }
        });

        claseModificar.setText("Modificar");
        claseModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseModificarActionPerformed(evt);
            }
        });

        jLabel4.setText("Numero de alumnos: ");

        jLabel6.setText("Asignatura: ");

        claseInstituto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseInstitutoActionPerformed(evt);
            }
        });

        claseSalir.setText("Salir");
        claseSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claseAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claseBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claseModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(claseAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addComponent(jLabel6)
                        .addGap(36, 36, 36)
                        .addComponent(claseAsignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(claseInstituto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(claseSalir)
                .addGap(26, 26, 26)
                .addComponent(claseCancelar)
                .addGap(56, 56, 56)
                .addComponent(claseGuardar)
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(claseAñadir)
                        .addGap(52, 52, 52)
                        .addComponent(claseBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(claseModificar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(claseAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(claseAsignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(claseInstituto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(claseCancelar)
                    .addComponent(claseGuardar)
                    .addComponent(claseSalir))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("Clase", jPanel2);

        profeTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "COD", "NOMBRE", "APELLIDO", "DNI", "CLASE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        profeTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profeTablaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(profeTabla);

        profeAñadir.setText("Añadir");
        profeAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profeAñadirActionPerformed(evt);
            }
        });

        profeBorrar.setText("Borrar");
        profeBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profeBorrarActionPerformed(evt);
            }
        });

        profeGuardar.setText("Guardar");
        profeGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profeGuardarActionPerformed(evt);
            }
        });

        profeCancelar.setText("Cancelar");
        profeCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profeCancelarActionPerformed(evt);
            }
        });

        profeModificar.setText("Modificar");
        profeModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profeModificarActionPerformed(evt);
            }
        });

        jLabel7.setText("Nombre: ");

        jLabel8.setText("Dni:");

        jLabel9.setText("Apellido: ");

        profesorClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profesorClaseActionPerformed(evt);
            }
        });

        profeSalir.setText("Salir");
        profeSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profeSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(profeAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profeBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profeModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(profeNombre)
                            .addComponent(profeDni, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                        .addGap(131, 131, 131)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(profeApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profesorClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(profeSalir)
                .addGap(26, 26, 26)
                .addComponent(profeCancelar)
                .addGap(56, 56, 56)
                .addComponent(profeGuardar)
                .addGap(36, 36, 36))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(profeAñadir)
                        .addGap(52, 52, 52)
                        .addComponent(profeBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(profeModificar))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(profeNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profeApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(profeDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profesorClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profeGuardar)
                    .addComponent(profeCancelar)
                    .addComponent(profeSalir))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("Profesor", jPanel3);

        alumnoAñadir.setText("Añadir");
        alumnoAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alumnoAñadirActionPerformed(evt);
            }
        });

        alumnoBorrar.setText("Borrar");
        alumnoBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alumnoBorrarActionPerformed(evt);
            }
        });

        alumnoGuardar.setText("Guardar");
        alumnoGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alumnoGuardarActionPerformed(evt);
            }
        });

        alumnoCancelar.setText("Cancelar");
        alumnoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alumnoCancelarActionPerformed(evt);
            }
        });

        alumnoModificar.setText("Modificar");
        alumnoModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alumnoModificarActionPerformed(evt);
            }
        });

        jLabel10.setText("Nombre: ");

        jLabel11.setText("Dni:");

        alumnoTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "COD", "NOMBRE", "APELLIDO", "DNI", "PROFESOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        alumnoTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alumnoTablaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(alumnoTabla);

        jLabel12.setText("Apellido: ");

        claseSalir1.setText("Salir");
        claseSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claseSalir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alumnoAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(alumnoBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(alumnoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(alumnoNombre)
                            .addComponent(alumnoDni, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                        .addGap(131, 131, 131)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alumnoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(alumnoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(claseSalir1)
                .addGap(26, 26, 26)
                .addComponent(alumnoCancelar)
                .addGap(56, 56, 56)
                .addComponent(alumnoGuardar)
                .addGap(36, 36, 36))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(alumnoAñadir)
                        .addGap(52, 52, 52)
                        .addComponent(alumnoBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alumnoModificar))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(alumnoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alumnoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(alumnoDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alumnoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alumnoGuardar)
                    .addComponent(alumnoCancelar)
                    .addComponent(claseSalir1))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("Alumno", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void instiGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instiGuardarActionPerformed
        switch(opcion){
        case 0:
            {
                try {
                    añadirInsti();
                } catch (SQLException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            opcion = 5;
            desactivarCamposInsti();
            llenarArrayInsti();
            actualizarTablaInsti(); 
            llenarComboBoxes();
            vaciarCamposInsti(); 
            activarBotonesInsti();


      
      break;



            
        case 1:
            //modificar
            controlador.modificarInstituto(arrayInsti.get(posicion));
            striNombre = instiNombre.getText();
            striProvincia = instiProvincia.getText();
            striCalle =  instiCalle.getText();
            //int codInsti = arrayInsti.get(posicion).getCodInstituto();
             Instituto i = arrayInsti.get(posicion);
             i.setNombre(striNombre);
             i.setProvincia(striProvincia);
             i.setCalle(striCalle);
            controlador.modificarInstituto(i);
            arrayInsti.set(posicion, i);
            
            actualizarTablaInsti();
            desactivarCamposInsti();
            vaciarCamposInsti();
            activarBotonesInsti();
            
            opcion = 5;


            break;
        }
    }//GEN-LAST:event_instiGuardarActionPerformed

    private void profeGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profeGuardarActionPerformed
        
        switch(opcion){
        case 0:
            
            {
                try {
                    //añadir
                    añadirProfe();
                } catch (SQLException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            opcion = 5;
            desactivarCamposProfe();
            llenarArrayProfe();
            actualizarTablaProfe();
            vaciarCamposProfe(); 
            activarBotonesProfe();
            llenarComboBoxes();
            break;

            
            

            
        case 1:
            //modificar
           strpNombre = profeNombre.getText();
           strpApellido = profeApellido.getText();
           strpDni = profeDni.getText();
           String asignatura = profesorClase.getSelectedItem().toString();
            
           Clase claseProfe = new Clase();
           for(Clase caux : arrayClase){
                if(caux.getAsignatura()== asignatura){
                    claseProfe = caux;
                }
           }
           
           Profesor paux = arrayProfesor.get(posicion);
           paux.setNombre(strpNombre);
           paux.setApellido(strpApellido);
           paux.setDni(strpDni);
           paux.setClase(claseProfe);
           
           controlador.modificarProfe(paux);
           arrayProfesor.set(posicion, paux);
           
            desactivarCamposProfe();
            actualizarTablaProfe();
            vaciarCamposProfe(); 
            activarBotonesProfe();
            
            opcion = 5;


            break;
        }
        
    }//GEN-LAST:event_profeGuardarActionPerformed

    private void profeBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profeBorrarActionPerformed
        opcion = 2;
        try {
            borrarProfe();
        } catch (SQLException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        vaciarCamposProfe();
        actualizarTablaProfe();
        llenarComboBoxes();
        
    }//GEN-LAST:event_profeBorrarActionPerformed
   
    private void alumnoBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alumnoBorrarActionPerformed
        opcion = 2;
        
        try {
            borrarAlumno();
        } catch (SQLException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        llenarArrayAlumno();
        actualizarTablaAlumno();
        vaciarCamposAlumno();
        actualizarTablaAlumno();
        llenarComboBoxes();
        
            
    }//GEN-LAST:event_alumnoBorrarActionPerformed

    private void alumnoGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alumnoGuardarActionPerformed
        
        switch(opcion){
        case 0:
            
            {
                try {
                    añadirAlumno();
                } catch (SQLException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
           
            desactivarCamposAlumno();
            llenarArrayAlumno();
            actualizarTablaAlumno();
            vaciarCamposAlumno(); 
            activarBotonesAlumno();
            llenarComboBoxes();
            opcion = 5;
            
            break;

            
            

            
        case 1:
            //modificar
            
           straNombre = alumnoNombre.getText();
            straApellido = alumnoApellido.getText();
            straDni = alumnoDni.getText();
            String profe= alumnoProfesor.getSelectedItem().toString();
            
            Profesor profeAlumno = new Profesor();
             for(Profesor paux : arrayProfesor){
                if(paux.getNombre()== profe){
                    profeAlumno = paux;
                }
           }
             
             Alumno aaux = arrayAlumno.get(posicion);
             aaux.setNombre(straNombre);
             aaux.setApellido(straApellido);
             aaux.setDni(straDni);
             aaux.setProfesor(profeAlumno);
             
             controlador.modificarAlumno(aaux);
             arrayAlumno.set(posicion, aaux);
            
            opcion = 5;
            desactivarCamposAlumno();
            actualizarTablaAlumno();
            vaciarCamposAlumno(); 
            activarBotonesAlumno();
            
            opcion = 5;


            break;
        }
        
    }//GEN-LAST:event_alumnoGuardarActionPerformed

    private void instiAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instiAñadirActionPerformed
        opcion = 0;
        vaciarCamposInsti();
        activarCamposInsti();
        desactivarBotonesInsti();
    }//GEN-LAST:event_instiAñadirActionPerformed

    private void instiBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instiBorrarActionPerformed
        opcion = 2;
        try {
            borrarInsti();
        } catch (SQLException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        llenarArrayInsti();

        llenarComboBoxes();
        actualizarTablaInsti();
        vaciarCamposInsti();
        leerArrayInsti();
    }//GEN-LAST:event_instiBorrarActionPerformed

    private void instiModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instiModificarActionPerformed
       /* Boton Modificar */
        opcion = 1;
        activarCamposInsti();
        desactivarBotonesInsti();
    }//GEN-LAST:event_instiModificarActionPerformed

    private void instiNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instiNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_instiNombreActionPerformed

    private void instiTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instiTablaMouseClicked
        //Mostrar que objeto has seleccionado en la tabla Instituto:
        
        posicion = instiTabla.getSelectedRow();
        instiNombre.setText(arrayInsti.get(posicion).getNombre());
        instiProvincia.setText(arrayInsti.get(posicion).getProvincia());
        instiCalle.setText(arrayInsti.get(posicion).getCalle());
    }//GEN-LAST:event_instiTablaMouseClicked

    private void instiCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instiCancelarActionPerformed
        vaciarCamposInsti();
        activarBotonesInsti();
        desactivarCamposInsti();
        
        posicion = instiTabla.getSelectedRow();
        instiNombre.setText(arrayInsti.get(posicion).getNombre());
        instiProvincia.setText(arrayInsti.get(posicion).getProvincia());
        instiCalle.setText(arrayInsti.get(posicion).getCalle());
    }//GEN-LAST:event_instiCancelarActionPerformed

    private void claseInstitutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseInstitutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claseInstitutoActionPerformed

    private void claseAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseAñadirActionPerformed
        opcion = 0;
        
        vaciarCamposClase();
        activarCamposClase();
        desactivarBotonesClase();

    }//GEN-LAST:event_claseAñadirActionPerformed

    private void claseBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseBorrarActionPerformed
        opcion = 2;
        
        try {
            borrarClase();
        } catch (SQLException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizarTablaClase();
        //actualizarTablaProfe();
        vaciarCamposClase();
        llenarComboBoxes();

    }//GEN-LAST:event_claseBorrarActionPerformed

    private void claseModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseModificarActionPerformed
       opcion = 1;
       
        activarCamposClase();
        desactivarBotonesClase();
        llenarComboBoxes();

    }//GEN-LAST:event_claseModificarActionPerformed

    private void claseGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseGuardarActionPerformed
        
        switch(opcion){
            
        case 0:
            
            {
                try {
                    //añadir
                    añadirClase();
                } catch (SQLException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            opcion = 5;
            desactivarCamposClase();
            actualizarTablaClase();
            vaciarCamposClase(); 
            activarBotonesClase();
            llenarComboBoxes();
            break;

            
            

            
        case 1:
            //modificar
            
           strcAsignatura = claseAsignaturas.getText();
           int intcNumeroAlumnos = Integer.parseInt(claseAlumnos.getText());
           String claseInsti = claseInstituto.getSelectedItem().toString();
           
           Instituto instDeClase = new Instituto();
           for(Instituto iaux : arrayInsti){
                if(iaux.getNombre()== claseInsti){
                    instDeClase = iaux;
                }
           }
           
           
           Clase caux = arrayClase.get(posicion);
           caux.setNumeroAlumnos(intcNumeroAlumnos);
           caux.setAsignatura(strcAsignatura);
           caux.setInstituto(instDeClase);
           controlador.modificarClase(caux);
           arrayClase.set(posicion, caux);
           
           
            actualizarTablaClase();
            desactivarCamposClase();
            vaciarCamposClase();
            activarBotonesClase();
            
            opcion = 5;
            
            


            break;
        }
        
        
    }//GEN-LAST:event_claseGuardarActionPerformed

    private void claseTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_claseTablaMouseClicked
        //Mostrar que objeto has seleccionado en la tabla Instituto:
        
        posicion = claseTabla.getSelectedRow();
        claseAlumnos.setText(Integer.toString(arrayClase.get(posicion).getNumeroAlumnos()));
        claseAsignaturas.setText(arrayClase.get(posicion).getAsignatura());
        
        
    }//GEN-LAST:event_claseTablaMouseClicked

    private void claseCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseCancelarActionPerformed
        
        vaciarCamposClase();
        activarBotonesClase();
        desactivarCamposClase();
        
        posicion = claseTabla.getSelectedRow();
        claseAlumnos.setText(Integer.toString(arrayClase.get(posicion).getNumeroAlumnos()));
        claseAsignaturas.setText(arrayClase.get(posicion).getAsignatura());

    }//GEN-LAST:event_claseCancelarActionPerformed

    private void profeAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profeAñadirActionPerformed
        opcion = 0;
        
        vaciarCamposProfe();
        activarCamposProfe();
        desactivarBotonesProfe();

    }//GEN-LAST:event_profeAñadirActionPerformed

    private void profeModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profeModificarActionPerformed
        opcion = 1;
        
        activarCamposProfe();
        desactivarBotonesProfe();

    }//GEN-LAST:event_profeModificarActionPerformed

    private void profeCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profeCancelarActionPerformed
        
        vaciarCamposProfe();
        activarBotonesProfe();
        desactivarCamposProfe();

        
        posicion = profeTabla.getSelectedRow();
        profeApellido.setText(arrayProfesor.get(posicion).getApellido());
        profeNombre.setText(arrayProfesor.get(posicion).getNombre());
        profeDni.setText(arrayProfesor.get(posicion).getDni());
        profesorClase.setSelectedIndex(posicion);
    }//GEN-LAST:event_profeCancelarActionPerformed

    private void profeTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profeTablaMouseClicked
        //Mostrar que objeto has seleccionado en la tabla Instituto:
        
        posicion = profeTabla.getSelectedRow();
        profeApellido.setText(arrayProfesor.get(posicion).getApellido());
        profeNombre.setText(arrayProfesor.get(posicion).getNombre());
        profeDni.setText(arrayProfesor.get(posicion).getDni());
        //profesorClase.setSelectedIndex(arrayProfesor.get(posicion).getClase());
    }//GEN-LAST:event_profeTablaMouseClicked

    private void alumnoAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alumnoAñadirActionPerformed
        opcion = 0;
        
        vaciarCamposAlumno();
        activarCamposAlumno();
        desactivarBotonesAlumno();

    }//GEN-LAST:event_alumnoAñadirActionPerformed

    private void alumnoModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alumnoModificarActionPerformed
        opcion = 1;
        
        activarCamposAlumno();
        desactivarBotonesAlumno();

    }//GEN-LAST:event_alumnoModificarActionPerformed

    private void alumnoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alumnoCancelarActionPerformed
         
        vaciarCamposAlumno();
        activarBotonesAlumno();
        desactivarCamposAlumno();

        
        posicion = alumnoTabla.getSelectedRow();
        alumnoApellido.setText(arrayAlumno.get(posicion).getApellido());
        alumnoNombre.setText(arrayAlumno.get(posicion).getNombre());
        alumnoDni.setText(arrayAlumno.get(posicion).getDni());
        alumnoProfesor.setSelectedIndex(posicion);
    }//GEN-LAST:event_alumnoCancelarActionPerformed

    private void alumnoTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alumnoTablaMouseClicked
        //Mostrar que objeto has seleccionado en la tabla Instituto:
        
        posicion = alumnoTabla.getSelectedRow();
        alumnoApellido.setText(arrayAlumno.get(posicion).getApellido());
        alumnoNombre.setText(arrayAlumno.get(posicion).getNombre());
        alumnoDni.setText(arrayAlumno.get(posicion).getDni());
        //alumnoProfesor.setSelectedIndex(arrayAlumno.get(posicion).getProfesor());
    }//GEN-LAST:event_alumnoTablaMouseClicked

    private void profesorClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profesorClaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profesorClaseActionPerformed

    private void claseAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseAlumnosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claseAlumnosActionPerformed

    private void profeSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profeSalirActionPerformed
        
        System.exit(0);
    }//GEN-LAST:event_profeSalirActionPerformed

    private void instiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instiSalirActionPerformed
        
        System.exit(0);
    }//GEN-LAST:event_instiSalirActionPerformed

    private void claseSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseSalirActionPerformed
    System.exit(0);
    }//GEN-LAST:event_claseSalirActionPerformed

    private void claseSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claseSalir1ActionPerformed
        
        System.exit(0);
    }//GEN-LAST:event_claseSalir1ActionPerformed

  
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Interfaz().setVisible(true);
                } catch (SAXException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alumnoApellido;
    private javax.swing.JButton alumnoAñadir;
    private javax.swing.JButton alumnoBorrar;
    private javax.swing.JButton alumnoCancelar;
    private javax.swing.JTextField alumnoDni;
    private javax.swing.JButton alumnoGuardar;
    private javax.swing.JButton alumnoModificar;
    private javax.swing.JTextField alumnoNombre;
    private javax.swing.JComboBox<String> alumnoProfesor;
    private javax.swing.JTable alumnoTabla;
    private javax.swing.JTextField claseAlumnos;
    private javax.swing.JTextField claseAsignaturas;
    private javax.swing.JButton claseAñadir;
    private javax.swing.JButton claseBorrar;
    private javax.swing.JButton claseCancelar;
    private javax.swing.JButton claseGuardar;
    private javax.swing.JComboBox<String> claseInstituto;
    private javax.swing.JButton claseModificar;
    private javax.swing.JButton claseSalir;
    private javax.swing.JButton claseSalir1;
    private javax.swing.JTable claseTabla;
    private javax.swing.JButton instiAñadir;
    private javax.swing.JButton instiBorrar;
    private javax.swing.JTextField instiCalle;
    private javax.swing.JButton instiCancelar;
    private javax.swing.JButton instiGuardar;
    private javax.swing.JButton instiModificar;
    private javax.swing.JTextField instiNombre;
    private javax.swing.JTextField instiProvincia;
    private javax.swing.JButton instiSalir;
    private javax.swing.JTable instiTabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField profeApellido;
    private javax.swing.JButton profeAñadir;
    private javax.swing.JButton profeBorrar;
    private javax.swing.JButton profeCancelar;
    private javax.swing.JTextField profeDni;
    private javax.swing.JButton profeGuardar;
    private javax.swing.JButton profeModificar;
    private javax.swing.JTextField profeNombre;
    private javax.swing.JButton profeSalir;
    private javax.swing.JTable profeTabla;
    private javax.swing.JComboBox<String> profesorClase;
    // End of variables declaration//GEN-END:variables
}
