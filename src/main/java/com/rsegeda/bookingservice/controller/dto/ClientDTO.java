/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller.dto;

import com.rsegeda.bookingservice.service.model.helpers.Gender;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ClientDTO {

  private UUID id;

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("last_name")
  private String lastName;
  private String email;
  private String phone;
  private Gender gender;
  private Boolean banned = false;

  private List<AppointmentDTO> appointments;

}