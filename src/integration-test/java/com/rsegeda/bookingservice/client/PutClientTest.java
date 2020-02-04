/*
    Created by Roman Segeda on 03 February 2020
*/

package com.rsegeda.bookingservice.client;

import com.rsegeda.bookingservice.controller.dto.ClientDTO;
import com.rsegeda.bookingservice.service.ClientService;
import com.rsegeda.bookingservice.service.model.helpers.Gender;

import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( {RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class PutClientTest {

  private final ClientDTO clientDTOBeforeRequest = ClientDTO.builder()
      .id(UUID.randomUUID().toString())
      .firstName("Jane")
      .lastName("Doe")
      .email("jane.doe@mail.com")
      .gender(Gender.FEMALE)
      .phone("+1321321321")
      .build();
  private final ClientDTO clientDTOFromRequest = ClientDTO.builder()
      .firstName("Jane")
      .lastName("Doe")
      .email("jane.doe@mail.com")
      .gender(Gender.FEMALE)
      .phone("+666")
      .build();
  private final ClientDTO clientDTOExpected = ClientDTO.builder()
      .id(clientDTOBeforeRequest.getId())
      .firstName(clientDTOBeforeRequest.getFirstName())
      .lastName(clientDTOBeforeRequest.getLastName())
      .email(clientDTOBeforeRequest.getEmail())
      .gender(clientDTOBeforeRequest.getGender())
      .phone(clientDTOFromRequest.getPhone())
      .build();
  @MockBean
  ClientService clientService;
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp(WebApplicationContext webApplicationContext,
             RestDocumentationContextProvider restDocumentation) {

    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation))
        .alwaysDo(
            document("{method-name}", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
        .build();

    when(clientService.update(clientDTOBeforeRequest.getId(), clientDTOFromRequest))
        .thenReturn(Optional.of(clientDTOExpected));
  }

  @Test
  void putClientHasCorrectHttpStatus() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .put("/clients/" + clientDTOBeforeRequest.getId())
        .content(objectMapper.writeValueAsString(clientDTOFromRequest))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void putClientHasCorrectBody() throws Exception {
    String contentAsString =
        mockMvc.perform(MockMvcRequestBuilders
            .put("/clients/" + clientDTOExpected.getId())
            .content(objectMapper.writeValueAsString(clientDTOFromRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andReturn().getResponse().getContentAsString();

    ClientDTO response = objectMapper.readValue(contentAsString, ClientDTO.class);

    Assertions.assertEquals(clientDTOExpected.getId(), response.getId());
    Assertions.assertEquals(clientDTOExpected.getId(), response.getId());
    Assertions.assertEquals(clientDTOExpected.getBanned(), response.getBanned());
    Assertions.assertEquals(clientDTOExpected.getEmail(), response.getEmail());
    Assertions.assertEquals(clientDTOExpected.getFirstName(), response.getFirstName());
    Assertions.assertEquals(clientDTOExpected.getLastName(), response.getLastName());
    Assertions.assertEquals(clientDTOExpected.getPhone(), response.getPhone());
    Assertions.assertEquals(clientDTOExpected.getGender(), response.getGender());
  }
}
