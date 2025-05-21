<%-- 
    Document   : crearCita
    Created on : 20-may-2025, 18:21:03
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pedir cita - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%@ include file="/includes/cabecera.jsp" %>

        <h1>Pedir cita</h1>

        <s:form action="crearC" method="post">
            <s:select 
                label="Tramite"
                name="tramite.id"
                list="tramites"
                listKey="id"
                listValue="nombre"
                />
            <!-- Damos la opcion de seleccionar el tramite -->
            <s:textfield name="observaciones" label="Comentarios" />
            <s:hidden name="estado" value="PENDIENTE"/>
            <s:hidden name="formulario" value="crear" />
            <s:submit value="Pedir cita" />
        </s:form>

        <a href="atrasatrasCit.action" class="s-button">Volver al listado</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>