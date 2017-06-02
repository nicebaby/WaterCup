package com.zslin.web.model;

import javax.persistence.*;

/**
 * Created by Ji on 2017/3/16.
 */
@Entity
@Table(name = "t_device")
public class Device {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long d_id;
    private String sn;
    private Long model_id;
    private int status;
    private  int s_id;
    private String remainder;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    public Long getD_id() {
        return d_id;
    }

    public void setD_id(Long d_id) {
        this.d_id = d_id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getModel_id() {
        return model_id;
    }

    public void setModel_id(Long model_id) {
        this.model_id = model_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getRemainder() {
        return remainder;
    }

    public void setRemainder(String remainder) {
        this.remainder = remainder;
    }


}
