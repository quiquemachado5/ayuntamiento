<%-- 
    Document   : home
    Created on : 16-may-2025, 13:01:19
    Author     : emdominguez
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="includes/cabecera.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inicio - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" type="text/css" href="css/estilos.css" />
    </head>
    <body>
        <div class="container">
            <h1>Panel de Administración</h1>
            <h3>Total de incidencias: <s:property value="totalIncidencias" /></h3>
            <h3>Total de incidencias <s:property value="#session.usuario.nombre"/> : <s:property value="totalIncidenciasUsuarios" /></h3>
            <!-- Indicador actualizado cada vez que uno está en Home con las incidencias totales y las del usuario logueado en ese momento-->

            <!-- Menú didáctico con fotos para acceder a cada apartado de la web  -->
            <div class="menu-grid">

                <s:set var="isAdmin" value="#session.usuario.rol == 'ADMIN'" />
                <s:set var="isCiudadano" value="#session.usuario.rol == 'CIUDADANO'" />

                <div class="menu-card" 
                     style="<s:if test='%{#isCiudadano}'>filter: grayscale(100%) opacity(0.6); pointer-events: none; position: relative;</s:if>">
                         <img src="img/usuarios.jpg" alt="Gestión de Usuarios" />
                         <h2>
                             Usuarios
                         <s:if test="%{#isCiudadano}">
                             <span style="text-decoration: line-through; color: gray;">(No accesible)</span>
                         </s:if>
                     </h2>
                     <a href="<s:property value='%{#isAdmin ? "listarUsuarios.action" : "#"}'/>" 
                        class="boton"
                        <s:if test="%{#isCiudadano}">onclick="return false;" style="cursor: not-allowed; pointer-events: none;"</s:if>>
                            Gestionar
                        </a>
                     </div>

                     <div class="menu-card" 
                          style="<s:if test='%{#isCiudadano}'>filter: grayscale(100%) opacity(0.6); pointer-events: none; position: relative;</s:if>">
                         <img src="img/departamentos.jpg" alt="Gestión de Departamentos" />
                         <h2>
                             Departamentos
                         <s:if test="%{#isCiudadano}">
                             <span style="text-decoration: line-through; color: gray;">(No accesible)</span>
                         </s:if>
                     </h2>
                     <a href="<s:property value='%{#isAdmin ? "listarDepartamentos.action" : "#"}'/>" 
                        class="boton"
                        <s:if test="%{#isCiudadano}">onclick="return false;" style="cursor: not-allowed; pointer-events: none;"</s:if>>
                            Gestionar
                        </a>
                     </div>

                     <div class="menu-card">
                         <img src="img/citas.jpg" alt="Gestión de Citas" />
                         <h2>Citas</h2>
                         <a href="listarCitas.action" class="boton">Gestionar</a>
                     </div>
                     <div class="menu-card">
                         <img src="img/eventos.jpg" alt="Gestión de Eventos" />
                         <h2>Eventos</h2>
                         <a href="listarEventos.action" class="boton">Gestionar</a>
                     </div>
                     <div class="menu-card">
                         <img src="img/incidencias.jpg" alt="Gestión de Incidencias" />
                         <h2>Incidencias</h2>
                         <a href="listarIncidencias.action" class="boton">Gestionar</a>
                     </div>
                     <div class="menu-card">
                         <img src="img/tramites.png" alt="Gestión de Trámites" />
                         <h2>Trámites</h2>
                         <a href="listarTramites.action" class="boton">Gestionar</a>
                     </div>
                </div>

            </div>

        <jsp:include page="includes/footer.jsp"/>

    </body>
</html>