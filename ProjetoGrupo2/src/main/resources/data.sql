-- Tabela usuario
INSERT INTO USERS(name, email, cpf, password) VALUES('Aluno', 'aluno@fatec.sp.gov.br', '50808689029', '$2a$10$.RIdlN0DUSoGwMbpguWaz.rRe15krkr5v4CPRST6sQGjwOeBr5tWy');

-- Tabela produtos
INSERT INTO PRODUCT(name, type, unit_price, quantity) VALUES('Coxinha', 'FRITO', 7.00, 10);
INSERT INTO PRODUCT(name, type, unit_price, quantity) VALUES('Mistao', 'ASSADO', 5.00, 8);
INSERT INTO PRODUCT(name, type, unit_price, quantity) VALUES('Coca-Cola', 'BEBIDA', 3.50, 20);
INSERT INTO PRODUCT(name, type, unit_price, quantity) VALUES('Lanche_Natural', 'NATURAL', 9.00, 5);
INSERT INTO PRODUCT(name, type, unit_price, quantity) VALUES('BomBom', 'DOCE', 2.00, 12);

-- Tabela Pedidos
INSERT INTO ORDERS(quantity, purchase_date, delivery_date) VALUES( 1, '2022-05-23 12:41:00', '2022-05-23 22:00:00');
INSERT INTO ORDERS(quantity, purchase_date, delivery_date) VALUES( 1, '2022-05-01 16:23:00', '2022-05-01 22:00:00');
INSERT INTO ORDERS(quantity, purchase_date, delivery_date) VALUES( 1, '2022-05-13 07:35:00', '2022-05-13 22:00:00');
INSERT INTO ORDERS(quantity, purchase_date, delivery_date) VALUES( 1, '2022-05-16 21:21:00', '2022-05-21 22:00:00');

-- Tabela promoção
INSERT INTO PROMOTION(description, promotion_price) VALUES('Cupom de salgado', 0.1);