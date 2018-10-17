/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelMain;
import views.ViewMain;

/**
 *
 * @author Zeo
 */
public class ControllerMain {

    private final ModelMain modelMain;
    private final ViewMain viewMain;
    
    /**
     * Esta variable almacena los controladores controllerAgenda y 
     * controllerDetalleContacto para poder utilizarlos dentro del mismo JFrame.
     */
    private Object controllers[];
    
    private ControllerAgenda controllerAgenda;
    private ControllerDetalleContacto controllerDetalleContacto;

    /**
     * Controlador principal del proyecto que un el modelMain y viewMain, ademas
     * recibe los controladores controllerAgenda y controllerDetalleContacto
     * dentro del arreglo controllers, con esto se puede tener acceso a toda la
     * programación desarrollada dentro de cada controller.
     * @param modelMain 
     * @param viewMain
     * @param controllers arreglo con todos los controladores del proyecto. 
     */
    public ControllerMain(ModelMain modelMain, ViewMain viewMain, Object[] controllers) {
        this.modelMain = modelMain;
        this.viewMain = viewMain;
        this.controllers = controllers;
        setControllers();
        setActionListener();
        initComponets();
    }

    /**
     * Separa cada uno de los controlladores almacendados en controllers, de
     * esta forma se puede acceder a todas las variables y métodos publicos
     * de cada uno.
     */
    private void setControllers() {
        controllerAgenda = (ControllerAgenda) controllers[0];
        controllerDetalleContacto = (ControllerDetalleContacto) controllers[1];
    }

    /**
     * Muesta la vista principal ViewMain.
     */
    private void initComponets() {
        viewMain.setTitle("Agenda");
        viewMain.setLocationRelativeTo(null);
        viewMain.setVisible(true);
        jmi_agenda_actionPerformed();
    }
    
    /**
     * Asigna el actionListener a cada uno de los JMenuItems de la vista 
     * ViewMain.
     */
    private void setActionListener() {
        viewMain.jmi_detalle_contacto.addActionListener(actionListener);
        viewMain.jmi_agenda.addActionListener(actionListener);
        viewMain.jmi_salir.addActionListener(actionListener);
    }
    
    /**
     * Evalua el componente que genero el evento y llama a un método en particular.
     */
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewMain.jmi_detalle_contacto) {
                jmi_detalle_contacto_actionPerformed();
            } else if (e.getSource() == viewMain.jmi_agenda) {
                jmi_agenda_actionPerformed();
            } else if (e.getSource() == viewMain.jmi_salir) {
                jmi_salir_actionPerformed();
            }
        }
    };

    /**
     * 1.- Obtiene los valores de nombre y email del modelAgenda
     * y posteriormente los asigna a las variables nombre y email del
     * modelDetalleContacto,
     * 
     * 2.- Muestra en la vista viewDetalleContacto el nombre y email
     * almacenados en el modelDetalleContacto,
     * 
     * 3.- Muestra el JPanel ViewDetalleContacto dentro del JFrame
     * ViewMain (incluido todo el funcionamiento programado).
     */
    private void jmi_detalle_contacto_actionPerformed() {
        // 
        controllerDetalleContacto.modelDetalleContacto.setNombre(controllerAgenda.modelAgenda.getNombre());
        controllerDetalleContacto.modelDetalleContacto.setEmail(controllerAgenda.modelAgenda.getEmail());
        controllerDetalleContacto.modelDetalleContacto.setTelefono(controllerAgenda.modelAgenda.getTelefono());
        controllerDetalleContacto.initView();
        
        viewMain.setContentPane(controllerDetalleContacto.viewDetalleContacto);
        viewMain.revalidate();
        viewMain.repaint();
    }

    /**
     * Muestra el JPanel ViewAgenda dentro del JFrame ViewMain, (incluido todo
     * el funcionamiendo programado).
     */
    private void jmi_agenda_actionPerformed() {
        viewMain.setContentPane(controllerAgenda.viewAgenda);
        viewMain.revalidate();
        viewMain.repaint();
    }
    
    /**
     * Cierra la aplicacion.
     */
    private void jmi_salir_actionPerformed() {
        System.exit(0);
    }
}
