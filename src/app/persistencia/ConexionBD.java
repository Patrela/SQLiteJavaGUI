/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author cahuc
 */
public class ConexionBD {

    String url;
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public ConexionBD() {

        url = "jdbc:sqlite:database.db";

        try {
            con = DriverManager.getConnection(url);

            if (con != null) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.INFO, "Conexión exitosa");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {

        if (con != null) {

            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar: " + e.getMessage());
            }
        }

    }

    public ResultSet consultarBD(String sentencia) {

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(null, "ERROR SQL: " + sqlEx.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR DESCONOCIDO: " + e.getMessage());
        }

        return rs;
    }

    public boolean insertar(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException e) {
            return false;
        }
        return true;
    }

    public boolean actualizar(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException e) {
            return false;
        }
        return true;
    }

    public boolean borrar(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException e) {
            return false;
        }

        return true;
    }

}
