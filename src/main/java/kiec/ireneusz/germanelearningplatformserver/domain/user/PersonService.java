package kiec.ireneusz.germanelearningplatformserver.domain.user;

import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.PersonDTO;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.UpdateApi;
import kiec.ireneusz.germanelearningplatformserver.exception.NotFoundException;
import kiec.ireneusz.germanelearningplatformserver.utils.Mapper;
import kiec.ireneusz.germanelearningplatformserver.utils.PageableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PeopleRepository repository;

    @Autowired
    public PersonService(
            PeopleRepository repository
    ) {
        this.repository = repository;
    }

    List<PersonDTO> getAll(PageableRequest pageableRequest) {
        Page<Person> peoplePage = repository.findAllByDeletedAtIsNull(
                PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize())
        );
        return peoplePage.stream().map(Mapper::toPersonDTO)
                .collect(Collectors.toList());
    }

    Person getOne(Long personId) throws NotFoundException {
        return repository.findByIdAndDeletedAtIsNull(personId)
                .orElseThrow(() -> new NotFoundException());
    }

    List<Person> getByMail(String userMail, PageableRequest pageableRequest){
        Page<Person> peoplePage = repository.findByMailLikeAndDeletedAtIsNull(
                "%" + userMail + "%",
                PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize()));
        return peoplePage.stream().collect(Collectors.toList());
    }

    void giveRole(Long personId, Role role) throws NotFoundException {
        Person person = this.getOne(personId);
        person.giveRole(role);
        repository.save(person);
    }

    Person update(Long personId, UpdateApi api) throws NotFoundException {
        Person person = repository.findByIdAndDeletedAtIsNull(personId)
                .orElseThrow(() -> new NotFoundException());
        person.update(api);
        person = repository.save(person);
        return person;
    }

    void delete(Long personId) throws NotFoundException {
        Person person = repository.findByIdAndDeletedAtIsNull(personId)
                .orElseThrow(() -> new NotFoundException());
        person.delete();
        repository.save(person);
    }
}
