/*
    Created by Roman Segeda on 03 February 2020
*/

package com.rsegeda.bookingservice.controller.mapper;

import com.rsegeda.bookingservice.controller.dto.ClientDTO;
import com.rsegeda.bookingservice.service.model.Client;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AppointmentMapper.class)
public interface ClientMapper {

  ClientDTO toDTO(Client client);

  @Mapping(target = "appointments", ignore = true)
  @Mapping(target = "clientStatsList", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "persisted", ignore = true)
  Client toDomain(ClientDTO clientDTO);

  List<Client> toDomains(List<ClientDTO> clientDTO);

  List<ClientDTO> toDTOs(List<Client> clientList);

}
