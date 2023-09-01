package com.dwomo.houseowner.services;

import com.dwomo.houseowner.dto.PropertyEventDTO;
import com.dwomo.houseowner.events.domain.LandArea;
import com.dwomo.houseowner.events.domain.Location;
import com.dwomo.houseowner.events.domain.PropertyEvent;
import com.dwomo.houseowner.repository.PropertyRepository;
import com.dwomo.houseowner.service.PropertyService;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PropertyServiceTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private PropertyService propertyService;

    @MockBean
    private PropertyRepository propertyRepository;


    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetProperties() {
        PropertyEvent propertyEvent = new PropertyEvent(
                "64e2260672d21c199a99eb0e",
                "0000000015",
                BigDecimal.valueOf(20000.00),
                new LandArea(70, 100),
                1.0,
                "commercial",
                "land",
                new Location("Offinso", "44.0", "66.0"),
                new ArrayList<>(),
                new ArrayList<>(),
                "PENDING",
                true,
                "Kwadwo",
                new Date()
        );

        PropertyEventDTO expectedPropertyEventDTO = new PropertyEventDTO(
                "64e2260672d21c199a99eb0e",
                "0000000015",
                BigDecimal.valueOf(20000.00),
                new LandArea(70, 100),
                1.0,
                "commercial",
                "land",
                new Location("Offinso", "44.0", "66.0"),
                new ArrayList<>(),
                new ArrayList<>(),
                "PENDING",
                true,
                "Kwadwo",
                new Date()
        );

        when(propertyRepository.findAll()).thenReturn(Flux.just(propertyEvent));

        Flux<PropertyEventDTO> propertyEventDTOMono = propertyService.getProperties();

        StepVerifier.create(propertyEventDTOMono)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }

    @Test
    public void testGetProperty() {
        PropertyEvent propertyEvent = new PropertyEvent(
                "64e2260672d21c199a99eb0e",
                "0000000015",
                BigDecimal.valueOf(20000.00),
                new LandArea(70, 100),
                1.0,
                "commercial",
                "land",
                new Location("Offinso", "44.0", "66.0"),
                new ArrayList<>(),
                new ArrayList<>(),
                "PENDING",
                true,
                "Kwadwo",
                new Date()
        );

        PropertyEventDTO expectedPropertyEventDTO = new PropertyEventDTO(
                "64e2260672d21c199a99eb0e",
                "0000000015",
                BigDecimal.valueOf(20000.00),
                new LandArea(70, 100),
                1.0,
                "commercial",
                "land",
                new Location("Offinso", "44.0", "66.0"),
                new ArrayList<>(),
                new ArrayList<>(),
                "PENDING",
                true,
                "Kwadwo",
                new Date()
        );

        when(propertyRepository.findById("64e2260672d21c199a99eb0e")).thenReturn(Mono.just(propertyEvent));

        Mono<PropertyEventDTO> propertyEventDTOMono = propertyService.getProperty();

        StepVerifier.create(propertyEventDTOMono)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }


    @Test
    public void testGetPropertyCount() {
        Long expectedPropertyCount = 1L;

        when(propertyRepository.count()).thenReturn(Mono.just(1L));

        Mono<Long> propertyCount = propertyService.getPropertyCount();

        StepVerifier.create(propertyCount)
                .expectNext(expectedPropertyCount)
                .verifyComplete();
    }

    @Test
    public void testSaveProperty() {
        PropertyEventDTO expectedPropertyEventDTO = new PropertyEventDTO(
                "64e2260672d21c199a99eb0e",
                "0000000015",
                BigDecimal.valueOf(20000.00),
                new LandArea(70, 100),
                1.0,
                "commercial",
                "land",
                new Location("Offinso", "44.0", "66.0"),
                new ArrayList<>(),
                new ArrayList<>(),
                "PENDING",
                true,
                "Kwadwo",
                new Date()
        );

        PropertyEvent propertyEvent = new PropertyEvent(
                "64e2260672d21c199a99eb0e",
                "0000000015",
                BigDecimal.valueOf(20000.00),
                new LandArea(70, 100),
                1.0,
                "commercial",
                "land",
                new Location("Offinso", "44.0", "66.0"),
                new ArrayList<>(),
                new ArrayList<>(),
                "PENDING",
                true,
                "Kwadwo",
                new Date()
        );

        when(propertyRepository.save(Mockito.any(PropertyEvent.class))).thenReturn(Mono.just(propertyEvent));

        Mono<PropertyEventDTO> propertyCreatedEventDTOMono = propertyService.save(Mono.just(expectedPropertyEventDTO));

        StepVerifier.create(propertyCreatedEventDTOMono)
                .expectNextMatches(propertyEventDTO -> propertyEventDTO.getId().equals("64e2260672d21c199a99eb0e")
                        && propertyEventDTO.getLandTitleNumber().equals("0000000015")
                        && propertyEventDTO.getPrice().equals(BigDecimal.valueOf(20000.00))
                        && propertyEventDTO.getLandArea().equals(new LandArea(70, 100))
                        && propertyEventDTO.getNumberOfPlots().equals(1.0)
                        && propertyEventDTO.getLandType().equals("commercial")
                        && propertyEventDTO.getPropertyType().equals("land")
                        && propertyEventDTO.getLocation().equals(new Location("Offinso", "44.0", "66.0"))
                        && propertyEventDTO.getMessages().equals(new ArrayList<>())
                        && propertyEventDTO.getPictures().equals(new ArrayList<>())
                        && propertyEventDTO.getStatus().equals("PENDING")
                        && propertyEventDTO.isDeleted()
                        && propertyEventDTO.getCreatedBy().equals("Kwadwo"))
                .verifyComplete();
    }


    @Test
    public void testDeleteProperty() {

        when(propertyRepository.deleteById("64e2260672d21c199a99eb0e")).thenReturn(Mono.empty());

        Mono<Void> deletePropertyResponse = propertyService.delete();

        StepVerifier.create(deletePropertyResponse)
                .verifyComplete();
    }


    @Test
    public void testDeleteProperties() {
        when(propertyRepository.deleteAll()).thenReturn(Mono.empty());

        Mono<Void> deletePropertyResponse = propertyService.deleteProperties();

        StepVerifier.create(deletePropertyResponse)
                .verifyComplete();
    }
}
