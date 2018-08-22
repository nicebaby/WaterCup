package com.zslin.web.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

/**
 *
 */

@Data//lombok的注解，相当于@setter、@getter、@tostring、@equalsandhashcode
@Entity//hibernate的注解，与下面的注解结合使用，申明该实体类映射为数据库库的t_account表
@Table(name = "t_account")
public class Account {

    @Id//映射生成主键
    @GeneratedValue(strategy = GenerationType.AUTO)//主键生成策略
    private Integer id;

    private String email;

    private String nickname;

    /** 座右铭 */
    private String motto;

    @Lob//实体BLOB、CLOB类型的注解
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

   @ManyToOne
    private Card card;

   @OneToMany(mappedBy = "account")
    private List<Order> orders;
}
