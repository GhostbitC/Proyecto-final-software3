INSERT INTO CIUDAD VALUES (1,'Bogota');
INSERT INTO CIUDAD VALUES (2,'Cali');
INSERT INTO CIUDAD VALUES (3,'Calarcá');

INSERT INTO DIRECCION VALUES (1,'27a','24-31',3);

INSERT INTO USUARIO VALUES (1,'Quintero','1193409775','sebastianquinteroosorio2104@gmail.com','Botsorio','Sebastian','s123','04/01/2001',100.999,1);

INSERT INTO ADMINISTRADOR VALUES (2,'Piedrahita','1193421285','braianc.piedrahitar@uqvirtual.edu.co','Ghostbit','Braian','b123');

INSERT INTO CATEGORIA VALUES(1,'Equipo','Portatiles',2);

INSERT INTO PRODUCTO(id, nombre, descripcion, precio, unidades, estado,usuario_id)  VALUES (1,'Asus Gaming','Producto1',100.999,2,false,1);

INSERT INTO PRODUCTO(id, nombre, descripcion, precio, unidades, estado,usuario_id,administrador_id)  VALUES (2,'Asus vivoBook','Producto2',300.999,2,true,1,2);

INSERT INTO ESPECIFICACION VALUES(1,'Compacto',2);

INSERT INTO COMPROBANTE_PAGO(id,url) VALUES(1,'adkmadaldada');

INSERT INTO IMAGEN(id,url) VALUES(1,'xmdjfssds');

INSERT INTO COMPRA(id,medio_pago,estado,fecha_venta,usuario_id) VALUES (1,'Consignación',false,'2021/10/10',1);

INSERT INTO COMPRA(id,medio_pago,estado,fecha_venta,administrador_id,comprobante_pago_id,usuario_id) VALUES (2,'Consignación',true,'2022/05/05',2,1,1);

INSERT INTO COMENTARIO VALUES(1,4,'Gran producto','2022/05/04',2,1);

INSERT INTO DETALLE_COMPRA VALUES(1,300.999,2,1,2);