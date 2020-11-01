
/**
 * This is a simple test class to test Ex0 functionality & performance.
 * Note: This is not a JUnit test - and should surely be, to be fixed in Ex1.
 * <p>
 * output example (4 years ols slow computer):
 * <p>
 * Running tests for Ex0 - this may take up to 10 seconds!
 * <p>
 * Running tests for Ex0 - this may take up to 10 seconds!
 * 0) test0   pass: true
 * 1) test1   pass: true
 * 2) test2()   pass: true
 * 3) test2()   pass: true
 * 4) test2(a)   pass: true
 * 5) test9(a)   pass: true
 * 6) test9(b)   pass: true
 * 7) test_n(a)   pass: true
 * 8) test_n(b)   pass: true
 * 9) test_n(a)   pass: true
 * 10) test_n(b)   pass: true
 * 11) test_n(a)   pass: true
 * 12) test_n(b)   pass: true
 * 13) test_n(a)   pass: true
 * 14) test_n(b)   pass: true
 * 15) test_connectivity0   pass: true
 * 16) test_connectivity1   pass: true
 * 17) test_connectivity2   pass: true
 * 18) test_path   pass: true
 * 19) runtime test:   pass: true
 * number of Errors: 0 of 20 tests, 0 exceptions, time: 3.841 seconds
 * grade: 100
 * Process finished with exit code 0
 */

import java.util.*;

public class Graph_Ex0_Test {
    private static Random _rnd = null;
    private static int _errors = 0, _tests = 0, _number_of_exception = 0;
    private static String _log = "";

