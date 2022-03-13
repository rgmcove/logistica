# logistica - prueba técnica 
Proyecto backend con spring boot 

#Enlaces de descarga MySQL y MYSQL WorkBench
https://dev.mysql.com/downloads/mysql/
https://dev.mysql.com/downloads/workbench/

#Documentación API
-	Para la documentación de la API se usó la herramienta Swagger que se puede revisar, al tener desplegado el proyecto de manera local se puede consultar así: http://localhost:8080/logistica-service/swagger-ui/index.html#/
-	En el swagger se pueden consultar los diferentes endpoints construidos y se puede probar su funcionalidad.
 
A todos los endpoints se les implemento seguridad, por lo cual para poderlos usar primero se debe generar el token con el endpoints /login
http://localhost:8080/logistica-service/login
 
Parcialmente se puede generar el token con cualquier valor en los parámetros username y password debido a que no se implemento registro de usuarios aún. 
 
En el swagger en el botón authorize se ingresa el token para habilitar el uso de los endpoints.

#Despliegue en servidores

Se implemento la base de datos MySQL en la herramienta Clever Cloud a la cual se puede acceder con los siguientes datos:
Host: bexuqazczajpyzggicwv-mysql.services.clever-cloud.com
Database Name: bexuqazczajpyzggicwv
User: u8dqcprei4mt3vlb
Password: h7tOheQEuqGGAFURWbGH
Port: 3306
Connection URI: mysql://u8dqcprei4mt3vlb:h7tOheQEuqGGAFURWbGH@bexuqazczajpyzggicwv-mysql.services.clever-cloud.com:3306/bexuqazczajpyzggicwv
MySQL CLI: mysql -h bexuqazczajpyzggicwv-mysql.services.clever-cloud.com -P 3306 -u u8dqcprei4mt3vlb -p bexuqazczajpyzggicwv

- En la rama server del repositorio se uso esta instancia de base de datos.
- En la rama develop del repositorio se uso instancia local de base de datos.
