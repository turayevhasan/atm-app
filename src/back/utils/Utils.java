package back.utils;

public interface Utils {

    static boolean isValidUserPassword(String password) {
        return password.length() >= 8;
    }

    static boolean isValidCardPassword(int password) {
        String str = String.valueOf(password);
        return str.length() == 4;
    }

    static boolean isValidCardNumber(String cardNumber) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < cardNumber.length(); i++) {
            if (Character.isAlphabetic(cardNumber.charAt(i)))
                return false;
            if (cardNumber.charAt(i) != ' ')
                str.append(cardNumber.charAt(i));
        }
        return str.length() == 16;
    }

}
