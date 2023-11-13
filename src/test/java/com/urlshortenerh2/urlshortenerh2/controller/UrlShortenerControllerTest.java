package com.urlshortenerh2.urlshortenerh2.controller;

import com.urlshortenerh2.controller.UrlShortenerController;
import com.urlshortenerh2.dto.UrlDetailDTO;
import com.urlshortenerh2.dto.UrlShortenerRequestDTO;
import com.urlshortenerh2.dto.UrlShortenerResponseDTO;
import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.service.UrlShortenerService;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static javax.management.Query.eq;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UrlShortenerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UrlShortenerRequestDTO> urlShortenerRequestJacksonTester;

    @Autowired
    private JacksonTester<UrlShortenerResponseDTO> urlShortenerResponseJacksonTester;

    @MockBean
    private UrlShortenerService urlShortenerService;

    @InjectMocks
    private UrlShortenerController urlShortenerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Retorne o código HTTP400 quando as informações não estão validadas");
    void scenario1(){
            var response = mvc.perform(post("/api/urlshortener"))
                    .andReturn().getResponse();
            assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }

    @Test
    @DisplayName("Retorne o código HTTP200 quando as informações não estão validadas");
    void scenario2 () throws Exception{
        UrlShortenerRequestDTO urlRequestTest = new UrlShortenerRequestDTO();
        urlRequestTest.setLongLink("https://cursos.alura.com.br/dashboard");

        var urlShortenerResponse = new UrlShortenerResponseDTO();
        urlShortenerResponse.setShortLink("/api/00ad38cf");

        when(urlShortenerService.generateShortLink(any())).thenReturn(urlShortenerResponse);

        var response = mvc.perform(post("/api/urlshortener")
                .contentType(MediaType.APPLICATION_JSON)
                .content(urlShortenerResponseJacksonTester.write(urlRequestTest).getJson())
        )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var calledJson = urlShortenerResponseJacksonTester.write((urlShortenerResponse).getJson();
        assertThat(response.getContentAsString()).isEqualTo(calledJson);
    }

    @Test
    @DisplayName("Retorne o código HTTP404 quando a URL encurtada não aparecer")
    void scenario3 () throws Exception {
    String key = "non-existent-key";
    String shortLink = "http://localhost/" + key;

    when(urlShortenerService.generateShortLink(eq(shortLink))).thenReturn(null);

    var response = mvc.perform(MockMvcRequestBuilders.get("/" + key))
            .andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    @DisplayName("Detalhar Url por ID - URL Found")
    void scenario4(){
        Long id = 1L;
        UrlDetailDTO urlDetailDTO = new UrlDetailDTO(id, "http://urlshort/abc123", "http://www.example.com", 5L);
        when(UrlShortenerService.urlDetailForId(id)).theReturn(urlDetailDTO);

        ResponseEntity<UrlDetailDTO> response = UrlShortenerController.urlDetailForId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(UrlDetailDTO, response.getBody());
    }

    @Test
    @DisplayName("Detalhar Url por ID - URL Not Found")
    void scenario5(){
        Long id = 1L;
        when(urlShortenerService.detailUrlForId(id).thenReturn(null));

        ResponseEntity<UrlDetailDTO> response = urlShortenerController.detailUrlForId(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Listar URL")
    void scenario6(){
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<UrlDetailDTO> page = mock(Page.class);
        when(urlShortenerService.listPage(pageRequest)).thenReturn(page);

        ResponseEntity<Page<UrlDetailDTO>> response = urlShortenerController.listUrl(pageRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(page);
    }

    @Test
    @DisplayName("Excluir URL")
    void scenario7(){
        Long id = 1L;
        ResponseEntity<Void> response = urlShortenerController.deleteUrl(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Atualizar LongLink - Url Found")
    void scenario8(){
        Long id = 1L;
        String newLongLink = "http://www.new-example.com";
        UpdateUrlDTO updateUrl = new UpdateUrlDTO();
        updateUrl.setId(id);
        updateUrl.setLongLink(newLongLink);

        UrlShortener updatedUrlShortener = new UrlShortener();
        when(urlShortenerService.updateLongLink(id, newLongLink)).thenReturn(updatedUrlShortener);

        ResponseEntity<String> response = urlShortenerController.updateLongLink(updateUrl);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("Atualizar LongLink - Url Not Found")
    void scenario9(){
        Long id = 1L;
        String newLongLink = "http://www.new-example.com";
        UpdateUrlDTO updateUrl = UpdateUrlDTO();
        when(urlShortenerService.updateLongLink(id, newLongLink)).thenReturn(null);

        ResponseEntity<String> response = urlShortenerController.updateLongLink(updateUrl);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Liste os Top 10 Url's")
    void scenario10(){

        List<UrlShortener> top10urls = createTop10Urls() {
            when(urlShortenerService.getTop10VisitedLinks()).thenReturn(top10urls);

            List<UrlShortener> response = UrlShortenerController.getTop10VisitedLinks();

            assertThat(response).isEqualTo(top10urls);
    }

    private List<UrlShortener> createTop10Urls(){
            List<UrlShortener> urls = new ArrayList<>();
            for (int i =1; i <=10; i++){
                UrlShortener url = new UrlShortener();
                url.setId((long) i);
                url.setShortLink("http://urlshortener/" + i);
                url.setLongLink("http://www.example.com/" + i);
                urls.add(url);
            }
            return urls;
        }
    }
}
