package com.factorIt.eCommerce.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factorIt.eCommerce.bo.CarritoBo;
import com.factorIt.eCommerce.bo.ClienteBo;
import com.factorIt.eCommerce.models.Carrito;
import com.factorIt.eCommerce.models.Producto;

@RestController
public class CarritoController {
	@Autowired
	private CarritoBo carritoBo;
	
	@Autowired
	private ClienteBo clienteBo;
	
	
	private static final String pattern = "yyyy-MM-dd";
	
	
	@GetMapping({"carrito/{id}"})
	public Collection<Producto> getCarritoListaProducto(@PathVariable Integer id) {
		return this.carritoBo.getCarritoListaProducto(id);
	}
	
	@GetMapping({"carrito/close/{id}"})
	public double closeCarritoAndGetMontoFinal(@PathVariable Integer id) {
		return this.carritoBo.closeCarritoAndGetMontoFinal(id);
	}
	
	/*el cliente podria obtenerse con la informacion de la sesion con el usuario ya logueado
	pero para que funcione en este caso lo pase por parametro*/
	@RequestMapping(value = "carrito/{dni}",method = RequestMethod.POST)
	public void createCarrito(@PathVariable Integer dni , @RequestParam(value = "isSpecial", required = true) boolean isSpecial, @RequestParam (value = "fecha" ,required = true) @DateTimeFormat(pattern = pattern) String fecha ) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate fechaCreacionDate = LocalDate.parse(fecha, formatter);
		carritoBo.createCarrito(new Carrito(clienteBo.getCliente(dni), fechaCreacionDate, isSpecial));
	}
	
	@RequestMapping(value = "carrito/{id}/agregarProducto/{idProducto}",method = RequestMethod.POST)
	public void AgregarProductoCarrito(@PathVariable Integer id ,@PathVariable Integer idProducto) {
		carritoBo.addProducto(id,idProducto);
	}
	
	@RequestMapping(value = "carrito/{id}/agregarProducto/{idProducto}",method = RequestMethod.DELETE)
	public void EliminarProductoCarrito(@PathVariable Integer id ,@PathVariable Integer idProducto) {
		carritoBo.deleteProducto(id,idProducto);
	}
	/* considerando que el producto existe en el carrito y fue elegido para ser eliminado*/
	@RequestMapping(value = "carrito/{id}", method = RequestMethod.DELETE)
	public void deleteCarrito(@PathVariable Integer id) {
		carritoBo.deleteCarrito(id);
	}
	
	
	
}
