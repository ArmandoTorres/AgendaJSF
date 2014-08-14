package com.agenda.entities;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;

/**
 * @author Armando
 */
@Entity
@Table(name="contacto")
@NamedQueries({@NamedQuery(name="FindByUser", query="SELECT a FROM Agenda a WHERE a.user = :P1 order by a.name"),
               @NamedQuery(name="FindByParameter", 
                           query="SELECT b FROM Agenda b WHERE (b.name like :P2 OR b.surname like :P2 OR b.phone like :P2 OR b.email like :P2) and b.user = :P3 order by b.name"),
               @NamedQuery(name="FindAgendaById", query="SELECT c FROM Agenda c WHERE c.id = :P4")})
public class Agenda implements Serializable {
    
    public static final String FIND_BY_USER = "FindByUser";
    public static final String FIND_BY_PARAMETER = "FindByParameter";
    public static final String FIND_BY_ID = "FindAgendaById";
    
    @Id
    @GeneratedValue(strategy=AUTO)
    private int id;
    
    @Column(name="name", nullable=false, length=60)
    private String name;
    
    @Column(name="surname", nullable=false, length=60)
    private String surname;
    
    @Column(name="phone", nullable=false, length=60)
    private String phone;
    
    @Column(name="email", length=60)
    private String email;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}