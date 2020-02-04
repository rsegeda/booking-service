/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AppointmentDTO {

  private UUID id;

  @JsonProperty("client_id")
  private UUID clientId;

  @JsonProperty("start_time")
  private LocalDateTime startTime;

  @JsonProperty("end_time")
  private LocalDateTime endTime;

}
