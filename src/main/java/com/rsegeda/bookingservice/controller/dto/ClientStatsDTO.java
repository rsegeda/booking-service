/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class ClientStatsDTO {

  private UUID id;
  private Long loyaltyPointsSum;
}
