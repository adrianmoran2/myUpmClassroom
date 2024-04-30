create schema myupmclassroom;

use myupmclassroom;

create table `alumno` (
    num_matricula varchar(6) not null,
    dni varchar(9) unique not null,
    nombre varchar(45) not null,
    primer_apellido varchar(20) not null,
    segundo_apellido varchar(20) not null,
    correo varchar(70) unique not null,
    contrasenia varchar(50) not null,
    primary key (num_matricula)
);

create table `pdi` (
	codigo_trabajador varchar(20) not null,
	dni varchar(9) unique not null,
    nombre varchar(45) not null,
    primer_apellido varchar(20) not null,
    segundo_apellido varchar(20) not null,
    correo varchar(70) unique not null,
    contrasenia varchar(50) not null,
    categoria varchar (20),
    primary key (codigo_trabajador),
    constraint check (categoria in ('Ayudante', 'ProfesorAyudanteDoctor', 'ProfesorContratadoDoctor', 'Titular', 'Catedratico'))
);

create table `pas` (
	codigo_personal varchar(20) not null,
	dni varchar(9) unique not null,
    nombre varchar(45) not null,
    primer_apellido varchar(20) not null,
    segundo_apellido varchar(20) not null,
    correo varchar(70) unique not null,
    contrasenia varchar(50) not null,
    anio_incorporacion integer not null,
    primary key (codigo_personal)
);

create table `aula` (
	id_interno varchar(45) not null,
    nombre_centro varchar(60) not null,
    superficie float not null,
    aforo integer not null,
    tipo_aula varchar(20) not null,
    id_pas varchar(20) not null,
    primary key (id_interno),
    check (tipo_aula in ('Teoria', 'Laboratorio', 'Mixta')),
    foreign key (id_pas)
    references pas(codigo_personal)
);

create table `subscripciones_alumnos` (
	num_matricula varchar(6) not null,
    id_aula varchar(45) not null,
    primary key (num_matricula , id_aula),
    foreign key (num_matricula)
    references alumno(num_matricula),
    foreign key (id_aula)
    references aula(id_interno)
);

create table `subscripciones_pdi` (
	codigo_trabajador varchar(20) not null,
    id_aula varchar(45) not null,
    primary key (codigo_trabajador, id_aula),
    foreign key (codigo_trabajador)
    references pdi (codigo_trabajador),
    foreign key (id_aula)
    references aula (id_interno)
);

-- Inserts para los test unitarios. También sirven para probar la aplicación

insert into `alumno` values
	("ba1234", "35790213H", "Alumno", "Primero", "Segundo", "alumno@alumnos.upm.es","contrasenia");
    
insert into `pas` values
	("admin", "35490213L", "Admin", "Admin", "Admin", "admin@upm.es", "admin", 2014);
    
insert into `pdi` values
	("pdi", "35492213P", "PDI", "PDI" , "PDI", "pdi@upm.es", "pdi", "Catedratico");
    
insert into `aula` values
	("3201", "ETSISI UPM", 50.0, 20, "Mixta", "admin");