package com.example.base.models;

import com.example.base.enums.Permission;
import com.example.base.enums.Status;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_account")
@Getter
@Setter
@EqualsAndHashCode
public class User extends BaseEntity {

  @Id
  @GeneratedValue(generator = "uuid-hibernate-generator")
  @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  private String name;

  private String userName;

  private String passWord;

  private Status status;

  private String email;

  private String salt;

  private String address;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Permission permission;

}
