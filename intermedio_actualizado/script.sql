--SCRIPT TIENDA VIRTUAL ALUMNO PIERO ROSPIGLIOSI----

--ejecutar primero esta por separado---
CREATE USER piero WITH PASSWORD 'piero';

--y luego esta---
CREATE DATABASE "tiendaPiero"
  WITH OWNER = piero
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Spain.1252'
       LC_CTYPE = 'Spanish_Spain.1252'
       CONNECTION LIMIT = -1;

---LOS CREATE E INSERT SON INNCESARIOS SI SE MODIFICA ARCHIVO DS.PROPERTIES EN RESOURCES DEL PROYECTO
--create tables e inserts---

create sequence Persona_sequence start 10 increment 1;
GRANT ALL PRIVILEGES ON Persona_sequence TO piero;
create sequence Role_sequence start 10 increment 1;
GRANT ALL PRIVILEGES ON Role_sequence TO piero;
create sequence Usuario_sequence start 10 increment 1;
GRANT ALL PRIVILEGES ON Usuario_sequence TO piero;
create sequence Producto_sequence start 10 increment 1;
GRANT ALL PRIVILEGES ON Producto_sequence TO piero;

 create table Persona (
        id int8 not null,
        codigo varchar(255),
        nombre varchar(255),
        primary key (id)
    );
GRANT ALL PRIVILEGES ON Persona TO piero;
create table Producto (
        id int8 not null,
        cantidad int4,
        codigo varchar(255),
        nombre varchar(255),
        precio numeric(19, 2),
        primary key (id)
    );
GRANT ALL PRIVILEGES ON Producto TO piero;

create table Rol (
        id int8 not null,
        esAdmin boolean,
        name varchar(255) not null,
        orden int4,
        primary key (id)
   );
GRANT ALL PRIVILEGES ON Rol TO piero;

create table Usuario (
        id int8 not null,
        password varchar(255),
        username varchar(255),
        persona_id int8,
        primary key (id)
    );

GRANT ALL PRIVILEGES ON Usuario TO piero;

create table Usuario_Rol (
        Usuario_id int8 not null,
        roles_id int8 not null,
        primary key (Usuario_id, roles_id)
    );
GRANT ALL PRIVILEGES ON Usuario_Rol TO piero;

alter table Persona add constraint UK3i0987o5yqupe8ec65r7in1in unique (codigo);

alter table Producto add constraint UKteoilsm9esf52bg3tkga5v3ok unique (codigo);

alter table Rol add constraint UK_n7sgxky8sue90xhs9ov4kciv0 unique (name);

create index IDXfetcftrp41j1u46pan86ogs1c on Usuario (persona_id);

create index IDX471i15k6vbj1lfsfb19getcdi on Usuario (username);

alter table Usuario add constraint UK471i15k6vbj1lfsfb19getcdi unique (username);

alter table Usuario add constraint FKpwmov35tuwavb70fabk8iusg foreign key (persona_id) references Persona;
 
alter table Usuario_Rol add constraint FKlalkjgcshuaskjgqv07bfkx8h  foreign key (roles_id)  references Rol;
alter table Usuario_Rol  add constraint FKfsir46g83sqwviryyejqo8b35  foreign key (Usuario_id) references Usuario;



--roles--
insert into rol  values (1,true,'ADMIN',1);
insert into rol  values (2,false,'CLIENTE',1);

--persona---
insert into persona values(1,'PR','piero');
insert into persona values(2,'DB','dario');

--usuarios--
insert into usuario values(1,'1234','admin',1);
insert into usuario values(2,'1234','cliente',2);

--usuario role---

insert into usuario_rol values (1,1);
insert into usuario_rol values (2,2);


---productos----

insert into producto values (1,5,'AAA','Pantalon',200);
insert into producto values (2,7,'BBB','Camisa',500);
insert into producto values (3,2,'CCC','Televisor',600);
insert into producto values (4,5,'DDD','Libro Spring',600);


