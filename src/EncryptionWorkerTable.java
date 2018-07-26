import java.util.Map;

class EncryptionWorkerTable implements Runnable {
    private final char[] message;
    private final Map<Character, Character> translationTable;
    private final int offset;
    private final int length;

    EncryptionWorkerTable(char[] message, Map<Character, Character> translationTable, int offset, int length) {
        this.message = message;
        this.translationTable = translationTable;
        this.offset = offset;
        this.length = length;
    }

    public void run() {
        for (int i = 0; i < length; i++) {
            if (translationTable.containsKey(message[offset + i])) {
                message[offset + i] = translationTable.get(message[offset + i]);
            }
        }
    }
}
