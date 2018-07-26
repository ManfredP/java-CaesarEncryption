import java.util.HashMap;
import java.util.Map;

class CaesarEncryption {
    public static void main(String[] args) {
        DataContainer dataCont = UserDialogue.getUserData();
        Map<Character, Character> translationTable = new HashMap<>();
        if (dataCont.encryptLower) {
            TransTableGen.createLowerCaseTable(translationTable, dataCont.encOffset);
        }
        if (dataCont.encryptUpper) {
            TransTableGen.createUpperCaseTable(translationTable, dataCont.encOffset);
        }
        int charsPerThread = dataCont.message.length / dataCont.numThreads;
        int charsLeft = dataCont.message.length % dataCont.numThreads;
        if (charsPerThread < 1) {
            charsPerThread = 1;
            dataCont.numThreads = dataCont.message.length;
            charsLeft = 0;
        }
        Thread[] tPool = new Thread[dataCont.numThreads];
        for (int i = 0; i < dataCont.numThreads; i++) {
            if (i + 1 != dataCont.numThreads) {
                tPool[i] = new Thread(new EncryptionWorkerTable(dataCont.message, translationTable, i * charsPerThread, charsPerThread));
            } else {
                tPool[i] = new Thread(new EncryptionWorkerTable(dataCont.message, translationTable, i * charsPerThread, charsPerThread + charsLeft));
            }
            tPool[i].start();
        }
        try {
            for (int i = 0; i < dataCont.numThreads; i++) {
                tPool[i].join();
            }
        } catch (InterruptedException e) {
            System.exit(1);
        }
        System.out.println("Got result: " + String.valueOf(dataCont.message));
    }
}
