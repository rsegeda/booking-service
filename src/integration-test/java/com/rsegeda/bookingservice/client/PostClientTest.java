/*
    Created by Roman Segeda on 03 February 2020
*/

package com.rsegeda.bookingservice.client;

import com.rsegeda.bookingservice.controller.dto.ClientDTO;
import com.rsegeda.bookingservice.service.ClientService;
import com.rsegeda.bookingservice.service.model.helpers.Gender;

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
class PostClientTest {

  private final ClientDTO clientDTOAfterSave = ClientDTO.builder()
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
  }

  @Test
  void postClientHasCorrectHttpStatus() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .post("/clients")
        .content(objectMapper.writeValueAsString(clientDTOAfterSave))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  void postClientHasCorrectBody() throws Exception {
    when(clientService.create(clientDTOFromRequest)).thenReturn(clientDTOAfterSave);
    String contentAsString =
        mockMvc.perform(MockMvcRequestBuilders
            .post("/clients")
            .content(objectMapper.writeValueAsString(clientDTOFromRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andReturn().getResponse().getContentAsString();

    ClientDTO response = objectMapper.readValue(contentAsString, ClientDTO.class);

    Assertions.assertEquals(clientDTOAfterSave.getId(), response.getId());
    Assertions.assertEquals(clientDTOFromRequest.getBanned(), response.getBanned());
    Assertions.assertEquals(clientDTOFromRequest.getEmail(), response.getEmail());
    Assertions.assertEquals(clientDTOFromRequest.getFirstName(), response.getFirstName());
    Assertions.assertEquals(clientDTOFromRequest.getLastName(), response.getLastName());
    Assertions.assertEquals(clientDTOFromRequest.getPhone(), response.getPhone());
    Assertions.assertEquals(clientDTOFromRequest.getGender(), response.getGender());
  }
}
