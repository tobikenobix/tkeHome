package splatrees;

public class SplayTree {
    Node root;
    /**
     * Start recursion for adding with root node. This will only satisfy bst, SplayTree is done in insert()
     * @param value Integer value of the node to add
     */
    public void add(int value){
        root = addRecursive(root, value);
    }
    /**
     * prints the Tree by calling the Util function
     */
    public void print2D(){
        print2DUtil(root, 0);
    }
    /**
     * does the splay
     * @param root root node
     * @param key key you want to splay
     * @return Node
     */
    public Node splay(Node root, int key){
        if(root == null || root.value == key) return root;
        if(root.value > key){
            if(root.left == null) return root;
            if(root.left.value > key){
                root.left.left = splay(root.left.left, key);
                root = rightRotation(root);
            }
            else if(root.left.value < key){
                root.left.right = splay(root.left.right, key);
                if(root.left.right != null) root.left=leftRotation(root.left);
            }
            return (root.left == null) ? root : rightRotation(root);
        }
        else{
            if(root.right == null) return root;
            if(root.right.value > key){
                root.right.left = splay(root.right.left, key);
                if(root.right.left != null) root.right = rightRotation(root.right);
            }
            else if(root.right.value < key){
                root.right.right = splay(root.right.right, key);
                root = leftRotation(root);
            }
            return (root.right ==  null) ? root : leftRotation(root);
        }
    }
    /**
     * inserts key into SplayTree
     * @param key key to be inserted to
     */
    public void insert(int key){
        add(key);
        root =  splay(root, key);
    }

    /**
     * Goes through the tree and splays it, if element is found
     * @param key value you want to look for
     * @return true if found, false if not
     */
    public boolean search(int key){
        boolean found = containsNodeRecursive(root, key);
        if(found) root = splay(root, key);
        return found;
    }





    // private helper methods block
    private Node rightRotation(Node x){
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }
    private Node leftRotation(Node x){
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }
    private boolean containsNodeRecursive(Node current, int key){
        if(current == null) return false;
        if(current.value == key) return true;
        return key < current.value ? containsNodeRecursive(current.left, key) : containsNodeRecursive(current.right, key);
    }

    /**
     * Adds node to the Tree, this is binary search tree algorithm for insert
     * @param current current node
     * @param value value you want to insert
     * @return the new node if it doesn't exist yet
     */
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        }
        else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }
        else {
            return current;
        }
        return current;
    }

    /**
     * prints the tree
     * @param root root node
     * @param space space between nodes
     */
    private  void print2DUtil(Node root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += 10;

        // Process right child first
        print2DUtil(root.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        System.out.print(root.value + "\n");

        // Process left child
        print2DUtil(root.left, space);
    }

}
