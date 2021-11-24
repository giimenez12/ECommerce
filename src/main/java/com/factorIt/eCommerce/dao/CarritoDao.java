package com.factorIt.eCommerce.dao;

import java.time.LocalDate;
import java.util.Collection;

import com.factorIt.eCommerce.models.Carrito;
import com.factorIt.eCommerce.models.Producto;

public interface CarritoDao {
	public Collection<Carrito> getCarritosCompras(Integer id_cliente);
	public Collection<Carrito> getCarritosCompras(Integer id_cliente, LocalDate from ,LocalDate to);
	public Collection<Carrito> getCarritosComprasOrder(Integer id_cliente, Boolean asc, String collumn);
	public void createCarrito(Carrito carrito);
	public void deleteCarrito(Integer id);
	public Carrito getCarrito(Integer id);
	public void saveCarrito(Carrito carrito);
	public Collection<Producto> getProductos(Integer id);
	
}
