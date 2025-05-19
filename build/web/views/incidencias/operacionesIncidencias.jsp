<%-- 
    Document   : operacionesIncidencias
    Created on : 17-may-2025, 18:52:52
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


        <s:if test="#session.usuario.rol == 'ADMIN'">
            <h2>Listado de incidencias</h2>

            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Departamento</th>
                        <th>Titulo</th>
                        <th>Descripcion</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <s:if test="incidencias != null && !incidencias.isEmpty()">
                        <s:iterator value="incidencias" var="i">
                            <tr>
                                <td><s:property value="#i.departamento != null ? #i.departamento.nombre : 'Sin departamento'" /></td>
                                <td><s:property value="#i.titulo" /></td>
                                <td><s:property value="#i.descripcion" /></td>
                                <td><s:property value="#i.estado" /></td>
                                <td>
                                    <s:url var="actualizarUrl" action="editarIncidencia">
                                        <s:param name="id" value="#i.id"/>
                                        <s:param name="titulo" value="#i.titulo"/>
                                    </s:url>
                                    <a href="${actualizarUrl}" class="btn-accion">Actualizar</a>

                                    <s:url var="borrarUrl" action="borrarIncidencia">
                                        <s:param name="id" value="#i.id"/>
                                    </s:url>
                                    <a href="${borrarUrl}" class="btn-accion btn-borrar">Borrar</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <tr>
                            <td colspan="5" style="text-align: center;">No hay incidencias registradas.</td>
                        </tr>
                    </s:else>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <h2>Listado de incidencias</h2>

            <div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;">
                <s:form action="crearIncidencia" method="post" style="all:unset;">
                    <s:submit value="Abrir incidencia" />
                </s:form>
            </div>

            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Departamento</th>
                        <th>Titulo</th>
                        <th>Descripcion</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <s:if test="incidencias != null && !incidencias.isEmpty()">
                        <s:iterator value="incidencias" var="i">
                            <tr>
                                <td><s:property value="#i.departamento != null ? #i.departamento.nombre : 'Sin departamento'" /></td>
                                <td><s:property value="#i.titulo" /></td>
                                <td><s:property value="#i.descripcion" /></td>
                                <td><s:property value="#i.estado" /></td>
                                <td>
                                    <s:url var="actualizarUrl" action="editarIncidencia">
                                        <s:param name="id" value="#i.id"/>
                                    </s:url>
                                    <a href="${actualizarUrl}" class="btn-accion">Actualizar</a>

                                    <s:url var="borrarUrl" action="borrarIncidencia">
                                        <s:param name="id" value="#i.id"/>
                                    </s:url>
                                    <a href="${borrarUrl}" class="btn-accion btn-borrar">Borrar</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <tr>
                            <td colspan="5" style="text-align: center;">No hay incidencias registradas.</td>
                        </tr>
                    </s:else>
                </tbody>
            </table>
        </s:else>

        <a href="home.action" class="s-button">Menú Principal</a>


        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
