package edu1;

import com.google.gson.Gson;

import java.io.*;

public class JsonSerializer {
    public static void writeRecords(Dictionary[] dictionary) throws IOException {
        Gson gson = new Gson();
        Writer writer = new FileWriter("Dictionary.json");
        try (writer) {
            String json = gson.toJson(dictionary);
            writer.write(json);
        }
    }

    public static void addRecordToDb(Dictionary dictionary) throws IOException {
        Gson gson = new Gson();
        Writer writer = new FileWriter("Dictionary.json");
        try (writer) {
            String json = gson.toJson(dictionary);
            writer.append(json);
        }
    }

    public static Dictionary[] getRecords() throws IOException {
        Gson gson = new Gson();
        Reader reader = new FileReader("Dictionary.json");
        try (reader) {
            return gson.fromJson(reader, Dictionary[].class);
        }
    }
}
