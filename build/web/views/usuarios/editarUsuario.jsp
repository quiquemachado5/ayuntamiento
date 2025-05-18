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
        <title>Editar Usuario</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>

        <%@ include file="/includes/cabecera.jsp" %>

        
        <h1>Editar Usuario</h1>

        <s:form action="editar" method="post">
            <!-- Email, lo hacemos readonly porque es el identificador -->
            <s:textfield name="email" label="Email" readonly="true" />
            <s:textfield name="nombre" label="Nombre" />
            <s:password name="password" label="Contraseña" />
            <s:textfield name="telefono" label="Teléfono" />
            <s:textfield name="direccion" label="Dirección" />
            <s:select name="rol"
                      label="Rol"
                      list="{'CIUDADANO','ADMIN'}"
                      listKey="top"
                      listValue="top" />

            <s:hidden name="formulario" value="editar" />
            <s:submit value="Guardar Cambios" />
        </s:form>

        <a href="operacionesUsuarios.jsp" class="s-button">Volver al listado</a>

        <jsp:include page="/includes/footer.jsp" />

    </body>
</html>
