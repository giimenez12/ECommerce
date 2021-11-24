package com.factorIt.eCommerce.bo.impl;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factorIt.eCommerce.CompraService;
import com.factorIt.eCommerce.bo.CarritoBo;
import com.factorIt.eCommerce.bo.ClienteBo;
import com.factorIt.eCommerce.bo.ProductoBo;
import com.factorIt.eCommerce.dao.CarritoDao;
import com.factorIt.eCommerce.models.Carrito;
import com.factorIt.eCommerce.models.Cliente;
import com.factorIt.eCommerce.models.Compra;
import com.factorIt.eCommerce.models.Producto;
@Service
public class CarritoBoImpl implements CarritoBo {
	
	@Autowired
	private CompraService compraService;
	@Autowired
	private ClienteBo clienteBo;
	@Autowired
	private ProductoBo productoBo;
	@Autowired
	private CarritoDao carritoDao;
	@Override
	public Collection<Carrito> getCarritosCompras(Integer dni) {
		Cliente cliente = clienteBo.getCliente(dni);
		return carritoDao.getCarritosCompras(cliente.getId());
	}
	@Override
	public Collection<Carrito> getCarritosCompras(Integer dni, LocalDate from, LocalDate to) {
		Cliente cliente = clienteBo.getCliente(dni);
		return carritoDao.getCarritosCompras(cliente.getId(), from, to);
	}
	@Override
	public Collection<Carrito> getCarritosComprasOrder(Integer dni, Boolean asc, String collumn) {
		Cliente cliente = clienteBo.getCliente(dni);
		return carritoDao.getCarritosComprasOrder(cliente.getId(), asc, collumn);
	}
	@Override
	public void createCarrito(Carrito carrito) {
		carritoDao.createCarrito(carrito);
	}
	@Override
	public void deleteCarrito(Integer id) {
		carritoDao.deleteCarrito(id);
		
	}

	@Override
	public Carrito getCarrito(Integer id) {
		return carritoDao.getCarrito(id);

	}
	@Override
	public void saveCarrito(Carrito carrito) {
		carritoDao.saveCarrito(carrito);
	}
	@Override
	public void addProducto(Integer id, Integer idProducto) {
		Carrito carrito = this.getCarrito(id);
		Producto producto = productoBo.getProducto(idProducto);
		carrito.agregarProducto(producto);
		this.saveCarrito(carrito);
		
	}
	@Override
	public void deleteProducto(Integer id, Integer idProducto) {
		Carrito carrito = this.getCarrito(id);
		Producto producto = productoBo.getProducto(idProducto);
		carrito.deleteProducto(producto);
		this.saveCarrito(carrito);
		
	}
	@Override
	public Collection<Producto> getCarritoListaProducto(Integer id) {
		return carritoDao.getProductos(id);
	}

	@Override
	public double closeCarritoAndGetMontoFinal(Integer id) {
		Carrito carrito = this.getCarrito(id);
		carrito.setCerrado(Boolean.TRUE);
		Compra compra = new Compra();
		carrito.setCompra(compra);
		carrito.getCompra().setPrecioFinal(carrito.getPrecioFinal(isClienteVip(carrito)));
		this.carritoDao.saveCarrito(carrito);
		return carrito.getCompra().getPrecioFinal();
	}
	public Boolean isClienteVip(Carrito carrito) {
		return this.compraService.compraService(carrito.getCliente().getDni()).stream()
				.filter(c -> c.getFecha().getMonth().getValue() == carrito.getCompra().getFecha().getMonth().getValue())
				.mapToDouble(c -> c.getPrecioFinal())
				.sum() > 5000;//considerando que dice compras en un mes(total del monto de compras en dic
	}
	
}
