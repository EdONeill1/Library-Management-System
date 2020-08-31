package main.java.ie.bibliotech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Date;
import java.util.Stack;

@Entity
@Table(name = "members")
@DynamicUpdate
public class Member {

    public Member() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String currentLoan;
    private String favouriteGenre;
    @CreationTimestamp
    private Date dateJoined;

    public String getFavouriteGenre() {
        return favouriteGenre;
    }

    public void setFavouriteGenre(String favouriteGenre) {
        this.favouriteGenre = favouriteGenre;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getCurrentLoan() {
        return this.currentLoan;
    }

    public void setCurrentLoan(String newCurrentLoan) {
        this.currentLoan = newCurrentLoan;
    }

    public Date getDateJoined() {
        return this.dateJoined;
    }

    public void setDateJoined(Date newDate) {
        this.dateJoined = newDate;
    }



    @Override
    public String toString() {
        return "Name: " + this.getName() + "\n";
    }

}
