package com.factorIt.eCommerce.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factorIt.eCommerce.bo.ProductoBo;
import com.factorIt.eCommerce.dao.ProductoDao;
import com.factorIt.eCommerce.models.Producto;
@Service
public class ProductoBoImpl implements ProductoBo {
	@Autowired
	private ProductoDao productoDao;

	@Override
	public Producto getProducto(Integer id) {
		
		return productoDao.getProducto(id);
	}

}
