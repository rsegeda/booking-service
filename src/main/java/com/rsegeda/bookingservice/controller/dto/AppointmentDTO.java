/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDTO {

  private String id;

  @JsonProperty("client_id")
  @CsvBindByName(column = "client_id")
  private String clientId;

  @JsonProperty("start_time")
  @CsvBindByName(column = "start_time")
  @CsvDate(value = "yyyy-MM-dd HH:mm:ss Z")
  private LocalDateTime startTime;

  @JsonProperty("end_time")
  @CsvDate(value = "yyyy-MM-dd HH:mm:ss Z")
  @CsvBindByName(column = "end_time")
  private LocalDateTime endTime;

}
