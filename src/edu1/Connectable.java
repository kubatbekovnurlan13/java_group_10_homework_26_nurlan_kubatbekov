package edu1;

public interface Connectable {
    void openConnection() throws Exception;

    void closeConnection() throws Exception;

    boolean checkConnection();

    Dictionary getByIndex(String index) throws Exception;

    boolean checkByKey(String key) throws Exception;

    Dictionary getByKey(String key) throws Exception;

    Dictionary[] getSomeRecords(int startIndex, int endIndex) throws Exception;

    int getLenOfRecords() throws Exception;

    void addRecordToDb(Dictionary dictionary) throws Exception;

    void updateValueByIndex(int index, String newValue) throws Exception;

    void updateValueByKey(String key, String newValue) throws Exception;
}
