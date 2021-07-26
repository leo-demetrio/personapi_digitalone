package one.digitalinovation.personapi.service;

import one.digitalinovation.personapi.dto.request.PersonDTO;
import one.digitalinovation.personapi.entity.Person;
import one.digitalinovation.personapi.dto.MessageResponseDTO;
import one.digitalinovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinovation.personapi.utils.PersonUtils.*;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSaveMassage(){
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavePerson = createFakeEntity();

        when(personRepository.save(ArgumentMatchers.any(Person.class)))
                .thenReturn(expectedSavePerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavePerson.getId());

        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedSuccessMessage, successMessage);
    }
    private MessageResponseDTO createExpectedMessageResponse(Long id){

        return MessageResponseDTO
                .builder()
                .message("Success person with ID = " + id)
                .build();
    }

}
