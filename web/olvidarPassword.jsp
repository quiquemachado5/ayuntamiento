<%-- 
    Document   : olvidarPassword
    Created on : 02-jun-2025, 16:01:57
    Author     : emdominguez
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Recuperar Contraseña - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" type="text/css" href="css/estilos.css" />
    </head>
    <body>
        <jsp:include page="/includes/cabecera.jsp" />
        
         <!-- Mensajes de acción (éxito) -->
        <s:actionmessage cssClass="mensaje-exito" />

        <!-- Mensajes de error generales -->
        <s:actionerror cssClass="mensaje-error" />

        <!-- Mensajes de error por campo (del validate) -->
        <s:fielderror cssClass="mensaje-error-campo" />

        <!-- Correo al que se te mandará el enlace -->
        <div class="form-login">
            <h2>Recuperar Contraseña</h2>
            <p style="text-align: center">Introduce tu correo electrónico y te enviaremos un enlace para restablecer tu contraseña.</p>

            <s:form action="enviarRecuperacion" method="post">
                <s:textfield name="email" label="Correo electrónico" />
                <s:submit value="Enviar enlace de recuperación" />
            </s:form>

            
        </div>
        
        <a href="home.action" class="s-button">Menú Principal</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>

