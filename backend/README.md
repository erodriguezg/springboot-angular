
# SPRING BOOT BACKEND - REST API

## REQUERIMIENTOS
1) Tener instalado Java JDK 1.8 o superior
2) Tener instalado un IDE compatible con Maven o directamente instalar Maven
3) Crear una base datos postgresql 9.x o superior, vacía

## LEVANTAR AMBIENTE DESARROLLO
1) Copiar src/main/resources/application.yml.template a src/main/resources/application.yml
2) Configurar src/main/resources/application.yml con la configuración de desarrollo 
3) (Opcional) Agregar variable de entorno en el IDE: spring.profiles.active=PROD (o como parametro -D en comando IDE)
5) Lanzar comando: "spring-boot:run -Duser.language=es -Duser.region=CL -Dfile.encoding=UTF-8"
9) Backend queda en "localhost:8080"
