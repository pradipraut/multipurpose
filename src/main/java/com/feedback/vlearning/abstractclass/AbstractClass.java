package com.feedback.vlearning.abstractclass;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractClass<PK extends Serializable> {


    private static final long serialVersionUID = 8453654076725018243L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Version
    @Column
    private int version;

    public AbstractClass() {
        setCreated(new Date());
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return created;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
