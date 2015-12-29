# Aufgabe 8.6
### a)
1. Richtig
2. Richtig
3. Falsch
4. Falsch
5. Richtig
6. Richtig

### b)
1. A a = new A( ); a.g( ); </br>
g( ) in A </br>
f( ) in A </br>
*g( ) ist eine Methode der Klasse A und f( ) ist auch aus der Klasse A*
2. B b = new B( ); b.h( ); </br>
h( ) in B </br>
f( ) in B </br>
g( ) in A </br>
f( ) in B </br>
*Klasse B vererbt von A. f( ) wird in B überschrieben (dispatching), aber g( ) wird aus der Klasse A aufgerufen*
3. C c = new C( ); c.g( ); </br>
g( ) in A </br>
f( ) in A </br>
*C erbt von A, f( ) und g( ) sind nicht überschrieben*
4. B b = new B(); A a = b; a.g(); </br>
g( ) in A </br>
f( ) in B </br>
*B extends A, deshalb werden nach der Polymorphie die Methoden der Klasse B genommen, wenn sie vorhanden sind, sonst von der Klasse A*
5. D d = new E(); d.n(true); </br>
n( ) in E </br>
o( ) in D </br>
n( ) in E </br>
*D ist eine Abstrakte Klasse, aber die erste Zuweisung wird zugelassen, weil die Klasse E, die von D erbt, nicht mehr abstrakt ist, denn sie hat alle abstrakten Methoden schon implementiert. Die erste Zeile wird ausgegeben, weil es dynamisch referenziert ist und die letzte auch deswegen. Und die zweite o( ) ist nur in D vorhanden.*  
6. F f = new F(); f.o(); </br>
o( ) in D </br>
n( ) in F </br>
*F erbt von E und E erbt von D, wo die nicht überschriebene Methode o( ) liegt. n( ) ist in F überschrieben.*
