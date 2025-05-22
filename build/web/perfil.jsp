<%-- 
    Document   : perfil
    Created on : 21-may-2025, 12:00:07
    Author     : emdominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="includes/cabecera.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" type="text/css" href="css/estilos.css" />
    </head>
    <body>
        <h2>Mi Perfil</h2>

        <s:form action="actualizarPerfil" method="post">
            <s:hidden name="id" value="%{#session.usuario.id}" />

            <s:textfield name="nombre" value="%{#session.usuario.nombre}" label="Nombre" />
            <s:textfield name="email" value="%{#session.usuario.email}" label="Email"/>
            <s:textfield name="password" value="%{#session.usuario.password}" label="Contraseña"/>
            <s:textfield name="direccion" value="%{#session.usuario.direccion}" label="Direccion"/>
            <s:textfield name="telefono" value="%{#session.usuario.telefono}" label="Telefono" /> 

            <s:submit value="Guardar cambios" cssClass="boton-guardar" />
        </s:form>

        <s:form action="subirFotoTemporal" method="post" enctype="multipart/form-data">
            <s:file name="imagen" label="Nueva foto de perfil"/>
            <s:hidden name="id" value="%{#session.usuario.id}" />
            <s:submit value="Actualizar foto"/>
        </s:form>

        <s:if test="#session.usuario.fotoPerfil != null">
            <img src="img/tmp/mini_<s:property value="#session.usuario.fotoPerfil"/>.png"
                 alt="Vista previa"
                 style="display: block; margin: 20px auto; width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
        </s:if>




        <a href="home.action" class="s-button">Menú Principal</a>

        <jsp:include page="/includes/footer.jsp" />
    </body>
</html>
