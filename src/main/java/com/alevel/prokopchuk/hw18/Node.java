package com.alevel.prokopchuk.hw18;

import java.util.HashMap;

public class Node {
    private String name;
    private boolean isVisited = false;
    private int minPath = Integer.MAX_VALUE;
    private HashMap<Node, Integer> linkNodes  = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addNode(Node node, int pathToNode) {
        linkNodes.put(node, pathToNode);
    }

    public void deleteNode(Node node) {
        linkNodes.remove(node);
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited() {
        isVisited = true;
    }

    public void setUnvidited() {
        isVisited = false;
    }

    public HashMap<Node, Integer> getLinkNodes() {
        return linkNodes;
    }

    public int getMinPath() {
        return minPath;
    }

    public void setMinPath(int minPath) {
        this.minPath = minPath;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", minPath=" + minPath +
                '}';
    }
}
