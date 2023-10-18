INSERT INTO users (id, is_active, email, first_name, last_name, password)
VALUES
    (1, 1, 'admin@example.com', 'Admin', 'Adminov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151');


INSERT INTO `brands` (`id`, `name`)
VALUES
    (1, 'Toyota'),
    (2, 'Audi'),
    (3, 'Mercedes-benz'),
    (4, 'Ford'),
    (5, 'Volvo'),
    (6, 'Yamaha');


INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES
    (1, 'CAR', 1, 'Camry'),
    (2, 'CAR', 1, 'Corolla'),
    (3, 'CAR', 4, 'Focus'),
    (4, 'CAR', 4, 'Fiesta'),
    (5, 'CAR', 2, 'A6'),
    (6, 'CAR', 2, 'A6 allroad'),
    (7, 'CAR', 2, 'RS 7'),
    (8, 'CAR', 3, 'Cl-class'),
    (9, 'CAR', 4, 'S-class'),
    (10, 'TRUCK', 5, 'S960'),
    (11, 'CAR', 5, 'V90s'),
    (12, 'MOTORCYCLE', 6, 'Hiabusa');
