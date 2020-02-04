/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller;

import com.rsegeda.bookingservice.controller.dto.ClientDTO;
import com.rsegeda.bookingservice.exception.BookingServiceException;
import com.rsegeda.bookingservice.service.ClientService;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
@Transactional
public class ClientController {

  private final ClientService clientService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ClientDTO create(@RequestBody @Valid final ClientDTO clientDTO) {
    return clientService.create(clientDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientDTO> retrieve(@PathVariable("id") final String id) {
    return ResponseEntity.of(clientService.findById(id));
  }

  @PutMapping("/{id}")
  public ClientDTO update(@PathVariable String id, @Valid @RequestBody ClientDTO dto) {
    return clientService.update(id, dto)
        .orElseThrow(() -> new BookingServiceException("Client was not updated"));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    clientService.delete(id);
  }
}
