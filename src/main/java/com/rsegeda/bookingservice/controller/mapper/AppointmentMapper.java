/*
    Created by Roman Segeda on 03 February 2020
*/

package com.rsegeda.bookingservice.controller.mapper;

import com.rsegeda.bookingservice.controller.dto.AppointmentDTO;
import com.rsegeda.bookingservice.service.model.Appointment;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

  @Mapping(target = "clientId",
      expression = "java( java.util.UUID.fromString(appointment.getClient().getId()) )")
  AppointmentDTO toDTO(Appointment appointment);

  default UUID idToUUID(String id) {
    return UUID.fromString(id);
  }

  // @Mapping(target = "appointments", expression = "java( new java.util.ArrayList() )")
  // @Mapping(target = "clientStatsList", expression = "java( new java.util.ArrayList() )")
  // Client toDomain(ClientDTO clientDTO);
  //
  // List<Client> toDomains(List<ClientDTO> clientDTO);
  //
  // List<ClientDTO> toDTOs(List<Client> clientList);

}
