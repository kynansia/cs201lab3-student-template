public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     }

     // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3) {
          String[] input = { traversal1, traversal2, traversal3 };

        
          String[] first = traversal1.split("-");
          if (traversal2.split("-").length != first.length ||
                    traversal3.split("-").length != first.length) {
               return "Invalid traversals";
          }

          for (int candidate = 0; candidate < 3; candidate++) {
               String[] values = input[candidate].split("-");
               Node root = null;

           
               for (String value : values) {
                    root = insert(root, Integer.parseInt(value));
               }

               String in = getInorder(root);
               String pre = getPreorder(root);
               String post = getPostorder(root);

               String[] types = { in, pre, post };
               String[] names = { "Inorder", "Preorder", "Postorder" };

          
               for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                         for (int c = 0; c < 3; c++) {

                              if (a == b || a == c || b == c) {
                                   continue;
                              }

                              if (input[0].equals(types[a]) &&
                                        input[1].equals(types[b]) &&
                                        input[2].equals(types[c])) {

                                   return "Traversal 1 - " + names[a]
                                             + ", Traversal 2 - " + names[b]
                                             + ", Traversal 3 - " + names[c];
                              }
                         }
                    }
               }
          }

          return "Invalid traversals";
     }

     static class Node {
          int data;
          Node left, right;

          Node(int data) {
               this.data = data;
          }
     }

     private static Node insert(Node root, int value) {
          if (root == null) {
               return new Node(value);
          }

          if (value < root.data) {
               root.left = insert(root.left, value);
          } else if (value > root.data) {
               root.right = insert(root.right, value);
          }

          return root;
     }

     private static String getInorder(Node root) {
          if (root == null)
               return "";

          return join(getInorder(root.left), String.valueOf(root.data),
                    getInorder(root.right));
     }

     private static String getPreorder(Node root) {
          if (root == null)
               return "";

          return join(String.valueOf(root.data), getPreorder(root.left),
                    getPreorder(root.right));
     }

     private static String getPostorder(Node root) {
          if (root == null)
               return "";

          return join(getPostorder(root.left), getPostorder(root.right),
                    String.valueOf(root.data));
     }

     private static String join(String... parts) {
          StringBuilder result = new StringBuilder();

          for (String part : parts) {
               if (part.isEmpty())
                    continue;

               if (result.length() > 0) {
                    result.append("-");
               }

               result.append(part);
          }

          return result.toString();
     }
}