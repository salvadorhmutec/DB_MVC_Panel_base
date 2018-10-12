/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private String nombre;
    private String email;
    private String id;
    private String telefono;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método que realiza las siguietnes acciones: 1- Conecta con la base
     * agenda_mvc, 2- Consulta todo los registros de la tabla contactos, 3-
     * Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public Connection conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "user_mvc", "pass_mvc.2018");
            /*
            String sql = "SELECT * FROM contactos;";
            ps = conexion.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery(sql);
            rs.next();
            setValues();*/
            actualizarContactos();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }

        return conexion;
    }

    /**
     * Lee los valores del registro seleccionado y los asigna a las variables
     * miembre nombre y email.
     */
    public void setValues() {
        try {
            id = rs.getString("id_contacto");
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            telefono = rs.getString("telefono");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error model 102: " + e.getMessage());

        }
    }

    public void guardarRegistro(String nombre, String email, String telefono) {
        try {
            conexion = null;
            conexion = conectarDB();
            ps = conexion.prepareStatement("INSERT INTO contactos (nombre, email, telefono) VALUES (?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, telefono);
            int devuelto = ps.executeUpdate();
            if (devuelto > 0) {
                JOptionPane.showMessageDialog(null, "Datos registrados");
                actualizarContactos();

            } else {
                JOptionPane.showMessageDialog(null, "Datos NO registrados");
            }

        } catch (SQLException e) {
            Logger.getLogger(ModelAgenda.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarRegistro(String nombre, String email, String telefono, String id) {
        try {
            Connection conexion = null;
            conexion = conectarDB();
            ps = conexion.prepareStatement("UPDATE contactos SET nombre=?,email=?, telefono=? WHERE id_contacto=?");
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, telefono);
            ps.setString(4, id);
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Datos de Actualizados");
                actualizarContactos();
            } else {
                JOptionPane.showMessageDialog(null, "Error 001-guardar");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarRegistro(String id) {
        int des = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar este contacto?", "Eliminar contacto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (des != JOptionPane.YES_NO_OPTION ) {
        } else {
            try {
                conexion = null;
                conexion = conectarDB();
                ps = conexion.prepareStatement("DELETE FROM contactos WHERE id_contacto = ?");
                ps.setString(1, id);
                int res = ps.executeUpdate();
                actualizarContactos();
                JOptionPane.showMessageDialog(null, "Contacto eliminado");
            } catch (SQLException ex) {
                Logger.getLogger(ModelAgenda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro() {
        System.out.println("moverPrimerRegistro");
        try {
            if (rs.isFirst() == false) {
                rs.first();
                setValues();;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error_S" + e.getMessage());
        }
    }

    public void moverSiguienteRegistro() {
        System.out.println("moverSiguienteRegistro");
        try {
            if (rs.isLast() == false) {
                rs.next();
                setValues();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error_S" + e.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al anterior
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverAnteriorRegistro() {
        System.out.println("moverAnteriorRegistro");
        try {
            if (rs.isFirst() == false) {
                rs.previous();
                setValues();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error_S" + e.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la ariable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro() {
        System.out.println("moverUltimoRegistro");
        try {
            if (rs.isLast() == false) {
                rs.last();
                setValues();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error_S" + e.getMessage());
        }
    }

    public void actualizarContactos() {
        try {
            String sql = "SELECT * FROM contactos;";
            ps = conexion.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery(sql);
            rs.next();
            setValues();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error_Actualizar_tabla" + e.getMessage());
        }
    }

}//termina la clase

/**
 * Método que realiza las siguiente acciones: 1.- Moverse al siguiente registro
 * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre 3.-
 * obtener el valor del email de rs y guardarlo en la variable email
 */
