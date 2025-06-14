<%-- 
    Document   : home
    Created on : 16-may-2025, 13:01:19
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Usuarios - Ayuntamiento de Sevilla</title>
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
        
        <!-- Sino es admin no puede entrar el usuario ya que la lógica es que el admin elimnine o actualice usuarios, se registran (crean) en el login -->
        <s:if test="%{#session.usuario.rol == 'ADMIN'}">
            <h2>Listado de usuarios</h2>
            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Teléfono</th>
                        <th>Dirección</th>
                        <th>Rol</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="usuarios" var="u">
                        <tr>
                            <td><s:property value="#u.nombre" /></td>
                            <td><s:property value="#u.email" /></td>
                            <td><s:property value="#u.telefono" /></td>
                            <td><s:property value="#u.direccion" /></td>
                            <td><s:property value="#u.rol" /></td>
                            <td>
                                <%-- 
                                <s:url var="actualizarUrl" action="editarUsuario">
                                    <s:param name="email" value="#u.email"/>
                                </s:url>
                                <a href="${actualizarUrl}" class="btn-accion">Actualizar</a>
                                --%>
                                <s:url var="borrarUrl" action="borrarUsuario">
                                    <s:param name="email" value="#u.email"/>
                                </s:url>
                                <a href="${borrarUrl}" class="btn-accion btn-borrar">Borrar</a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <!-- Esta pantalla le salía anteriormente al usuario tipo CIUDADANO, ahora con css hago que ni siquiera acceda a esta página -->
            <h2>No tiene acceso a la gestión de usuarios</h2>
        </s:else>

        <a href="home.action" class="s-button">Menú Principal</a>


        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
