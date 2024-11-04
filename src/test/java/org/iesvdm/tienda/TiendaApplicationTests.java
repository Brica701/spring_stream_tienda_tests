package org.iesvdm.tienda;

import org.apache.logging.log4j.util.PropertySource;
import org.iesvdm.tienda.modelo.Fabricante;
import org.iesvdm.tienda.modelo.Producto;
import org.iesvdm.tienda.repository.FabricanteRepository;
import org.iesvdm.tienda.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@SpringBootTest
class TiendaApplicationTests {

	@Autowired
	FabricanteRepository fabRepo;
	
	@Autowired
	ProductoRepository prodRepo;

	@Test
	void testAllFabricante() {
		var listFabs = fabRepo.findAll();
		
		listFabs.forEach(f -> {
			System.out.println(">>"+f+ ":");
			f.getProductos().forEach(System.out::println);
		});
	}
	
	@Test
	void testAllProducto() {
		var listProds = prodRepo.findAll();

		listProds.forEach( p -> {
			System.out.println(">>"+p+":"+"\nProductos mismo fabricante "+ p.getFabricante());
			p.getFabricante().getProductos().forEach(pF -> System.out.println(">>>>"+pF));
		});
				
	}

	
	/**
	 * 1. Lista los nombres y los precios de todos los productos de la tabla producto
	 */
	@Test
	void test1() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream().map(p -> "nombre = " + p.getNombre() + " , precio = " + p.getPrecio());
	}
	
	
	/**
	 * 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares .
	 */
	@Test
	void test2() {
		var listProds = prodRepo.findAll();
		//TODO
		final double tasaConversion = 1.1;
		listProds.stream()
				.forEach(producto -> {
					double precioDolares = producto.getPrecio() * tasaConversion;
					System.out.println("Nombre: " + producto.getNombre() + ", Precio en Dólares: " + precioDolares);
				});
	}
	
	/**
	 * 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
	 */
	@Test
	void test3() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream().map(prod -> String.format("Nombre: %s, Precio: %.2f", prod.getNombre().toUpperCase(), prod.getPrecio())).forEach(System.out::println);

	}
	
	/**
	 * 4. Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.
	 */
	@Test
	void test4() {
		var listFabs = fabRepo.findAll();
		//TODO
		listFabs.stream().map(fab -> String.format("Nombre: %s, Dos carácteres en mayúsculas: %s", fab.getNombre(), fab.getNombre().substring(0, 2).toUpperCase())).forEach(System.out::println);
	}
	
	/**
	 * 5. Lista el código de los fabricantes que tienen productos.
	 */
	@Test
	void test5() {
		var listFabs = fabRepo.findAll();
		//TODO
		listFabs.stream().map(Fabricante::getNombre).forEach(System.out::println);
	}
	
	/**
	 * 6. Lista los nombres de los fabricantes ordenados de forma descendente.
	 */
	@Test
	void test6() {
		var listFabs = fabRepo.findAll();
		//TODO
		listFabs.stream().map(Fabricante::getNombre).sorted(Comparator.reverseOrder()).forEach(System.out::println);
	}
	
	/**
	 * 7. Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
	 */
	@Test
	void test7() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.map(Producto::getNombre)
				.sorted(Comparator.reverseOrder())
				.forEach(System.out::println);
		listProds.stream()
				.map(Producto::getNombre)
				.sorted(Comparator.reverseOrder())
				.forEach(System.out::println);

	}
	
	/**
	 * 8. Devuelve una lista con los 5 primeros fabricantes.
	 */
	@Test
	void test8() {
		var listFabs = fabRepo.findAll();
		//TODO
		listFabs.stream()
				.map(Fabricante::getNombre)
				.limit(5) // Limitar a los primeros 5 fabricantes
				.forEach(System.out::println);
	}
	
	/**
	 * 9.Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
	 */
	@Test
	void test9() {
		var listFabs = fabRepo.findAll();
		//TODO
		var resultFabs = listFabs.stream()
				.skip(3)
				.limit(2)
				.toList();
		resultFabs.stream()
				.map(Fabricante::getNombre)
				.forEach(System.out::println);
	}
	
	/**
	 * 10. Lista el nombre y el precio del producto más barato
	 */
	@Test
	void test10() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.min((p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio())) // Encuentra el más barato
				.map(producto -> "Producto más barato: " + producto.getNombre() +
						", Precio: " + producto.getPrecio()) // Formatear salida
				.ifPresent(System.out::println); // Imprimir el resultado, si está presente

	}
	
	/**
	 * 11. Lista el nombre y el precio del producto más caro
	 */
	@Test
	void test11() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.max((p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio())) //Producto mas caro
				.map(producto -> "Producto más caro: " + producto.getNombre() +
						", Precio: " + producto.getPrecio())//Formatear la salida
						.ifPresent(System.out::println); //Damos el resultado
	}
	
	/**
	 * 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
	 * 
	 */
	@Test
	void test12() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.filter(producto -> producto.getFabricante().getCodigo() == 2) // Filtrar por código de fabricante
				.map(Producto::getNombre) // Obtener el nombre del producto
				.forEach(System.out::println); // Imprimir cada nombre


	}
	
	/**
	 * 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
	 */
	@Test
	void test13() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.filter(producto -> producto.getPrecio() <= 120) // Filtrar por precio
				.map(Producto::getNombre) // Obtener el nombre del producto
				.forEach(System.out::println); // Imprimir cada nombre
	}
	
	/**
	 * 14. Lista los productos que tienen un precio mayor o igual a 400€.
	 */
	@Test
	void test14() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.filter(producto -> producto.getPrecio() >= 400) //Filtrar por precio
				.map(Producto::getNombre) //Obtener el nombre del producto
				.forEach(System.out::println);//Imprimir resultadp
	}
	
	/**
	 * 15. Lista todos los productos que tengan un precio entre 80€ y 300€. 
	 */
	@Test
	void test15() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.filter(producto -> producto.getPrecio() >= 80 && producto.getPrecio() <= 300) // Filtrar por rango de precio
				.forEach(producto -> System.out.println(producto.getNombre() + " - Precio: " + producto.getPrecio())); // Imprimir nombre y precio
	}
	
	/**
	 * 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
	 */
	@Test
	void test16() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.filter(producto -> producto.getPrecio() >200 && producto.getFabricante().getCodigo() == 6)
				.forEach(producto -> System.out.println(producto.getNombre() + " - Precio: " + producto.getPrecio()));
	}
	
	/**
	 * 17. Lista todos los productos donde el código de fabricante sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
	 */
	@Test
	void test17() {
		var listProds = prodRepo.findAll();
		//TODO
		Set<Integer> fabricanteCodes = Set.of(1, 3, 5);

		listProds.stream()
				.filter(producto -> fabricanteCodes.contains(producto.getFabricante().getCodigo()))
				.forEach(producto -> System.out.println(producto.getNombre() + " - Fabricante: " + producto.getFabricante().getCodigo()));
	}
	
	/**
	 * 18. Lista el nombre y el precio de los productos en céntimos.
	 */
	@Test
	void test18() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.forEach(producto -> System.out.println(producto.getNombre() + " - Precio en céntimos: " + (int)(producto.getPrecio() * 100)));

	}
	
	
	/**
	 * 19. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S
	 */
	@Test
	void test19() {
		var listFabs = fabRepo.findAll();
		//TODO
		listFabs.stream()
				.filter(fabricante -> fabricante.getNombre().startsWith("S"))
				.forEach(fabricante -> System.out.println(fabricante.getNombre()));
	}
	
	/**
	 * 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
	 */
	@Test
	void test20() {
		var listProds = prodRepo.findAll();
		//TODO
		var portableProducts = listProds.stream()
				.filter(producto -> producto.getNombre().contains("Portátil"))
				.collect(Collectors.toList());

		portableProducts.forEach(producto -> System.out.println(producto.getNombre()));
	}
	
	/**
	 * 21. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
	 */
	@Test
	void test21() {
		var listProds = prodRepo.findAll();
		//TODO
		var monitorProducts = listProds.stream()
				.filter(producto -> producto.getNombre().contains("Monitor") && producto.getPrecio() < 215)
				.map(producto -> producto.getNombre())
				.collect(Collectors.toList());

		monitorProducts.forEach(System.out::println);
	}
	
	/**
	 * 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
	 */
	void test22() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.filter(producto -> producto.getPrecio() >= 180)
				.sorted(Comparator.comparingDouble(Producto::getPrecio).reversed()
						.thenComparing(Producto::getNombre))
				.forEach(producto -> System.out.println(producto.getNombre() + " - Precio: " + producto.getPrecio()));
	}
	
	/**
	 * 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos. 
	 * Ordene el resultado por el nombre del fabricante, por orden alfabético.
	 */
	@Test
	void test23() {
		var listProds = prodRepo.findAll();
		//TODO
		var productInfoList = listProds.stream()
				.sorted(Comparator.comparing(producto -> producto.getFabricante().getNombre()))
				.map(producto -> producto.getNombre() + " - Precio: " + producto.getPrecio() + "€ - Fabricante: " + producto.getFabricante().getNombre())
				.collect(Collectors.toList());

		productInfoList.forEach(System.out::println);
	}
	
	/**
	 * 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
	 */
	@Test
	void test24() {
		var listProds = prodRepo.findAll();
		//TODO
		listProds.stream()
				.max(Comparator.comparingDouble(Producto::getPrecio))
				.ifPresent(mostExpensiveProduct ->
						System.out.println("Producto más caro: " + mostExpensiveProduct.getNombre() +
								" - Precio: " + mostExpensiveProduct.getPrecio() + "€ - Fabricante: " +
								mostExpensiveProduct.getFabricante().getNombre()));


	}
	
	/**
	 * 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
	 */
	@Test
	void test25() {
		var listProds = prodRepo.findAll();
		//TODO
		var crucialProducts = listProds.stream()
				.filter(producto -> "Crucial".equals(producto.getFabricante().getNombre()) && producto.getPrecio() > 200)
				.collect(Collectors.toList());

		crucialProducts.forEach(producto ->
				System.out.println("Producto: " + producto.getNombre() + " - Precio: " + producto.getPrecio() + "€"));
	}
	
	/**
	 * 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate
	 */
	@Test
	void test26() {
		var listProds = prodRepo.findAll();
		//TODO
		Set<String> fabricantes = Set.of("Asus", "Hewlett-Packard", "Seagate");

		var selectedProducts = listProds.stream()
				.filter(producto -> fabricantes.contains(producto.getFabricante().getNombre()))
				.collect(Collectors.toList());

		selectedProducts.forEach(producto ->
				System.out.println("Producto: " + producto.getNombre() + " - Fabricante: " + producto.getFabricante().getNombre()));
	}
	
	/**
	 * 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
	 * El listado debe mostrarse en formato tabla. Para ello, procesa las longitudes máximas de los diferentes campos a presentar y compensa mediante la inclusión de espacios en blanco.
	 * La salida debe quedar tabulada como sigue:

Producto                Precio             Fabricante
-----------------------------------------------------
GeForce GTX 1080 Xtreme|611.5500000000001 |Crucial
Portátil Yoga 520      |452.79            |Lenovo
Portátil Ideapd 320    |359.64000000000004|Lenovo
Monitor 27 LED Full HD |199.25190000000003|Asus

	 */		
	@Test
	void test27() {
		var listProds = prodRepo.findAll();
		//TODO
		// Filter, sort and collect products
		var filteredProducts = listProds.stream()
				.filter(producto -> producto.getPrecio() >= 180)
				.sorted(Comparator.comparingDouble(Producto::getPrecio).reversed()
						.thenComparing(Producto::getNombre))
				.collect(Collectors.toList());

		// Determine the maximum lengths for each column
		int maxNameLength = filteredProducts.stream()
				.mapToInt(producto -> producto.getNombre().length())
				.max().orElse(0);

		int maxPriceLength = filteredProducts.stream()
				.mapToInt(producto -> String.valueOf(producto.getPrecio()).length())
				.max().orElse(0);

		int maxFabricanteLength = filteredProducts.stream()
				.mapToInt(producto -> producto.getFabricante().getNombre().length())
				.max().orElse(0);

		// Print the header
		System.out.printf("%-" + (maxNameLength + 2) + "s %" + (maxPriceLength + 2) + "s %-" + (maxFabricanteLength + 2) + "s%n",
				"Producto", "Precio", "Fabricante");
		System.out.println("-".repeat(maxNameLength + maxPriceLength + maxFabricanteLength + 6));

		// Print the products in a formatted manner
		for (var producto : filteredProducts) {
			System.out.printf("%-" + (maxNameLength + 2) + "s %" + (maxPriceLength + 2) + ".2f %-" + (maxFabricanteLength + 2) + "s%n",
					producto.getNombre(), producto.getPrecio(), producto.getFabricante().getNombre());
		}
	}
	
	/**
	 * 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos. 
	 * El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados. 
	 * SÓLO SE PUEDEN UTILIZAR STREAM, NO PUEDE HABER BUCLES
	 * La salida debe queda como sigue:
Fabricante: Asus

            	Productos:
            	Monitor 27 LED Full HD
            	Monitor 24 LED Full HD

Fabricante: Lenovo

            	Productos:
            	Portátil Ideapd 320
            	Portátil Yoga 520

Fabricante: Hewlett-Packard

            	Productos:
            	Impresora HP Deskjet 3720
            	Impresora HP Laserjet Pro M26nw

Fabricante: Samsung

            	Productos:
            	Disco SSD 1 TB

Fabricante: Seagate

            	Productos:
            	Disco duro SATA3 1TB

Fabricante: Crucial

            	Productos:
            	GeForce GTX 1080 Xtreme
            	Memoria RAM DDR4 8GB

Fabricante: Gigabyte

            	Productos:
            	GeForce GTX 1050Ti

Fabricante: Huawei

            	Productos:


Fabricante: Xiaomi

            	Productos:

	 */
	@Test
	void test28() {
		var listFabs = fabRepo.findAll();
		//TODO
		listFabs.stream()
				.forEach(fabricante -> {
					System.out.println("Fabricante: " + fabricante.getNombre());
					System.out.println("             Productos:");


					var productos = prodRepo.findAll().stream()
							.filter(producto -> producto.getFabricante().getNombre().equals(fabricante.getNombre()))
							.map(Producto::getNombre)
							.collect(Collectors.toList());


					if (productos.isEmpty()) {
						System.out.println("             ");
					} else {
						productos.forEach(producto -> System.out.println("             \t" + producto));
					}


					System.out.println();
				});
	}
	
	/**
	 * 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
	 */
	@Test
	void test29() {
		var listFabs = fabRepo.findAll();
		var listProds = prodRepo.findAll();
		//TODO
		listFabs.stream()
				.filter(fabricante -> listProds.stream()
						.noneMatch(producto -> producto.getFabricante().getNombre().equals(fabricante.getNombre())))
				.forEach(fabricante -> System.out.println("Fabricante sin productos: " + fabricante.getNombre()));
	}
	
	/**
	 * 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
	 */
	@Test
	void test30() {
		var listProds = prodRepo.findAll();
		//TODO
		long totalProducts = listProds.stream()
				.count();

		System.out.println("Número total de productos: " + totalProducts);
	}

	
	/**
	 * 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
	 */
	@Test
	void test31() {
		var listProds = prodRepo.findAll();
		//TODO
		long numberOfManufacturersWithProducts = listProds.stream()
				.map(producto -> producto.getFabricante().getNombre())
				.distinct()
				.count();

		System.out.println("Número de fabricantes con productos: " + numberOfManufacturersWithProducts);
	}
	
	/**
	 * 32. Calcula la media del precio de todos los productos
	 */
	@Test
	void test32() {
		var listProds = prodRepo.findAll();
		//TODO
		double averagePrice = listProds.stream()
				.mapToDouble(product -> product.getPrecio())
				.average()
				.orElse(0.0);
		System.out.println("Media del precio de todos los productos: " + averagePrice);
	}
	
	/**
	 * 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
	 */
	@Test
	void test33() {
		var listProds = prodRepo.findAll();
		//TODO
		double lowestPrice = listProds.stream()
				.mapToDouble(product -> product.getPrecio())
				.min()
				.orElse(0.0);
		System.out.println("Precio más barato de todos los productos: " + lowestPrice);
	}
	
	/**
	 * 34. Calcula la suma de los precios de todos los productos.
	 */
	@Test
	void test34() {
		var listProds = prodRepo.findAll();
		//TODO
		double totalSumOfPrices = listProds.stream()
				.mapToDouble(product -> product.getPrecio())
				.sum();
		System.out.println("Suma de los precios de todos los productos: " + totalSumOfPrices);

	}
	
	/**
	 * 35. Calcula el número de productos que tiene el fabricante Asus.
	 */
	@Test
	void test35() {
		var listProds = prodRepo.findAll();
		//TODO
		long asusProductCount = listProds.stream()
				.filter(product -> "Asus".equals(product.getFabricante().getNombre()))
				.count();
		System.out.println("Número de productos del fabricante Asus: " + asusProductCount);
	}
	
	/**
	 * 36. Calcula la media del precio de todos los productos del fabricante Asus.
	 */
	@Test
	void test36() {
		var listProds = prodRepo.findAll();
		//TODO
		double asusAveragePrice = listProds.stream()
				.filter(product -> "Asus".equals(product.getFabricante().getNombre()))
				.mapToDouble(product -> product.getPrecio())
				.average()
				.orElse(0.0);
		System.out.println("Media del precio de los productos del fabricante Asus: " + asusAveragePrice);
	}
	
	
	/**
	 * 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial. 
	 *  Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 */
	@Test
	void test37() {
		var listProds = prodRepo.findAll();
		//TODO

	}
		/**
		 * 38. Muestra el número total de productos que tiene cada uno de los fabricantes.
		 * El listado también debe incluir los fabricantes que no tienen ningún producto.
		 * El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene.
		 * Ordene el resultado descendentemente por el número de productos. Utiliza String.format para la alineación de los nombres y las cantidades.
		 * La salida debe queda como sigue:

		 Fabricante     #Productos
		 -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
		 Asus              2
		 Lenovo              2
		 Hewlett-Packard              2
		 Samsung              1
		 Seagate              1
		 Crucial              2
		 Gigabyte              1
		 Huawei              0
		 Xiaomi              0

		 */
		@Test
		void test38 () {
			var listFabs = fabRepo.findAll();
			//TODO

		}

		/**
		 * 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes.
		 * El resultado mostrará el nombre del fabricante junto con los datos que se solicitan. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
		 * Deben aparecer los fabricantes que no tienen productos.
		 */
		@Test
		void test39 () {
			var listFabs = fabRepo.findAll();
			//TODO
		}

		/**
		 * 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes que tienen un precio medio superior a 200€.
		 * No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
		 */
		@Test
		void test40 () {
			var listFabs = fabRepo.findAll();
			//TODO
		}

		/**
		 * 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
		 */
		@Test
		void test41 () {
			var listFabs = fabRepo.findAll();
			//TODO
		}

		/**
		 * 42. Devuelve un listado con los nombres de los fabricantes y el número de productos que tiene cada uno con un precio superior o igual a 220 €.
		 * Ordenado de mayor a menor número de productos.
		 */
		@Test
		void test42 () {
			var listFabs = fabRepo.findAll();
			//TODO
		}

		/**
		 * 43.Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
		 */
		@Test
		void test43 () {
			var listFabs = fabRepo.findAll();
			//TODO
		}

		/**
		 * 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
		 * Ordenado de menor a mayor por cuantía de precio de los productos.
		 */
		@Test
		void test44 () {
			var listFabs = fabRepo.findAll();
			//TODO
		}

		/**
		 * 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante.
		 * El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante.
		 * El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
		 */
		@Test
		void test45 () {
			var listFabs = fabRepo.findAll();
			//TODO
		}

		/**
		 * 46. Devuelve un listado de todos los productos que tienen un precio mayor o igual a la media de todos los productos de su mismo fabricante.
		 * Se ordenará por fabricante en orden alfabético ascendente y los productos de cada fabricante tendrán que estar ordenados por precio descendente.
		 */
		@Test
		void test46 () {
			var listFabs = fabRepo.findAll();
			//TODO
		}

	}
