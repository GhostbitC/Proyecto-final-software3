INSERT INTO ciudad VALUES (1,'Bogota');
INSERT INTO ciudad VALUES (2,'Cali');
INSERT INTO ciudad VALUES (3,'Calarcá');

INSERT INTO direccion VALUES (1,'27a','24-31',3);

INSERT INTO usuario VALUES (1,'Quintero','1193409775','sebastianquinteroosorio2104@gmail.com','Botsorio','Sebastian','s123','04/01/2001',100.999,1);

INSERT INTO administrador VALUES (2,'Piedrahita','1193421285','braianc.piedrahitar@uqvirtual.edu.co','Ghostbit','Braian','b123');

INSERT INTO categoria VALUES(1,'Equipo','Portatiles',2);

INSERT INTO producto(id, nombre, descripcion, precio, unidades, estado,usuario_id)  VALUES (1,'Asus Gaming','Producto1',100.999,2,false,1);

INSERT INTO producto(id, nombre, descripcion, precio, unidades, estado,usuario_id,administrador_id)  VALUES (2,'Asus vivoBook','Producto2',300.999,2,true,1,2);

INSERT INTO especificacion VALUES(1,'Compacto',2);

INSERT INTO comprobante_pago(id,url) VALUES(1,'adkmadaldada');

INSERT INTO imagen(id,url) VALUES(1,'xmdjfssds');

INSERT INTO compra(id,medio_pago,estado,fecha_venta,usuario_id) VALUES (1,'Consignación',false,'2021/10/10',1);

INSERT INTO compra(id,medio_pago,estado,fecha_venta,administrador_id,comprobante_pago_id,usuario_id) VALUES (2,'Consignación',true,'2022/05/05',2,1,1);

INSERT INTO comentario VALUES(1,4,'Gran producto','2022/05/04',2,1);

INSERT INTO detalle_compra VALUES(1,300.999,2,1,2);