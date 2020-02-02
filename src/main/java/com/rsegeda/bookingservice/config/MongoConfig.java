/*
    Created by Roman Segeda on 01 February 2020
*/

package com.rsegeda.bookingservice.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

  private final ServiceProperties serviceProperties;

  @Override
  protected String getDatabaseName() {
    return "booking-service";
  }

  @Override
  public MongoClient mongoClient() {
    return MongoClients.create(serviceProperties.getMongo().getUri());
  }
}