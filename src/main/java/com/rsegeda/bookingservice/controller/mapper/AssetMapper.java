/*
    Created by Roman Segeda on 03 February 2020
*/

package com.rsegeda.bookingservice.controller.mapper;

import com.rsegeda.bookingservice.controller.dto.AssetDTO;
import com.rsegeda.bookingservice.service.model.Asset;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssetMapper {

  AssetDTO toDTO(Asset asset);

  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  Asset toDomain(AssetDTO assetDTO);

  List<Asset> toDomains(List<AssetDTO> assets);

  List<AssetDTO> toDTOs(List<Asset> assets);

}
