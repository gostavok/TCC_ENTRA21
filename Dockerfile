FROM tomcat:9.0.41-jdk11-openjdk-buster

ADD /Textilsoft/target/Textilsoft.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]