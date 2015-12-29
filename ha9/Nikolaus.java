public class Nikolaus {

  /*
   *   2
   *  / \
   * 1---3
   * | X |
   * 0---4
   * 
   * Adjacency matrix representation of Nikolaus' house.
   */
  public static int[][] edges = {{ 0, 1, 0, 1, 1 },
                                 { 1, 0, 1, 1, 1 },
                                 { 0, 1, 0, 1, 0 },
                                 { 1, 1, 1, 0, 1 },
                                 { 1, 1, 0, 1, 0 }};


  public static int countSolutions (int pos, int linesLeft) {

      int res = 0;
      //when there aren't any available lines left, means that a new solution was found, so we add a one
      if (linesLeft == 0){
          return 1;
      }

      for (int i = 0; i < edges.length; i++) {

          if(edges[pos][i] != 0) {
              //mark that we've been here for the recursion
              edges[pos][i] = edges[i][pos] = 0;
              //draw the next possible line and start over the process recursively from the next point
              res += countSolutions(i, linesLeft - 1);
              //unmark
              edges[pos][i] = edges[i][pos] = 1;

          }

      }

      return res;

  }

  public static void main (String[] args) {
      //calculating how many lines are there to draw
      int totalLines = 0;
      int sum = 0;

      for (int i = 0; i < edges.length; i++) {
          for (int j = 0; j < edges.length; j++) {
              if (edges[i][j] == 1) totalLines++;
          }
      }
      //because the value is doubled, I have to divide it by two
      totalLines /= 2;

      for (int i = 0; i < 5; i++) {

          sum += countSolutions(i, totalLines);

          System.out.println(i + ": \t" + countSolutions(i, totalLines));

      }

      System.out.println("Insgesamt: \t" + sum);

  }

}
