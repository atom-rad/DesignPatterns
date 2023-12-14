import java.util.Queue;
import java.util.LinkedList;

public class VisitorQueue {
    
    private Queue<Visitor> visitorQueue;

    public VisitorQueue(){
        visitorQueue = new LinkedList<>();
    }

    public void addVisitor(Visitor v){
        visitorQueue.add(v);
        System.out.println("The visitor with id " + v.getId() + " was added to the queue");
      }
    
    public Visitor removeVisitor(){
      Visitor visitor = visitorQueue.remove();
      System.out.println("The "+visitor.getClass().toString().substring(6)+" with id "+visitor.getId()+" left the queue");
      return visitor;
    }


}
