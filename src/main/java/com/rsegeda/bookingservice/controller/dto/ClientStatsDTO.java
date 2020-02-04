/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class ClientStatsDTO {

  private String id;
  private Long loyaltyPointsSum;
}
