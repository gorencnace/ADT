// narejeno s pomocjo prosojnic prof. dr. Igorja Kononenka

class BSTNode {
    Comparable key;
    BSTNode left;
    BSTNode right;
    
    public BSTNode(Comparable v) {
        this.key = v;
        left = null;
        right = null;
    }
}

class BSTree {
    
    BSTNode root;
    
    public BSTree() {
        makenull();
    }
    
    void makenull() {
        root = null;
    }
    
    // VSTAVLJANJE
    
    public void insert(Comparable v) {
        root = insert(root, v);
    }
    
    private BSTNode insert(BSTNode node, Comparable v) {
        if (node == null)
            node = new BSTNode(v);
        else if (v.compareTo(node.key) < 0)
            node.left = insert(node.left, v);
        else if (v.compareTo(node.key) > 0)
            node.right = insert(node.right, v);
        return node;
    }
    
    // ali je vozlisce v drevesu
    public boolean member(Comparable v) {
        return member(root, v);
    }
    
    private boolean member(BSTNode node, Comparable v) {
        if (node == null)
            return false;
        else if (node.key.compareTo(v) == 0)
            return true;
        return member(node.left, v) || member(node.right, v);
    }
    
    // VISINA DREVESA
    
    public int height() {
        return height(root);
    }
    
    private int height(BSTNode node) {
        if (node == null)
            return 0;
        return 1 + ((height(node.left) > height(node.right))? height(node.left) : height(node.right));
    }
    
    // desna rotacija
    private BSTNode rightRotation(BSTNode node) {
        BSTNode tmp = node;
        node = node.left;
        tmp.left = node.right;
        node.right = tmp;
        return node;
    }
    // leva rotacija
    private BSTNode leftRotation(BSTNode node) {
        BSTNode tmp = node;
        node = node.right;
        tmp.right = node.left;
        node.left = tmp;
        return node;
    }
    
    // BRISANJE
    
    // brisanje z menjavo brisanega vozlisca z najmanjsim
    // elementom v desnem poddrevesu
    
    public void deleteRightMin(Comparable v) {
        root = deleteR(root, v);
    }
    
    private BSTNode deleteR(BSTNode node, Comparable v) {
        if (node != null) {
            if (v.compareTo(node.key) == 0) {
                if (node.left == null)
                    node = node.right;
                else if (node.right == null)
                    node = node.left;
                else {
                    node.key = minNodeKey(node.right);
                    node.right = deleteMin(node.right);
                }
            }
            else if (v.compareTo(node.key) < 0)
                node.left = deleteR(node.left, v);
            else
                node.right = deleteR(node.right, v);
        }
        return node;
    }
    
    private Comparable minNodeKey(BSTNode node) {
        if (node.left != null)
            return minNodeKey(node.left);
        else
            return node.key;
    }
    
    private BSTNode deleteMin(BSTNode node) {
        if (node.left != null) {
            node.left = deleteMin(node.left);
            return node;
        }
        return node.right;
    }
    
    // brisanje z menjavo brisanega vozlisca z najvecjim
    // elementom v levem poddrevesu
    
    public void deleteLeftMax(Comparable v) {
        root = deleteL(root, v);
    }
    
    private BSTNode deleteL(BSTNode node, Comparable v) {
        if (node != null) {
            if (v.compareTo(node.key) == 0) {
                if (node.left == null)
                    node = node.right;
                else if (node.right == null)
                    node = node.left;
                else {
                    node.key = maxNodeKey(node.left);
                    node.left = deleteMax(node.left);
                }
            }
            else if (v.compareTo(node.key) < 0)
                node.left = deleteL(node.left, v);
            else
                node.right = deleteL(node.right, v);
        }
        return node;
    }
    
    private Comparable maxNodeKey(BSTNode node) {
        if (node.right != null)
            return minNodeKey(node.right);
        else
            return node.key;
    }
    
    private BSTNode deleteMax(BSTNode node) {
        if (node.right != null) {
            node.right = deleteMin(node.right);
            return node;
        }
        return node.left;
    }
    
    // inorder zapis drevesa
    
    public void writeInorder() {
        writeI(root);
        System.out.println();
    }
    
    private void writeI(BSTNode node) {
        if (node != null) {
            System.out.print("[");
            writeI(node.left);
            System.out.print(node.key);
            writeI(node.right);
            System.out.print("]");
        }
    }
}