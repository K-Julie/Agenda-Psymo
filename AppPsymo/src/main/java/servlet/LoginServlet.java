/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package servlet;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import conexion.conexionDB;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipoDoc = request.getParameter("tipo_documento");
        String numero  = request.getParameter("numero_documento");
        String pass    = request.getParameter("contrasena"); // sin ñ para evitar inconvenientes

        try (Connection con = conexionDB.getConnection()) {

            String sql = "SELECT nombres, rol FROM usuarios "
                       + "WHERE tipo_documento=? AND numero_documento=? AND contrasena=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipoDoc);
            ps.setString(2, numero);
            ps.setString(3, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombres = rs.getString("nombres");
                String rol = rs.getString ("rol");
                
                //Guardar nombres y rol en la sesión
                
                HttpSession session = request.getSession();
                session.setAttribute("nombreUsuario" , nombres);
                session.setAttribute("rol", rol);
                
               //Tiempo de inactividad para que caudique automátiicamente la sesión
                session.setMaxInactiveInterval(900);
                        
               // Redirección según rol   
               
               if ("administrador".equals(rol)) { //exactamente el valor en mi tabla MySql!!!!
                   response.sendRedirect("adminPanel.jsp");
               } else if ("consultante".equals(rol)){
                   response.sendRedirect("panelconsultante.jsp");  
               } else if ("recepcionista".equals(rol)){
                   response.sendRedirect("recepcionistaPanel.jsp"); 
               } else { //En caso de que el rol no sea válido
                   request.setAttribute("error", "Rol no válido, contacte al administrador");
                   request.getRequestDispatcher("login.jsp").forward(request, response);
               }
               
               } else {
                //Si algún dato es incorrectos:
                request.setAttribute("error", "Documento o contraseña no válidos, por favor intente de nuevo");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Error en la conexión o consulta", e);
        }
    }
}
