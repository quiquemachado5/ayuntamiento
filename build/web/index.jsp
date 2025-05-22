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
        
         <!-- Mensajes de acción (éxito) -->
        <s:actionmessage cssClass="mensaje-exito" />

        <!-- Mensajes de error generales -->
        <s:actionerror cssClass="mensaje-error" />

        <!-- Mensajes de error por campo (del validate) -->
        <s:fielderror cssClass="mensaje-error-campo" />


        <s:if test="#session.usuario == null">
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
                    <s:form action="olvidar" method="post">
                        <s:submit value="He olvidado mi contraseña" />
                    </s:form>


                </div>
            </div>
        </s:if>
        <s:else>
            <h2>Ya tiene una sesión activa </h2>
            <a href="home.action" class="s-button">Menú Principal</a>
        </s:else>

        <jsp:include page="/includes/footer.jsp" />


    </body>
</html>

