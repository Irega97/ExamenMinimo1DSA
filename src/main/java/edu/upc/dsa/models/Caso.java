package edu.upc.dsa.models;

public class Caso {

    public String id;
    public String name;
    public String surname;
    public String fechaNacimiento;
    public String fechaCaso;
    public String riskLevel;
    public String gender;
    public String email;
    public String telephone;
    public String direccion;
    public String clasificacion;

    public Caso(){
    }

    public Caso(String id, String name, String surname, String fechaNacimiento, String fechaCaso,
                String riskLevel, String gender, String email, String telephone, String direccion, String clasificacion) {
        this();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCaso = fechaCaso;
        this.riskLevel = riskLevel;
        this.gender = gender;
        this.email = email;
        this.telephone = telephone;
        this.direccion = direccion;
        this.clasificacion = clasificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaCaso() {
        return fechaCaso;
    }

    public void setFechaCaso(String fechaCaso) {
        this.fechaCaso = fechaCaso;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    @Override
    public String toString() {
        return "CASO: [id=" + this.getId() + "Nombre: " + this.getName() + "Apellido: " + this.getSurname() + "Fecha nacimiento: " + this.getFechaNacimiento()
                + "Fecha registro caso: "+this.getFechaCaso()+"Nivel de riesgo: "+this.getRiskLevel()+"Genero: "+this.getGender()+"Email: "+this.getEmail()
                + "Telefono: "+this.getTelephone()+"Direccion: "+this.getDireccion()+"Clasificacion: "+this.getClasificacion()+"]";
    }
}
