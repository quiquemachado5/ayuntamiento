FROM tomcat:9.0

# Elimina las aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu archivo WAR al directorio de despliegue de Tomcat
COPY dist/ayuntamientoSevilla.war /usr/local/tomcat/webapps/ROOT.war

# Configura Tomcat para escuchar en el puerto proporcionado por Railway
CMD ["sh", "-c", "sed -i 's/8080/$PORT/' /usr/local/tomcat/conf/server.xml && catalina.sh run"]
