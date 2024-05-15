import java.util.*;

interface ServiceInterface {
  int somma(int a, int b);

  int sottrazzione(int a, int b);
}

class Service implements ServiceInterface {
  public int somma(int a, int b) {
    return a + b;
  }

  public int sottrazzione(int a, int b) {
    return a - b;
  }
}

class Proxy implements ServiceInterface {
  private Service Service;
  HashMap<String, Integer> cache = new HashMap<>();

  public Proxy(Service s) {
    Service = s;
  }

  public int somma(int a, int b) {
    String key = "somma-" + a + "-" + b;
    if (cache.containsKey(key)) {
      System.out.println("cache");
      return cache.get(key);
    } else {
      int sum = Service.somma(a, b);
      cache.put(key, sum);
      return sum;
    }
  }

  public int sottrazzione(int a, int b) {
    String key = "sottrazzione-" + a + "-" + b;
    if (cache.containsKey(key)) {
      System.out.println("cache");
      return cache.get(key);
    } else {
      int sot = Service.sottrazzione(a, b);
      cache.put(key, sot);
      return sot;
    }
  }
}

public class Main {
  public static void main(String args[]) {
    Service service = new Service();
    Proxy proxy = new Proxy(service);
    System.out.println(proxy.somma(3, 2));
    System.out.println(proxy.somma(3, 2));    
    System.out.println(proxy.sottrazzione(3, 2));  
    System.out.println(proxy.sottrazzione(3, 2));
    System.out.println(proxy.sottrazzione(3, 4));
  }
}