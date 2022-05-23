package lt.codeacademy.rentProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PropertyType")
    private String propertytype;

    @Column(name = "Municipality")
    private String municipality;

    @Column(name = "NoOfRooms")
    private String noofrooms;

    @Column(name = "Area")
    private String area;

    @Column(name = "Equipment")
    private String equipment;

    @Column(name = "PricePerSqMeter")
    private String pricepersqmeter;

    @Column(name = "BuitYear")
    private String builtyear;

    @Column(name = "Floors")
    private String floors;

    @Column(name = "Floor")
    private String floor;

    @Column(name = "Seller")
    private String seller;


    @Column(name = "price")
    @Min(value = 0, message = "#{Min.property.price}")
    private Integer price;

    @Column(name = "description")
    private String description;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

}

