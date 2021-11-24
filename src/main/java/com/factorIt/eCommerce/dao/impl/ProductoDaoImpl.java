package com.factorIt.eCommerce.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.factorIt.eCommerce.dao.ProductoDao;
import com.factorIt.eCommerce.models.Producto;

@Repository
@Transactional
public class ProductoDaoImpl implements ProductoDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Producto getProducto(Integer id) {
		return entityManager.find(Producto.class , id);
	}

}
