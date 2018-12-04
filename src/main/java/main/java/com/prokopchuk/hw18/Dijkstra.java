package main.java.com.prokopchuk.hw18;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");

        node1.addNode(node2, 10);
        node1.addNode(node3, 30);
        node1.addNode(node4, 50);
        node1.addNode(node5, 10);

        node3.addNode(node5, 10);

        node4.addNode(node2, 40);
        node4.addNode(node3, 20);

        node5.addNode(node1, 10);
        node5.addNode(node3, 10);
        node5.addNode(node4, 30);

        //Map minPaths = getPaths(node1, 0);
        //System.out.println(minPaths);

    }

    private static Node dijkstra(Node root) {
        root.setMinPath(0);
        getPaths(root, 0);
        return root;
    }

//    private static List<Node> getPaths(Node node, int pathToNode) {
//        Map<Node, Integer> linkNodes = node.getLinkNodes();
//        int path;
//        if (linkNodes == null || linkNodes.isEmpty()) {
//            return null;
//        }
//        for (Map.Entry<Node, Integer> entryLinkNodes : linkNodes.entrySet()) {
//            Node linkedNode = entryLinkNodes.getKey();
//            if (linkedNode.isVisited()) {
//                continue;
//            }
//            path = entryLinkNodes.getValue() + pathToNode;
//            if (linkedNode.getMinPath() > path) {
//                linkedNode.setMinPath(path);
//            }
//
//        }
//        return null;
//        Map<Node, Integer> result = new HashMap<>();
//        Map<Node, Integer> linkNodes = node.getLinkNodes();
//        for (Map.Entry<Node, Integer> entryLinkNodes : linkNodes.entrySet()) {
//            Node linkedNode = entryLinkNodes.getKey();
//            int path = entryLinkNodes.getValue();
//            if (!linkedNode.isVisited()) {
//                result.put(linkedNode, path);
//                if (!linkedNode.getLinkNodes().isEmpty()){
//                    Map<Node, Integer> intermediateResult = getPaths(linkedNode);
//                    for (Map.Entry<Node, Integer> entryIntermediateResult : linkNodes.entrySet()) {
//                        Node intermediateResultNode = entryIntermediateResult.getKey();
//                        int fullPath = entryIntermediateResult.getValue() + path;
//                        if (result.containsKey(intermediateResultNode) &&
//                        result.get(intermediateResultNode) > fullPath) {
//                            result.put(intermediateResultNode, fullPath);
//                        }
//                    }
//
//                }
//            }
//        }
//        node.setVisited();
//        return result;
//    }

    private static Set<Node> getPaths(Node node, int pathToNode) {
        Map<Node, Integer> linkNodes = node.getLinkNodes();
        int path;
        if (linkNodes == null || linkNodes.isEmpty()) {
            return null;
        }
        for (Map.Entry<Node, Integer> entryLinkNodes : linkNodes.entrySet()) {
            Node linkedNode = entryLinkNodes.getKey();
            if (linkedNode.isVisited()) {
                continue;
            }
            path = entryLinkNodes.getValue() + pathToNode;
            if (linkedNode.getMinPath() > path) {
                linkedNode.setMinPath(path);
            }

        }
        return null;
    }
}
