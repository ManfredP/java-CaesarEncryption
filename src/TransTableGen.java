import java.util.Map;

class TransTableGen {
    private static final int lowerA = (int) 'a';
    private static final int lowerZ = (int) 'z';
    private static final int upperA = (int) 'A';
    private static final int upperZ = (int) 'Z';

    private static void createShiftedSeqTable(Map<Character, Character> translationTable, int start, int end, int offset) {
        final int width = (end - start) + 1;
        int shiftedChar;
        for (int i = start; i <= end; i++) {
            shiftedChar = i + offset;
            if (shiftedChar > end) {
                shiftedChar = shiftedChar - width;
            }
            if (shiftedChar < start) {
                shiftedChar = shiftedChar + width;
            }
            translationTable.put((char) i, (char) shiftedChar);
        }
    }

    static void createUpperCaseTable(Map<Character, Character> translationTable, int offset) {
        createShiftedSeqTable(translationTable, upperA, upperZ, offset);
    }

    static void createLowerCaseTable(Map<Character, Character> translationTable, int offset) {
        createShiftedSeqTable(translationTable, lowerA, lowerZ, offset);
    }
}