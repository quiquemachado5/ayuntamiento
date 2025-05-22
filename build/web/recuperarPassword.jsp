<%-- 
    Document   : recuperarPassword
    Created on : 02-jun-2025, 16:16:31
    Author     : emdominguez
--%>

<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Restablecer Contraseña</title>
        <link rel="stylesheet" href="css/estilos.css" />
    </head>
    <body>
        <jsp:include page="/includes/cabecera.jsp" />

        <div class="form-login">
            <h2>Restablecer tu contraseña</h2>

            <!-- El email esta hidden y lo cogemos de la url -->
            <s:form action="cambiarPassword" method="post">
                <s:hidden name="email" value="%{#parameters.email}" />
                <s:password name="password" label="Nueva contraseña" />
                <s:submit value="Guardar nueva contraseña" />
            </s:form>

            <s:if test="hasActionErrors()">
                <div class="error-message">
                    <s:actionerror />
                </div>
            </s:if>

            <s:if test="hasActionMessages()">
                <div class="success-message">
                    <s:actionmessage />
                </div>
            </s:if>
        </div>
        
        <a href="home.action" class="s-button">Menú Principal</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>

