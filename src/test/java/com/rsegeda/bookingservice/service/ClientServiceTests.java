/*
    Created by Roman Segeda on 03 February 2020
*/

package com.rsegeda.bookingservice.service;

import com.rsegeda.bookingservice.controller.dto.ClientDTO;
import com.rsegeda.bookingservice.controller.mapper.ClientMapper;
import com.rsegeda.bookingservice.controller.mapper.ClientMapperImpl;
import com.rsegeda.bookingservice.service.model.Client;
import com.rsegeda.bookingservice.service.repository.ClientRepository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTests {

  @Captor
  ArgumentCaptor<Client> clientArgumentCaptor;

  @Test
  void findByIdCallsRepository() {
    var repository = Mockito.mock(ClientRepository.class);
    var mapper = Mockito.mock(ClientMapper.class);
    var service = new ClientService(repository, mapper);

    String uuid = UUID.randomUUID().toString();
    service.findById(uuid);

    verify(repository).findById(uuid);
  }

  @Test
  void createCallsRepository() {
    var repository = Mockito.mock(ClientRepository.class);
    var mapper = new ClientMapperImpl();
    var service = new ClientService(repository, mapper);

    ClientDTO client = ClientDTO.builder().build();
    service.create(client);

    verify(repository).save(clientArgumentCaptor.capture());
    Assertions.assertNotNull(clientArgumentCaptor.getValue());
  }

  @Test
  void updateCallsRepository() {
    var repository = Mockito.mock(ClientRepository.class);
    var mapper = Mockito.mock(ClientMapper.class);
    var service = new ClientService(repository, mapper);

    ClientDTO clientDTO = ClientDTO.builder().id(UUID.randomUUID().toString()).build();
    Client client = Client.builder().id(clientDTO.getId()).build();
    when(repository.findById(clientDTO.getId())).thenReturn(Optional.of(client));

    service.update(clientDTO.getId(), clientDTO);

    verify(repository).save(clientArgumentCaptor.capture());
    Assertions.assertNotNull(clientArgumentCaptor.getValue());
  }

  @Test
  void deleteCallsRepository() {
    var repository = Mockito.mock(ClientRepository.class);
    var mapper = Mockito.mock(ClientMapper.class);
    var service = new ClientService(repository, mapper);
    String uuid = UUID.randomUUID().toString();

    service.delete(uuid);

    verify(repository).deleteById(eq(uuid));
  }
}