    /**
     * Simple main, run all the tests.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Running tests for Ex0 - this may take up to 10 seconds!");
//        testShortestPath();
        long start = new Date().getTime();
        try {
            test0();
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        try {
            test1();
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        try {
            test2();
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        try {
            test2a();
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        try {
            test9();
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        try { test_n(10, 9, 1, 1, 1);
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        try {
            test_n(100, 235, 1, 47, 1);
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        try {
            test_n(1000, 3400, 2, 19, 1);
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        int no = 1000 * 50, ed = 302650;
        try {
            test_n(no, ed, 3, 9, 1000);
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        try {
            test_connectivity(100, 1);
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        try {
            test_path(10, 1);
        } catch (Exception e) {
            e.printStackTrace();
            _number_of_exception++;
        }
        long end = new Date().getTime();
        double dt = (end - start) / 1000.0;
        boolean t = dt < 10;
        test("runtime test: ", t, true);
        System.out.println(_log);
        double g = 100.0 * (_tests - _errors) / _tests;
        g = g - _number_of_exception * 10;
        g = Math.max(g, 20);
        System.out.println("number of Errors: " + _errors + " of " + _tests + " tests, " + _number_of_exception
                + " exceptions, time: " + dt + " seconds");
        System.out.println("grade: " + (int) g);
    }

    /**
     * Generate a random graph with v_size nodes and e_size edges
     * @param v_size
     * @param e_size
     * @param seed
     * @return
     */
    public static graph graph_creator(int v_size, int e_size, int seed) {
        graph g = new Graph_DS();
        _rnd = new Random(seed);
        for (int i = 0; i < v_size; i++) {
            node_data n = new NodeData();
            g.addNode(n);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        int[] nodes = nodes(g);
        while (g.edgeSize() < e_size) {
            int a = nextRnd(0, v_size);
            int b = nextRnd(0, v_size);
            int i = nodes[a];
            int j = nodes[b];
            g.connect(i, j);
        }
        return g;
    }

    /**
     * Simple empty test
     */
    public static void test0() {
        graph g0 = graph_creator(0, 0, 1);
        //  System.out.println(g0);
        graph_algorithms ga0 = new Graph_Algo();
        ga0.init(g0);
        boolean b = ga0.isConnected();
        test("test0 ", b, true);
    }

    /**
     * Simple single node graph
     */
    public static void test1() {
        graph g0 = graph_creator(1, 0, 1);
        //    System.out.println(g0);
        graph_algorithms ga0 = new Graph_Algo();
        ga0.init(g0);
        boolean b = ga0.isConnected();
        test("test1 ", b, true);
    }

    /**
     * graph with two nodes and no edges - not connected
     */
    public static void test2() {
        graph g0 = graph_creator(2, 0, 1);
        //   System.out.println(g0);
        graph_algorithms ga0 = new Graph_Algo();
        ga0.init(g0);
        boolean b = ga0.isConnected();
        test("test2() ", b, false);
        test("test2() ", b, false);

    }

    /**
     * graph with two nodes and a single edge - connected
     */
    public static void test2a() {
        graph g0 = graph_creator(2, 1, 1);
        graph_algorithms ga0 = new Graph_Algo();
        ga0.init(g0);
        boolean b = ga0.isConnected();
        test("test2(a) ", b, true);
    }

    /**
     * small graph test (|V|=10, |E|=30), remove edges and a node:
     * so the updates graph will have (|V|=9, |E|=21)
     */
    public static void test9() {
        graph g10 = graph_creator(10, 30, 1);
        //   System.out.println(g10);
        int[] nodes = nodes(g10);
        int a0 = nodes[0];
        int a1 = nodes[1];
        int a2 = nodes[2];
        g10.removeEdge(a0, a1);
        g10.removeEdge(a2, a0);
        g10.removeEdge(a2, a1);
        g10.removeNode(a2);
        g10.removeNode(a2);
        System.out.println();
        //    System.out.println(g10);
        int re = 9;
        test("test9(a) ", re, g10.nodeSize());
        re = 21;
        test("test9(b) ", re, g10.edgeSize());
    }

    /**
     * testing graph generation and connectivity
     * @param size - number of nodes
     * @param edge - number of edges
     * @param seed - the random seed
     * @param tt - number of times for removing a group of nodes
     * @param jump - the group of nodes to be removed
     */
    public static void test_n(int size, int edge, int seed, int tt, int jump) {
        graph g = graph_creator(size, edge, seed);
        graph_algorithms ga = new Graph_Algo();
        ga.init(g);
        //   System.out.println(g);
        int[] nodes = nodes(g);
        int i = 0;
        boolean b = true;
        while (i < tt) {
            b &= ga.isConnected();
            for (int x = 0; x < jump; x++) {
                int s = g.nodeSize();
                int r = nextRnd(0, s);
                int key = nodes[r]; // bug fix
                g.removeNode(key);
            }
            i++;
        }

        test("test_n(a) ", b,true);
        b = ga.isConnected();
        //  System.out.println(g);
        //System.out.println(""+i+") "+g.nodeSize()+"  con: "+b);
        test("test_n(b) ", b, false);
    }

    /**
     * test connectivity & shortest path of a double-list like graph
     * @param size
     * @param seed
     */
    public static void test_connectivity(int size, int seed) {
        graph g = graph_creator(size, 0, seed);
        graph_algorithms ga = new Graph_Algo();
        ga.init(g);
        int[] nodes = nodes(g);
        for (int i = 2; i < size; i++) {
            int n0 = nodes[i - 2];
            int n1 = nodes[i - 1];
            int n2 = nodes[i];
            g.connect(n0, n1);
            g.connect(n0, n2);
        }
        int dist = ga.shortestPathDist(nodes[0], nodes[size - 1]);
        test("test_connectivity0 ", dist, size / 2);
        int ind = nodes[size / 2];
        g.removeNode(ind);
        dist = ga.shortestPathDist(nodes[0], nodes[size - 1]);
        test("test_connectivity1 ", dist, size / 2);
        ind = nodes[1 + size / 2];
        g.removeNode(ind); // Bug fixed - thanks Amit!
        dist = ga.shortestPathDist(nodes[0], nodes[size - 1]);
        test("test_connectivity2 ", dist, -1);
    }

    /**
     * Simple test of shortest path
     * @param size
     * @param seed
     */
    public static void test_path(int size, int seed) {
        graph g = graph_creator(size, 0, seed);
        graph_algorithms ga = new Graph_Algo();
        ga.init(g);
        int[] nodes = nodes(g);
        for (int i = 2; i < size; i++) {
            int n0 = nodes[i - 2];
            int n1 = nodes[i - 1];
            int n2 = nodes[i];
            g.connect(n0, n1);
            g.connect(n0, n2);
        }
        List<node_data> path = ga.shortestPath(nodes[0], nodes[size - 1]);
        test("test_path ", path.size(), size / 2 + 1);
    }

    ////////////////////// Private Functions /////////////////////
    private static void test(String test, boolean val, boolean req) {
        test(test, "" + val, "" + req);
    }

    private static void test(String test, int val, int req) {
        test(test, "" + val, "" + req);
    }

    private static void test(String test, String val, String req) {
        boolean ans = true;
        ans = val.equals(req);
        String tt = _tests + ") " + test + "  pass: " + ans;
        _log += "\n" + tt;
        if (!ans) {
            _errors++;
            String err = "  ERROR(" + _errors + ") " + val + "!=" + req;
            System.err.println(tt + err);
            _log += err;

        }
        _tests++;
    }

    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0 + min, (double) max);
        int ans = (int) v;
        return ans;
    }

    private static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max - min;
        double ans = d * dx + min;
        return ans;
    }

    /**
     * Simple method for returning an array with all the node_data of the graph,
     * Note: this should be using an  Iterator<node_edge> to be fixed in Ex1
     * @param g
     * @return
     */
    private static int[] nodes(graph g) {
        int size = g.nodeSize();
        Collection<node_data> V = g.getV();
        node_data[] nodes = new node_data[size];
        V.toArray(nodes); // O(n) operation
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = nodes[i].getKey();
        }
        Arrays.sort(ans);
        return ans;
    }



