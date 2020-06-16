package kiec.ireneusz.germanelearningplatformserver.domain.user;

import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.RegisterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {

    private final PeopleRepository repository;

    @Autowired
    PeopleService(PeopleRepository repository) {
        this.repository = repository;
    }

    Person create(RegisterApi api) {
        Person person = new Person(api);
        System.out.println(person.toString());
        return repository.save(person);
    }

}