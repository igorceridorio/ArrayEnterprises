-- ERP Array Enterprises - População inicial para o banco de dados "ERPArrayEnterprises"

-- Arquivo: populacaoTabelas.sql


-- ramo 1 = alimenticio
-- ramo 2 = medicamentos
INSERT INTO Produto VALUES
	(1, 'Dipirona', 2.50, 2, 10),
	(2, 'Mertiolate', 5.00, 2, 10),
	(3, 'Farinha de trigo', 3.00, 1, 10),
	(4, 'Arroz Integral Tipo I', 7.50, 1, 10),
	(5, 'Arroz Integral Tipo II', 10.50, 1, 10),
	(6, 'Açucar Cristal', 4.00, 1, 10),
	(7, 'Milho Enlatado', 2.00, 1, 10),
	(8, 'Feijão Carioca', 6.50, 1, 10),
	(9, 'Feijão Preto', 7.00, 1, 10),
	(10, 'Dorflex', 3.50, 2, 10),
	(11, 'Pão de Hamburger', 2.00, 1, 10),
	(12, 'Hamburger', 3.50, 1, 10),
	(13, 'Queijo Cheddar', 8.50, 1, 10);

INSERT INTO Cliente VALUES
	(1, 'Supermercado Nagumo', 'Alimentício', 'Supermercado'),
	(2, 'Drogaria São João', 'Medicamentos', 'Rede de Farmácias'),
	(3, 'Burger King', 'Alimentício', 'Fast-food'),
	(4, 'Atacadão da Economia', 'Alimentício', 'Atacadista'),
	(5, 'Linus Pauling Inc.', 'Medicamentos', 'Distribuidora'),
	(6, 'Treviso', 'Alimentício', 'Hipermercado'),
	(7, 'Subway', 'Alimentício', 'Fast-food'),
	(8, 'Drograria São-Paulo', 'Medicamentos', 'Rede de Farmácias'),
	(9, 'Dia', 'Alimentício', 'Supermercado'),
	(10, 'Neo Farma', 'Medicamentos', 'Distribuidora');

INSERT INTO Lote VALUES
	(1, 2, '2014-02-12', '2016-02-12', 5000, 5000),
	(2, 4, '2014-08-30', '2016-08-15', 3500, 2800),
	(3, 6, '2013-10-20', '2014-10-20', 8000, 3400),
	(4, 8, '2013-11-30', '2014-11-30', 4700, 1550),
	(5, 10, '2012-01-10', '2015-01-10', 9000, 4600),
	(6, 1, '2011-05-15', '2014-11-15', 15000, 5000),
	(7, 3,'2014-02-20', '2014-12-20', 450, 120),
	(8, 5, '2014-08-14', '2016-08-14', 4500, 4500),
	(9, 7, '2012-11-19', '2014-11-24', 7850, 850),
	(10, 9, '2014-05-20', '2016-05-20', 4600, 3580),
	(11, 4, '2013-07-19', '2015-06-28', 1500, 734),
	(12, 8, '2011-06-29', '2015-02-28', 10000, 3500),
	(13, 3, '2014-08-14', '2014-12-29', 5400, 2185),
	(14, 5, '2013-02-28', '2015-02-28', 2800, 800),
	(15, 10, '2012-05-14', '2016-05-14', 15000, 4890);

INSERT INTO Usuario VALUES
        ('jabuti', '123456');

INSERT INTO Venda VALUES
(1,3, 2559.00),
(2,4, 402.00);

INSERT INTO Produtos_venda VALUES
(1,3,400),
(1,4,70),
(1,5,60),
(2, 4, 60);