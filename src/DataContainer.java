class DataContainer {
    int numThreads;
    int encOffset;
    char[] message;
    boolean encryptUpper;
    boolean encryptLower;

    DataContainer(int messageLength) {
        message = new char[messageLength];
    }
}
