/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.service.model;

import com.rsegeda.bookingservice.service.model.helpers.Gender;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Entity
@Document
public class Client {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  protected String id;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @Email
  private String email;

  private String phone;

  @NotNull
  @Enumerated
  private Gender gender;

  private Boolean banned;

}
