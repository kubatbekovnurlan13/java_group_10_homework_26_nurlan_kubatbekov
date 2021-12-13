
package edu1;

public interface Connectable {
    void openConnection();

    void closeConnection();

    void checkConnection();

    String readByIndex(int index);

    boolean checkByKey(String key);

    Dictionary readByKey(String key);

    Dictionary[] readSomeRecords(int... params);

    int getLenOfRecords();

    void addRecordToDb(Dictionary dictionary);

    void updateValueByIndex(int index);

    void updateValueByKey(String key);
}
