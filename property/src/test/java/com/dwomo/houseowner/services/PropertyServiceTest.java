package com.dwomo.houseowner.services;

import com.dwomo.houseowner.PropertyApplication;
import com.dwomo.houseowner.dto.PropertyEventDTO;
import com.dwomo.houseowner.events.domain.LandArea;
import com.dwomo.houseowner.events.domain.Location;
import com.dwomo.houseowner.events.domain.Message;
import com.dwomo.houseowner.service.PropertyService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.when;

@SpringBootTest
@DataMongoTest
@ContextConfiguration(classes = PropertyApplication.class)
public class PropertyServiceTest {

    @Mock
    private PropertyService propertyService;


   @BeforeMethod
   public void setup() {
       //
       MockitoAnnotations.openMocks(this);
   }

    @Test
    public void testGetActiveProperties() {
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
                "ACTIVE",
                false,
                "Kwadwo",
                new Date()
        );

        when(propertyService.getActiveProperties()).thenReturn(Flux.just(expectedPropertyEventDTO));

        Flux<PropertyEventDTO> propertyCreatedEventDTOFlux = propertyService.getActiveProperties();

        StepVerifier.create(propertyCreatedEventDTOFlux)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }


    @Test
    public void testGetPendingProperties() {
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
                false,
                "Kwadwo",
                new Date()
        );

        when(propertyService.getPendingProperties()).thenReturn(Flux.just(expectedPropertyEventDTO));

        Flux<PropertyEventDTO> propertyCreatedEventDTOFlux = propertyService.getPendingProperties();

        StepVerifier.create(propertyCreatedEventDTOFlux)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }


    @Test
    public void testGetSoftDeletedProperties() {
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

        when(propertyService.getSoftDeletedProperties()).thenReturn(Flux.just(expectedPropertyEventDTO));

        Flux<PropertyEventDTO> propertyCreatedEventDTOFlux = propertyService.getSoftDeletedProperties();

        StepVerifier.create(propertyCreatedEventDTOFlux)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }


    @Test
    public void testGetNonSoftDeletedProperty() {
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
                false,
                "Kwadwo",
                new Date()
        );

        when(propertyService.getNonSoftDeleted()).thenReturn(Flux.just(expectedPropertyEventDTO));

        Flux<PropertyEventDTO> propertyCreatedEventDTOFlux = propertyService.getNonSoftDeleted();

        StepVerifier.create(propertyCreatedEventDTOFlux)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }


    @Test
    public void testGetPropertiesBetweenPriceRange() {
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
                false,
                "Kwadwo",
                new Date()
        );

        BigDecimal minimumPrice = BigDecimal.valueOf(1000);
        BigDecimal maximumPrice = BigDecimal.valueOf(2000);

        when(propertyService.getPropertiesBetweenPriceRange(minimumPrice, maximumPrice)).thenReturn(Flux.just(expectedPropertyEventDTO));

        Flux<PropertyEventDTO> propertyCreatedEventDTOFlux = propertyService.getPropertiesBetweenPriceRange(minimumPrice, maximumPrice);

        StepVerifier.create(propertyCreatedEventDTOFlux)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }


    @Test
    public void testSoftDelete() {
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

        when(propertyService.softDelete()).thenReturn(Mono.just(expectedPropertyEventDTO));

        Mono<PropertyEventDTO> deletePropertyResponse = propertyService.softDelete();

        StepVerifier.create(deletePropertyResponse)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }


    @Test
    public void testSetPropertyStatus() {
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

        when(propertyService.setPropertyStatus("ACTIVE")).thenReturn(Mono.just(expectedPropertyEventDTO));

        Mono<PropertyEventDTO> setPropertyStatusResponse = propertyService.setPropertyStatus("ACTIVE");

        StepVerifier.create(setPropertyStatusResponse)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }


    @Test
    public void testCreateMessage() {
       List<Message> messages = new ArrayList<>();

       Message firstPotentialBuyerMessage = new Message("Hello first Property Potential Buyer Message", "Kwadwo Dwomo II", new Date());
       Message secondPotentialBuyerMessage = new Message("Hello second Property Potential Buyer Message", "Addo Baffour Dwomo", new Date());
       Message thirdPotentialBuyerMessage = new Message("Hello third Property Potential Buyer Message", "Samuel Dwomoh", new Date());

       messages.add(firstPotentialBuyerMessage);
       messages.add(secondPotentialBuyerMessage);
       messages.add(thirdPotentialBuyerMessage);

        PropertyEventDTO expectedPropertyEventDTO = new PropertyEventDTO(
                "64e2260672d21c199a99eb0e",
                "0000000015",
                BigDecimal.valueOf(20000.00),
                new LandArea(70, 100),
                1.0,
                "commercial",
                "land",
                new Location("Offinso", "44.0", "66.0"),
                messages,
                new ArrayList<>(),
                "PENDING",
                true,
                "Kwadwo",
                new Date()
        );

        when(propertyService.createMessage(messages)).thenReturn(Mono.just(expectedPropertyEventDTO));

        Mono<PropertyEventDTO> propertyEventDTOMono = propertyService.createMessage(messages);

        StepVerifier.create(propertyEventDTOMono)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }


    @Test
    public void testSearchProperty() {
       String locationName = "Offinso";

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

        when(propertyService.searchProperty(locationName)).thenReturn(Flux.just(expectedPropertyEventDTO));

        Flux<PropertyEventDTO> propertyEventDTOFlux = propertyService.searchProperty(locationName);

        StepVerifier.create(propertyEventDTOFlux)
                .expectNext(expectedPropertyEventDTO)
                .verifyComplete();
    }
}
