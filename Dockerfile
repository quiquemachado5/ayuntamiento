FROM tomcat:9.0

COPY dist/ayuntamientoSevilla.war /usr/local/tomcat/webapps/ROOT.war

CMD sh -c "sed -i 's/8080/${PORT}/' /usr/local/tomcat/conf/server.xml && catalina.sh run"
