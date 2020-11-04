package com.feedback.vlearning.branch;

import com.feedback.vlearning.abstractclass.AbstractClass;
import com.feedback.vlearning.user.Status;
import com.feedback.vlearning.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "branch")
public class Branch extends AbstractClass<Long> {

    private String branchName;

    private String branchCode;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Status status;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
