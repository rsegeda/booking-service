/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.service;

import com.rsegeda.bookingservice.controller.dto.ClientDTO;
import com.rsegeda.bookingservice.controller.mapper.ClientMapper;
import com.rsegeda.bookingservice.service.repository.ClientRepository;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  public Optional<ClientDTO> findById(String id) {
    return clientRepository.findById(id).map(clientMapper::toDTO);
  }

  public ClientDTO create(ClientDTO clientDto) {
    if (clientDto.getId() != null) {
      throw new IllegalArgumentException("A new client should not contain an id.");
    }

    return clientMapper.toDTO(clientRepository.save(clientMapper.toDomain(clientDto)));
  }

  public Optional<ClientDTO> update(String id, ClientDTO clientDto) {
    return clientRepository.findById(id).map(c -> {
      c.setFirstName(clientDto.getFirstName());
      c.setLastName(clientDto.getLastName());
      c.setBanned(clientDto.getBanned());
      c.setEmail(clientDto.getEmail());
      c.setGender(clientDto.getGender());
      c.setPhone(clientDto.getPhone());
      return clientRepository.save(c);
    }).map(clientMapper::toDTO);
  }

  public void delete(String id) {
    clientRepository.deleteById(id);
  }
}
