public class Stringtausch{
//Habe einen Container fuer String gemacht,
//damit ich es als reference an die Methode uebergeben kann.
  private static class StringContainer{
    public String value;
    public StringContainer(String s){
      value = s;
    }
  }

  public static void main(String[] args){
    //Initialisierng der Werte
    StringContainer varA = new StringContainer("eins");
    StringContainer varB = new StringContainer("zwei");
    StringContainer varC = new StringContainer("drei");
    print(varA,varB,varC);  //output mit dem Aufruf einer statischen Funktion
    swap(varA,varB);        //Austausch
    print(varA,varB,varC);
    swap(varA,varB,varC);
    print(varA,varB,varC);
  }

  public static void print(StringContainer a,StringContainer b,StringContainer c){
    System.out.println("varA = "+a.value);
    System.out.println("varB = "+b.value);
    System.out.println("varC = "+c.value+"\n");
  }

  public static void swap(StringContainer a,StringContainer b){
    String temp = a.value;
    a.value = b.value;
    b.value = temp;
  }
//Ueberladung der Methode swap
  public static void swap(StringContainer a,StringContainer b,StringContainer c){
    String temp = a.value;
    a.value = c.value;
    c.value = temp;
    temp = b.value;
    b.value = c.value;
    c.value = temp;
  }
}
