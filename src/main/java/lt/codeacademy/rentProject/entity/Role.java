package lt.codeacademy.rentProject.entity;

import lombok.Data;
import javax.persistence.*;

@Table(name="roles")
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RolesAuthority role;

}
