package com.siixo.videogame.entity;

public class Device {
    private Integer id;
    private String name;
    private String type;
    private String manufacturer;

    public Device() {}

    public Device(String name, String type, String manufacturer) {
        this.name =  name;
        this.type = type;
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }





}
