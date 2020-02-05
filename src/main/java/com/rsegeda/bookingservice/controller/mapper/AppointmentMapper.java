/*
    Created by Roman Segeda on 03 February 2020
*/

package com.rsegeda.bookingservice.controller.mapper;

import com.rsegeda.bookingservice.controller.dto.AppointmentDTO;
import com.rsegeda.bookingservice.service.model.Appointment;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

  AppointmentDTO toDTO(Appointment appointment);

  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  Appointment toDomain(AppointmentDTO appointmentDTO);

  List<Appointment> toDomains(List<AppointmentDTO> appointments);

  List<AppointmentDTO> toDTOs(List<Appointment> appointments);

}
