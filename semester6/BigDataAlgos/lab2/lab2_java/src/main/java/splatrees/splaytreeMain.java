package splatrees;

import com.sun.source.tree.BinaryTree;

public class splaytreeMain {

    public static void main(String[] args) {
        SplayTree test2 = new SplayTree();
        test2.insert(1);
        test2.insert(5);
        test2.insert(9);
        test2.insert(15);
        test2.insert(18);
        test2.insert(6);
        test2.print2D();
        System.out.println("after search");
        test2.search(1);
        test2.search(15);
        test2.search(5);
        test2.print2D();
    }

}
