INSERT INTO regiones(id,nombre) VALUES(1,'Sudamérica');
INSERT INTO regiones(id,nombre) VALUES(2,'Centroamérica');
INSERT INTO regiones(id,nombre) VALUES(3,'Norteamérica');
INSERT INTO regiones(id,nombre) VALUES(4,'Europa');
INSERT INTO regiones(id,nombre) VALUES(5,'Asia');
INSERT INTO regiones(id,nombre) VALUES(6,'Africa');
INSERT INTO regiones(id,nombre) VALUES(7,'Oceanía');
INSERT INTO regiones(id,nombre) VALUES(8,'Antártida');

INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (1,'Erick','González','erickgozan@gmail.com','2021-10-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (1,'Lucia','Muñoz','miluz24@gmail.com','2021-10-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (2,'John','Doe','john.doe@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (3,'Linus','Torvalds','linus.torvalds@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (3,'Jane','Herrera','jane.herrera@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (2,'Rasmus','Andrade','rasmus.andrade@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (2,'Richard','Perez','richard.perez@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (3,'Sergio','Vicente','sergiovicente@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (1,'Roberto','Palomo','roberto_palomo@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (1,'Teresa','Cuevas','teresa.cuevas@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (7,'Jazmin','Meza','jazmin.meza@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (7,'Arel','Alonso','arel_alonso@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (4,'Ana','Gonzalez','anagonzalez@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (4,'Leslie','Ochoa','lelie_ochoa@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (5,'Itzel','Fernand�z','itzel.fernandez@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (6,'consuelo','Elizalde','consuelo_elizalde@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (1,'Ivan','Campos','ivan.campos@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (4,'Anastacia','Cruz','anastaciacruz@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (8,'Ariadna','Andrade','ariadnaandrade@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (8,'Miguel','Cuevas','miguel.cuevas@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (3,'Eduardo','Río','eduardo_rios@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (4,'Gerardo','Mendéz','gerardo.mendez@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (5,'Sofia','Hidalgo','sofiahidalgo@gmail.com','2020-04-04');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES (6,'Andrea','Juarez','andreajuarez@gmail.com','2020-04-04');

/*Crear los usuarios y los roles*/
INSERT INTO usuarios(username,password,enabled,nombre,apellido,email) VALUES ('erick','$2a$10$B1kOEyd93Sqb9jZ61CeqAOXg6.PLyc5a5NKgViW.aJ5eGHfQuTZVe',1,"Erick","Gonzalez","erickgozan@tianguisweb.com");
INSERT INTO usuarios(username,password,enabled,nombre,apellido,email) VALUES ('admin','$2a$10$2xvVCnzmIR/j1kRh9Ekog.g3fhBhqF88DA1dVMiWNBzs/9hOeMta2',1,"Antonio","Flores","antony.flores@tianguisweb.com");

INSERT INTO roles(nombre) VALUES ('ROLE_USER');
INSERT INTO roles(nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles(usuario_id,role_id)VALUES(1,1);
INSERT INTO usuarios_roles(usuario_id,role_id)VALUES(2,1);
INSERT INTO usuarios_roles(usuario_id,role_id)VALUES(2,2);

/*tabla de productos*/
INSERT INTO productos(nombre,precio,create_at) VALUES ('Panasonic Pantalla LCD',25999,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('Sony Camara digital DSC-W3208',12398,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('Apple ipod shuffle',45999,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('Sony Notebook Z110',18999,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('Hewlett packard Multifuncional F2280',38475,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('Blanchi Bicicleta Aro 26',5999,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('Mica Comoda 5 cajones',3999,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES ('Samsung galaxy s20 plus',23999,NOW());

/*Creamos las facturas*/
INSERT INTO facturas(descripcion,observacion,cliente_id,create_at) VALUES ('Factura equipos de oficina',null,1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,1);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (2,1,4);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,5);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,7);

INSERT INTO facturas(descripcion,observacion,cliente_id,create_at) VALUES ('Factura bicileta','Alguna nota importante',1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (3,2,6);



