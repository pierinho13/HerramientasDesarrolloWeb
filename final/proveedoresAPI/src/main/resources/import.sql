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


