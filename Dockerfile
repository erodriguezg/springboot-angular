FROM sgrio/java-oracle:jdk_8
ADD target/springboot-angular.war /app/springboot-angular.war
ENTRYPOINT [ "java", "-jar", "/app/springboot-angular.war" ]
EXPOSE 8080
