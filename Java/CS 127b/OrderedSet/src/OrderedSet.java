/**
 * Implement the OrderedSet ADT using a binary search tree data structure.
 * 
 * @author Brendon Hudnell
 * @param <E>
 *            The type of element to be stored in the set.
 */
public class OrderedSet<E extends Comparable<E>> {

	private class TreeNode {
		private TreeNode left;
		private TreeNode right;
		private E data;

		private TreeNode(E data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private TreeNode root;
	private int numNodes;
	private OrderedSet<E> intersect;
	private OrderedSet<E> union;
	private OrderedSet<E> aSubset;

	public OrderedSet() {
		root = null;
		numNodes = 0;
	}

	// Add element to this OrderedSet and return true keeping this an
	// OrderedSet. If element is found to already exist, do not change this
	// OrderedSet, return false.
	public boolean insert(E element) {
		if (root == null) {
			root = new TreeNode(element);
			numNodes++;
			return true;
		}
		return insert(root, element);
	}

	private boolean insert(TreeNode t, E el) {
		boolean exists = false;
		if (el.compareTo(t.data) < 0) {
			if (t.left == null) {
				t.left = new TreeNode(el);
				numNodes++;
				exists = true;
			} else
				exists = insert(t.left, el);
		} else if (el.compareTo(t.data) > 0) {
			if (t.right == null) {
				t.right = new TreeNode(el);
				numNodes++;
				exists = true;
			} else
				exists = insert(t.right, el);
		}
		return exists;
	}

	// The number of elements in this OrderedSet, which should be 0 when first
	// constructed.
	public int size() {
		return numNodes;
	}

	// Return one string that concatenates all elements in this OrderedSet as
	// they are visited in order. Elements are separated by spaces.
	public String toStringInorder() {
		return toStringInOrder(root).trim();
	}

	private String toStringInOrder(TreeNode t) {
		String result = "";
		if (t == null)
			return "";

		result += toStringInOrder(t.left);
		result += t.data + " ";
		result += toStringInOrder(t.right);
		return result;
	}

	// Return true is search equals an element in this OrderedSet.
	public boolean contains(E search) {
		return contains(root, search);
	}

	private boolean contains(TreeNode t, E search) {
		if (t == null)
			return false;
		if (search.compareTo(t.data) == 0)
			return true;
		if (search.compareTo(t.data) < 0)
			return contains(t.left, search);
		return contains(t.right, search);
	}

	// Return the element in this OrderedSet that is greater than all other
	// elements. If this OrderedSet is empty, return null.
	public E max() {
		if (root == null)
			return null;
		return max(root);
	}

	private E max(TreeNode t) {
		while (t.right != null)
			t = t.right;
		return t.data;
	}

	// Return how many nodes are at the given level. If level > the height of
	// the tree, return 0.
	public int nodesAtLevel(int level) {
		if (level > height(root))
			return 0;
		return nodesAtLevel(root, level);
	}

	private int nodesAtLevel(TreeNode t, int level) {
		if (t == null)
			return 0;
		if (level == 0)
			return 1;
		return nodesAtLevel(t.left, level - 1) + nodesAtLevel(t.right, level - 1);
	}

	private int height(TreeNode t) {
		if (t == null)
			return -1;
		return 1 + Math.max(height(t.left), height(t.right));
	}

	// Return the intersection of this OrderedSet and the other OrderedSet as a
	// new OrderedSet. Do not modify this OrderedSet or the other OrderedSet.
	// The intersection of two sets is the set of elements that are in both
	// sets.
	// The intersection of {2, 4, 5, 6} and {2, 5, 6, 9} is {2, 5, 6}
	public OrderedSet<E> intersection(OrderedSet<E> other) {
		intersect = new OrderedSet<E>();
		intersection(root, other.root);
		return intersect;
	}

	private void intersection(TreeNode thisT, TreeNode otherT) {
		if (thisT != null && otherT != null) {
			if (thisT.data.compareTo(otherT.data) == 0) {
				intersect.insert(thisT.data);
				intersection(thisT.left, otherT.left);
				intersection(thisT.right, otherT.right);
			} else if (thisT.data.compareTo(otherT.data) > 0) {
				intersection(thisT, otherT.right);
				intersection(thisT.left, otherT);
			} else {
				intersection(thisT.right, otherT);
				intersection(thisT, otherT.left);
			}
		}
	}

	// Return the union of this OrderedSet and the other OrderedSet as a new
	// OrderedSet. Do not modify this OrderedSet or the other OrderedSet. The
	// union of two sets is the set all distinct elements in the collection.
	// The union of {2, 4, 6} and {2, 5, 9} is {2, 4, 5, 6, 9}
	public OrderedSet<E> union(OrderedSet<E> other) {
		union = new OrderedSet<E>();
		union(root, other.root);
		return union;
	}

	private void union(TreeNode thisT, TreeNode otherT) {
		if (thisT != null) {
			union.insert(thisT.data);
			union(thisT.left, otherT);
			union(thisT.right, otherT);
		}
		if (otherT != null) {
			if (!union.contains(otherT.data))
				union.insert(otherT.data);
			union(thisT, otherT.left);
			union(thisT, otherT.right);
		}
	}

	// Return an OrderedSet that contains all elements that are greater than or
	// equal to the first parameter (inclusive) and strictly less than the
	// second parameter (exclusive).
	public OrderedSet<E> subset(E inclusive, E exclusive) {
		aSubset = new OrderedSet<E>();
		subset(root, inclusive, exclusive);
		return aSubset;
	}

	private void subset(TreeNode t, E incl, E excl) {
		if (t != null) {
			if (incl.compareTo(t.data) > 0)
				subset(t.right, incl, excl);
			else if (excl.compareTo(t.data) <= 0)
				subset(t.left, incl, excl);
			else {
				aSubset.insert(t.data);
				subset(t.right, incl, excl);
				subset(t.left, incl, excl);
			}
		}
	}

	// Return true if two different OrderedSet objects have the same exact
	// structure. Each node must have the same number of nodes on every level,
	// the same height, the same size, the same number of leaves, and the same
	// number of internal nodes. Each corresponding node must also have the same
	// number of children (0, 1, or 2) in the same place. The data need NOT be
	// the same. Do not compare corresponding elements.
	//
	// Precondition: E is the same for both OrderedSets
	public boolean sameStructure(OrderedSet<E> other) {
		return sameStructure(root, other.root);
	}

	private boolean sameStructure(TreeNode thisT, TreeNode otherT) {
		boolean same = false;
		if (thisT == null && otherT != null || thisT != null && otherT == null)
			return false;
		if (thisT == null && otherT == null)
			return true;
		same = sameStructure(thisT.left, otherT.left);
		if (same)
			same = sameStructure(thisT.right, otherT.right);
		return same;
	}

	// If element equals an element in this OrderedSet, remove it and return
	// true. Return false whenever element is not found. In all cases, this
	// OrderedSet must remain a true OrderedSet.
	public boolean remove(E element) {
		if (root == null)
			return false;
		if (root.data.equals(element)) {
			if (root.left == null)
				root = root.right;
			else {
				TreeNode prevMax = root.left;
				TreeNode max = root.left;
				while (max.right != null) {
					prevMax = max;
					max = max.right;
				}
				root.data = max.data;
				if (prevMax.equals(max))
					root.left = max.left;
				else
					prevMax.right = max.left;
			}
			numNodes--;
			return true;
		}

		TreeNode curr = root;
		TreeNode prev = root;
		while (curr != null) {
			if (element.compareTo(curr.data) < 0) {
				prev = curr;
				curr = curr.left;
			} else if (element.compareTo(curr.data) > 0) {
				prev = curr;
				curr = curr.right;
			} else {
				if (curr.left == null) {
					if (curr.equals(prev.left))
						prev.left = curr.right;
					else
						prev.right = curr.right;
					numNodes--;
					return true;
				} else {
					TreeNode prevMax = curr;
					TreeNode max = curr.left;
					while (max.right != null) {
						prevMax = max;
						max = max.right;
					}
					curr.data = max.data;
					if (prevMax.equals(curr))
						curr.left = max.left;
					else
						prevMax.right = max.left;
					numNodes--;
					return true;
				}
			}
		}
		return false;
	}
}
