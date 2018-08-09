package com.tienda.online.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tienda.online.models.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {
	
	List<Producto> findByDescuento(Integer porcentaje);
	
	//Retorna los productos por categoria id
	List<Producto> findByCategoriaId(String categoria);
 
	List<Producto> findByCategoriaNombreAndPrecioGreaterThanAndDescuentoLessThan(String nombreCategoria, BigDecimal precio, Integer descuento);

	//Equivalente al metodo findByCategoriaNombreAndPrecioGreaterThanAndDescuentoLessThan
	@Query("select p from Producto p where p.categoria.nombre = ?1 and p.precio >= ?2 and p.descuento <= ?3")
	List<Producto> reporteProductos(String nombreCategoria, BigDecimal precio, Integer descuento );
	
	Producto findByNombre(String nombre);
}
