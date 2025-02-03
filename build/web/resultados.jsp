<%-- 
    Document   : results
    Created on : 14 ene. 2025, 21:21:22
    Author     : dmont
--%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Resultados del Cálculo VLSM</title>
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: flex-start;
                min-height: 100vh;
                margin: 0;
            }

            .container {
                width: auto;
                max-width: 90%;
                padding: 20px;
                background-color: #f4f4f4;
                border-radius: 8px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                color: #333;
                font-size: 24px;
            }
            .result-text {
                margin-top: 20px;
                padding: 15px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 1em;
                color: #333;
            }
            table {
                border-spacing: 15px;
            }
            th, td {
                text-align: center;
                font-size: 16px;
            }
            .back-button {
                text-align: center;
                margin-top: 20px;
            }
            button {
                padding: 10px 20px;
                background-color: #333;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 14px;
            }
            button:hover {
                background-color: #444;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Resultados del Cálculo VLSM</h1>
            <div class="result-text">
                <%
                    
                    // Obtener los resultados desde la solicitud
                    @SuppressWarnings("unchecked")
                    List<String[]>[] result = (List<String[]>[]) request.getAttribute("resultado");
                    
                    // Guardamos el resultado en la sesión
                    session = request.getSession();
                    session.setAttribute("resultado", result);
                        
                    // Si la lista es válida, guardarla en la sesión
                    if (result[0] != null && !result[0].isEmpty()) {
                        
                        for (int i = 0; i < result[0].size(); i++) {
                            String line = result[0].get(i)[0];
                            // Reemplazar '\t' por 4 espacios no rompibles (&nbsp;)
                            line = line.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
                            // Reemplazar '\n' por un salto de línea HTML (<br>)
                            line = line.replace("\n", "<br>");
                            out.println(line);
                        }
                    } else {
                        out.println("No se encontraron resultados.");
                    }
                %>

                <table>
                    <tr>
                        <th>Nombre</th>
                        <th>Hosts</th>
                        <th>Máscara</th>
                    </tr>
                    <%
                        if (result[1] != null && !result[1].isEmpty()) {
                            for (String[] fila : result[1]) {
                    %>
                    <tr>
                        <td><%= fila[0]%></td>
                        <td><%= fila[1]%></td>
                        <td><%= fila[2]%></td>
                    </tr>
                    <% }
                    } else { %>
                    <tr>
                        <td colspan="5">No se encontraron resultados para mostrar.</td>
                    </tr>
                    <% }%>
                </table>

                <%
                    // Si la lista es válida, guardarla en la sesión
                    if (result[2] != null && !result[2].isEmpty()) {

                        for (int i = 0; i < result[2].size(); i++) {
                            String line = result[2].get(i)[0];
                            // Reemplazar '\t' por 4 espacios no rompibles (&nbsp;)
                            line = line.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
                            // Reemplazar '\n' por un salto de línea HTML (<br>)
                            line = line.replace("\n", "<br>");
                            out.println(line);
                        }
                    } else {
                        out.println("No se encontraron resultados.");
                    }
                %>

                <table>
                    <tr>
                        <th>Nombre</th>
                        <th>IP de Red</th>
                        <th>IP Minima</th>
                        <th>IP Maxima</th>
                        <th>Broadcast</th>
                    </tr>
                    <%
                        if (result[3] != null && !result[3].isEmpty()) {
                            for (String[] fila : result[3]) {
                    %>
                    <tr>
                        <td><%= fila[0]%></td>
                        <td><%= fila[1]%></td>
                        <td><%= fila[2]%></td>
                        <td><%= fila[3]%></td>
                        <td><%= fila[4]%></td>
                    </tr>
                    <% }
                    } else { %>
                    <tr>
                        <td colspan="5">No se encontraron resultados para mostrar.</td>
                    </tr>
                    <% }%>
                </table>

            </div>
                
            <div class="back-button">
                <form action="index.jsp">
                    <button type="submit">Regresar al Inicio</button>
                </form>
            </div>
            <div class="back-button">
                <button onclick="window.location.href = 'ExportarPDFServlet'">Generar PDF</button>
            </div>

        </div>

    </body>
</html>
