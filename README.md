# Proxy

### Documentacion

#### Estructura del proyecto
![img.png](images/img.png)

#### Información previa

Antes de poder utilizar este proyecto debe tener instalado lo siguiente.

- IntelliJ Idea o ide de preferencia
- Maven
- Java JDK8
- SoapUI

#### Inicializar proyecto
1. Realizar la descarga del repositorio.
2. Compilar el proyecto para poder obtener generar los recursos necesarios de cxf
3. Agregar la siguiente variable de entorno PATH_CLIENT_PROPERTIES la cual debe contener la ruta de nuestro client.properties,
el cual debe tener la misma estructura de nuestro application-dev.properties
4. Previamente se deben tener configurados los llaves jks, las cuales vamos a agregar(ruta) a nuestro properties
5. Despues de tener lo anterior configurado en nuestro application.yml, ajustamos la ruta donde queremos generar nuestros logs.
6. Podemos iniciar el proyecto.(DecevalApplication.java)


