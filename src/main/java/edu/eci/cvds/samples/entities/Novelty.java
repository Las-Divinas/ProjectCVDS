package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class  Novelty implements Serializable {
    private static final long serialVersionUID = -3203284066887482905L;
    private String description;
    private Date date;
    private String user_id ;
    private Integer laboratory_id;
    private Integer equipment_id;
    private Integer element_id;
    private int id;
    private String title;


    public Novelty() {
    }

    public Novelty(int id,String description,String title, Date date,String user_id,int equipment_id) {
        this.id=id;
        this.description = description;
        this.date = date;
        this.user_id = user_id;
        this.equipment_id = equipment_id;
        this.title = title;
    }

    public Novelty(int id,String description,String title,Date date,String user_id,Integer equipment_id,Integer element_id) {
        this.id=id;
        this.description = description;
        this.date = date;
        this.user_id = user_id;
        this.equipment_id = equipment_id;
        this.element_id = element_id;
        this.title = title;
    }

    public Novelty(String description,String title,Date date,String user_id) {
        this.description = description;
        this.date = date;
        this.user_id = user_id;
        this.title = title;
    }

    //ElementoBean.registrarElemento()
    public Novelty(String description,String title,Date date,String user_id,Integer equipment_id,String classEquipment) {
        this.description = description;
        this.title = title;
        this.date = date;
        this.user_id = user_id;

        if(classEquipment=="Equipment") {
            this.equipment_id = equipment_id;
        }
        else if(classEquipment=="Element") {
            this.element_id = equipment_id;
        }
        else if(classEquipment=="Laboratory") {
            this.laboratory_id = equipment_id;
        }
    }

    public Novelty(String description,String title,Date date,String user_id,Integer equipment_id,Integer element_id) {
        this.description = description;
        this.date = date;
        this.user_id = user_id;
        this.equipment_id = equipment_id;
        this.element_id = element_id;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public Integer getElement_id() {
        return element_id;
    }

    public void setElement_id(Integer element_id) {
        this.element_id = element_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLaboratory_id() {
        return laboratory_id;
    }

    public void setLaboratory_id(Integer laboratory_id) {
        this.laboratory_id = laboratory_id;
    }
}