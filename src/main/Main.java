package main;

import data_structures.Tree;

public class Main {
	public static void main(String[] args) {

		Tree tree = new Tree(Integer.valueOf(15));
		tree.addNode(tree.getRoot(), Integer.valueOf(10));

		for (Tree.Node n : tree) {
			System.out.println(n.content);
		}
	}
}