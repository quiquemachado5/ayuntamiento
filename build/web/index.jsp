<%-- 
    Document   : index
    Created on : 16-may-2025, 12:27:43
    Author     : emdominguez
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inicio - Ayuntamiento de Sevilla</title>
        <link rel="stylesheet" type="text/css" href="css/estilos.css" />
    </head>
    <body>
        <jsp:include page="/includes/cabecera.jsp" />

        <h1>Bienvenido al Ayuntamiento de Sevilla</h1>
        <div class="formularios-container">
            <div class="form-registro">
                <h2>Registrarse</h2>
                <s:form action="registroUsuario" method="post">
                    <s:textfield name="nombre" label="Nombre completo" />
                    <s:textfield name="email" label="Correo electrónico" />
                    <s:password name="password" label="Contraseña" />
                    <s:textfield name="telefono" label="Teléfono" />
                    <s:textfield name="direccion" label="Dirección" />
                    <s:select name="rol"
                              label="Rol"
                              list="{'CIUDADANO','ADMIN'}"
                              listKey="top"
                              listValue="top" />

                    <s:hidden name="formulario" value="registro" />
                    <s:submit value="Registrarse" />
                </s:form>

                
            </div>

            <hr/>
            <div class="form-login">
                <h2>Iniciar Sesión</h2>
                <s:form action="loginUsuario" method="post">
                    <s:textfield name="emailLogin" label="Correo electrónico" />
                    <s:password name="passwordLogin" label="Contraseña" />

                    <s:hidden name="formulario" value="login" />
                    <s:submit value="Entrar" />
                </s:form>

                <s:if test="hasActionErrors()">
                    <div class="error-message">
                        <s:actionerror />
                    </div>
                </s:if>

                <s:if test="hasActionMessages()">
                    <div class="success-message">
                        <s:actionmessage />
                    </div>
                </s:if>
            </div>
        </div>


        <jsp:include page="/includes/footer.jsp" />


    </body>
</html>

