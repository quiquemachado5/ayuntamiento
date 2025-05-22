<%-- 
    Document   : operacionesTramites
    Created on : 19-may-2025, 15:52:17
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Trámites - Ayuntamiento de Sevilla</title>
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

        <s:if test="#session.usuario.rol == 'ADMIN'">
            <h2>Listado de trámites</h2>
            
            <div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;">
                <s:form action="crearTramite" method="post" style="all:unset;">
                    <s:submit value="Crear trámite" />
                </s:form>
            </div>

            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Departamento</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Activo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <s:if test="tramites != null && !tramites.isEmpty()">
                        <s:iterator value="tramites" var="t">
                            <tr>
                                <td><s:property value="#t.departamento != null ? #t.departamento.nombre : 'Sin departamento'" /></td>
                                <td><s:property value="#t.nombre" /></td>
                                <td><s:property value="#t.descripcion" /></td>
                                <td><s:property value="#t.activo ? 'Sí' : 'No'" /></td>
                                <td>
                                     <!-- Solo borra y edita el admin los trámites, el usuario los ve y nada más -->
                                    <s:url var="actualizarUrl" action="editarTramite">
                                        <s:param name="id" value="#t.id"/>
                                    </s:url>
                                    <a href="${actualizarUrl}" class="btn-accion">Editar</a>

                                    <s:url var="borrarUrl" action="borrarTramite">
                                        <s:param name="id" value="#t.id"/>
                                    </s:url>
                                    <a href="${borrarUrl}" class="btn-accion btn-borrar">Borrar</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <tr>
                            <td colspan="5" style="text-align: center;">No hay trámites registrados.</td>
                        </tr>
                    </s:else>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <h2>Listado de trámites</h2>

            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>Departamento</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Activo</th>
                    </tr>
                </thead>
                <tbody>
                    <s:if test="tramites != null && !tramites.isEmpty()">
                        <s:iterator value="tramites" var="t">
                            <tr>
                                <td><s:property value="#t.departamento != null ? #t.departamento.nombre : 'Sin departamento'" /></td>
                                <td><s:property value="#t.nombre" /></td>
                                <td><s:property value="#t.descripcion" /></td>
                                <td><s:property value="#t.activo ? 'Sí' : 'No'" /></td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <tr>
                            <td colspan="5" style="text-align: center;">No hay trámites registrados.</td>
                        </tr>
                    </s:else>
                </tbody>
            </table>
        </s:else>

        <a href="home.action" class="s-button">Menú Principal</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
