package entities;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.aggregates.entities.Property;
import com.houseowner.property.interfaces.PropertyInterface;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class PropertyTest {
    Property property;
    PropertyDTO newPropertyDTO;
    PropertyDTO updatePropertyDTO;
    String type = "land";
    String updatedType = "house";
    String landTitleNumber = "0001";
    String updatedLandTitleNumber = "0002";
    double landSize = 2.5;
    double updatedLandSize = 3.5;
    BigDecimal price = BigDecimal.valueOf(5000.00);
    BigDecimal updatedPrice = BigDecimal.valueOf(1000.00);
    String location = "London";
    String updatedLocation = "Derby";
    String pictures = "helloEarth.jpg";
    String updatedPictures = "helloVenus.png";
    String status = "PENDING";
    String updatedStatus = "ACTIVE";
    String owner = "KWADWO DWOMO II";
    String updatedOwner = "NANA BOAKYEWAA";
    private final String PROPERTY_ID = "0";


    @BeforeMethod
    public void setup() {

        property = new Property();

        updatePropertyDTO = new PropertyDTO(
                updatedType,
                updatedLandTitleNumber,
                updatedLandSize,
                updatedPrice,
                updatedLocation,
                updatedPictures,
                updatedOwner);

        newPropertyDTO = new PropertyDTO(
                type,
                landTitleNumber,
                landSize,
                price,
                location,
                pictures,
                owner);
    }

    @Test
    public void testCreate() {

        Mono<Property> newProperty = property.create(newPropertyDTO);

        newProperty.flatMap(property -> {

            assertThat(property.getType()).isEqualTo(type);
            assertThat(property.getLandTitleNumber()).isEqualTo(landTitleNumber);
            assertThat(property.getLandSize()).isEqualTo(landSize);
            assertThat(property.getPrice()).isEqualTo(price);
            assertThat(property.getLocation()).isEqualTo(location);
            assertThat(property.getPictures()).isEqualTo(pictures);
            assertThat(property.getStatus()).isEqualTo(status);
            assertThat(property.getOwner()).isEqualTo(owner);
            assertThat(property.isDeleted()).isFalse();

            return newProperty;
        });
    }

    @Test
    public void testUpdate() {

        Mono<Property> updatedProperty = property.create(newPropertyDTO);

        updatedProperty.flatMap(updatedPropertyMono -> {

            updatedPropertyMono.update(updatePropertyDTO);

            return updatedProperty;
        });

        updatedProperty.flatMap(property -> {

            assertThat(property.getType()).isEqualTo(updatedType);
            assertThat(property.getLandTitleNumber()).isEqualTo(updatedLandTitleNumber);
            assertThat(property.getLandSize()).isEqualTo(updatedLandSize);
            assertThat(property.getPrice()).isEqualTo(updatedPrice);
            assertThat(property.getLocation()).isEqualTo(updatedLocation);
            assertThat(property.getPictures()).isEqualTo(updatedPictures);
            assertThat(property.getStatus()).isEqualTo(status);
            assertThat(property.isDeleted()).isFalse();

            return updatedProperty;
        });

    }


    @Test
    public void testDelete() {

        Mono<Property> deletedProperty = property.create(newPropertyDTO);

        deletedProperty.flatMap(updatedPropertyMono -> {

            updatedPropertyMono.delete();
            return deletedProperty;
        });

        deletedProperty.flatMap(property -> {

            assertThat(property.isDeleted()).isTrue();

            return deletedProperty;
        });

    }

    @Test
    public void testBuy() {

        Mono<Property> boughtProperty = property.create(newPropertyDTO);

        boughtProperty.flatMap(boughtPropertyMono -> {

            boughtPropertyMono.buy();

            return boughtProperty;
        });

        boughtProperty.flatMap(property -> {

            assertThat(property.isDeleted()).isTrue();

            return boughtProperty;
        });
    }


    @Test
    public void testChangeStatus() {

        Mono<Property> activeProperty = property.create(newPropertyDTO);

        activeProperty.flatMap(activePropertyMono -> {

            activePropertyMono.changeStatus();

            return activeProperty;
        });

        activeProperty.flatMap(property -> {

            assertThat(property.getStatus()).isEqualTo(updatedStatus);

            return activeProperty;
        });
    }
}
