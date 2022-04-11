-- Tabela usuario
INSERT INTO USER(name, email, cpf, password) VALUES('Aluno', 'aluno@email.com', '49556578805', '$2a$10$.RIdlN0DUSoGwMbpguWaz.rRe15krkr5v4CPRST6sQGjwOeBr5tWy');

-- Tabela produtos
INSERT INTO PRODUCT(name, type, unit_price, quantity) VALUES('Coxinha', 'Frito', 7.00, 10);

-- Tabela Pedidos
INSERT INTO ORDER(quantity, purchase_date, delivery_date) VALUES( 1, '2022-05-21 12:41:00', '2022-05-21 12:47:00');