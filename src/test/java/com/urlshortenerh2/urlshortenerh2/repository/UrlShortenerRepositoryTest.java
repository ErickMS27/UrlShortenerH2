package com.urlshortenerh2.urlshortenerh2.repository;

import com.urlshortenerh2.dto.UrlDetailDTO;
import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.repository.UrlShortenerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")

class UrlShortenerRepositoryTest {

    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Se existe URL Encurtada no DB")
    void findByShortLinkReturnIfExistsUrlShortener(){
        UrlShortener urlShortener = new UrlShortener("short", "long");
        urlShortenerRepository.save(urlShortener);
        UrlShortener existsUrl = urlShortenerRepository.findByShortLink("short");
        assertNotNull(existsUrl);
        assertEquals("short",existsUrl.getShortLink());
    }

    @Test
    @DisplayName("Se não existe URL Encurtada no DB")
    void findByShortLinkReturnNullIfNotExistsUrlShortener(){
        UrlShortener existsUrl = urlShortenerRepository.findByShortLink("nonexistent");
        assertNull(existsUrl);
    }

    @Test
    @DisplayName("Se existe URL Longa no DB")
    void findByLongLinkReturnIfExistsUrlShortener(){
        UrlShortener urlShortener = new UrlShortener("short", "long");
        UrlShortener existsUrl = urlShortenerRepository.findByLongLink("long");
        assertNotNull(existsUrl);
        assertEquals("long",existsUrl.getLongLink());
    }

    @Test
    @DisplayName("Se existem mais de 10 links no DB")
    void findTenFirstsByOrderByCountViewDesc_ShouldReturnTenFirstShortLinks(){
        for (int i = 0; i < 15; i++){
            urlShortenerRepository.save(new UrlShortener("short" + i, "long" + i, (long) i));
        }
        List<UrlShortener> first10Urls = urlShortenerRepository.findTop10ByOrderByVisitCountDesc();
        assertEquals(10,first10Urls.size());
        assertEquals("short14", first10Urls.get(0).getShortLink());
        assertEquals("short5", first10Urls.get(9).getShortLink());
    }

    @Test
    @DisplayName("Se existem menos de 10 links no DB")
    void findTenFirstsByOrderByCountViewDesc_ShouldReturnEmptyIfIsShortLinksFewerThanTen(){
        List<UrlShortener> first10Urls = urlShortenerRepository.findTop10ByOrderByVisitCountDesc();
        assertTrue(first10Urls.isEmpty());
    }

    @Test
    @DisplayName("Se a páginação está válida com os resultados")
    void findAllProjectBy_ShouldReturnUrlShortenerWithValidPagination(){
        for (int i =1; i<= 10; i++){
            urlShortenerRepository.save(new UrlShortener("short" + i,"long" + i));
        }
        Pageable pageable = PageRequest.of(0,5);
        Page<UrlDetailDTO> pageResult = urlShortenerRepository.findAllProjectBy(pageable);
        assertEquals(5, pageResult.getNumberOfElements());
    }

    @Test
    @DisplayName("Páginação válida e vazia")
    void findAllProjectBy_ShouldReturnEmptyPageWithValidPagination(){
        Pageable pageable = PageRequest.of(1,5);
        Page<UrlDetailDTO> pageResult = urlShortenerRepository.findAllProjectBy(pageable);
        assertTrue(pageResult.isEmpty());
    }

    @Test
    @DisplayName("Páginação inválida e vazia")
    void findAllProjectBy_ShouldReturnEmptyPageWithInvalidPagination(){
        Pageable pageable = PageRequest.of(0,5);
        Page<UrlDetailDTO> pageResult = urlShortenerRepository.findAllProjectBy(pageable);
        assertTrue(pageResult.isEmpty());
    }

}
