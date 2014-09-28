import java.util.Vector;

public class NodePqGram {
	public String value;
	public boolean isRoot = false;
	public Vector<NodePqGram> children = new Vector<NodePqGram>();

	public NodePqGram(String value) {

		this.value = value;

	}

	public void addChild(NodePqGram nodePqGram) {
		if (isRoot) {
			children.add(0, nodePqGram);
		} else {
			if (this.value.equals("*")) {

				children.clear();
			}

			else if (nodePqGram.value.equals("*")) {
				children.add(0, new NodePqGram("*"));
				children.add(1, new NodePqGram("*"));
				children.add(2, new NodePqGram("*"));
			} else {

				if (children.size() == 0) {
					children.add(0, new NodePqGram("*"));
					children.add(1, new NodePqGram("*"));
					children.add(2, nodePqGram);
					children.add(3, new NodePqGram("*"));
					children.add(4, new NodePqGram("*"));

				}

				else {
					int index = children.size() - 2;
					children.add(index, nodePqGram);

				}

			}
		}
	}

	public void printChildren() {
		if (!children.isEmpty()){
		for (NodePqGram st : children) {
			System.out.print(st.value + " ");
		}
		}

	}

	public void printAll() {
		System.out.println(this.value);
		this.printChildren();
		System.out.println();
		for (NodePqGram p : this.children) {
			p.printChildren();
			System.out.print("\t");
		}

		for (NodePqGram p : this.children) {
			System.out.println();
			for (NodePqGram p1 : p.children) {
				p1.printChildren();
				if (p1.hasChildren()) {
					System.out.print("\t");
				}

			}
		}

		System.out.println();
		for (NodePqGram p : this.children) {
			for (NodePqGram p1 : p.children) {
				for (NodePqGram p2 : p1.children) {
			
					p2.printChildren();
					if (p2.hasChildren()) {
						System.out.print("\t");
					}

				}
			}
		}
		System.out.println();
		for (NodePqGram p : this.children){
			for (NodePqGram p1 : p.children){
				for (NodePqGram p2 : p1.children){
					for (NodePqGram p3 : p2.children){
				//		System.out.print("VALUE: "+p3.value+ " ");
						p3.printChildren();
						if (p3.hasChildren()) {
							
							System.out.print("\t");
						}
						
				
				}
			}
		}
		
		}
		
		
	
		System.out.println();
		
	}
	
	
	
	public boolean hasChildren(){
		if (children.size() == 0){
			return false;
			
		}
		else{ return true;}
		
	}

}
