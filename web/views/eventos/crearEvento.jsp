<%-- 
    Document   : crearEvento
    Created on : 17-may-2025, 12:35:09
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Crear evento - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%@ include file="/includes/cabecera.jsp" %>

        <h1>Crear evento</h1>
        <!-- Formulario básico para crear el evento -->
        <s:form action="crearE" method="post">
            <s:textfield name="titulo" label="Titulo" />
            <s:textfield name="descripcion" label="Descripción" />
            <s:textfield name="fecha" label="Fecha" placeholder="mm/dd/YYYY" />
            <s:textfield name="lugar" label="Lugar" />

            <s:hidden name="formulario" value="crear" />
            <s:submit value="Crear evento" />
        </s:form>
        
        <!-- Botón atrás para ir al listado -->
       <a href="atras.action" class="s-button">Volver al listado</a>


        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
