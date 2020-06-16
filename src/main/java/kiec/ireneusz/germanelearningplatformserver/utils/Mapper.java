package kiec.ireneusz.germanelearningplatformserver.utils;

import kiec.ireneusz.germanelearningplatformserver.domain.post.Post;
import kiec.ireneusz.germanelearningplatformserver.domain.post.dto.PostDTO;
import kiec.ireneusz.germanelearningplatformserver.domain.user.Login;
import kiec.ireneusz.germanelearningplatformserver.domain.user.Person;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.PersonDTO;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.UserDTO;


public class Mapper {

    public static PersonDTO toPersonDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .mail(person.getMail())
                .role(person.getRole())
                .build();
    }

    public static PostDTO toPostDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .headline(post.getHeadline())
                .description(post.getDescription())
                .article(post.getArticle())
                .image(post.getImage())
                .link(post.getLink())
                .visible(post.isVisible())
                .build();
    }

    public static UserDTO toUserDTO(Login login) {
        return UserDTO.builder()
                .id(login.getId())
                .mail(login.getMail())
                .password(login.getPassword())
                .firstName(login.getPerson().getFirstName())
                .lastName(login.getPerson().getLastName())
                .active(login.isActive())
                .authorities(login.getAuthorities())
                .build();
    }
}
