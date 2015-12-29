public class superClass {
public class A {
  public void f() {
    System.out.println("f() in A");
  }
  public void g() {
    System.out.println("g() in A");
    f();
  }
}

public class B extends A {
  public void f() {
    System.out.println("f() in B");
  }
  public void h() {
    System.out.println("h() in B");
    f();
    g();
  }
}

public static void main(String[] args) {
  B b = new B();
  b.h();
}

}
