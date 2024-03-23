//Data una classe per la connessione a Database che implementa banalmente 
//le quattro operazioni CRUD, implementare un meccanismo di proxy che 
//tracci (log in txt) le operazioni effettuate

import java.io.*;
import java.util.Scanner;

interface ServiceInterface {
    void create(FileWriter fileWriter) throws IOException;

    void read(FileWriter fileWriter) throws IOException;

    void update(FileWriter fileWriter) throws IOException;

    void delete(FileWriter fileWriter) throws IOException;
}

class Service implements ServiceInterface {
    public void create(FileWriter fileWriter) throws IOException {
        fileWriter.write("Service: create\n");
    }

    public void read(FileWriter fileWriter) throws IOException {
        fileWriter.write("Service: read\n");
    }

    public void update(FileWriter fileWriter) throws IOException {
        fileWriter.write("Service: update\n");
    }

    public void delete(FileWriter fileWriter) throws IOException {
        fileWriter.write("Service: delete\n");
    }
}

class Proxy implements ServiceInterface {
    private Service service;

    public Proxy(Service service) {
        this.service = service;
    }

    public void create(FileWriter fileWriter) throws IOException {
        fileWriter.write("Proxy: create\n");
        service.create(fileWriter);
    }

    public void read(FileWriter fileWriter) throws IOException {
        fileWriter.write("Proxy: read\n");
        service.read(fileWriter);
    }

    public void update(FileWriter fileWriter) throws IOException {
        fileWriter.write("Proxy: update\n");
        service.update(fileWriter);
    }

    public void delete(FileWriter fileWriter) throws IOException {
        fileWriter.write("Proxy: delete\n");
        service.delete(fileWriter);
    }
}

public class App {
    public static void main(String args[]) {
        String shellLine = null;
        try {
            FileWriter fileWriter = new FileWriter("log.txt", true);
            Service service = new Service();
            Proxy proxy = new Proxy(service);

            while (true) {
                helper();
                Scanner input = new Scanner(System.in);
                shellLine = input.nextLine();
                switch (shellLine) {
                    case "create":
                        proxy.create(fileWriter);
                        fileWriter.flush();
                        System.out.println("fatto!");
                        break;
                    case "read":
                        proxy.read(fileWriter);
                        fileWriter.flush();
                        System.out.println("fatto!");
                        break;
                    case "update":
                        proxy.update(fileWriter);
                        fileWriter.flush();
                        System.out.println("fatto!");
                        break;
                    case "delete":
                        proxy.delete(fileWriter);
                        fileWriter.flush();
                        System.out.println("fatto!");
                        break;
                    case "exit":
                        fileWriter.close();
                        fileWriter.flush();
                        System.out.println("fine");
                        break;
                    default:
                        System.out.println("comando non valido");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante la scrittura sul file.");
            e.printStackTrace();
        }
    }
    public static void helper(){
        System.out.println("Puoi usare i seguenti comandi: \n-create\n-delete\n-update \n-exit\n");
    }
}