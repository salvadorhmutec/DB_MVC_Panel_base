/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ControllerAgenda {

    public ModelAgenda modelAgenda;
    public ViewAgenda viewAgenda;
    private String desicion;

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_nuevo) {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_editar) {
                jbtn_editar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_eliminar) {
                jbtn_eliminar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_guardar) {
                jbtn_guardar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_cancelar) {
                jbtn_cancelar_actionPerformed();
            }

        }

    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        setActionListener();
        initDB();
        initComponents();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    private void initDB() {
        modelAgenda.conectarDB();
        setValues();
        //viewAgenda.jtf_id.setText(modelAgenda.getId());
        //viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        //viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        //viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
    }

//    /**
//     * Metodo para inicializar la ViewAgenda
//     */
 public void initComponents() {
      //viewAgenda.setVisible(true);
      viewAgenda.jtf_email.setEditable(false);
        viewAgenda.jtf_nombre.setEditable(false);
        viewAgenda.jtf_telefono.setEditable(false);
        viewAgenda.jbtn_guardar.setEnabled(false);
        viewAgenda.jbtn_cancelar.setEnabled(false);
        viewAgenda.jtf_id.setVisible(false);
        

 }

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    private void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jbtn_guardar.addActionListener(actionListener);
        viewAgenda.jbtn_cancelar.addActionListener(actionListener);
        viewAgenda.jbtn_nuevo.addActionListener(actionListener);
        viewAgenda.jbtn_eliminar.addActionListener(actionListener);
        viewAgenda.jbtn_editar.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero");
         modelAgenda.moverPrimerRegistro();
        setValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior");
        modelAgenda.moverAnteriorRegistro();
        setValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo");
        modelAgenda.moverUltimoRegistro();
        setValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente");
        modelAgenda.moverSiguienteRegistro();
        setValues();
    }
    
    private void jbtn_nuevo_actionPerformed(){
        desicion = "nuevo";
        deshabilitarControles();
        viewAgenda.jtf_email.setText("");
        viewAgenda.jtf_nombre.setText("");
        viewAgenda.jtf_telefono.setText("");
        editarNuevo();
    }
    
    private void jbtn_editar_actionPerformed(){
        desicion = "editar";
        deshabilitarControles();
        editarNuevo();
    }
    
    private void jbtn_eliminar_actionPerformed(){
        modelAgenda.eliminarRegistro(viewAgenda.jtf_id.getText());
        setValues();
    }
    
    private void jbtn_guardar_actionPerformed(){
        habilitarControles();
        if (desicion == "editar") {
            modelAgenda.editarRegistro(viewAgenda.jtf_nombre.getText(), viewAgenda.jtf_email.getText(), viewAgenda.jtf_telefono.getText(), viewAgenda.jtf_id.getText());
        } else if(desicion == "nuevo") {
            modelAgenda.guardarRegistro(viewAgenda.jtf_nombre.getText(), viewAgenda.jtf_email.getText(), viewAgenda.jtf_telefono.getText());
        }
        setValues();
        cancelarGuardar();
        
    }
    
    private void jbtn_cancelar_actionPerformed(){
        habilitarControles();
        cancelarGuardar();
    }
    
    
    
    public void cancelarGuardar(){
        viewAgenda.jtf_email.setEditable(false);
        viewAgenda.jtf_nombre.setEditable(false);
        viewAgenda.jtf_telefono.setEditable(false);
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
        viewAgenda.jbtn_eliminar.setEnabled(true);
        viewAgenda.jbtn_nuevo.setEnabled(true);
        viewAgenda.jbtn_editar.setEnabled(true);
        viewAgenda.jbtn_guardar.setEnabled(false);
        viewAgenda.jbtn_cancelar.setEnabled(false);
    }
    
    
    public void editarNuevo(){
        viewAgenda.jtf_email.setEditable(true);
        viewAgenda.jtf_nombre.setEditable(true);
        viewAgenda.jtf_telefono.setEditable(true);
        viewAgenda.jbtn_eliminar.setEnabled(false);
        viewAgenda.jbtn_nuevo.setEnabled(false);
        viewAgenda.jbtn_editar.setEnabled(false);
        viewAgenda.jbtn_guardar.setEnabled(true);
        viewAgenda.jbtn_cancelar.setEnabled(true);
    }
    /**
     * Muestra el nombre y email almacenados en el modelAgenda en el viewAgenda.
     */
    
    public void habilitarControles(){
        viewAgenda.jbtn_anterior.setEnabled(true);
        viewAgenda.jbtn_ultimo.setEnabled(true);
        viewAgenda.jbtn_siguiente.setEnabled(true);
        viewAgenda.jbtn_primero.setEnabled(true);
    }
    
    public void deshabilitarControles(){
        viewAgenda.jbtn_anterior.setEnabled(false);
        viewAgenda.jbtn_ultimo.setEnabled(false);
        viewAgenda.jbtn_siguiente.setEnabled(false);
        viewAgenda.jbtn_primero.setEnabled(false);
    }
    
    
    private void setValues() {
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
        viewAgenda.jtf_id.setText(modelAgenda.getId());
    }
}
