package com.zslin.web.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "t_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    private String nickname;

    /** 座右铭 */
    private String motto;

    @Lob
    private String remark;

    /** 状态 */
    private String status;

    /** 头像 */
    private String headimg;

    private String password;

    private String remain;

    /**  充值金额*/
   private String paymoney;

    /**  充值状态：0为未成功，1为成功，null为初始值*/
   private String paystate;

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }

    public String getPaystate() {
        return paystate;
    }

    public void setPaystate(String paystate) {
        this.paystate = paystate;
    }

}
