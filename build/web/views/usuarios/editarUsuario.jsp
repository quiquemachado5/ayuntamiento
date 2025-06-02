<%-- 
    Document   : editarUsuario
    Created on : 16-may-2025, 18:34:12
    Author     : emdominguez
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Usuario - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>

        <%@ include file="/includes/cabecera.jsp" %>


        <h1>Editar Usuario</h1>

        <s:form action="editarU" method="post">
            <!-- Email, lo hacemos readonly porque es el identificador -->
            <s:textfield name="email" label="Email" readonly="true" />
            <s:textfield name="nombre" label="Nombre" />
            <s:password name="password" label="Contrase�a" />
            <s:textfield name="telefono" label="Tel�fono" />
            <s:textfield name="direccion" label="Direcci�n" />
            <!-- Se selecciona que rol tendr�, USER o ADMIN -->
            <s:select name="rol"
                      label="Rol"
                      list="{'CIUDADANO','ADMIN'}"
                      listKey="top"
                      listValue="top" />

            <s:hidden name="formulario" value="editar" />
            <s:submit value="Guardar Cambios" />
        </s:form>

        <!-- Atr�s -->
        <a href="atrasatrasUsu.action" class="s-button">Volver al listado</a>

        <jsp:include page="/includes/footer.jsp" />

        <!-- A esta vista solo accede el admin -->

    </body>
</html>
