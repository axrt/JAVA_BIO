package taxonomy;

import java.util.ArrayList;
import java.util.List;
/**
 * Java Bio is a free open source library for routine bioinformatics tasks.
 * Copyright (C) 2013  Alexander Tuzhikov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
     * @param rootData {@code T} root node
     */
    public AbstractTree(T rootData) {
        root = new Node<>();
        root.data = rootData;
    }

    /**
     * Static node class
     * @param <T> {@code T}
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
         * @return {@code T} data
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
         * @param parent {@link Node} parent node
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
