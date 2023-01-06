package shpp.com.ua.example.validator;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import shpp.com.ua.example.model.Person;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;

class IpnValidatorImplTest {

    @Test
    void isValidMethodTestSetValidIpnAndReturnTrue() {
        Person person = new Person();
        person.setIpn("1234567899");
        ConstraintValidatorContext validator = Mockito.mock(ConstraintValidatorContext.class);
        boolean actual = new IpnValidatorImpl().isValid(person.getIpn(), validator);
        assertTrue(actual);
    }

    @Test
    void isValidMethodTestSetNotValidIpnAndReturnFalse() {
        Person person = new Person();
        person.setIpn("1234567898");
        ConstraintValidatorContext validator = Mockito.mock(ConstraintValidatorContext.class);
        boolean actual = new IpnValidatorImpl().isValid(person.getIpn(), validator);
        assertFalse(actual);
    }
}