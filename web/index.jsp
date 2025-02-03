<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String step = request.getParameter("step");
    if (step == null) {
        step = "1";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mi Calculadora</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fff;
            color: #333;
            margin: 0;
            padding: 0;
            line-height: 1.6;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f4f4f4;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-bottom: 0;
            text-align: center;
            color: #333;
            font-size: 30px;
        }
        h2 {
            margin-top: 0;
            text-align: center;
            color: #333;
            font-size: 21px;
        }
        label {
            display: block;
            margin-bottom: 6px;
            font-size: 20px;
            font-weight: normal;
        }
        input, button {
            width: 100%;
            padding: 12px;
            margin-bottom: 18px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 20px;
            color: #333;
        }
        input:focus, button:focus {
            outline: none;
            border-color: #999;
        }
        button {
            background-color: #333;
            color: #fff;
            cursor: pointer;
            font-weight: bold;
            border: none;
        }
        button:hover {
            background-color: #444;
        }
    </style>
    <script>
        
    </script>
</head>
<body>
    <div class="container">
        <h1>Calculadora VLSM</h1>
        <h2>Hecho por: Diego Montesdeoca</h2>
        <%
            if ("1".equals(step)) {
        %>
        <form action="index.jsp" method="get">
            <input type="hidden" name="step" value="2">
            <label>Dirección IP Base:</label>
            <input type="text" name="baseIP" placeholder="192.168.0.0" required pattern="^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$">
            
            <label>Máscara de Red:</label>
            <input type="number" name="netmask" placeholder="24" required min="0" max="30">
            
            <label>Cantidad de Subredes:</label>
            <input type="number" name="numSubnets" required min="1">
            
            <button type="submit">Continuar</button>
        </form>
        <%
            } else if ("2".equals(step)) {
                int numSubnets = Integer.parseInt(request.getParameter("numSubnets"));
        %>
        <form action="VLSMServlet" method="post">
            <input type="hidden" name="baseIP" value="<%= request.getParameter("baseIP") %>">
            <input type="hidden" name="netmask" value="<%= request.getParameter("netmask") %>">
            <input type="hidden" name="numSubnets" value="<%= numSubnets %>">
            <%
                for (int i = 1; i <= numSubnets; i++) {
            %>
            <label>Hosts Requeridos para Subred <%= i %>:</label>
            <input type="number" name="hostsRequired<%= i %>" required min="1">
            <%
                }
            %>
            <button type="submit">Calcular Subredes</button>
        </form>
        <%
            }
        %>
    </div>
</body>
</html>