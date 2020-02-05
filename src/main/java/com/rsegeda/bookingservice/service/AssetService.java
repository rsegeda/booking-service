/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.service;

import com.rsegeda.bookingservice.controller.dto.AssetDTO;
import com.rsegeda.bookingservice.controller.mapper.AssetMapper;
import com.rsegeda.bookingservice.service.model.Asset;
import com.rsegeda.bookingservice.service.model.helpers.AssetType;
import com.rsegeda.bookingservice.service.repository.AssetRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetService {

  private final AssetRepository assetRepository;
  private final AssetMapper assetMapper;

  List<AssetDTO> createAll(List<AssetDTO> appointmentDTOs, AssetType assetType) {
    List<Asset> newAppointments = assetMapper.toDomains(appointmentDTOs);
    newAppointments.parallelStream().forEach(a -> a.setAssetType(assetType));
    return assetMapper.toDTOs(assetRepository.saveAll(newAppointments));
  }

}
