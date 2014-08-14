package com.agenda.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;

/**
 * @author Armando
 */
@Entity
@Table(name="users")
@NamedQueries({@NamedQuery(name="Authenticate",
                           query="SELECT b FROM User b WHERE (b.name = :P2 OR b.email = :P2) AND b.password = :P3 AND b.status = :status"),
               @NamedQuery(name="FindByEmail",query="SELECT c FROM User c WHERE c.email = :P4 AND c.status = :status"),
               @NamedQuery(name="FindAllActiveUser",query="SELECT d FROM User d WHERE d.status = :status"),
               @NamedQuery(name="FindByUserID",query="SELECT e FROM User e WHERE e.id=:id")})
public class User implements Serializable{
    
    public static final String AUTHENTICATE = "Authenticate";
    public static final String FIND_BY_EMAIL = "FindByEmail";
    public static final String FIND_ALL_ACTIVE = "FindAllActiveUser";
    public static final String FIND_BY_ID = "FindByUserID";

    @Id
    @GeneratedValue(strategy=AUTO)
    @Column(name="user_id")
    private int id;
    
    @Column(name="name", nullable=false, length=25)
    private String name;
    
    @Column(name="surname", nullable=false, length=25)
    private String surname;
    
    @Column(name="email", nullable=false, length=60, unique=true)
    private String email;
    
    @Column(name="password", nullable=false, length=40)
    private String password;
    
    @Column(name="status", length=1)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(name="role", length=20)
    @Enumerated(EnumType.STRING)
    private Roles role;
    
    @OneToMany(mappedBy="user")
    private List<Agenda> agenda;

    public User() {
    }
    
    public User(String name, String surname, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name+" "+surname;
    }
    
}
