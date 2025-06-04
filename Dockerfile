FROM tomcat:9.0

# Copiamos el WAR y la plantilla
COPY dist/ayuntamientoSevilla.war /usr/local/tomcat/webapps/ROOT.war
COPY hibernate.cfg.template.xml /usr/local/tomcat/hibernate.cfg.template.xml

# Comando que hace 3 cosas:
# 1. Sustituye el puerto 8081 por el que indica Railway (por ejemplo, 41839)
# 2. Espera a que el WAR se descomprima (puedes ajustar si no hace falta)
# 3. Sustituye las variables de entorno en hibernate.cfg.template.xml y lo deja donde Hibernate lo espera
CMD sh -c "\
    sed -i 's/8081/${PORT}/' /usr/local/tomcat/conf/server.xml && \
    sleep 5 && \
    envsubst < /usr/local/tomcat/hibernate.cfg.template.xml > /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/hibernate.cfg.xml && \
    catalina.sh run"
