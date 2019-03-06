package Trees;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution
{

//	static class Node
//	{
//        int val;
//        Node left;
//        Node right;
//        public Node(int value)
//        {
//            this.val = value;
//        }
//    }

    static Node createTree(String data) {
        if (data == null || data.length() == 0) return null;
        StringTokenizer st = new StringTokenizer(data, " ");
        return deserialize(st);
    }

    static Node deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens())
            return null;
        String s = st.nextToken();
        if (s.equals("#"))
            return null;
        Node root = new Node(Integer.valueOf(s));
        root.left = deserialize(st);
        root.right = deserialize(st);

        return root;
    }
	public static void main(String[] args) throws IOException
	{
		 Scanner in = new Scanner(System.in);
      //  final String fileName = System.getenv("/Users/ali/Documents/workspace/KickStart/Notes/Tree/UniVal/TestCases/input001.txt");
      //  BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int res;
        int _size;
        
        File textFile = new File("/Users/ali/Documents/workspace/KickStart/Notes/Tree/UniVal/TestCases/input004.txt");
        in = new Scanner(textFile);
        _size = Integer.parseInt(in.nextLine().trim());
        String _str;
       // while(in.hasNextLine()){
	        try {
	            _str= in.nextLine();
	        } catch (Exception e) {
	            _str = null;
	        }
      //  }
        Node n = createTree(_str);
        CountUnival p=new CountUnival();
        res = p.findSingleValueTrees(n);
     //   bw.write(String.valueOf(res));
     //   bw.newLine();
        
      //  bw.close();

	}

}
