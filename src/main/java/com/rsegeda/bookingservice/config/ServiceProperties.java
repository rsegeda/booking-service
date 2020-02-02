/*
    Created by Roman Segeda on 01 February 2020
*/

package com.rsegeda.bookingservice.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config")
public class ServiceProperties {

  @Getter
  private final Mongo mongo = new Mongo();

  public static class Mongo {

    private String uri;

    public String getUri() {
      return uri;
    }

    public void setUri(String uri) {
      this.uri = uri;
    }
  }
}
