import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    static <T> BinaryTree<T> l(T v) {
        return new BinaryTree<T>(v, null, null);
    }
    static <T> BinaryTree<T> b(T v1, BinaryTree<T> v2, BinaryTree<T> v3){
        return new BinaryTree<T>(v1,v2,v3);
    }
    BinaryTree<Integer> empty = null;
    BinaryTree<Integer> single = l(1);
    BinaryTree<Integer> shortL = b(13, b(11, l(7),null),null);
    BinaryTree<Integer> shortR = b(13, null, b(11,null, l(7)));
    BinaryTree<Integer> confusing = b(13, b(5,b(2,l(1),l(3)), l(7)), b(20,b(16,l(14),l(18)),b(96,l(25),l(100))));
    @Test
    void countNodes(){
        assertEquals(0, BinaryTree.countNodes(empty));
        assertEquals(1, BinaryTree.countNodes(single));
        assertEquals(3, BinaryTree.countNodes(shortL));
    }
    @Test
    void mirror(){
        assertEquals(null, BinaryTree.mirror(empty));
        assertEquals(shortR, BinaryTree.mirror(shortL));
        assertEquals(single, BinaryTree.mirror(single));
    }
    //LL's
    Pair<Integer> shortLPair = new Pair<>(13, new Pair<>(11, new Pair<>(7, null)));
    Pair<Integer> oneDig = new Pair<>(1, null);
    Pair<Integer> confusingExpected =
            new Pair<>(13, new Pair<>(5, new Pair<>(2, new Pair<>(1,
                    new Pair<>(3, new Pair<>(7, new Pair<>(20, new Pair<>(16,
                            new Pair<>(14, new Pair<>(18, new Pair<>(96, new Pair<>(25,
                                    new Pair<>(100, null)))))))))))));
    @Test
    void preorder(){
        assertEquals(shortLPair, BinaryTree.preorder(shortL));
        assertEquals(oneDig, BinaryTree.preorder(single));
        assertEquals(confusingExpected, BinaryTree.preorder(confusing));
    }

    Pair<String> pathHere = new Pair<>("here",null);
    Pair<String> pathL = new Pair<>("left", new Pair<>("left",new Pair<>("here",null)));
    Pair<String> pathTo18 = new Pair<>("right", new Pair<>("left", new Pair<>("right", new Pair<>("here",null))));
    @Test
    void findPath(){
        assertEquals(pathHere, BinaryTree.findPath(single,1));
        assertEquals(pathL, BinaryTree.findPath(shortL,7));
        assertEquals(pathTo18, BinaryTree.findPath(confusing, 18));
        assertEquals(null, BinaryTree.findPath(shortR, 27));
    }
}
