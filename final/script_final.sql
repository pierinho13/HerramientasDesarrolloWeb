--create bbdd---
CREATE DATABASE "apiPiero"
  WITH OWNER = piero
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Spain.1252'
       LC_CTYPE = 'Spanish_Spain.1252'
       CONNECTION LIMIT = -1;


----CREATE AND INSERTS--------



---sequences

create sequence Personaapi_sequence start 20 increment 20;
GRANT ALL PRIVILEGES ON Personaapi_sequence TO piero;
create sequence Productoapi_sequence start 20 increment 20;
GRANT ALL PRIVILEGES ON Productoapi_sequence TO piero;
create sequence Proveedor_sequence start 20 increment 20;
GRANT ALL PRIVILEGES ON Proveedor_sequence TO piero;
create sequence Roleapi_sequence start 20 increment 3;
GRANT ALL PRIVILEGES ON Roleapi_sequence TO piero;
create sequence Usuarioapi_sequence start 20 increment 1;
GRANT ALL PRIVILEGES ON Usuarioapi_sequence  TO piero;


-----tables------


    create table PersonaApi (
        id int8 not null,
        codigo varchar(255),
        nombre varchar(255),
        primary key (id)
    );
    GRANT ALL PRIVILEGES ON PersonaApi TO piero;

    create table ProductoProveedor (
        id int8 not null,
        cantidad int4,
        codigo varchar(255),
        fechaEntrada timestamp,
        nombre varchar(255),
        precio numeric(19, 2),
        primary key (id)
    );
    GRANT ALL PRIVILEGES ON ProductoProveedor TO piero;

    create table Proveedor (
        id int8 not null,
        cif varchar(255),
        direccion varchar(255),
        nombre varchar(255),
        telefono varchar(255),
        primary key (id)
    );
    GRANT ALL PRIVILEGES ON Proveedor TO piero;

    create table Proveedor_ProductoProveedor (
        Proveedor_id int8 not null,
        productos_id int8 not null,
        primary key (Proveedor_id, productos_id)
    );
GRANT ALL PRIVILEGES ON Proveedor_ProductoProveedor TO piero;

    create table RolApi (
        id int8 not null,
        esAdmin boolean,
        name varchar(255) not null,
        orden int4,
        primary key (id)
    );
GRANT ALL PRIVILEGES ON RolApi TO piero;

    create table UsuarioApi (
        id int8 not null,
        password varchar(255),
        username varchar(255),
        personaApi_id int8,
        primary key (id)
    );

GRANT ALL PRIVILEGES ON UsuarioApi  TO piero;

    create table UsuarioApi_RolApi (
        UsuarioApi_id int8 not null,
        roles_id int8 not null,
        primary key (UsuarioApi_id, roles_id)
    );

GRANT ALL PRIVILEGES ON UsuarioApi_RolApi TO piero;

    alter table PersonaApi 
        add constraint UK810yj7hhb6l9flt1l68rn6lec unique (codigo);

    alter table ProductoProveedor 
        add constraint UKatb6ca58cc3yfckbsf8p1wa8r unique (codigo);

    alter table Proveedor 
        add constraint UKfdcfr8lhev1pm2pkjncanejrh unique (cif);

    alter table RolApi 
        add constraint UK_a20tkgvyippv16e09w14v6bmv unique (name);

    create index IDX6fass7pwifilaueubls1ct7l0 on UsuarioApi (personaApi_id);

    create index IDXqucus1epll3qdv0dnfhgwvca3 on UsuarioApi (username);

    alter table UsuarioApi 
        add constraint UKqucus1epll3qdv0dnfhgwvca3 unique (username);

    alter table Proveedor_ProductoProveedor 
        add constraint FKaekvu6kufk53w4rq0vdsc59dn 
        foreign key (productos_id) 
        references ProductoProveedor;

    alter table Proveedor_ProductoProveedor 
        add constraint FKjgsaxv33tdl2a1ymepa4ki40p 
        foreign key (Proveedor_id) 
        references Proveedor;

    alter table UsuarioApi 
        add constraint FKie0uc2uv1kdeur7xidvtc7i4n 
        foreign key (personaApi_id) 
        references PersonaApi;

    alter table UsuarioApi_RolApi 
        add constraint FKlkyqw03k5yycu4tr1lk16g51u 
        foreign key (roles_id) 
        references RolApi;

    alter table UsuarioApi_RolApi 
        add constraint FKak632d27amfq6500xq9r5x2ay 
        foreign key (UsuarioApi_id) 
        references UsuarioApi;

-----inserts----



--roles--
insert into rolapi  values (1,true,'ADMIN',1);
insert into rolapi  values (2,false,'CLIENTE',1);

--persona---
insert into personaapi values(1,'PR','piero');
insert into personaapi values(2,'DB','dario');

--usuarios--
insert into usuarioapi values(1,'1234','admin',1);
insert into usuarioapi values(2,'1234','cliente',2);

--usuario role---

insert into usuarioapi_rolapi values (1,1);
insert into usuarioapi_rolapi values (2,2);

---proveedores----

insert into proveedor values (1,'AAABBB','direccion','uah proveedor',608683606);
insert into proveedor values (2,'BBBCCC','guadalajra','profesor uah',654552564);

---productos-----

insert into productoproveedor values (1,10,'prod1','2018-03-14','Pantalon',600);
insert into productoproveedor values (2,5,'prod2','2018-02-16','Camisa',600);
insert into productoproveedor values (3,4,'prod3','2018-04-18','Televisor',600);
insert into productoproveedor values (4,3,'prod4','2018-02-14','Libro Spring',600);
insert into productoproveedor values (5,7,'prod5','2018-02-14','Nuevo Producto',20);


----proveedor_productoproveedor-------------

insert into proveedor_productoproveedor values (1,1);
insert into proveedor_productoproveedor values (1,2);
insert into proveedor_productoproveedor values (1,3);
insert into proveedor_productoproveedor values (2,4);
insert into proveedor_productoproveedor values (2,5);
