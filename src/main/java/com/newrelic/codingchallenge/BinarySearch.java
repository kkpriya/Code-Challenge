package com.newrelic.codingchallenge;

import java.io.*;

/**
 * This class creates the log file, stores the de-duplicated list of numbers to the log, and keeps a count of duplicate and unique numbers using Binary Search algorithm.
 * Because application has a large array, and is constantly checking for duplicate/unique numbers, binary search algorithm is more efficient 
 * as it speeds up the search process. 
 *  
 * @author priya
 */

public class BinarySearch<Key extends Comparable<Key>> {
	
	private File file;
	private Node root; // root of the Binary Search Tree
	private int duplicates = 0; // keeps count of the duplicate numbers
	
	/**
	 * Remove log file if exists
	 */
	public BinarySearch() {
		file = new File("./numbers.log");
		if (file.exists()) file.delete();
	}
	
	/**
	 * Class contains root of the node, left and right of the current node, key value, and the size of the subtrees. 
	 *
	 */
	private class Node {
		private Key key; 
		private Node left, right; 
		private int size; 

		public Node(Key key, int size) {
			this.key = key;
			this.size = size;
		}
	}

	/**
	 * Returns the count of duplicate numbers.
	 */
	public int getDuplicates() {
		return duplicates;
	}

	/**
	 * Returns the number of keys in this Tree at root or Node.
	 */
	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.size;
	}

	/** 
	 * Checks if key is empty. If not, calls the private method along with root value.
	 */
	public void insert(Key key) {
		if (key == null)
			throw new IllegalArgumentException("key is a null value");
		root = insert(root, key);
	}

	/**
	 * Inserts the keys into the Tree, keeps track and checks for duplicate numbers, and calls addToLog method to write to the log file if not duplicate.
	 */
	private Node insert(Node node, Key key) {
		if (node == null) {
			addToLog((int) key);
			return new Node(key, 1);
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			node.left = insert(node.left, key);
		else if (cmp > 0)
			node.right = insert(node.right, key);
		else
			duplicates++;
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	/**
	 * This method adds the numbers sent from the client to the log file.
	 * Because the parameter is passed as an integer, it excludes 0 from integers leading with 0. So, the integers are formatted back into
	 * strings and padded with 0's if integer length isn't 9.
	 */
	public void addToLog(int value) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
			bw.write(String.format("%09d", value) + System.lineSeparator());
		} catch (IOException e) {
			System.out.println("Unable to read file " + file.toString());
		}
	}
}
