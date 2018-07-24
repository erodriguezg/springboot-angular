FROM erodriguezg/oracle-jdk-alpine:8u181
ENV spring.profiles.active=QA
COPY target/springboot-angular.war /app/springboot-angular.war
EXPOSE 8080
CMD [ "java", "-jar", "/app/springboot-angular.war" ]