//import java.lang.reflect.Array;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Random;
//
//public class Graph_Ex0_Test {
//    static int seed = 31;
//    static Random _rnd = new Random(seed);
//    static int v_size = 10;
//    static int e_size = v_size * 3;
//    static graph g0 = new Graph_DS();
//    static graph_algorithms ga;
//
//    public static void main(String[] args) {
//        ga = new Graph_Algo();
//        testShortestPath();
////        test1();
////        System.out.println(g0);
////        test2();
////        System.out.println(g0);
//////        test3();
////        System.out.println(g0);
//    }

    public static void testShortestPath() {

        graph graph = new Graph_DS();
//        for (int i = 0; i < 7; i++) {
//            node_data n = new NodeData();
//            graph.addNode(n);
//        }
//
//        graph.connect(0, 1);
//        graph.connect(0, 2);
//        graph.connect(0, 3);
//        graph.connect(1, 4);
//        graph.connect(2, 5);
//        graph.connect(3, 5);
//        graph.connect(4, 5);
//        graph.connect(2, 6);
//        graph.connect(6, 1);
        graph_algorithms ga = new Graph_Algo();
        ga.init(graph);
        System.out.println(ga.isConnected());
//
//        System.out.println(ga.shortestPathDist(0, 4));
//
//        for (node_data node : ga.shortestPath(0, 4)) {
//            System.out.print(node.getKey() + " ");
//
//        }
//        System.out.println();
//
//        System.out.println(ga.shortestPathDist(0, 3));
//
//        for (node_data node : ga.shortestPath(0, 3)) {
//            System.out.print(node.getKey() + " ");
//        }
//
//        System.out.println();
//
//        System.out.println(ga.shortestPathDist(3, 1));
//
//        for (node_data node : ga.shortestPath(3, 1)) {
//            System.out.print(node.getKey() + " ");
//        }
//
//
//        System.out.println();
//
//        System.out.println(ga.shortestPathDist(6, 3));
//
//        for (node_data node : ga.shortestPath(6, 3)) {
//            System.out.print(node.getKey() + " ");
//        }


    }
}

//    public static void test0() {
//        graph graph = new Graph_DS();
//        for (int i = 0; i < 6; i++) {
//            node_data n = new NodeData();
//            graph.addNode(n);
//        }
//
//        // Display element by element using Iterator
//        for (node_data n : graph.getV()) {
//            System.out.print(n.getKey() + ",");
//        }
//
//        graph.connect(0, 1);
//        graph.connect(1, 3);
//        graph.connect(3, 5);
//        graph.connect(5, 2);
////        graph.connect(2,4);
//        graph_algorithms GA = new Graph_Algo();
//        GA.init(graph);
//        System.out.println(GA.isConnected());
//
//    }
//
//
//    public static void test1() {
//
//        for (int i = 0; i < v_size; i++) {
//            node_data n = new NodeData();
//            g0.addNode(n);
//        }
//        while (g0.edgeSize() < e_size) {
//            int a = nextRnd(0, v_size);
//            int b = nextRnd(0, v_size);
//            g0.connect(a, b);
//        }
//        // System.out.println(g0);
//    }
//
//    public static void test2() {
//        g0.removeEdge(9, 3);
//        g0.removeEdge(9, 3);
//        g0.removeNode(0);
//        g0.removeNode(0);
//        g0.removeNode(2);
//        g0.removeNode(8);
//    }
//
////    public static void test3() {
////        ga = new Graph_Algo(g0);
////        g1 = ga.copy();
////        ga.init(g1);
////        boolean isConnected = ga.isConnected();
////        int dist19 = ga.shortestPathDist(1, 9);
////        int dist91 = ga.shortestPathDist(1, 9);
////        List<node_data> sp = ga.shortestPath(1, 9);
////        System.out.println(g1);
////        System.out.println("Is connected: " + isConnected);
////        System.out.println("shortest path: 1,9 dist=" + dist19);
////        System.out.println("shortest path: 9,1 dist=" + dist91);
////        for (int i = 0; i < sp.size(); i++) {
////            System.out.println(" " + sp.get(i));
////        }
////    }
//
//    public static int nextRnd(int min, int max) {
//        double v = nextRnd(0.0 + min, (double) max);
//        int ans = (int) v;
//        return ans;
//    }
//
//    public static double nextRnd(double min, double max) {
//        double d = _rnd.nextDouble();
//        double dx = max - min;
//        double ans = d * dx + min;
//        return ans;
//    }
//}