import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

class UserDialogue {
    static DataContainer getUserData() {
        int encOffset = 0;
        int numThreads = 0;
        String message;
        boolean encryptUpper = false;
        boolean encryptLower = false;
        Scanner scn = new Scanner(System.in);
        scn.useDelimiter("\\n");
        boolean inputNOk = true;
        while (inputNOk) {
            System.out.printf("Please enter encryption offset: ");
            try {
                encOffset = scn.nextInt();
                if (encOffset != 0) {
                    inputNOk = false;
                }
            } catch (InputMismatchException ex) {
                scn.nextLine();
            }
        }
        System.out.printf("Go ahead and type your message: ");
        message = scn.next();
        inputNOk = true;
        while (inputNOk) {
            System.out.printf("What do you want to encrypt all characters(a), lowercase only(l), uppercase only(u), (a/l/u)? ");
            try {
                String whatToEnc = scn.next(Pattern.compile("[AaLlUu]"));
                switch (whatToEnc.toLowerCase()) {
                    case "a":
                        encryptLower = true;
                        encryptUpper = true;
                        inputNOk = false;
                        break;
                    case "l":
                        encryptLower = true;
                        encryptUpper = false;
                        inputNOk = false;
                        break;
                    case "u":
                        encryptLower = false;
                        encryptUpper = true;
                        inputNOk = false;
                        break;
                }
            } catch (InputMismatchException ex) {
                scn.nextLine();
            }
        }
        inputNOk = true;
        while (inputNOk) {
            System.out.printf("Please enter thread count: ");
            try {
                numThreads = scn.nextInt();
                if (numThreads > 0) {
                    inputNOk = false;
                }
            } catch (InputMismatchException ex) {
                scn.nextLine();
            }
        }
        DataContainer dataCont = new DataContainer(message.length());
        dataCont.encOffset = encOffset;
        dataCont.numThreads = numThreads;
        dataCont.message = message.toCharArray();
        dataCont.encryptLower = encryptLower;
        dataCont.encryptUpper = encryptUpper;
        return dataCont;
    }
}
