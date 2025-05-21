<%-- 
    Document   : editarCita
    Created on : 20-may-2025, 18:42:09
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar cita - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%@ include file="/includes/cabecera.jsp" %>

        <h1>Editar cita</h1>

        <s:form action="editarC" method="post">


            <s:select 
                name="tramite.id" 
                list="tramites" 
                listKey="id" 
                listValue="nombre" 
                label="Seleccione trámite"
               
                />

            <!-- No damos la opcion de EDITAR el tramite -->
            <s:textfield name="observaciones" label="Comentarios" readonly="true" />

            <s:select 
                name="estado" 
                label="Estado" 
                list="estadosValidos"
                value="%{estado}" 
                disabled="%{estadosValidos.size() == 1}" 
                />


            <s:hidden name="id" />

            <s:hidden name="formulario" value="editar" />
            <s:submit value="Editar cita" />
        </s:form>

        <a href="atrasatrasCit.action" class="s-button">Volver al listado</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
