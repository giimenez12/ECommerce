package com.factorIt.eCommerce.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.factorIt.eCommerce.dao.ClienteDao;
import com.factorIt.eCommerce.models.Cliente;

@Repository
@Transactional
public class ClienteDaoImpl implements ClienteDao {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Cliente getCliente(Integer dni) {
		String query = "select c FROM Cliente c WHERE c.dni=" + dni ;
		Cliente resultado = entityManager.createQuery(query, Cliente.class).getSingleResult();
		return resultado;
	}

}
