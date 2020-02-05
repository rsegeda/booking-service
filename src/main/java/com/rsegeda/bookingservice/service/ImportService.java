/*
    Created by Roman Segeda on 05 February 2020
*/

package com.rsegeda.bookingservice.service;

import com.rsegeda.bookingservice.controller.dto.AppointmentDTO;
import com.rsegeda.bookingservice.controller.dto.AssetDTO;
import com.rsegeda.bookingservice.controller.dto.ClientDTO;
import com.rsegeda.bookingservice.exception.FileServiceException;
import com.rsegeda.bookingservice.service.model.helpers.AssetType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("unchecked")
@Service
@Slf4j
@RequiredArgsConstructor
public class ImportService {

  private final ClientService clientService;
  private final AppointmentService appointmentService;
  private final AssetService assetService;

  @Transactional
  public void triggerImport(MultipartFile clients, MultipartFile appointments,
                            MultipartFile purchases,
                            MultipartFile services) {

    triggerClientImport(clients)
        .thenAccept((Void) -> log.info("clients imported"));
    triggerAppointmentImport(appointments)
        .thenAccept((Void) -> log.info("appointments imported"));
    triggerAssetImport(purchases, AssetType.PURCHASE)
        .thenAccept((Void) -> log.info("purchases imported"));
    triggerAssetImport(services, AssetType.SERVICE)
        .thenAccept((Void) -> log.info("services imported"));
  }

  private CompletableFuture<Void> triggerClientImport(MultipartFile file) {
    return CompletableFuture.supplyAsync(() -> {
      List<ClientDTO> clientDTOS = toCsv(file, ClientDTO.class);
      clientService.createAll(clientDTOS);
      return null;
    });
  }

  private CompletableFuture<Void> triggerAppointmentImport(MultipartFile file) {
    return CompletableFuture.supplyAsync(() -> {
      List<AppointmentDTO> appointmentDTOS = toCsv(file, AppointmentDTO.class);
      appointmentService.createAll(appointmentDTOS);
      return null;
    });
  }

  private CompletableFuture<Void> triggerAssetImport(MultipartFile file, AssetType assetType) {
    return CompletableFuture.supplyAsync(() -> {
      List<AssetDTO> assetDTOs = toCsv(file, AssetDTO.class);
      assetService.createAll(assetDTOs, assetType);
      return null;
    });
  }

  private List toCsv(MultipartFile file, Class beanType) throws FileServiceException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      CsvToBean csvParser = new CsvToBeanBuilder<>(reader).withType(beanType).build();
      return csvParser.parse();
    } catch (Exception e) {
      throw new FileServiceException("Parsing CSV failed. " + file.getName(), e);
    }
  }

}
