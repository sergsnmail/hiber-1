DROP TABLE IF EXISTS product;
CREATE TABLE product (id INT NOT NULL AUTO_INCREMENT, title VARCHAR(128), cost DECIMAL(10,2), PRIMARY KEY (id));
INSERT INTO product VALUES
(1, "Яблоко", 1.5),
(2, "Апельсин", 2.5),
(3, "Тыква", 5.0),
(4, "Помидор", 4.0),
(5, "Огурец", 2.5),
(6, "Виноград", 15.8),
(7, "Мандарин", 6.9),
(8, "Киви", 1.2),
(9, "Баклажан", 10.5),
(10, "Арбуз", 100.1);