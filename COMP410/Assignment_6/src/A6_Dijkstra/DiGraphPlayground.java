package A6_Dijkstra;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
    exTest();
    }
  
  
    public static void exTest(){
      DiGraph d = new DiGraph(); 
      d.addNode(0, "a");
      d.addNode(1, "b");
      d.addNode(2, "c");
      d.addNode(3, "d");
      d.addNode(4, "e");
      d.addNode(5, "f");
      d.addNode(6, "g");
      d.addEdge(0, "a", "f", 3, "hi");
      d.addEdge(1, "a", "b", 1, "d");
      d.addEdge(2, "b", "f", 1, "f");
      
      ShortestPathInfo[] ans = d.shortestPath("a");
      for (int i = 0; i < ans.length; i++) {
    	  System.out.println(ans[i].toString());
      }
      
      
    }
}