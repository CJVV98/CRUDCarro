/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.datatable2.utilitarios;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase de tipo carro
 * @author Corin V, Alisson Celeita
 */
public class Carros {
    /**
     * nombre, del vehiculo
     * marca, del vehiculo
     * modelo, del vehiculo
     */
    private String nombre;
    private String marca;
    private int modelo;
    
    /**
     * Constructor de la clase
     * @param nombre del carro
     * @param marca del carro
     * @param modelo (año) de lanzamiento
     */
    public Carros(String nombre, String marca, Date modelo) {
        this.nombre = nombre;
        this.marca = marca;
        String formato="yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        this.modelo = Integer.parseInt(dateFormat.format(modelo));;
    }
    /**
     * Metodo para obtener el nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo para asignar el nombre
     * @param nombre del carro
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Metodo para obtener la marca del carro
     * @return marca
     */
    public String getMarca() {
        return marca;
    }
    /**
     * Metodo para asignar la marca del carro
     * @param marca del carro
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
     * Metodo para obtener el modelo año del carro
     * @return modelo
     */
    public int getModelo() {
        return modelo;
    }
    /**
     * Metodo para asignar el modelo del carro
     * @param modelo del carro
     */
    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    
    
    
}
