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
comando ```maven clean install```
   ![img.png](images/mavenCompile.jpg)
3. Agregar la siguiente variable de entorno PATH_CLIENT_PROPERTIES la cual debe contener la ruta de nuestro client.properties,
el cual debe tener la misma estructura de nuestro application-dev.properties
   - Esta configuracion es para intellij para desplegar el aplicativo debera crear esta variable de entorno de forma local en su 
   sistema operativo de preferencia
      ![img.png](images/addpath.jpg)
4. Previamente se deben tener configurados los llaves jks, las cuales vamos a agregar(ruta) a nuestro properties
   - Ajustar las rutas y nombres de sus jks generados ssl y wssecurity 
   ![img.png](images/properties.png)
5. Despues de tener lo anterior configurado en nuestro application.yml, ajustamos la ruta donde queremos generar nuestros logs.
   - Ajustar ruta de preferencia donde desea guardar los logs del aplicativo
      ![img.png](images/logsconfig.jpg)
6. Podemos iniciar el proyecto.(DecevalApplication.java)


