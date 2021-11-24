package com.factorIt.eCommerce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.factorIt.eCommerce.models.Carrito;
import com.factorIt.eCommerce.models.Compra;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CompraService {
	public List<Compra> compraService(Integer dni) {
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/compra/"+dni, Object[].class);
		Object[] objects = responseEntity.getBody();
		return Arrays.stream(objects)
				  .map(object -> mapper.convertValue(object, Carrito.class))
				  .map(Carrito::getCompra)
				  .collect(Collectors.toList());
	}
	
	public class ComprasList {
	    private List<Carrito> compras;

	    public ComprasList() {
	        compras = new ArrayList<>();
	    }
	    
	    public List<Carrito>getCompras() {
	    	return this.compras;
	    }
	}
}
