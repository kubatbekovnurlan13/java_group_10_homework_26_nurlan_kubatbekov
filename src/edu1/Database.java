package edu1;

public class Database implements Connectable {
    private boolean isConnectionOpen;

    @Override
    public void openConnection() throws Exception {
        if (this.isConnectionOpen) {
            throw new Exception("Connection is already open...");
        }
        this.isConnectionOpen = true;
    }

    @Override
    public void closeConnection() throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is already close...");
        }
        this.isConnectionOpen = false;
    }

    @Override
    public boolean checkConnection() {
        return this.isConnectionOpen;
    }

    @Override
    public Dictionary getByIndex(int index) throws Exception {
        if (!this.isConnectionOpen) {
            throw new Exception("Connection is closed...");
        }
        isExist(index);
        Dictionary[] dictionaries = JsonSerializer.getRecords();
        return dictionaries[index];
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
        return dictionary;
    }

    @Override
    public Dictionary[] getSomeRecords(int... params) {
        return new Dictionary[0];
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
        JsonSerializer.addRecordToDb(dictionary);
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
