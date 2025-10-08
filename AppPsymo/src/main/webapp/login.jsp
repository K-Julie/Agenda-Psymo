
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AgendaPsymo</title>
    <link rel="stylesheet" href="login.css">
</head>

<body>
    <%--Contenedor principal para dividir el login en dos columnas--%>
    <div class="contenedor">

        <div class="columnaIzq">
            <header>
                 <div class="encabezado">
  <img src="imagenes/Logo Centro armonia.png" class="logoArmonia" alt="Logo Centro Armonía">
  <div class="textoEncabezado">
    <h2>Portal de Citas</h2>
    <h1>Bienvenido(a)</h1>
  </div>
</div>
     
            </header>

            <main>
                <div class="login"> 
                    <h3>Ingrese sus datos para continuar</h3>
                    <%--Formulario que envía al servelt--%>
                    <form method="post" action="LoginServlet">
                        <div class="seleccionar">
                            <select name="tipo_documento" required>
                                <option value="">Tipo de Documento</option>
                                <option value="cc">Cédula de ciudadanía</option>
                                <option value="ce">Cédula de extranjería</option>
                                <option value="ps">Pasaporte</option>
                            </select>
                        </div>

                        <div class="numerodoc">
                            <input type="text" name="numero_documento" placeholder="Número de documento" required>
                        </div>

                        <div class="contraseña">
                            <%-- Importante recordar que en name es sin ñ para evitar problemas: "contrasena" --%>
                            <input type="password" name="contrasena" placeholder="Contraseña" required>
                        </div>
                        <div class= "recordar">
                            <label> Olvidé mi contraseña </label>
                        </div>
                        <div class= "registrar">
                            <label> Quiero registrarme </label>
                        </div>
                            
                        <input type="submit" value="Ingresar">
                    </form>

                    <%--Mensaje de error si el inicio de sesión falla, desde el servlet--%>
                    <% if (request.getAttribute("error") != null) { %>
                        <p style="color:red; text-align:center;">
                            <%= request.getAttribute("error") %>
                        </p>
                    <% } %>
                </div>

                <%--<div class="logoApp">
                    <img src="imagenes/AgendaPsymo(1).png" alt="Logo App Web" class="logoApp">
                </div> --%>
            </main>
        </div>
        
        <div class="columnaDere">
            <img src="imagenes/consulta.jpg" alt="Imagén terapia psicologica" class="consulta">
        </div> 
    </div> 
    
    <footer>
        <img src="imagenes/Logotipo Blanco.png" alt="Logo Centro Armonía" class="logoFooter">
        <p>©Copyright 2025</p>
    </footer>
                
</body>
</html>
