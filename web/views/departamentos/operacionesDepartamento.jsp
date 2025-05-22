<%-- 
    Document   : operacionesDepartamento
    Created on : 16-may-2025, 20:02:16
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Departamentos - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%@ include file="/includes/cabecera.jsp" %>

        <!-- Mensajes de acción (éxito) -->
        <s:actionmessage cssClass="mensaje-exito" />

        <!-- Mensajes de error generales -->
        <s:actionerror cssClass="mensaje-error" />

        <!-- Mensajes de error por campo (del validate) -->
        <s:fielderror cssClass="mensaje-error-campo" />

        <!-- Si es ADMIN el usuario muestra los departamentos, y si no lo es no porque no tiene privilegios para esa operación según la lógica de la app -->
        <s:if test="%{#session.usuario.rol == 'ADMIN'}">
            <h2>Listado de departamentos</h2>

            <!-- Botón crear que lleva a la vista crearDepartamento.jsp -->
            <div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;">
                <s:form action="crearDepartamento" method="post" style="all:unset;">
                    <s:submit value="Crear departamento" />
                </s:form>
            </div>

            <!-- Tabla para recorrer los departamentos del sistema -->
            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Teléfono de contacto</th>
                        <th>Email de contacto</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="departamentos" var="d">
                        <tr>
                            <td><s:property value="#d.nombre" /></td>
                            <td><s:property value="#d.descripcion" /></td>
                            <td><s:property value="#d.telefonoContacto" /></td>
                            <td><s:property value="#d.emailContacto" /></td>
                            <td>
                                <!-- Botón para actualizar departamentos -->
                                <s:url var="actualizarUrl" action="editarDepartamento">
                                    <s:param name="emailContacto" value="#d.emailContacto"/>
                                </s:url>
                                <a href="${actualizarUrl}" class="btn-accion">Actualizar</a>
                                <!-- Botón para borrar departamento -->
                                <s:url var="borrarUrl" action="borrarDepartamento">
                                    <s:param name="emailContacto" value="#d.emailContacto"/>
                                </s:url>
                                <a href="${borrarUrl}" class="btn-accion btn-borrar">Borrar</a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <h2>No tiene acceso a la gestión de departamentos</h2>
        </s:else>


        <!-- Acción que lleva a home.jsp -->
        <a href="home.action" class="s-button">Menú Principal</a>


        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
