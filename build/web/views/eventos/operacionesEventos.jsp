<%-- 
    Document   : operacionesEventos
    Created on : 17-may-2025, 12:34:47
    Author     : emdominguez
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Eventos - Ayuntamiento de Sevilla</title>
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

        <!-- Solo crean eventos si es admin -->
        <s:if test="%{#session.usuario.rol == 'ADMIN'}">
            <h2>Listado de eventos</h2>

            <div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;">
                <s:form action="crearEvento" method="post" style="all:unset;">
                    <s:submit value="Crear Evento" />
                </s:form>
            </div>

            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Titulo</th>
                        <th>Descripción</th>
                        <th>Fecha</th>
                        <th>Lugar</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="eventos" var="e">
                        <tr>
                            <td><s:property value="#e.titulo" /></td>
                            <td><s:property value="#e.descripcion" /></td>
                            <td><s:property value="#e.fecha" /></td>
                            <td><s:property value="#e.lugar" /></td>
                            <td>
                                <s:url var="actualizarUrl" action="editarEvento">
                                    <s:param name="id" value="#e.id"/>
                                </s:url>
                                <a href="${actualizarUrl}" class="btn-accion">Actualizar</a>

                                <s:url var="borrarUrl" action="borrarEvento">
                                    <s:param name="id" value="#e.id"/>
                                </s:url>
                                <a href="${borrarUrl}" class="btn-accion btn-borrar">Borrar</a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:else>
             <h2>Listado de eventos</h2>
            <!-- El ciudadano normal solo los ve los eventos ya que es puramente informativo -->
            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Titulo</th>
                        <th>Descripción</th>
                        <th>Fecha</th>
                        <th>Lugar</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="eventos" var="e">
                        <tr>
                            <td><s:property value="#e.titulo" /></td>
                            <td><s:property value="#e.descripcion" /></td>
                            <td><s:property value="#e.fecha" /></td>
                            <td><s:property value="#e.lugar" /></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>

        </s:else>

        <a href="home.action" class="s-button">Menú Principal</a>


        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
