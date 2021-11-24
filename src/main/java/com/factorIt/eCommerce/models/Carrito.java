package com.factorIt.eCommerce.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.factorIt.eCommerce.CompraService;

@Entity
@Table(name = "carrito")
public class Carrito {
	
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name ="id_cliente")
	private Cliente cliente;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_compra", referencedColumnName = "id")
    private Compra compra;

	@JoinTable(
	        name = "carrito_producto",
	        joinColumns = @JoinColumn(name = "id_carrito", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="id_producto", nullable = false)
	    )

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Producto> listaProductos;
	
	@Column(name ="especial")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean carritoEspecial;
	@Column(name ="cerrado")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean cerrado;
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;
	
	private final static double DESCUENTO_ESPECIAL = 150;
	private final static double DESCUENTO = 100;
	
	public Carrito() {
		
	}
	public Carrito(Cliente cliente, LocalDate fechaCreacion, Boolean isSpecial) {
		super();
		this.carritoEspecial = isSpecial;
		this.cerrado = Boolean.FALSE;
		this.cliente = cliente;
		this.listaProductos = new ArrayList<Producto>();
		this.fechaCreacion = fechaCreacion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public Boolean getCarritoEspecial() {
		return carritoEspecial;
	}
	public void setCarritoEspecial(Boolean carritoEspecial) {
		this.carritoEspecial = carritoEspecial;
	}
	public Boolean getCerrado() {
		return cerrado;
	}
	public void setCerrado(Boolean cerrado) {
		this.cerrado = cerrado;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public void agregarProducto(Producto producto) {
		this.listaProductos.add(producto);
	}
	public void deleteProducto(Producto producto) {
		this.listaProductos.remove(producto);
	}
	public double getPrecioFinal(Boolean clienteVip) {
		
		double descuento = carritoEspecial?DESCUENTO_ESPECIAL:DESCUENTO;
		HashSet<Producto> hs = new HashSet(listaProductos);
		double montoTotal = 0; 
		for(Producto p : hs) {
			/*considerando que en el enunciado dice solo si compra 4 elementos 
			 de otra manera hubiese dividido la cantidad por 4 y restarle la cantidad de elementos que entraron en promo*/
			 List<Producto>listaMismoProducto = listaProductos.stream()
					 .filter(c -> c.getId() == p.getId())
					 .collect(Collectors.toList());
			Boolean es4X3 = listaMismoProducto.size() == 4;
			if(es4X3) //sumo 3 en vez de 4 al monto total 
				montoTotal += listaMismoProducto.get(0).getPrecio() * 3; 
			else
				montoTotal += listaMismoProducto.stream()
					.mapToDouble(prod -> prod.getPrecio())
					.sum();
		}
		
		if(listaProductos.size()>3)
			montoTotal -= descuento;
		if(clienteVip && (montoTotal>2000))
			montoTotal -= descuento;
		
		
		return montoTotal;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public List<Producto> getListaProductos() {
		return listaProductos;
	}
}
