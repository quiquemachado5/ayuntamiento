<%-- 
    Document   : crearTramite
    Created on : 19-may-2025, 15:52:24
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Crear trámite - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%@ include file="/includes/cabecera.jsp" %>

        <h1>Crear trámite</h1>

        <s:form action="crearT" method="post">
            <s:select 
                label="Departamento"
                name="departamento.id"
                list="departamentos"
                listKey="id"
                listValue="nombre"
                />
            <!-- Damos la opcion de seleccionar el departamento -->
            <s:textfield name="nombre" label="Nombre" />
            <s:textfield name="descripcion" label="Descripción" />
            <s:label label="Activo" value="Sí" />
            <s:hidden name="activo" value="true" />


            <!-- Escondemos el valor de estado y en activo por defecto siempre -->

            <s:hidden name="formulario" value="crear" />
            <s:submit value="Crear trámite" />
        </s:form>

        <a href="atrasatrasTra.action" class="s-button">Volver al listado</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
