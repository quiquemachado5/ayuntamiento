<%-- 
    Document   : crearIncidencia
    Created on : 18-may-2025, 11:44:47
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Crear incidencia - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%@ include file="/includes/cabecera.jsp" %>

        <h1>Crear incidencia</h1>

        <s:form action="crearI" method="post">

            <s:select 
                label="Departamento"
                name="departamento.id"
                list="departamentos"
                listKey="id"
                listValue="nombre"
                />
            <!-- Damos la opcion de seleccionar el departamento -->
            <s:textfield name="titulo" label="Titulo" />
            <s:textfield name="descripcion" label="Descripción" />
            <s:select 
                name="estado" 
                label="Estado" 
                list="{'ABIERTA'}"
                disabled="true"/>
            <s:hidden name="estado" value="ABIERTA"/>
            <!-- Escondemos el valor de estado y abierta por defecto siempre -->


            <s:hidden name="formulario" value="crear" />
            <s:submit value="Crear incidencia" />
        </s:form>

        <a href="atrasatrasInci.action" class="s-button">Volver al listado</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
