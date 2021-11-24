package com.factorIt.eCommerce.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factorIt.eCommerce.bo.ClienteBo;
import com.factorIt.eCommerce.dao.ClienteDao;
import com.factorIt.eCommerce.models.Cliente;
@Service
public class ClienteBoImpl implements ClienteBo{
	@Autowired
	private ClienteDao clienteDao;

	@Override
	public Cliente getCliente(Integer dni) {
		return this.clienteDao.getCliente(dni);
	}
}
