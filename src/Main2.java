import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class Main2 {
	static Vector<String> result1 = new Vector<String>();
	static Vector<String> done = new Vector<String>();

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, XPathExpressionException {

		test();

		// System.out.println(result1.toString());
		// System.out.println(done.toString());

	}

	public void getVector(NodePqGram root) {
		Vector<String> result = new Vector<String>();
		NodePqGram gram = root.children.get(0);

		for (int start = 0; start < gram.children.size(); start++) {

		}
	}

	public static void test() {

		NodePqGram p = new NodePqGram("*");
		p.isRoot = true;

		NodePqGram p1 = new NodePqGram("a");
		p.addChild(p1);
		NodePqGram p2 = new NodePqGram("c");
		NodePqGram p3 = new NodePqGram("b");
		NodePqGram p4 = new NodePqGram("c1");
		p1.addChild(p2);
		p1.addChild(p3);
		p1.addChild(p4);

		NodePqGram p5 = new NodePqGram("e");
		NodePqGram p6 = new NodePqGram("f");

		p2.addChild(new NodePqGram("*"));
		System.out.println(p2.children.size());

		p3.addChild(p5);
		p3.addChild(p6);
		p4.addChild(new NodePqGram("*"));

		p5.addChild(new NodePqGram("*"));
		p6.addChild(new NodePqGram("*"));

		// System.out.println(p.children.get(0).children.get(2).children.size());;
		pqGram(p, p1);
		System.out.println(result1.toString());

	}

	public static void pqGram(NodePqGram root, NodePqGram predecesor) {

		for (int start = 0; start < predecesor.children.size() - 2; start++) {

			String result = "(" + root.value + "," + predecesor.value;
			result += "," + predecesor.children.get(start).value;
			result += "," + predecesor.children.get(start + 1).value;
			result += "," + predecesor.children.get(start + 2).value + ")";
			result1.add(result);

			if ((!done.contains(predecesor.children.get(start).value))
					&& (predecesor.children.get(start).hasChildren())) {
				done.add(predecesor.children.get(start).value);
				pqGram(predecesor, predecesor.children.get(start));

			}
			if ((!done.contains(predecesor.children.get(start + 1).value))
					&& (predecesor.children.get(start + 1).hasChildren())) {
				done.add(predecesor.children.get(start + 1).value);
				pqGram(predecesor, predecesor.children.get(start + 1));

			}
			if ((!done.contains(predecesor.children.get(start + 2).value))
					&& (predecesor.children.get(start + 2).hasChildren())) {

				done.add(predecesor.children.get(start + 2).value);
				pqGram(predecesor, predecesor.children.get(start + 2));

			}

		}

	}

}
