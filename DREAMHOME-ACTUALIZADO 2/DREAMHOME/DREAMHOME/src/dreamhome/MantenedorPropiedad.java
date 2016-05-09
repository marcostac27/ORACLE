/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamhome;

import CONTROLADOR.ConexionEM;
import CONTROLADOR.PropiedadJpaController;
import MODELO.Empleado;
import MODELO.Propiedad;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisco
 */
public final class MantenedorPropiedad extends javax.swing.JFrame {

    ArrayList<Propiedad> propiedad;
   // PropiedadDB db = new PropiedadDB();
    EntityManager con =  ConexionEM.getConexion();
    PropiedadJpaController  control_propiedad = new PropiedadJpaController();
    Propiedad p=null;
    /**
     * Creates new form MantenedorPropiedad
     */
    public MantenedorPropiedad() {
        initComponents();
        CrearModelotblPropiedad();
        LimpiarFormulario();
    }

public void LimpiarFormulario(){
    DefaultTableModel tb = (DefaultTableModel)tblPropiedad.getModel();
    for (int i = tb.getRowCount()-1; i >= 0; i--) {
        tb.removeRow(i);
    }
}
public boolean Eliminar_propiedad(String id){
    con.getTransaction().begin();
    Propiedad p=con.find(Propiedad.class, id);
    boolean eliminado=false;
    if (p!=null) {
        eliminado=true;
    }
    con.remove(p);
    con.getTransaction().commit();
    return eliminado;
    
}

public void Editar(){
    
    if (p!=null) {
            txtNumPropiedad.setText(p.getNumpropiedad().toString());
            txtDireccion.setText(p.getCalle());
            txtCiudad.setText(p.getCiudad());
            txtCodPostal.setText(p.getCodigopostal());
            txtTipo.setText(p.getTipo());
            txtRenta.setText(p.getRenta().toString());
            txtNumHab.setText(p.getHab().toString());
            txtNumPropietario.setText(p.getNumpropietario());
            txtNumEmpleado.setText(p.getNumempleado().getNumempleado());
    }
}

public void Actualizar(){
    Propiedad pro2 = null;
    con.getTransaction().begin();
    if (camposLLenos()&&p!=null) {
         Empleado e=con.find(Empleado.class, txtNumEmpleado.getText());
          pro2 = con.find(Propiedad.class, txtNumPropiedad.getText());
        if (e!=null&&pro2!=null) {
           
         /*   p=null;
        p=new Propiedad();
        p.setNumpropiedad(txtNumPropiedad.getSelectedText());
        p.setCalle(txtDireccion.getText());
        p.setCiudad(txtCiudad.getText());
        p.setCodigopostal(txtCodPostal.getText());
        p.setTipo(txtTipo.getText());
        p.setRenta(new BigInteger(txtRenta.getText()));
        p.setHab(new BigInteger(txtNumHab.getText()));
        p.setNumpropietario(txtNumPropietario.getText());
        p.setNumempleado(e);
        */
        //pro2.setNumpropiedad(txtNumPropiedad.getSelectedText());
        pro2.setCalle(txtDireccion.getText());
        pro2.setCiudad(txtCiudad.getText());
        pro2.setCodigopostal(txtCodPostal.getText());
        pro2.setTipo(txtTipo.getText());
        pro2.setRenta(new BigInteger(txtRenta.getText()));
        pro2.setHab(new BigInteger(txtNumHab.getText()));
        pro2.setNumpropietario(txtNumPropietario.getText());
        pro2.setNumempleado(e);
        con.getTransaction().commit();
            try {
                if (pro2.getNumpropiedad()!=null) {
                    
                    System.out.println("valor nulo");
                }else
                {
                    System.out.println("no  nulo");
                }
                con.close();
            } catch (Exception ex) {
                
                ex.printStackTrace();
               
            }
       
      
       
        JOptionPane.showMessageDialog(null,"Propiedad Nº "+txtNumPropiedad.getText()+" Editado con Exito ","Editar Propiedad",JOptionPane.INFORMATION_MESSAGE);
            p=null;
        }else{
           JOptionPane.showMessageDialog(null,"Propiedad Nº "+txtNumPropiedad.getText()+" No se puedo Editar ","Editar Propiedad",JOptionPane.ERROR_MESSAGE);
           p=null;
        }
       
    }
            
           
           
          
    
}

public boolean camposLLenos(){
    boolean lleno=false;
    if (txtDireccion.getText().trim()!="") {
    lleno=true;
    }
    if ( txtCiudad.getText().trim()!="") {
    lleno=true;
    }
    if (txtCodPostal.getText().trim()!="") {
    lleno=true;
    }
    if (txtTipo.getText().trim()!="") {
    lleno=true;
    }
    if (txtRenta.getText().trim()!="") {
    lleno=true;
    }
    if (txtNumHab.getText().trim()!="") {
    lleno=true;
    }
    if (txtNumPropietario.getText().trim()!="") {
    lleno=true;
    }
    if (txtNumEmpleado.getText().trim()!="") {
    lleno=true;
    }
    if (txtNumPropiedad.getText().trim()!="") {
    lleno=true;
    }
    
    return lleno;
    
}

