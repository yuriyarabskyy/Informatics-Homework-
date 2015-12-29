public class Practice{
  public static void main(Stringp[] args){
    FileReader file = new FileReader(new File("..."));

    HashMap<boolean> map = new HashMap<>();

    HashMap<int> indexes = new HashMap<>();

    Queue<int> queue = new Queue<>();

    while(!file.isEmpty()){

      int element = file.nextInt();

      if(map.hasElement(element)){
        if(map.get(element)==true){

        queue.eraseAtIndex(indexes.get(element));

        }
        map.put(element, false);
      }
      else{

        map.put(element, true);

        queue.enqueue(element);

        indexes.put(element,queue.size()-1);

      }
    }
    System.out.println(queue.dequeue());
  }
}
