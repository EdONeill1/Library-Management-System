package main.java.ie.bibliotech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Date;

@Entity
@Table(name = "artifacts")
public class Artifact {

    public Artifact() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String name;
    private String type;
    private String genre;
    private String status;
    private Boolean reserved;

    public Boolean getReserved(){ return reserved; }
    public void setReserved(Boolean reserved){ this.reserved = reserved; }
    public String getAuthor(){ return author; }
    public void setAuthor(String author){ this.author = author; }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getType() {
        return type;
    }

    public void setType(String newType) {
        this.type = newType;
    }



    @Override
    public String toString() {
        return "Name: " + this.getName() + "\n";
    }

}
