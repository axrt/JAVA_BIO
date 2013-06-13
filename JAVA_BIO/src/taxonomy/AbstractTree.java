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

        protected Node<T> root;

        public AbstractTree(T rootData) {
            root = new Node<T>();
            root.data = rootData;
        }

        public static class Node<T> {
            protected T data;
            protected Node<T> parent;
            protected List<Node<T>> children;

            public Node() {
                 this.children=new ArrayList<>();
            }

            public void setParent(Node<T> parent){
                this.parent=parent;
            }
            public T getData() {
                return data;
            }

            public Node<T> getParent() {
                return parent;
            }

            public List<Node<T>> getChildren() {
                return children;
            }
        }

}
