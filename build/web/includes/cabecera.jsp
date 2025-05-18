<%-- 
    Document   : cabecera
    Created on : 16-may-2025, 16:15:20
    Author     : emdominguez
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="cabecera" style="display: flex; justify-content: space-between; align-items: center;">
    <!-- Logo a la izquierda -->
    <div class="logo">
        <img src="img/logo.png" alt="Logo Ayuntamiento de Sevilla" />
    </div>

    <!-- Área de usuario a la derecha -->
    <div class="usuario" style="display: flex; align-items: center; gap: 10px;">
        <s:if test="%{#session.usuario != null}">
            <span class="nombre-usuario">Bienvenido, <s:property value="#session.usuario.nombre"/> - <s:property value="#session.usuario.rol"/></span>
            <s:form action="logout" method="post" cssStyle="display:inline;all:unset;">
                <s:submit value="Cerrar sesión" cssClass="boton-cerrar-sesion"/>
            </s:form>

        </s:if>
        <s:else>
            <a href="index.jsp" title="Iniciar sesión o registrarse">
                <img src="img/user.png" alt="Usuario" />
            </a>
        </s:else>
    </div>
</div>

