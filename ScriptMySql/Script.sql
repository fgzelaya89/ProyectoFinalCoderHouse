CREATE DATABASE IF NOT EXISTS coderhouse;
USE coderhouse;

select * from clientes c;
select * from detalle_pedidos dp;
select * from pedido_cliente pc;
select * from pedidos p;
select * from productos pd;

ALTER TABLE Pedidos ADD fecha_hora TIMESTAMP;