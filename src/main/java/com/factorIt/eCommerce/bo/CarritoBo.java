package com.factorIt.eCommerce.bo;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.factorIt.eCommerce.models.Carrito;
import com.factorIt.eCommerce.models.Producto;

public interface CarritoBo {
		public Collection<Carrito> getCarritosCompras(Integer dni);
		public Collection<Carrito> getCarritosCompras(Integer dni, LocalDate from, LocalDate to);
		public Collection<Carrito> getCarritosComprasOrder(Integer dni, Boolean asc, String collumn);
		public void createCarrito(Carrito carrito);
		public void deleteCarrito(Integer id);
		public Carrito getCarrito(Integer id);
		public void saveCarrito(Carrito carrito);
		public void addProducto(Integer id, Integer idProducto);
		public void deleteProducto(Integer id, Integer idProducto);
		public Collection<Producto> getCarritoListaProducto(Integer id);
		public double closeCarritoAndGetMontoFinal(Integer id);
	
}
