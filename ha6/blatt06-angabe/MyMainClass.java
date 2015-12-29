import java.util.LinkedList;
class Howler {
  private int    i;
  private int    j;
  private Walker walker;
  public Howler(int i, int j, Walker b) {
     setIandJandWalker(i, j, b);
  }
  public void setIandJandWalker(int i, int j, Walker newWalker) {
     i      = i;
     this.j = j;
     walker = newWalker;
  }
  public String toString() {
     return "Toll, ich bin eine Instanz von Howler und habe ein " +
            "i=" + i + " und ein " +
            "j=" + j + " und eine Referenz auf den Walker:\n\t" + walker;
  }
}

class Walker {
  private String name;
  private int    i;
  public Walker(String name, int i) {
     this.i    = i;
     this.name = name;
  }
  public String toString() {
     return "Wow, ich bin eine Instanz von Walker und heisse " +
            name + ". Ausserdem habe ich ein i=" + i;
  }
  public void increase() {
     i++;
  }
  public boolean referencesSameName(String s) {
     return (s == name);
  }
  public boolean equalsSameName(String s) {
     return s.equals(name);
  }
}

public class MyMainClass {
  public static void main(String[] args) {
     int i = 1, j = 2;
     Walker w = new Walker("TestB", 0);
     Howler h = new Howler(i, j, w);
     System.out.println(h.toString());
     System.out.println(h);
     i++;
     w.increase();
     System.out.println(h.toString());
     w = new Walker("hollaDieWaldfee", 0);
     System.out.println(h.toString());
     LinkedList<String> s = new LinkedList<>();
     s.add("holla"); 
     s.add("Die"); 
     s.add("Waldfee");
     System.out.println(w.referencesSameName(s.get(0) + s.get(1) + s.get(2)));
     System.out.println(w.equalsSameName(s.get(0) + s.get(1) + s.get(2)));
  }
}
