package com.feedback.vlearning.branch;

import com.feedback.vlearning.user.Status;

import java.io.Serializable;
import java.util.Objects;

public class BranchDTO implements Serializable {

    private Long id;

    private String branchName;

    private String branchCode;

    private String address;

    private Long userId;

    private String userLogin;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchDTO branchDTO = (BranchDTO) o;
        return Objects.equals(id, branchDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
                "id=" + id +
                ", branchName='" + branchName + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                ", userLogin='" + userLogin + '\'' +
                ", status=" + status +
                '}';
    }
}
