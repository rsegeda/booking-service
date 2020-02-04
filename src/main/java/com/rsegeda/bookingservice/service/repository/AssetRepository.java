/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.service.repository;

import com.rsegeda.bookingservice.service.model.Asset;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends MongoRepository<Asset, String> {

}
