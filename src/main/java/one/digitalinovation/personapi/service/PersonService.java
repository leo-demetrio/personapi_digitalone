package one.digitalinovation.personapi.service;

import one.digitalinovation.personapi.dto.MessageResponseDTO;
import one.digitalinovation.personapi.dto.request.PersonDTO;
import one.digitalinovation.personapi.entity.Person;
import one.digitalinovation.personapi.mapper.PersonMapper;
import one.digitalinovation.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public PersonService(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);
        Person savePerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID = " + savePerson.getId())
                .build();
    }
}
