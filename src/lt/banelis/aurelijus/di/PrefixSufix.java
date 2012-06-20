package lt.banelis.aurelijus.di;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Prefiksinis, postfiksinis medžio apėjimas
 * 
 * Algoritmo pavyzdys Dirbtinio intelekto paskaitai
 * (http://uosis.mif.vu.lt/~cyras/teaching.htm)
 * 
 * @author Aurelijus Banelis
 */
public class PrefixSufix {
    class Tree <T> {
        private T element = null;
        private LinkedList<Tree <T>> childs = new LinkedList<Tree <T>>();
        
        public Tree() { }
        
        public Tree(T element) {
            this.element = element;
        }
        
        public void add(Tree <T> child) {
            childs.add(child);
        }

        public LinkedList<Tree <T>> getChilds() {
            return childs;
        }

        public final T getElement() {
            return element;
        }

        @Override
        public String toString() {
            if (getElement() != null) {
                return getElement().toString() + "{" + childs.size() + "}";
            } else {
                return "";
            }
        }
        
        public String toStringPrefix() {
            StringBuilder result = new StringBuilder();
            if (getElement() != null) {
                result.append(getElement().toString());
            }
            for (Tree<T> tree1 : childs) {
                result.append(tree1.toStringPrefix());
            }
            return result.toString();
        }
        
        public String toStringSuffix() {
            StringBuilder result = new StringBuilder();
            for (Tree<T> tree1 : childs) {
                result.append(tree1.toStringSuffix());
            }
            if (getElement() != null) {
                result.append(getElement().toString());
            }
            return result.toString();
        }
    }
    
    private Tree<Character> tree = new Tree<Character>();
    
    public static void main(String[] args) {
        PrefixSufix pf = new PrefixSufix();
        pf.read(args[0]);
        System.out.println(pf.getPrefixRoute());
        System.out.println(pf.getSufixRoute());
    }
    
    public void read(String file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            put(reader.readLine(), 0, tree, 0);
            reader.close();
        } catch (IOException ex) {
            System.err.println("Errror reading file:" + file);
        }
    }

    /**
     * @return new offset
     */
    private int put(String data, int offset, Tree<Character> tree, int deep) {
        Tree<Character> lastTree = null;
        while (offset < data.length()) {
            Character c = data.charAt(offset);
            if (c == '(') {
                offset++;
                if (lastTree == null) {
                    lastTree = new Tree<Character>();
                    tree.add(lastTree);
                }
                offset = put(data, offset, lastTree, deep + 1);
            } else if (c == ')') {
                offset++;
                return offset;
            } else {
                lastTree = new Tree<Character>(c);
                tree.add(lastTree);
                offset++;
            }
        }
        return offset;
    }
    
    public String getPrefixRoute() {
        return tree.toStringPrefix();
    }
    
    public String getSufixRoute() {
        return tree.toStringSuffix();
    }
}