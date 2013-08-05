package taxonomy;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract tree, generified
 *
 * @param <T> generic
 */
public abstract class AbstractTree<T> {
    /**
     * Root node
     */
    protected Node<T> root;

    /**
     * Constructor from root node
     * @param rootData {@link T} root node
     */
    public AbstractTree(T rootData) {
        root = new Node<>();
        root.data = rootData;
    }

    /**
     * Static node class
     * @param <T> {@link T}
     */
    public static class Node<T> {
        /**
         * data
         */
        protected T data;
        /**
         * Parent node
         */
        protected Node<T> parent;
        /**
         * A list of children nodes
         */
        protected List<Node<T>> children;

        /**
         * Constructor
         */
        public Node() {
            this.children = new ArrayList<>();
        }

        /**
         * Data getter
         * @return {@link T} data
         */
        public T getData() {
            return data;
        }

        /**
         * Parent node getter
         * @return {@link Node} parent node
         */
        public Node<T> getParent() {
            return parent;
        }

        /**
         * A setter for the parent {@link Node}
         * @param parent
         */
        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        /**
         * A getter for the {@link List} of children nodes
         * @return {@link List} of children nodes
         */
        public List<Node<T>> getChildren() {
            return children;
        }
    }

}
