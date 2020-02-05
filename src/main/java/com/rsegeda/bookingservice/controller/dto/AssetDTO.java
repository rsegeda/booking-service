/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller.dto;

import com.rsegeda.bookingservice.service.model.helpers.AssetType;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssetDTO {

  private String id;
  private String name;
  private BigDecimal price;

  @CsvIgnore
  private AssetType assetType;

  @JsonProperty("appointment_id")
  @CsvBindByName(column = "appointment_id")
  private String appointmentId;

  @JsonProperty("loyalty_points")
  @CsvBindByName(column = "loyalty_points")
  private Integer loyaltyPoints;
}
