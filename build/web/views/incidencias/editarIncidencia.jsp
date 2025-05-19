<%-- 
    Document   : editarIncidencia
    Created on : 18-may-2025, 12:41:31
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

        <h1>Editar incidencia</h1>
        <s:if test="#session.usuario.rol == 'ADMIN'">
            <s:form action="editar" method="post">

                <s:select 
                    label="Departamento"
                    name="departamento.id"
                    list="departamentos"
                    listKey="id"
                    listValue="nombre"
                    />


                <s:textfield name="titulo" label="Titulo" />
                <s:textfield name="descripcion" label="Descripción" />
                <s:select 
                    name="estado" 
                    label="Estado" 
                    list="{'ABIERTA','EN_PROCESO','RESUELTA','CERRADA'}"
                    value="%{estado}" 
                    />


                <s:hidden name="id" />

                <s:hidden name="formulario" value="editar" />
                <s:submit value="Editar incidencia" />
            </s:form>

            <a href="atrasatrasInci.action" class="s-button">Volver al listado</a>

        </s:if>
        <s:else>
            <s:form action="editar" method="post">

                <s:select 
                    label="Departamento"
                    name="departamento.id"
                    list="departamentos"
                    listKey="id"
                    listValue="nombre"
                    />


                <s:textfield name="titulo" label="Titulo" />
                <s:textfield name="descripcion" label="Descripción" />

                <!-- Mostrar estado como texto -->
                <p>Estado: <s:property value="estado"/></p>
                <!-- Campo oculto para enviar el estado -->
                <input type="hidden" name="estado" value="<s:property value='estado'/>" />

                <s:hidden name="id" />

                <s:hidden name="formulario" value="editar" />
                <s:submit value="Editar incidencia" />
            </s:form>

            <a href="atrasatrasInci.action" class="s-button">Volver al listado</a>
        </s:else>


        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
