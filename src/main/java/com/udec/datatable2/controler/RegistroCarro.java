/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.datatable2.controler;

import static com.sun.javafx.logging.PulseLogger.addMessage;
import com.udec.datatable2.utilitarios.Carros;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 * Clase encargada de registrar los carros
 *
 * @author Corin V, Alisson Celeita
 */
@Named(value = "registroCarro")
@SessionScoped
public class RegistroCarro implements Serializable {

    /**
     * nombre, del vehiculo marca, del carro marcas, existentes de carros
     * filtros, lista para los filtros modelo, año de lanzamiento del carro
     * marca, del vehiculo
     * marcas, listado de marcas
     * filtros, de carros
     * Loger, bitacora de eventos
     * carroSeleccionado, carros seleccionados
     * Clase carro
     */
    private String nombre;
    private String marca;
    private List<String> marcas;
    private List<Carros> carros;
    private List<Carros> filtros;
    private Date modelo;
    private Logger loger;
    private Carros carroSeleccionado;
    private Carros carro;

    /**
     * Constructor de la clase, se inicializa las listas y se crea la lista de
     * marcas
     */
    @PostConstruct
    public void iniciar() {
        carros = new ArrayList<>();
        marcas = new ArrayList<>();
        marcas.add("Renault");
        marcas.add("Mazda");
        marcas.add("Chevrolet");
        marcas.add("Ford");
        marcas.add("Hyundai");
        marcas.add("Susuki");
        loger = Logger.getLogger("RegistroCarro");
  

    }

    /**
     * Metodo para obtener el nombre del vehiculo
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para asignar el nombre del carro
     *
     * @param nombre del carro
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo obtiene la marca seleccionada
     *
     * @return marca seleccionada
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Metodo para asignar la marca del carro
     *
     * @param marca del carro
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el listado de las marcas
     *
     * @return listado de marcas
     */
    public List<String> getMarcas() {
        return marcas;
    }

    /**
     * Asigna el listado de marcas de carro
     *
     * @param marcas de carros
     */
    public void setMarcas(List<String> marcas) {
        this.marcas = marcas;
    }

    /**
     * Metodo para obtener el modelo(año) del carro
     *
     * @return año de lanzamiento del carro
     */
    public Date getModelo() {
        return modelo;
    }

    /**
     * Metodo para asignar el año del modelo
     *
     * @param modelo del carro
     */
    public void setModelo(Date modelo) {
        this.modelo = modelo;
    }

    /**
     * Metodo para obtener el listado de carros
     *
     * @return listado de carros
     */
    public List<Carros> getCarros() {
        return carros;
    }

    /**
     * Metodo para asignar el listado de carros
     *
     * @param carros, listado de carros
     */
    public void setCarros(List<Carros> carros) {
        this.carros = carros;
    }

    /**
     * Metodo para obtener el listado segun el filtro
     *
     * @return lista filtrada
     */
    public List<Carros> getFiltros() {
        return filtros;
    }

    /**
     * Metodo para asignar el listado de filtros
     *
     * @param filtros, listado segun el filtro
     */
    public void setFiltros(List<Carros> filtros) {
        this.filtros = filtros;
    }

    /**
     * Metodo para obtener carro seleccionado
     *
     * @return carro seleccionado
     */
    public Carros getCarroSeleccionado() {
        return carroSeleccionado;
    }

    /**
     * Metodo asigna el carro seleccionado
     *
     * @param carroSeleccionado
     */
    public void setCarroSeleccionado(Carros carroSeleccionado) {
        this.carroSeleccionado = carroSeleccionado;
    }

    /**
     * Metodo para obtener carro
     *
     * @return carro
     */
    public Carros getCarro() {
        return carro;
    }

    /**
     * Metodo para asignar carro
     *
     * @param carro
     */
    public void setCarro(Carros carro) {
        this.carro = carro;
    }

    /**
     * Metodo para llenar el DataTable
     */
    public void llenarTabla() {
        carros.add(new Carros(nombre, marca, modelo));
        String serializado= "Insercion de objeto carro" + nombre + "," + modelo + "," + marca;
        loger.log(Level.INFO,serializado);
        nombre = "";
        modelo = null;

    }

    /**
     * Metodo para editar fila
     *
     * @param event generado
     */
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Carro Editado", ((Carros) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    /**
     * Metodo para cancelar la edicion
     * @param event generado
     */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada", ((Carros) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    /**
     * Metodo para la edicion por celda
     * @param event generado
     */
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        Carros car=(Carros) oldValue;
        Carros carroEditado=(Carros) oldValue;
        String serializado="Antiguo valor "+car.getNombre()+","+car.getMarca()+","+car.getModelo();
        String serializadoNuevo="Nuevo valor "+carroEditado.getNombre()+","+carroEditado.getMarca()+","+carroEditado.getModelo();
        loger.log(Level.INFO,serializado);
        loger.log(Level.INFO,serializadoNuevo);
    }
    /**
     * Eliminacion  de una fila
     * @param carro 
     */
    public void eliminar(Carros carro) {
        String serializado= "Eliminacion del carro: "+carro.getNombre()+","+carro.getMarca()+","+carro.getModelo();
        loger.log(Level.INFO,serializado);
        carros.remove(carro);
        addMessage("Información", "Carro eliminado");

    }
    /**
     * Añadir mensaje para notificar la eliminación
     * @param summary
     * @param detail 
     */
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


}
