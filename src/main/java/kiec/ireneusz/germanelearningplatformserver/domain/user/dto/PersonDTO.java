package kiec.ireneusz.germanelearningplatformserver.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kiec.ireneusz.germanelearningplatformserver.domain.user.Person;
import kiec.ireneusz.germanelearningplatformserver.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
    private Role role;
    private String description;
    private String reference;
    private String image;

    public PersonDTO(Person person){
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.mail = person.getMail();
        this.role = person.getRole();
        this.description = person.getDescription();
        this.reference = person.getReference();
        this.image = person.getImage();
    }
}
