package lt.codeacademy.rentProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Propertytype")
    private String propertytype;

    @Column(name = "Municipality")
    private String municipality;

    @Column(name = "Noofrooms")
    private Integer noofrooms;

    @Column(name = "Area")
    private Integer area;

    @Column(name = "Equipment")
    private String equipment;

    @Column(name = "Pricepersqmeter")
    private Integer pricepersqmeter;

    @Column(name = "Builtyear")
    private Integer builtyear;

    @Column(name = "Floors")
    private String floors;

    @Column(name = "Floor")
    private String floor;

    @Column(name = "Seller")
    private String seller;

    @Column(name = "Adress")
    private String adress;

    @Column(name = "Price")
    @Min(value = 0, message = "#{Min.property.price}")
    private Integer price;

    @Column(name = "Description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade=ALL, mappedBy = "property")
    private Set<Image> images;

    @Transient
    private String firstImagePath;

}

