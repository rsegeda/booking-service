/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.service;

import com.rsegeda.bookingservice.controller.dto.AppointmentDTO;
import com.rsegeda.bookingservice.controller.mapper.AppointmentMapper;
import com.rsegeda.bookingservice.service.model.Appointment;
import com.rsegeda.bookingservice.service.repository.AppointmentRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final AppointmentMapper appointmentMapper;

  List<AppointmentDTO> createAll(List<AppointmentDTO> appointmentDTOs) {
    List<Appointment> newAppointments = appointmentMapper.toDomains(appointmentDTOs);
    return appointmentMapper.toDTOs(appointmentRepository.saveAll(newAppointments));
  }

}
