/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.controller;

import com.rsegeda.bookingservice.service.ImportService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/import")
@Transactional
public class ImportController {

  private final ImportService importService;

  @PostMapping
  public ResponseEntity uploadFiles(
      @RequestParam final MultipartFile appointments,
      @RequestParam final MultipartFile clients,
      @RequestParam final MultipartFile services,
      @RequestParam final MultipartFile purchases
  ) {
    importService.triggerImport(clients, appointments, purchases, services);
    return ResponseEntity.ok("Import requested");
  }
}
