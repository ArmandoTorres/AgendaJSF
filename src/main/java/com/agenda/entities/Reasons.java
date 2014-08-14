package com.agenda.entities;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;

/**
 * @author Armando
 */
@Entity
@Table(name="reasons")
@NamedQueries({@NamedQuery(name="FindReasonsByDescription",query="SELECT a FROM Reasons a WHERE a.descripcion = :P1"),
               @NamedQuery(name="FindReasonsById", query="SELECT b FROM Reasons b WHERE b.id = :P2")})
public class Reasons implements Serializable {
    
    public static final String FIND_BY_DESCRIPTION = "FindReasonsByDescription";
    public static final String FIND_BY_ID = "FindReasonsById";
    
    @Id
    @GeneratedValue(strategy=AUTO)
    private int id;
    
    @Column(name="description", nullable=false, length=200)
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return descripcion;
    }
    
}