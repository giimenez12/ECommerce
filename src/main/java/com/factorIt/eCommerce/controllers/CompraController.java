package com.factorIt.eCommerce.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factorIt.eCommerce.bo.CarritoBo;
import com.factorIt.eCommerce.models.Carrito;
@RestController
public class CompraController {
	
	@Autowired
	private CarritoBo carritoBo;
	
	
	private static final String PARAMETER_ORDER_BY_FECHA_ASC ="fechaAsc";
	private static final String PARAMETER_ORDER_BY_FECHA_DESC ="fechaDesc";

	private static final String PARAMETER_ORDER_BY_MONTO_DESC ="montoDesc";
	private static final String PARAMETER_ORDER_BY_MONTO_ASC ="montoAsc";
	
	private static final String PARAMETER_ORDER_FECHA_STR = "fecha";
	private static final String PARAMETER_ORDER_MONTO_STR = "precioFinal";
	private static final String pattern = "yyyy-MM-dd";
	
	@GetMapping({"compra/{dni}"})
	public Collection<Carrito> getCompras(@PathVariable Integer dni,@RequestParam(value = "orderBy", required = false) String orderBy){
		if (orderBy != null) {
			switch(orderBy) {
				case PARAMETER_ORDER_BY_FECHA_ASC:
					return this.carritoBo.getCarritosComprasOrder(dni, Boolean.TRUE,PARAMETER_ORDER_FECHA_STR);
				case PARAMETER_ORDER_BY_FECHA_DESC:
					return this.carritoBo.getCarritosComprasOrder(dni, Boolean.FALSE,PARAMETER_ORDER_FECHA_STR);
				case PARAMETER_ORDER_BY_MONTO_ASC:
					return this.carritoBo.getCarritosComprasOrder(dni, Boolean.TRUE,PARAMETER_ORDER_MONTO_STR);
				case PARAMETER_ORDER_BY_MONTO_DESC:
					return this.carritoBo.getCarritosComprasOrder(dni, Boolean.FALSE,PARAMETER_ORDER_MONTO_STR);
				default:
					return null;
			}
			
		} else
			return this.carritoBo.getCarritosCompras(dni);
	}
	
	@GetMapping({"compra/{dni}/{from}","compra/{dni}/{from}/{to}"})
	public Collection<Carrito> getComprasFromTo(@PathVariable Integer dni, @PathVariable (required = true) @DateTimeFormat(pattern = pattern) String from, @PathVariable(required = false) String to ) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate fromDate = LocalDate.parse(from, formatter);
		if(to != null) {
			LocalDate toDate = LocalDate.parse(to, formatter);
			return this.carritoBo.getCarritosCompras(dni, fromDate, toDate);
		}else
			return this.carritoBo.getCarritosCompras(dni, fromDate, null);
	}
	
}
