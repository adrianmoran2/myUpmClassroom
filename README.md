# MyUPMClassroom

Este repositorio contiene el código fuente de la práctica de Fundamentos de Ingeniería de Software.

Proyecto hecho en 2022

## Dependencias

Para realizar este proyecto hemos usado: <br>
<ul>
    <li>
        <a href="https://hibernate.org/orm/documentation/6.0/">
            Hibernate ORM
        </a>
    </li>
    <li>
        <a href="https://junit.org/junit4/index.html">
            JUnit 4
        </a>
    </li>
    <li>
        El archivo JAR de Moodle
    </li>
</ul>

## Como ejecutar el proyecto

El proyecto usa Hibernate para realizar operaciones sobre una base de datos.
El gestor de base de datos que hemos utilizado es <a href="https://www.mysql.com">MySQL</a>, por lo tanto
es muy recomendable utilizar <a href="https://dev.mysql.com/downloads/workbench/">MySQL Workbench</a>
o en su defecto MySQL Command Line Client. Los pasos previos para poder usar este proyecto son: 

<ol>
    <li>
    Se debe crear una base de datos con el nombre "myupmclassroom"
    </li><br>
    <li>
    El proyecto está configurado para ejecutarse en el puerto 3306, pero esto se puede
    cambiar en el fichero hibernate.cfg.xml
    </li><br>
    <li>
    El proyecto está configurado para conectarse a la base de datos
    usando el usuario "root" y la contraseña "password", pero de nuevo esto
    se puede editar en el fichero hibernate.cfg.xml
    </li><br>
    <li>
    Ejecutar el archivo tablas.sql, que contiene el código necesario para generar el esquema de la base de datos.
    </li><br>
    <li>
    Abrir un IDE para ejecutar el codigo y descargar las dependencias de Maven.
    </li>
</ol>

## Autores

- Líder de Requisitos: Zhou Dong Dong Sheng
- Líder de Análisis: Adrián Morán Casas
- Líder de Diseño: David Emanuel Almansa Arévalo
- Líder de Implementación: Sergio Lobariñas Ríos
- Líder de Pruebas: Juan Antonio Celaya Rodríguez
