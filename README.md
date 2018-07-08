
# SPRING BOOT ANGULAR APP
Aplicación plantilla con componentes PrimeNG

## REQUERIMIENTOS
1) Tener instalado Java JDK 1.8 o superior
2) Tener instalado un IDE compatible con Maven o directamente instalar Maven
3) Crear una base datos postgresql 9.x vacía

## LEVANTAR AMBIENTE DESARROLLO
1) copiar plantilla src/main/resources/application.properties.plantilla a src/main/resources/application.properties. Luego configurar como corresponda al ambiente.
2) Lanzar comando: "FLYWAY: Clean"
3) Lanzar comando: "FLYWAY: Migrate"
4) Lanzar comando: "BACKEND: Run"
5) Lanzar comando: "FRONTEND: Instalar"
6) Lanzar comando: "FRONTEND: Compilar"
7) Lanzar comando: "FRONTEND: Run"
8) Abrir navegador en: "localhost:4000". Backend queda en "localhost:8080"

#### NOTA: estos comandos se guardan en el IDE (eclipse, intellij o netbeans) como comandos maven.

### Comandos MAVEN BACKEND:
* BACKEND: Run) spring-boot:run -Duser.language=es -Duser.region=CL -Dfile.encoding=UTF-8
* FLYWAY: Clean) flyway:clean -Dplugin.flyway.url=jdbc:postgresql://localhost:5432/springboot-angular -Dplugin.flyway.user=postgres -Dplugin.flyway.password=postgres
* FLYWAY: Migrate) flyway:migrate -Dplugin.flyway.url=jdbc:postgresql://localhost:5432/springboot-angular -Dplugin.flyway.user=postgres -Dplugin.flyway.password=postgres


### Comandos MAVEN FRONTEND:
* FRONTEND: Instalar) mvn frontend:install-node-and-yarn
* FRONTEND: Compilar) mvn frontend:yarn -Dfrontend.yarn.arguments="install"
* FRONTEND: Run) mvn frontend:yarn -Dfrontend.yarn.arguments="run start"
* FRONTEND: Deploy Dev) mvn frontend:yarn -Dfrontend.yarn.arguments="run build-dev" 



