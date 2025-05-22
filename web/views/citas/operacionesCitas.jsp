<%-- 
    Document   : operacionesCitas
    Created on : 20-may-2025, 17:52:00
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Citas - Ayuntamiento de Sevilla</title>
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

        <!-- Si el usuario es admin muestra la tabla diferente a usuario. Un admin puede borrar y editar -->
        <s:if test="#session.usuario.rol == 'ADMIN'">
            <h2>Listado de citas</h2>

            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Trámite</th>
                        <th>Usuario</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Observaciones</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <s:if test="citas != null && !citas.isEmpty()">
                        <s:iterator value="citas" var="c">
                            <tr>
                                <td>
                                    <s:if test="#c.tramite != null">
                                        <s:property value="#c.tramite.nombre" />
                                    </s:if>
                                </td>
                                <td><s:property value="#c.usuario" /></td>
                                <td><s:property value="#c.fecha" /></td>
                                <td><s:property value="#c.estado" /></td>
                                <td><s:property value="#c.observaciones" /></td>
                                <td>
                                    <!-- Solo se edita si no está en estado atendida -->
                                    <s:if test="#c.estado != 'ATENDIDA'">
                                        <s:url var="actualizarUrl" action="editarCita">
                                            <s:param name="id" value="#c.id"/>
                                        </s:url>
                                        <a href="${actualizarUrl}" class="btn-accion">Editar</a>
                                    </s:if>
                                    <!-- Se borra si ha sido atendida únicamente -->
                                    <s:if test="#c.estado == 'ATENDIDA'">
                                        <s:url var="borrarUrl" action="borrarCita">
                                            <s:param name="id" value="#c.id"/>
                                        </s:url>
                                        <a href="${borrarUrl}" class="btn-accion btn-borrar">Borrar</a>
                                    </s:if>
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <!-- Si no hay citas no muestra nada, solo este mensaje-->
                        <tr>
                            <td colspan="5" style="text-align: center;">No hay citas aún.</td>
                        </tr>
                    </s:else>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <h2>Listado de citas</h2>

            <div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;">
                <s:form action="crearCita" method="post" style="all:unset;">
                    <s:submit value="Pedir cita" />
                </s:form>
            </div>

            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Trámite</th>
                        <th>Usuario</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Observaciones</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <s:if test="citas != null && !citas.isEmpty()">
                        <s:iterator value="citas" var="c">
                            <tr>
                                <td>
                                    <s:if test="#c.tramite != null">
                                        <s:property value="#c.tramite.nombre" />
                                    </s:if>
                                </td>
                                <td><s:property value="#c.usuario" /></td>
                                <td><s:property value="#c.fecha" /></td>
                                <td><s:property value="#c.estado" /></td>
                                <td><s:property value="#c.observaciones" /></td>
                                <td>
                                    <s:url var="pdfUrl" action="citaPDF">
                                        <s:param name="id" value="#c.id" />
                                    </s:url>
                                    <a href="${pdfUrl}" class="btn-accion">📄 Descargar cita</a>
                                    <!-- El usuario además de ver las citas, puede descargar en pdf el acuse de recibo de la cita -->
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <tr>
                            <td colspan="5" style="text-align: center;">No hay citas aún.</td>
                        </tr>
                    </s:else>
                </tbody>
            </table>
        </s:else>
            
        <!-- Botón atrás para el menú principal -->
        <a href="home.action" class="s-button">Menú Principal</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
