/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
class ClientStatsDTO {

  private String id;
  private Long loyaltyPointsSum;
}
