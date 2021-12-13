package edu1;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        System.out.println(database.checkConnection());
//        try {
//           database.closeConnection();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        };
//
//        try {
//            database.openConnection();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        };
//        System.out.println(database.checkConnection());

//
//        try {
//            System.out.println(database.getByKey("123123"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            System.out.println(database.getByIndex("fdf"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
////        try {
////            database.closeConnection();
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////        };
//
//        try {
//            System.out.println(database.checkByKey("fdf"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            System.out.println(database.getLenOfRecords());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            Dictionary dictionary = new Dictionary("key","value");
//            database.addRecordToDb(dictionary);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            database.updateValueByIndex(1,"newValue");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            database.updateValueByKey("key","new value after update");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        try {
            Dictionary[] dictionaries = database.getSomeRecords(1,6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
