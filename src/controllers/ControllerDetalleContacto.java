/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import models.ModelDetalleContacto;
import views.ViewDetalleContacto;
import models.ModelAgenda;

/**
 *
 * @author Zeo
 */
public class ControllerDetalleContacto {
    public ModelDetalleContacto modelDetalleContacto;
    public ViewDetalleContacto viewDetalleContacto;
    public ModelAgenda modelAgenda;

    /**
     * Controllador ControllerDetalleContacto que une el modelDetalleContacto y
     * viewDetalleContacto.
     * @param modelDetalleContacto
     * @param viewDetalleContacto 
     */
    public ControllerDetalleContacto(ModelDetalleContacto modelDetalleContacto, ViewDetalleContacto viewDetalleContacto) {
        this.modelDetalleContacto = modelDetalleContacto;
        this.viewDetalleContacto = viewDetalleContacto;
        initView();
    }
    
    /**
     * Muestra el nombre e email almacenados en el modelDetalleContacto en el 
     * viewDetalleContacto.
     */
    public final void initView(){
        viewDetalleContacto.jl_nombre.setText(modelDetalleContacto.getNombre());
        viewDetalleContacto.jl_email.setText(modelDetalleContacto.getEmail());
        viewDetalleContacto.jl_telefono.setText(modelDetalleContacto.getTelefono());
    }
}
