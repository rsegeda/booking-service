/*
    Created by Roman Segeda on 03 February 2020
*/

package com.rsegeda.bookingservice.client;

import com.rsegeda.bookingservice.service.ClientService;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( {RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class DeleteClientTest {

  @MockBean
  ClientService clientService;
  private MockMvc mockMvc;

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
  void deleteClientHasCorrectHttpStatus() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .delete("/clients/{id}", UUID.randomUUID().toString())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}
