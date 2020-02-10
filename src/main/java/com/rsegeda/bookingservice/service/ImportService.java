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
import java.util.concurrent.ExecutionException;

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
  public void triggerImport(MultipartFile clientsFile, MultipartFile appointmentsFile,
                            MultipartFile purchasesFile,
                            MultipartFile servicesFile) {

    CompletableFuture<List<ClientDTO>> clientsList = triggerClientImport(clientsFile);
    CompletableFuture<List<AppointmentDTO>> appointmentsList =
        triggerAppointmentImport(appointmentsFile);

    CompletableFuture<List<AssetDTO>> purchasesList = triggerAssetImport(purchasesFile,
        AssetType.PURCHASE);

    CompletableFuture<List<AssetDTO>> servicesList = triggerAssetImport(servicesFile,
        AssetType.SERVICE);

    try {
      List<ClientDTO> clients = clientsList.get();
      List<AppointmentDTO> appointments = appointmentsList.get();
      List<AssetDTO> purchases = purchasesList.get();
      List<AssetDTO> services = servicesList.get();

      log.info("clientsFile size " + clients.size());
      log.info("appointmentsFile size " + appointments.size());
      log.info("purchasesFile size " + purchases.size());
      log.info("servicesFile size " + services.size());

      clientService.createAll(clients);
      appointmentService.createAll(appointments);
      purchases.addAll(services);
      assetService.createAll(purchases);
    } catch (InterruptedException | ExecutionException e) {
      log.error("import failed");
    }
  }

  private CompletableFuture<List<ClientDTO>> triggerClientImport(MultipartFile file) {
    return CompletableFuture.supplyAsync(() -> toCsv(file, ClientDTO.class));
  }

  private CompletableFuture<List<AppointmentDTO>> triggerAppointmentImport(MultipartFile file) {
    return CompletableFuture.supplyAsync(() -> toCsv(file, AppointmentDTO.class));
  }

  private CompletableFuture<List<AssetDTO>> triggerAssetImport(MultipartFile file,
                                                               AssetType assetType) {
    return CompletableFuture.supplyAsync(() -> {
      List<AssetDTO> assets = toCsv(file, AssetDTO.class);
      assets.forEach(asset -> asset.setAssetType(assetType));
      return assets;
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
