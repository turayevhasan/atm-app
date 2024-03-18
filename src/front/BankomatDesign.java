package front;

import back.controller.BankomatControllerImpl;
import back.controller.CardControllerImpl;
import back.controller.contracts.AuthController;
import back.controller.AuthControllerImpl;
import back.controller.contracts.BankomatController;
import back.controller.contracts.CardController;
import back.payload.*;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BankomatDesign {

    private final static AuthController authController = AuthControllerImpl.getInstance();
    private final static CardController cardController = CardControllerImpl.getInstance();
    private final static BankomatController bankomatController = BankomatControllerImpl.getInstance();

    private final static Scanner scannerInt = new Scanner(System.in);
    private final static Scanner scannerDouble = new Scanner(System.in);
    private final static Scanner scannerStr = new Scanner(System.in);
    private static UserDTO currentUser = null;


    public static void main(String[] args) {

        while (true) {
            boolean islogin = false;

            while (!islogin) {
                boolean exit = false;
                showRegistration();

                int auth = getInputAsInt("\nChoose => ");

                switch (auth) {
                    case 0 -> exit = true;
                    case 1 -> islogin = signIn();
                    case 2 -> islogin = signUp();
                    default -> System.out.println("Error ❌");
                }

                if (exit) {
                    System.out.println("\nThank you 😊😊😊");
                    break;
                }
            }

            while (islogin) {
                showMenu();
                int menu = getInputAsInt("\nChoose => ");

                switch (menu) {
                    case 0 -> islogin = false;
                    case 1 -> addNewCar();
                    case 2 -> {
                        CardDTO card = myCards();
                        BankomatDTO bankomat = chooseBankomat();
                        boolean cardIsInBankomat = enterBankomat(card, bankomat);

                        while (cardIsInBankomat) {
                            cardOptions();

                            int option = getInputAsInt("\nChoose => ");

                            switch (option) {
                                case 0 -> cardIsInBankomat = false;
                                case 1 -> fillBalance(card, bankomat);
                                case 2 -> getMyBalance(card);
                                default -> System.out.println("Error ❌");
                            }

                        }

                    }
                    case 3 -> bankomats();
                    default -> System.out.println("Error ❌");
                }
            }

        }

    }

    private static void getMyBalance(CardDTO card) {
        System.out.println("--------------- Your Balance ----------------");
        System.out.println("Your balance : " + card.balance());
    }

    private static void fillBalance(CardDTO card, BankomatDTO bankomat) {
        System.out.println("------------------- Fill Your Balance ----------------------");
        try {
            Double money = getInputAsDouble("Enter money :");
            bankomatController.fillCardBalance(card.id(), money, bankomat.percentTransaction());
            System.out.println("Successfully ✅✅✅");
        } catch (Exception e) {
            System.out.println("User has an error : " + e.getMessage());
        }
    }

    private static void cardOptions() {
        System.out.println("------------- Options ----------------");
        System.out.println("1 => Fill Balance");
        System.out.println("2 => Show Balance");
        System.out.println("0 => Back");
        System.out.println();
    }


    private static boolean enterBankomat(CardDTO card, BankomatDTO bankomat) {
        System.out.println("--------------- Enter Bankomat ------------------");
        boolean isLogged = false;
        try {
            int password = getInputAsInt("Enter your card password : ");

            if (card.cardPassword() != password) {
                System.out.println("Password is incorrect ❌");
                return false;
            }

            isLogged = true;

        } catch (Exception e) {
            System.out.println("User has an error : " + e.getMessage());
        }
        return isLogged;
    }

    private static BankomatDTO chooseBankomat() {
        System.out.println("--------------- Bankomats ----------------");

        BankomatDTO choosenBankomat = null;
        List<BankomatDTO> all = bankomatController.getAll();
        int i = 1;

        try {
            for (BankomatDTO bankomat : all) {
                System.out.println(i + " => " + bankomat);
                i++;
            }
            int index = getInputAsInt("\nChoose => ");
            choosenBankomat = all.get(index - 1);

        } catch (Exception e) {
            System.out.println("User has an error : " + e.getMessage());
        }

        return choosenBankomat;
    }


    private static void bankomats() {
        System.out.println("---------------- Bankomats -------------------");

        List<BankomatDTO> all = bankomatController.getAll();
        int i = 1;

        for (BankomatDTO bankomat : all) {
            System.out.println(i + " => " + bankomat);
            i++;
        }
    }

    private static CardDTO myCards() {
        System.out.println("----------------- My Cards -------------------");

        CardDTO choosenCard = null;
        List<CardDTO> cards = cardController.myCards(currentUser.id());
        int i = 1;

        try {
            for (CardDTO card : cards) {
                System.out.println(i + " => Card [" + card.cardNumber() + "]" + " ,Status[" + card.isActive() + "]");
                i++;
            }

            int index = getInputAsInt("\nChoose => ");
            choosenCard = cards.get(index - 1);

        } catch (Exception e) {
            System.out.println("User has an error : " + e.getMessage());
        }
        return choosenCard;
    }


    private static void addNewCar() {
        System.out.println("-------------- Add New Card ---------------------");

        try {
            String cardNumber = getInputAsString("Card Number : ");
            int cardPassword = getInputAsInt("Card Password : ");

            UUID userId = currentUser.id();

            cardController.createCard(new CardAddDTO(cardNumber, cardPassword, 0.0, userId, true));
            System.out.println("Card successfully added ✅");

        } catch (Exception e) {
            System.out.println("User has an error : " + e.getMessage());
        }
    }

    private static boolean signUp() {
        System.out.println("---------------- Sign Up ------------------");

        try {
            String firstName = getInputAsString("FirstName : ");
            String lastName = getInputAsString("LastName : ");
            String username = getInputAsString("Username : ");
            String password = getInputAsString("Password : ");

            currentUser = authController.signUp(new SignUpDTO(firstName, lastName, username, password));
            System.out.println("\nUser successfully registered ✅");
            return true;

        } catch (Exception e) {
            System.out.println("User has an error : " + e.getMessage());
            return false;
        }
    }

    private static boolean signIn() {
        System.out.println("---------------- Sign In ------------------");

        try {
            String username = getInputAsString("Username : ");
            String password = getInputAsString("Password : ");

            currentUser = authController.signIn(new SignInDTO(username, password));
            System.out.println("\nUser successfully logged in ✅");

            return true;
        } catch (Exception e) {
            System.out.println("User has an error : " + e.getMessage());
            return false;
        }
    }


    private static void showMenu() {
        System.out.println("--------------------- Menu --------------------");
        System.out.println("1 => Add new Card");
        System.out.println("2 => My Cards");
        System.out.println("3 => Bankomats");
        System.out.println("0 => Exit");
    }


    private static void showRegistration() {
        System.out.println("------------------ Registration --------------------");
        System.out.println("1 ==> signIn");
        System.out.println("2 ==> signUP");
    }

    private static String getInputAsString(String message) {
        System.out.print(message);
        return scannerStr.nextLine();
    }

    private static int getInputAsInt(String message) {
        System.out.print(message);
        return scannerInt.nextInt();
    }

    private static Double getInputAsDouble(String message) {
        System.out.print(message);
        return scannerDouble.nextDouble();
    }
}
