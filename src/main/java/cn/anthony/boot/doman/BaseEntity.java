package cn.anthony.boot.doman;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_entity")
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "creation_time", nullable = false)
    private Date creationTime;

    @Column(name = "modification_time")
    private Date modificationTime;

    /**
     * @param title
     * @param description
     */
    public BaseEntity(String title, String description) {
	super();
	this.title = title;
	this.description = description;
	this.creationTime = Calendar.getInstance().getTime();
    }

    public BaseEntity() {
	super();
	this.creationTime = Calendar.getInstance().getTime();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Date getCreationTime() {
	return creationTime;
    }

    public void setCreationTime(Date creationTime) {
	this.creationTime = creationTime;
    }

    public Date getModificationTime() {
	return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
	this.modificationTime = modificationTime;
    }

}
