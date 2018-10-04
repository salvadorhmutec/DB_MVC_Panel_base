/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import models.ModelMain;
import views.ViewMain;
import controllers.ControllerMain;

import models.ModelAgenda;
import views.ViewAgenda;
import controllers.ControllerAgenda;

import models.ModelDetalleContacto;
import views.ViewDetalleContacto;
import controllers.ControllerDetalleContacto;
/**
 *
 * @author Zeo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModelAgenda modelAgenda = new ModelAgenda();
        ViewAgenda viewAgenda = new ViewAgenda();
        ControllerAgenda controllerAgenda = new ControllerAgenda(modelAgenda, viewAgenda);
        
        ModelDetalleContacto modelDetalleContacto = new ModelDetalleContacto();
        ViewDetalleContacto viewDetalleContacto = new ViewDetalleContacto();
        ControllerDetalleContacto controllerDetalleContacto = new ControllerDetalleContacto(modelDetalleContacto, viewDetalleContacto);
        
        Object[] controllers = new Object[2];
        controllers[0] = controllerAgenda;
        controllers[1] = controllerDetalleContacto;
        
        ModelMain modelMain = new ModelMain();
        ViewMain viewMain = new ViewMain();
        ControllerMain controllerMain = new ControllerMain(modelMain, viewMain, controllers);
        
    }
    
}
