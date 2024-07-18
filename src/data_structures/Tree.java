package data_structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tree implements Iterable<Tree.Node> {
	public class Node {
		private List<Node> childs;
		private Node parent;
		private String id;

		public Object content;

		public Node(Node parent, Object content, String id) {
			this.parent = parent;
			this.childs = new ArrayList<>();
			this.content = content;
			this.id = id;
		}

		public Node(Node parent, Object content) {
			this(parent, content, "");
		}

		public String getId() {
			return id;
		}

		public Node getParent() {
			return parent;
		}

		public void addChild(Node c) {
			childs.add(c);
		}

		public Node getChild(int index) {
			return childs.get(index);
		}

		public Node getChild(String id) {
			return childs.stream().filter(n -> n.getId() == id).findFirst().orElse(null);
		}

		public Node getChild(Object content) {
			return childs.stream().filter(n -> n.content == content).findFirst().orElse(null);
		}

		public List<Node> getChilds() {
			return childs;
		}

		public void removeChild(int index) {
			childs.remove(index);
		}

		public void removeChild(String id) {
			childs.stream().filter(n -> n.getId() == id).findFirst().ifPresent(n -> childs.remove(n));
		}

		public void removeChild(Object content) {
			childs.stream().filter(n -> n.content == content).findFirst().ifPresent(n -> childs.remove(n));
		}
	}

	private Node root;

	public Tree() {
		root = new Node(null, null, "root");
	}

	public Tree(Object content) {
		root = new Node(null, content, "root");
	}

	public Node getRoot() {
		return root;
	}

	public void addNode(Node parent, Object content) {
		Node n = new Node(parent, content);
		parent.addChild(n);
	}

	public void addNode(Node parent, Object content, String id) {
		Node n = new Node(parent, content, id);
		parent.addChild(n);
	}

	public void removeNode(Node n) {
		n.getParent().removeChild(n.content);
	}

	@Override
	public Iterator<Node> iterator() {
		return new Iterator<Node>() {
			private Node currentNode = root;
			private LinkedList<Node> toVisit = new LinkedList<>();

			@Override
			public boolean hasNext() {
				return currentNode != null;
			}

			/**
			 * Returns the next node in the tree using a breadth-first search.
			 *
			 * @return the next node in the tree traversal
			 */
			@Override
			public Node next() {
				Node ret = currentNode;

				toVisit.addAll(currentNode.getChilds());
				if (toVisit.isEmpty()) {
					currentNode = null;
				} else {
					currentNode = toVisit.poll();
				}

				return ret;
			}
		};
	}

}
