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
class GetClientTest {

  private final ClientDTO clientDTO = ClientDTO.builder()
      .id(UUID.randomUUID().toString())
      .firstName("Jane")
      .lastName("Doe")
      .email("jane.doe@mail.com")
      .gender(Gender.FEMALE)
      .phone("+1321321321")
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

    when(clientService.findById(clientDTO.getId())).thenReturn(Optional.of(clientDTO));
  }

  @Test
  void getClientHasCorrectHttpStatus() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .get("/clients/{id}", clientDTO.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void getClientHasCorrectBody() throws Exception {
    String contentAsString = mockMvc.perform(MockMvcRequestBuilders
        .get("/clients/{id}", clientDTO.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString();

    ClientDTO response = objectMapper.readValue(contentAsString, ClientDTO.class);

    Assertions.assertEquals(clientDTO.getId(), response.getId());
    Assertions.assertEquals(clientDTO.getBanned(), response.getBanned());
    Assertions.assertEquals(clientDTO.getEmail(), response.getEmail());
    Assertions.assertEquals(clientDTO.getFirstName(), response.getFirstName());
    Assertions.assertEquals(clientDTO.getLastName(), response.getLastName());
    Assertions.assertEquals(clientDTO.getPhone(), response.getPhone());
    Assertions.assertEquals(clientDTO.getGender(), response.getGender());
  }
}
