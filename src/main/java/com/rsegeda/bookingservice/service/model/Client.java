/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.service.model;

import com.rsegeda.bookingservice.service.model.helpers.Gender;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Entity
@Document
public class Client implements Persistable<String> {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  protected UUID id;

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

  @DBRef
  private List<Appointment> appointments;

  @DBRef
  private List<ClientStats> clientStatsList;


  private boolean persisted;

  @Override
  public String getId() {
    return id.toString();
  }

  @Override
  public boolean isNew() {
    return !persisted;
  }
}
