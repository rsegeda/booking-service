/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller.dto;

import com.rsegeda.bookingservice.service.model.helpers.Gender;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO {

  private String id;

  @JsonProperty("first_name")
  @CsvBindByName(column = "first_name")
  private String firstName;

  @CsvBindByName(column = "last_name")
  @JsonProperty("last_name")
  private String lastName;
  private String email;
  private String phone;
  private Gender gender;
  @Builder.Default()
  private Boolean banned = false;
}
