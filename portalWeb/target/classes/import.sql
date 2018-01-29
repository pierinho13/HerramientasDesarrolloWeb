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

insert into producto values (1,5'Pantalon',200);
insert into producto values (2,7,'Camisa',500);
insert into producto values (3,2,'Televisor',600);
insert into producto values (4,5,'Libro Spring',600);