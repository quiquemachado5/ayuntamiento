<%-- 
    Document   : crearDepartamento
    Created on : 16-may-2025, 20:20:41
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inicio - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%@ include file="/includes/cabecera.jsp" %>

        <h1>Crear departamento</h1>

        <s:form action="crear" method="post">
            <s:textfield name="nombre" label="Nombre" />
            <s:textfield name="descripcion" label="Descripción" />
            <s:textfield name="emailContacto" label="Email" />
            <s:textfield name="telefonoContacto" label="Teléfono" />

            <s:hidden name="formulario" value="crear" />
            <s:submit value="Crear departamento" />
        </s:form>

        <a href="atras.action" class="s-button">Volver al listado</a>


        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
