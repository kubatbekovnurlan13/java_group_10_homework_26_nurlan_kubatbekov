package edu1;

import java.text.NumberFormat;
import java.text.ParseException;

public class Database implements Connectable {
    private boolean isConnectionOpen;

    @Override
    public void openConnection() throws Exception {
        if (this.isConnectionOpen) {
            throw new Exception("Connection is already open...");
        }
        this.isConnectionOpen = true;
        System.out.println("Connection open!");
    }

    @Override
    public void closeConnection() throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is already close...");
        }
        this.isConnectionOpen = false;
        System.out.println("Connection close!");
    }

    @Override
    public boolean checkConnection() {
        return this.isConnectionOpen;
    }

    @Override
    public Dictionary getByIndex(String index) throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is closed...");
        }
        int intIndex = tryInt(index);
        isExist(intIndex);
        Dictionary[] dictionaries = JsonSerializer.getRecords();
        return dictionaries[intIndex];
    }

    public static int tryInt(String birth) throws ParseException {
        NumberFormat nf = NumberFormat.getIntegerInstance();
        return nf.parse(birth).intValue();
    }

    private void isExist(int index) throws Exception {
        Dictionary[] dictionaries = JsonSerializer.getRecords();
        int len = dictionaries.length;
        if ((len - 1) < index || index < 0) {
            throw new Exception("There is no object with this index: " + index);
        }
    }

    @Override
    public boolean checkByKey(String key) throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is closed...");
        }
        Dictionary[] dictionaries = JsonSerializer.getRecords();
        for (Dictionary dic : dictionaries) {
            if (dic.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Dictionary getByKey(String key) throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is closed...");
        }
        Dictionary[] dictionaries = JsonSerializer.getRecords();
        Dictionary dictionary = null;
        for (Dictionary dic : dictionaries) {
            if (dic.getKey().equals(key)) {
                return dic;
            }
        }
        throw new Exception("There is no object with such key: " + key);
    }

    @Override
    public Dictionary[] getSomeRecords(int startIndex, int endIndex) throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is closed...");
        }
        Dictionary[] dictionaries = JsonSerializer.getRecords();
        for (int i = startIndex; i <= endIndex; i++) {
            isExist(i);
        }
        Dictionary[] newDic = new Dictionary[endIndex - startIndex];
        System.out.println("len---: " + newDic.length);
        int z=0;
        for (int j = startIndex; j < endIndex; j++) {
            newDic[z] = dictionaries[j];
            z++;
        }
        return newDic;
    }

    @Override
    public int getLenOfRecords() throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is closed...");
        }
        return JsonSerializer.getRecords().length;
    }

    @Override
    public void addRecordToDb(Dictionary dictionary) throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is closed...");
        }
        Dictionary[] dictionaries = JsonSerializer.getRecords();
        Dictionary[] newDictionaries = new Dictionary[dictionaries.length + 1];
        for (int i = 0; i < dictionaries.length; i++) {
            newDictionaries[i] = dictionaries[i];
        }
        newDictionaries[dictionaries.length] = dictionary;
        JsonSerializer.writeRecords(newDictionaries);
    }

    @Override
    public void updateValueByIndex(int index, String newValue) throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is closed...");
        }
        isExist(index);
        Dictionary[] dictionaries = JsonSerializer.getRecords();
        dictionaries[index].setValue(newValue);
        JsonSerializer.writeRecords(dictionaries);
    }

    @Override
    public void updateValueByKey(String key, String newValue) throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is closed...");
        }
        Dictionary dictionary = getByKey(key);
        if (dictionary == null) {
            throw new Exception("There is no value with this key: " + key);
        } else {
            Dictionary[] dictionaries = JsonSerializer.getRecords();
            for (Dictionary dic : dictionaries) {
                if (dic.getKey().equals(key)) {
                    dic.setValue(newValue);
                }
            }
            JsonSerializer.writeRecords(dictionaries);
        }
    }
}
