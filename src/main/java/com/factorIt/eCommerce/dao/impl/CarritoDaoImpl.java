package com.factorIt.eCommerce.dao.impl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.factorIt.eCommerce.dao.CarritoDao;
import com.factorIt.eCommerce.models.Carrito;
import com.factorIt.eCommerce.models.Producto;
@Repository
@Transactional
public class CarritoDaoImpl implements CarritoDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Collection<Carrito> getCarritosCompras(Integer id_cliente) {
		String query = "select c FROM Carrito c join c.cliente e WHERE e.id =" + id_cliente + " and c.cerrado = 1" ;
		List<Carrito> resultado = entityManager.createQuery(query).getResultList();
		return resultado;
	}
	
	@Override
	public Collection<Carrito> getCarritosComprasOrder(Integer id_cliente, Boolean asc, String collumn) {
		String query;
		if(asc)
			query = "select c FROM Carrito c join c.cliente e WHERE e.id =" + id_cliente + " and c.cerrado = 1 order by c.compra."+ collumn  ;
		else
			query = "select c FROM Carrito c join c.cliente e WHERE e.id =" + id_cliente + " and c.cerrado = 1 order by c.compra." + collumn + " desc" ;
		List<Carrito> resultado = entityManager.createQuery(query).getResultList();
		return resultado;
	}

	@Override
	public Collection<Carrito> getCarritosCompras(Integer id_cliente, LocalDate from, LocalDate to) {
		String query;
		if(to != null) 
			query = "select c FROM Carrito c join c.cliente e WHERE e.id =" + id_cliente + " and c.cerrado = 1 and (c.compra.fecha >= "+ from +" )" ;
		else
			query = "select c FROM Carrito c join c.cliente e WHERE e.id =" + id_cliente + " and c.cerrado = 1 and (c.compra.fecha between "+ from +" and "+ to + " )" ;
		List<Carrito> resultado = entityManager.createQuery(query).getResultList();
		return resultado;
	}

	@Override
	public void createCarrito(Carrito carrito) {
		entityManager.merge(carrito);
	}

	@Override
	public void deleteCarrito(Integer id) {
		Carrito carrito = entityManager.find(Carrito.class, id);
		entityManager.remove(carrito);
	}

	@Override
	public Carrito getCarrito(Integer id) {
		return entityManager.find(Carrito.class, id);
	}

	@Override
	public void saveCarrito(Carrito carrito) {
		entityManager.merge(carrito);
		
	}

	@Override
	public Collection<Producto> getProductos(Integer id) {
		String query = "select c.listaProductos FROM Carrito c WHERE c.id =" + id ;
		List<Producto> resultado = entityManager.createQuery(query).getResultList();
		return resultado;
	}

}
