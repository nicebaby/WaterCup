package com.zslin.basic.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zslin.web.model.Site;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Entity
@Table(name="a_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    @Column(name="create_date")
    private Date createDate;
    /** 用户昵称 */
    private String nickname;

    @Column(name="is_admin")
    private Integer isAdmin;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Site> sites;

    public User() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public String getNickname() {
        return this.nickname;
    }

    public Integer getIsAdmin() {
        return this.isAdmin;
    }

    public List<Site> getSites() {
        return this.sites;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$createDate = this.getCreateDate();
        final Object other$createDate = other.getCreateDate();
        if (this$createDate == null ? other$createDate != null : !this$createDate.equals(other$createDate))
            return false;
        final Object this$nickname = this.getNickname();
        final Object other$nickname = other.getNickname();
        if (this$nickname == null ? other$nickname != null : !this$nickname.equals(other$nickname)) return false;
        final Object this$isAdmin = this.getIsAdmin();
        final Object other$isAdmin = other.getIsAdmin();
        if (this$isAdmin == null ? other$isAdmin != null : !this$isAdmin.equals(other$isAdmin)) return false;
        final Object this$sites = this.getSites();
        final Object other$sites = other.getSites();
        if (this$sites == null ? other$sites != null : !this$sites.equals(other$sites)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $createDate = this.getCreateDate();
        result = result * PRIME + ($createDate == null ? 43 : $createDate.hashCode());
        final Object $nickname = this.getNickname();
        result = result * PRIME + ($nickname == null ? 43 : $nickname.hashCode());
        final Object $isAdmin = this.getIsAdmin();
        result = result * PRIME + ($isAdmin == null ? 43 : $isAdmin.hashCode());
        final Object $sites = this.getSites();
        result = result * PRIME + ($sites == null ? 43 : $sites.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof User;
    }

    public String toString() {
        return "com.zslin.basic.model.User(id=" + this.getId() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", status=" + this.getStatus() + ", createDate=" + this.getCreateDate() + ", nickname=" + this.getNickname() + ", isAdmin=" + this.getIsAdmin() + ", sites=" + this.getSites() + ")";
    }
}
