import java.util.NoSuchElementException;

record Pair <T>(T val, Pair<T> rest) {
    static<T> Pair<T> append(Pair<T> a, Pair<T> b){
        switch(a){
            case null: return b;
            case Pair<T>(T v, Pair<T> r):
                return new Pair<T>(v, append(r,b));
        }
    }
}
record BinaryTree<T>(T val, BinaryTree<T> left, BinaryTree<T> right){
    //counts every node and returns the total amt
    static<T> int countNodes(BinaryTree<T> bt){
        switch (bt){
            case null: return 0;
            case BinaryTree(T v, BinaryTree<T> l, BinaryTree<T> r):
                return 1 + BinaryTree.countNodes(l) + BinaryTree.countNodes(r);
        }
    }
    //mirrors the tree exactly, left=right
    static<T> BinaryTree<T> mirror(BinaryTree<T> bt){
        switch (bt){
            case null: return null;
            case BinaryTree(T v, BinaryTree<T> l, BinaryTree<T> r):
                return new BinaryTree<T>(v, mirror(r), mirror(l));
        }
    }
    //returns a linked list ordered preorder (node, then left tree, then right)
    static<T> Pair<T> preorder(BinaryTree<T> bt){
        switch(bt){
            case null: return null;
            case BinaryTree(T v, BinaryTree<T> l, BinaryTree<T> r):
                return Pair.append(new Pair<T>(v, preorder(l)), preorder(r));
        }
    }
    static<T> Pair<String> findPath(BinaryTree<T> bt, T val){
        if (!contains(bt, val))
        {
            return null;
        }
        switch(bt){
            case null: return null;
            case BinaryTree(T v, BinaryTree<T> l, BinaryTree<T> r):
                if(v.equals(val)){
                    return new Pair<String>("here", null);
                }
                else if (BinaryTree.contains(l, val)){
                    return new Pair<String>("left", findPath(l, val));
                } else /*if (BinaryTree.contains(r, val))*/{
                    return new Pair<String>("right", findPath(r, val));
                }
        }
    }
    static<T> boolean contains(BinaryTree<T> bt, T val){
        switch (bt){
            case null: return false;
            case BinaryTree(T v, BinaryTree<T> l, BinaryTree<T> r):
                if (val.equals(v)){
                    return true;
                } else {
                    return contains(l, val) || contains(r, val);
                }
        }
    }
}
