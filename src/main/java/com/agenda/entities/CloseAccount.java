package com.agenda.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * @author Armando
 */
@Entity
@Table(name="closes_accounts")
@NamedQueries({@NamedQuery(name="FindCloseAccountByUser",query="SELECT a FROM CloseAccount a WHERE a.user = :P1"),
               @NamedQuery(name="FindCloseAccountById",query="SELECT b FROM CloseAccount b WHERE b.id = :P2")})
public class CloseAccount implements Serializable {
    
    public static final String FIND_BY_USER = "FindCloseAccountByUser";
    public static final String FIND_BY_ID   = "FindCloseAccountById";
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="reason_id")
    private Reasons reason;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    @Temporal(TemporalType.DATE)
    @Column(name="close_date")
    private Date closeDate; 
    
    @Column(name="feedback", nullable=false)
    private String feedback;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reasons getReason() {
        return reason;
    }

    public void setReason(Reasons reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
    
    
    
}
