SELECT nombre, precio FROM producto;


SELECT nombre, precio * 1.1 AS precio_dolares FROM producto;


SELECT UPPER(nombre) AS nombre, precio FROM producto;


SELECT nombre, UPPER(SUBSTRING(nombre, 1, 2)) AS dos_primeros_caracteres
FROM fabricante;


SELECT DISTINCT f.codigo
FROM fabricante f
         JOIN producto p ON f.codigo = p.codigo_fabricante;


SELECT nombre FROM fabricante ORDER BY nombre DESC;


SELECT nombre, precio FROM producto
ORDER BY nombre ASC, precio DESC;


SELECT nombre FROM fabricante LIMIT 5;


SELECT nombre FROM fabricante
                       LIMIT 2 OFFSET 3;


SELECT nombre, precio FROM producto
ORDER BY precio ASC LIMIT 1;

SELECT nombre, precio FROM producto
ORDER BY precio DESC LIMIT 1;


SELECT nombre FROM producto
WHERE codigo_fabricante = 2;


SELECT nombre FROM producto
WHERE precio <= 120;


SELECT nombre FROM producto
WHERE precio >= 400;


SELECT nombre, precio FROM producto
WHERE precio BETWEEN 80 AND 300;


SELECT nombre, precio FROM producto
WHERE precio > 200 AND codigo_fabricante = 6;


SELECT nombre, codigo_fabricante FROM producto
WHERE codigo_fabricante IN (1, 3, 5);


SELECT nombre, (precio * 100) AS precio_céntimos FROM producto;


SELECT nombre FROM fabricante
WHERE nombre LIKE 'S%';


SELECT nombre FROM producto
WHERE nombre LIKE '%Portátil%';

SELECT nombre FROM producto
WHERE nombre LIKE '%Monitor%' AND precio < 215;


SELECT nombre, precio FROM producto
WHERE precio >= 180
ORDER BY precio DESC, nombre ASC;

SELECT p.nombre AS producto, p.precio, f.nombre AS fabricante
FROM producto p
         JOIN fabricante f ON p.codigo_fabricante = f.codigo
ORDER BY f.nombre;


SELECT p.nombre AS producto, p.precio, f.nombre AS fabricante
FROM producto p
         JOIN fabricante f ON p.codigo_fabricante = f.codigo
ORDER BY p.precio DESC LIMIT 1;


SELECT nombre, precio FROM producto
WHERE codigo_fabricante = (SELECT codigo FROM fabricante WHERE nombre = 'Crucial') AND precio > 200;


SELECT p.nombre AS producto, f.nombre AS fabricante
FROM producto p
         JOIN fabricante f ON p.codigo_fabricante = f.codigo
WHERE f.nombre IN ('Asus', 'Hewlett-Packard', 'Seagate');


SELECT p.nombre AS producto, p.precio, f.nombre AS fabricante
FROM producto p
         JOIN fabricante f ON p.codigo_fabricante = f.codigo
WHERE p.precio >= 180
ORDER BY p.precio DESC, p.nombre ASC;


SELECT f.nombre AS fabricante, p.nombre AS producto
FROM fabricante f
         LEFT JOIN producto p ON f.codigo = p.codigo_fabricante
ORDER BY f.nombre;


SELECT nombre FROM fabricante
WHERE codigo NOT IN (SELECT DISTINCT codigo_fabricante FROM producto);


SELECT COUNT(*) AS total_productos FROM producto;

SELECT COUNT(DISTINCT codigo_fabricante) AS fabricantes_con_productos
FROM producto;


SELECT AVG(precio) AS media_precio FROM producto;


SELECT MIN(precio) AS precio_mas_barato FROM producto;


SELECT SUM(precio) AS suma_precios FROM producto;


SELECT COUNT(*) AS productos_asus
FROM producto
WHERE codigo_fabricante = (SELECT codigo FROM fabricante WHERE nombre = 'Asus');


SELECT AVG(precio) AS media_precio_asus
FROM producto
WHERE codigo_fabricante = (SELECT codigo FROM fabricante WHERE nombre = 'Asus');

SELECT
    MAX(precio) AS precio_maximo,
    MIN(precio) AS precio_minimo,
    AVG(precio) AS precio_medio,
    COUNT(*) AS total_productos
FROM producto
WHERE codigo_fabricante = (SELECT codigo FROM fabricante WHERE nombre = 'Crucial');

SELECT f.nombre AS fabricante, COUNT(p.codigo) AS productos
FROM fabricante f
         LEFT JOIN producto p ON f.codigo = p.codigo_fabricante
GROUP BY f.nombre
ORDER BY productos DESC;


SELECT
    f.nombre AS fabricante,
    MAX(p.precio) AS precio_maximo,
    MIN(p.precio) AS precio_minimo,
    AVG(p.precio) AS precio_medio
FROM fabricante f
         LEFT JOIN producto p ON f.codigo = p.codigo_fabricante
GROUP BY f.nombre;


SELECT
    codigo_fabricante,
    MAX(precio) AS precio_maximo,
    MIN(precio) AS precio_minimo,
    AVG(precio) AS precio_medio,
    COUNT(*) AS total_productos
FROM producto
GROUP BY codigo_fabricante
HAVING AVG(precio) > 200;
