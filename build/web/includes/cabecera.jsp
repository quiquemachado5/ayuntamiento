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
        <s:if test="%{#session.usuario != null}"> <!-- Si está logado a -->
            <span class="nombre-usuario">Bienvenido, <s:property value="#session.usuario.nombre"/> - <s:property value="#session.usuario.rol"/></span>

            <s:if test="#session.usuario.fotoPerfil != null">
                <s:if test="#session.usuario.fotoPerfil != null">
                    <img src="img/perfiles/<s:property value='#session.usuario.fotoPerfil' />?ts=<%=System.currentTimeMillis()%>"
                         alt="Foto de perfil"
                         style="width: 40px; height: 40px; border-radius: 50%; object-fit: cover;" />l
                </s:if>



            </s:if>


            <!-- Botón Ver perfil -->
            <s:form action="perfil" method="post" cssStyle="display:inline;all:unset;">
                <s:submit value="Ver perfil" cssClass="boton-cerrar-sesion"/>
            </s:form>

            <s:form action="logout" method="post" cssStyle="display:inline;all:unset;">
                <s:submit value="Cerrar sesión" cssClass="boton-cerrar-sesion"/>
            </s:form>

        </s:if>
        <s:else> <!-- Si no está logado lo manda a ello -->
            <a href="index.jsp" title="Iniciar sesión o registrarse">
                <img src="img/user.png" alt="Usuario" />
            </a>
        </s:else>
    </div>
</div>

