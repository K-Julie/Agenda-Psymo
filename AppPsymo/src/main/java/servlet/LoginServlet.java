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

            String sql = "SELECT nombres FROM usuarios "
                       + "WHERE tipo_documento=? AND numero_documento=? AND contrasena=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipoDoc);
            ps.setString(2, numero);
            ps.setString(3, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombres = rs.getString("nombres");
                request.setAttribute("nombreUsuario", nombres);
                request.getRequestDispatcher("inicio.jsp").forward(request, response); 
            } else {
                request.setAttribute("error", "Datos incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Error en la conexión o consulta", e);
        }
    }
}
