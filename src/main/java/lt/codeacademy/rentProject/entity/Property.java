package lt.codeacademy.rentProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @Size(min = 1, max = 20, message = "#{Min.property.title}" )
    private String title;


    @Column(name = "price")
    @Min(value = 0, message = "#{Min.property.price}")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

