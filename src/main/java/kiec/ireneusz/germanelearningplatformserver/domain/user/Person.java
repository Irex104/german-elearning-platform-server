package kiec.ireneusz.germanelearningplatformserver.domain.user;

import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.RegisterApi;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.UpdateApi;
import kiec.ireneusz.germanelearningplatformserver.utils.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "people")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String description;
    private String reference;
    private String image;

    public Person(RegisterApi api) {
        this.firstName = api.getFirstName();
        this.lastName = api.getLastName();
        this.mail = api.getMail();
        this.role = api.getRole();
        this.mail = api.getMail();
        this.description = api.getDescription();
        this.reference = api.getReference();
        this.image = api.getImage();
    }

    public void update(UpdateApi api){
        this.firstName = api.getFirstName();
        this.lastName = api.getLastName();
        this.mail = api.getMail();
        this.description = api.getDescription();
        this.reference = api.getReference();
        this.image = api.getImage();
    }

    public void giveRole(Role role){
        this.role = role;
    }
}
