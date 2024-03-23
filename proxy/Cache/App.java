//Si consideri una classe che implementi il caricamento di immagini. 
//Si implementi un proxy che effettua un servizio di cache.

import java.util.ArrayList;

interface ServiceInterface {
    public void add(String str);
}

class Service implements ServiceInterface {
    public void add(String str){
        System.out.println("Service: immagine caricata");
    }
}

class Proxy implements ServiceInterface {
    private Service service;
    private ArrayList<String> storage =new ArrayList<String>();

    public Proxy(Service service) {
        this.service = service;
    }

    public void add(String str){
        if (!storage.contains(str)) {
            storage.add(str);
            service.add(str);
            System.out.println("Proxy: immagine caricata\n\n");
        }else{
            System.out.println("Proxy: immagine non caricabile\n\n");
            return;
        }
    }
}

public class App {
    public static void main(String args[]) {
        Service service = new Service();
        Proxy proxy = new Proxy(service);
        proxy.add("supercazzola.jpg");
        proxy.add("duce.gpj");
        proxy.add("dariomoccia.png");
        proxy.add("palletta.jpg");
        proxy.add("duce.jpg");
    }
}