   /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        DREAMHOMEPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("DREAMHOMEPU").createEntityManager();
        propiedadQuery = java.beans.Beans.isDesignTime() ? null : DREAMHOMEPUEntityManager.createQuery("SELECT p FROM Propiedad p");
        propiedadList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : propiedadQuery.getResultList();
        empleadoQuery = java.beans.Beans.isDesignTime() ? null : DREAMHOMEPUEntityManager.createQuery("SELECT e FROM Empleado e");
        empleadoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : empleadoQuery.getResultList();
        lblNumPr = new javax.swing.JLabel();
        txtNumPropiedad = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblCodPostal = new javax.swing.JLabel();
        txtCodPostal = new javax.swing.JTextField();
        lblCiudad = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        lblNumProp = new javax.swing.JLabel();
        lblNumHab = new javax.swing.JLabel();
        txtNumHab = new javax.swing.JTextField();
        txtNumPropietario = new javax.swing.JTextField();
        lblRenta = new javax.swing.JLabel();
        txtRenta = new javax.swing.JTextField();
        lblNumEmp = new javax.swing.JLabel();
        txtNumEmpleado = new javax.swing.JTextField();
        Botones = new javax.swing.JPanel();
        btnIngresarPropiedad = new javax.swing.JButton();
        btnActualizarProp = new javax.swing.JButton();
        btnBuscarPropiedad = new javax.swing.JButton();
        btnEliminarPropiedad = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPropiedad = new javax.swing.JTable();
        cboNumEmp = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Propiedades");

        lblNumPr.setText("N° Propiedad");

        lblDireccion.setText("Direccion");

        lblCodPostal.setText("Cod. Postal");

        lblCiudad.setText("Ciudad");

        lblTipo.setText("Tipo");

        lblNumProp.setText("N° Propietario");

        lblNumHab.setText("N° de Hab");

        txtNumHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumHabActionPerformed(evt);
            }
        });

        lblRenta.setText("Renta");

        txtRenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRentaActionPerformed(evt);
            }
        });

        lblNumEmp.setText("N° Empleado");

        btnIngresarPropiedad.setText("Ingresar");
        btnIngresarPropiedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarPropiedadActionPerformed(evt);
            }
        });

        btnActualizarProp.setText("Actualizar");
        btnActualizarProp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPropActionPerformed(evt);
            }
        });

        btnBuscarPropiedad.setText("Buscar");
        btnBuscarPropiedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPropiedadActionPerformed(evt);
            }
        });

        btnEliminarPropiedad.setText("Eliminar");
        btnEliminarPropiedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPropiedadActionPerformed(evt);
            }
        });

        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BotonesLayout = new javax.swing.GroupLayout(Botones);
        Botones.setLayout(BotonesLayout);
        BotonesLayout.setHorizontalGroup(
            BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotonesLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnIngresarPropiedad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizarProp, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarPropiedad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarPropiedad)
                .addGap(79, 79, 79))
        );
        BotonesLayout.setVerticalGroup(
            BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotonesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnListar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BotonesLayout.createSequentialGroup()
                        .addGroup(BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscarPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarProp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnIngresarPropiedad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tblPropiedad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Propiedad", "Direccion", "Ciudad", "Codigo Postal", "Tipo", "N° de Hab", "Renta", "N° Propietario", "N° Empleado"
            }
        ));
        tblPropiedad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPropiedadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPropiedad);

        cboNumEmp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${resultList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, empleadoQuery, eLProperty, cboNumEmp);
        bindingGroup.addBinding(jComboBoxBinding);

        cboNumEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumEmpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNumEmp)
                                    .addComponent(lblNumPr))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNumProp)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCodPostal)
                                .addGap(18, 18, 18)
                                .addComponent(txtCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtRenta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipo)
                            .addComponent(lblCiudad)
                            .addComponent(lblDireccion))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTipo)
                                .addGap(161, 161, 161))
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNumHab)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumHab, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboNumEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumProp)
                            .addComponent(txtNumPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumEmp)
                            .addComponent(txtNumEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNumEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumPr)
                            .addComponent(txtNumPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRenta)
                            .addComponent(txtRenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodPostal)
                            .addComponent(txtCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCiudad)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNumHab)
                        .addComponent(txtNumHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRentaActionPerformed

    private void btnEliminarPropiedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPropiedadActionPerformed
        // TODO add your handling code here:
        if (Eliminar_propiedad(txtNumPropiedad.getText())) {
            JOptionPane.showMessageDialog(null,"Propiedad Nº "+txtNumPropiedad.getText()+" Eliminado ","Eliminar Propiedad",JOptionPane.INFORMATION_MESSAGE);
            
        }else
        {
            JOptionPane.showMessageDialog(null,"Propiedad Nº "+txtNumPropiedad.getText()+" NO se pudo Eliminar","Eliminar Propiedad",JOptionPane.ERROR_MESSAGE);
            
        }
        
    }//GEN-LAST:event_btnEliminarPropiedadActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        LimpiarFormulario();
        Llenartabla();
       
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnIngresarPropiedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarPropiedadActionPerformed
        Propiedad prd =  new Propiedad();
      
        Empleado empleado = null;
        String  numemple = null;
        
  
        numemple = txtNumEmpleado.getText();
      
        
        empleado = con.find(Empleado.class, numemple);
        if (empleado != null) {
            Propiedad p = new Propiedad();
            
            p.setNumpropiedad(txtNumPropiedad.getText());
            p.setCalle(txtDireccion.getText());
            p.setCiudad(txtCiudad.getText());
            p.setCodigopostal(txtCodPostal.getText());
            p.setTipo(txtTipo.getText());
            p.setHab(new BigInteger(txtNumHab.getText()));
            p.setRenta(new BigInteger(txtRenta.getText()));
            p.setNumpropietario(txtNumPropietario.getText());
            p.setNumempleado(empleado);
            
            con.getTransaction().begin();
            con.persist(p);
            con.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"Propiedad Nº "+txtNumPropiedad.getText()+" Ingresada ","Ingresar Propiedad",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Guardado");
        }else{
            JOptionPane.showMessageDialog(null,"Propiedad Nº "+txtNumPropiedad.getText()+" Ingresado ","Ingresar Propiedad",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnIngresarPropiedadActionPerformed

    private void tblPropiedadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPropiedadMouseClicked
        // TODO add your handling code here:
        //0 numPropiedad
        //1 direc
        //2 ciudad
        //3 codigopost
        //4 tipo
        //5 renta
        //6 hab
        //7numPropietario
        //8 num emplpeado
        con.getTransaction().begin();
        int fila=tblPropiedad.rowAtPoint(evt.getPoint());
        if (fila!=-1) {
           
          
            Empleado e= con.find(Empleado.class, (String)tblPropiedad.getValueAt(fila,8));
            if (e!=null) {
            p=new Propiedad();
            p.setNumpropiedad((String)tblPropiedad.getValueAt(fila,0));
            p.setCalle((String)tblPropiedad.getValueAt(fila,1));
            
            p.setCiudad((String)tblPropiedad.getValueAt(fila,2));
            p.setCodigopostal((String)tblPropiedad.getValueAt(fila,3));
            p.setTipo((String)tblPropiedad.getValueAt(fila,4));
            p.setHab(new BigInteger(tblPropiedad.getValueAt(fila,6).toString()));
            p.setRenta(new BigInteger(tblPropiedad.getValueAt(fila,5).toString()));
             p.setNumpropietario((String)tblPropiedad.getValueAt(fila,7));
            
            p.setNumempleado(e);
            con.close();
            }
           
            
        }
        
    }//GEN-LAST:event_tblPropiedadMouseClicked

    private void btnActualizarPropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPropActionPerformed
        // TODO add your handling code here:
        Actualizar();
    }//GEN-LAST:event_btnActualizarPropActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here
        Editar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBuscarPropiedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPropiedadActionPerformed
         String NumPro = txtNumPropiedad.getText();
        
        if (NumPro.isEmpty() == true) {
                 JOptionPane.showMessageDialog(null,"Se requiere un Numero de propiedad para Buscar "+JOptionPane.INFORMATION_MESSAGE);
                 return;
             }else{
                 
                try {
                    Propiedad algo = control_propiedad.findPropiedad(NumPro);
                    if (algo == null) {
                        JOptionPane.showMessageDialog(this, "La Propiedad a buscar no existe en el sistema");
                        return;
                    }else{
                        LimpiarFormulario();
                        
                         Object prop[]= null;            
                                
                        modelo2.addRow(prop);
                        modelo2.setValueAt(algo.getNumpropiedad(),0,0);
                        modelo2.setValueAt(algo.getCalle(),0,1);
                        modelo2.setValueAt(algo.getCiudad(),0,2);
                        modelo2.setValueAt(algo.getCodigopostal(),0,3);
                        modelo2.setValueAt(algo.getTipo(),0,4);
                        modelo2.setValueAt(algo.getRenta(),0,5);
                        modelo2.setValueAt(algo.getHab(),0,6);
                        modelo2.setValueAt(algo.getNumpropietario(),0,7);
                        modelo2.setValueAt(algo.getNumempleado().getNumempleado(),0,8); 
            
                       /* 
                        txtNumPropietario.setText(algo.getNumpropietario());
                        txtCiudad.setText(algo.getCiudad());
                        txtCodPostal.setText(algo.getCodigopostal());
                        txtDireccion.setText(algo.getCalle());
                        txtTipo.setText(algo.getTipo());
                        
                      */
                    }
                
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                }
    }//GEN-LAST:event_btnBuscarPropiedadActionPerformed

    private void txtNumHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumHabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumHabActionPerformed

    private void cboNumEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNumEmpActionPerformed

    public static DefaultTableModel modelo2;
    private void CrearModelotblPropiedad(){
    try {
    modelo2 = (new DefaultTableModel(
        null, new String [] {
        "NumPropiedad","Direccion","Ciudad","Codigo Postal",
        "Tipo","Renta","Habitaciones","NumPropietario","NumEmpleado"}){
        Class[] types = new Class [] {
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
        false,false,false,false,false,false,false,false,false
        };
        @Override
        public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
        }
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex){
        return canEdit [colIndex];
        }
        });
        tblPropiedad.setModel(modelo2);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null,e.toString()+"error2");
        }
        }
    
    private void Llenartabla(){
        try {
            Object prop[]= null;
            List<Propiedad> listPropiedad;
            
            listPropiedad = control_propiedad.findPropiedadEntities();
            
            for (int i = 0; i < listPropiedad.size(); i++) {
                modelo2.addRow(prop);
                modelo2.setValueAt(listPropiedad.get(i).getNumpropiedad(), i, 0);
                modelo2.setValueAt(listPropiedad.get(i).getCalle(), i, 1);
                modelo2.setValueAt(listPropiedad.get(i).getCiudad(), i, 2);
                modelo2.setValueAt(listPropiedad.get(i).getCodigopostal(), i, 3);
                modelo2.setValueAt(listPropiedad.get(i).getTipo(), i, 4);
                modelo2.setValueAt(listPropiedad.get(i).getRenta(), i, 5);
                modelo2.setValueAt(listPropiedad.get(i).getHab(), i, 6);
                modelo2.setValueAt(listPropiedad.get(i).getNumpropietario(), i, 7);
                modelo2.setValueAt(listPropiedad.get(i).getNumempleado().getNumempleado(), i, 8); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
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
            java.util.logging.Logger.getLogger(MantenedorPropiedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MantenedorPropiedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MantenedorPropiedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MantenedorPropiedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MantenedorPropiedad().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Botones;
    private javax.persistence.EntityManager DREAMHOMEPUEntityManager;
    private javax.swing.JButton btnActualizarProp;
    private javax.swing.JButton btnBuscarPropiedad;
    private javax.swing.JButton btnEliminarPropiedad;
    private javax.swing.JButton btnIngresarPropiedad;
    private javax.swing.JButton btnListar;
    private javax.swing.JComboBox<String> cboNumEmp;
    private java.util.List<MODELO.Empleado> empleadoList;
    private javax.persistence.Query empleadoQuery;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblCodPostal;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNumEmp;
    private javax.swing.JLabel lblNumHab;
    private javax.swing.JLabel lblNumPr;
    private javax.swing.JLabel lblNumProp;
    private javax.swing.JLabel lblRenta;
    private javax.swing.JLabel lblTipo;
    private java.util.List<MODELO.Propiedad> propiedadList;
    private javax.persistence.Query propiedadQuery;
    private javax.swing.JTable tblPropiedad;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCodPostal;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNumEmpleado;
    private javax.swing.JTextField txtNumHab;
    private javax.swing.JTextField txtNumPropiedad;
    private javax.swing.JTextField txtNumPropietario;
    private javax.swing.JTextField txtRenta;
    private javax.swing.JTextField txtTipo;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
