package shpp.com.ua.example.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class IpnValidatorImpl implements ConstraintValidator<IpnValidator, String> {
    private static final int SIZE = 10;
    private List<Integer> listOfIpnDigits;
    @Override
    public boolean isValid(String ipn, ConstraintValidatorContext constraintValidatorContext) {
        return checkIpn(ipn);
    }
    private boolean checkIpn(String ipn) {
        Integer lastNumber = countLastNumber(ipn);
        if ((lastNumber != null)) {
            return listOfIpnDigits.get(SIZE - 1).equals(lastNumber);
        } else {
            return false;
        }
    }

    private Integer countLastNumber(String ipn) {
        convertCharsToInt(ipn);
        if (listOfIpnDigits.size() == SIZE) {
            return (listOfIpnDigits.get(0) * (-1) +
                    listOfIpnDigits.get(1) * 5 +
                    listOfIpnDigits.get(2) * 7 +
                    listOfIpnDigits.get(3) * 9 +
                    listOfIpnDigits.get(4) * 4 +
                    listOfIpnDigits.get(5) * 6 +
                    listOfIpnDigits.get(6) * 10 +
                    listOfIpnDigits.get(7) * 5 +
                    listOfIpnDigits.get(8) * 7) % 11;
        } else {
            return null;
        }
    }

    private void convertCharsToInt(String ipn) {
        listOfIpnDigits = new ArrayList<>();
        for (int i = 0; i < ipn.length(); i++) {
            listOfIpnDigits.add(ipn.charAt(i) - '0');
        }
    }

}
