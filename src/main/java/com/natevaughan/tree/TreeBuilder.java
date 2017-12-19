package com.natevaughan.tree;

import java.util.Collection;
import java.util.Map;

/**
 * @author Nate Vaughan
 */
public interface TreeBuilder {
	Node buildTree(Collection<Map<String, Long>> dataset);
}
