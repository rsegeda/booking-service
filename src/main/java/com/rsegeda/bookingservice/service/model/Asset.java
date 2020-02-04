/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.service.model;

import com.rsegeda.bookingservice.service.model.helpers.AssetType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;
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

@Builder
@Data
@Entity
@Document
public class Asset implements Persistable<String> {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  protected UUID id;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  @DBRef
  @NotNull
  private Appointment appointment;

  @NotNull
  @Enumerated
  private AssetType assetType;

  @NotBlank
  private String name;

  @NotNull
  @PositiveOrZero
  private BigDecimal price;

  @NotNull
  @PositiveOrZero
  private Integer loyaltyPoints;


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
