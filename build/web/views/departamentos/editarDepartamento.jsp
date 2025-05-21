<%-- 
    Document   : editarDepartamento
    Created on : 16-may-2025, 20:41:23
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar departamento - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%@ include file="/includes/cabecera.jsp" %>

        <h1>Editar departamento</h1>

        <s:form action="editarD" method="post">
            <s:textfield name="nombre" label="Nombre" />
            <s:textfield name="descripcion" label="Descripción" />
            <p><strong>Email:</strong> <s:property value="emailContacto" /></p> <!-- Muestro el email -->
            <s:hidden name="emailContacto" value="%{emailContacto}" /> <!-- Lo paso en hidden para que tenga un valor en el action -->

            <s:textfield name="telefonoContacto" label="Teléfono" />

            <s:hidden name="id" /> <!-- Hidden el id para operar en el action en referencia al departamento seleccionado -->
            <s:hidden name="formulario" value="editar" /> <!-- El atributo formulario es para verificar en el validate desde donde viene -->
            <s:submit value="Editar departamento" />
        </s:form>

        <a href="atrasD.action" class="s-button">Volver al listado</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
