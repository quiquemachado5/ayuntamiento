<%-- 
    Document   : editarEvento
    Created on : 17-may-2025, 12:35:01
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar evento - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%@ include file="/includes/cabecera.jsp" %>

        <h1>Editar evento</h1>
        <!-- Mismo form que crear practicamente -->
        <s:form action="editarE" method="post">
            <s:hidden name="id" />
            
            <s:textfield name="titulo" label="Titulo" />
            <s:textfield name="descripcion" label="Descripción" />
             <s:textfield name="fecha" label="Fecha" placeholder="mm/dd/YYYY" />
            <s:textfield name="lugar" label="Lugar" />

            <s:hidden name="formulario" value="editar" />
            <s:submit value="Editar evento" />
        </s:form>
        
        <!-- Botón atrás para ir al listado -->
        <a href="atras.action" class="s-button">Volver al listado</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
