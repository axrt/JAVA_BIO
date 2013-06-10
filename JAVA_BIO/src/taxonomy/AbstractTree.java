package taxonomy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alext
 * Date: 6/10/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractTree<T> {

        private Node<T> root;

        public AbstractTree(T rootData) {
            root = new Node<T>();
            root.data = rootData;
            root.children = new ArrayList<>();
        }

        public static class Node<T> {
            private T data;
            private Node<T> parent;
            private List<Node<T>> children;
        }

}
