import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class Main {
	static Vector<String> result1 = new Vector<String>();
	static Vector<String> done = new Vector<String>();
	static HashMap<Integer, Commit> map;
	static double[][] array;
	final static int NUMBER_COMMITS = 668;
	final static int TEST = 60;
	// 668;
	static String toWrite = "";
	static int xMin = 2;
	static int yMin = 2;

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, XPathExpressionException {

		
		//Create the MAP IdCommit - Commit
		createMap();
		
		
		
		array = new double[map.size()][map.size()];
		
		createPath(map.get(60));
		
		

		Vector<Vector<Integer>> p = new Vector<Vector<Integer>>();

		for (int t = 0; t < array.length; t++) {
			Vector<Integer> tem = new Vector<Integer>();
			tem.add(t);
			p.add(tem);

			for (int o = 0; o < array.length; o++) {
				array[t][o] = 99;
			}
		}

		int xMin = 2;
		int yMin = 2;
		double min = 2;

		// createPath(map.get(TEST));

		
		  for (int i = 0; i < array.length; i++) { 
			  for (int z = i + 1; z <array.length; z++) { 
				  if (i != z) { 
		   //double result1 = getCrossDistance(map.get(i),map.get(z)); 
		  
		  writeFile("a.txt", i); //
		  //crea i.zip
		  writeFile("b.txt", z); // crea z.zip 
		  String acc = map.get(z).text + "\n" + map.get(i).text; 
		  writeFile("c.txt", acc); 
//		  /iz.zip
		  
		  long sizeI = getSize("a" + ".zip"); 
		  long sizeZ = getSize("b" +".zip"); 
		  long sizeIZ = getSize("c" + ".zip");
		  
		  long result = (sizeIZ - (Math.min(sizeI, sizeZ))); 
		  double result1 =
		  (double) result / (double) Math.max(sizeI, sizeZ);
		 
		
		  if (result1 < min) {
			  xMin = i; yMin = z; min = result1;
		  }
		  
		
		  
		 array[i][z] = result1;
		 
		   //(df.format(result1).replace(",", ".")); 
		   deleteFile("a" +".zip"); deleteFile("b" + ".zip"); deleteFile("c" + ".zip");
		 
				  }
		 }}
		  
		  
		  System.out.println("\n1¡ STEP");
		  
		  for (int t = 0; t < array.length; t++) { 
			  for (int o = 0; o <array.length; o++) { 
				  if (array[t][o] == 99.00 ) {
					  System.out.print("X" + " ");
				  } else { 
					  DecimalFormat df = new DecimalFormat("0.000");
					  System.out.print(df.format(array[t][o]).replace(",", ".") + " "); }
			  		} 
			  System.out.println(); 
		  
		  
		  }
		  
		  int lenght = array.length; Element1 el = new Element1(array, p); int
		  count = 2; int counter = 0;
		  
		  HashMap<Integer, String> map1 = new HashMap<Integer, String>();
		  
		  while (lenght != 1) { 
			  int num1 = 0; // MIN 
			  int num2 = 0; 
			  int num3 = 0;
		  
		  System.out.println("\n" + count + "¡ STEP");
		  
		  el = cluster(el); // el.print();
		  
		  
		  String temp = "[label=<" + el.min.toString() + ">]"; if
		  (el.min.size() == 1){ temp = "[label=<C_" + el.min.get(0)+">]";
		  
		  }
		 
		  
		  
		  String temp1 = "[label=<" + el.Xmin + ">]"; 
		  if (!el.Xmin.contains(","))
		  { temp1 = "[label=<C_" + el.Xmin.replace("[",
		  "").replace("]", "").replace("<", "").replace(">", "") + ">]";
		  
		  }
		  
		  String temp2 = "[label=<" + el.Ymin + ">]"; 
		  if (!el.Ymin.contains(",")){ 
			  temp2 = "[label=<C_" + el.Ymin.replace("[",
		  "").replace("]", "").replace("<", "").replace(">", "") + ">]";
		 
		 }
		  
		  
		 if (map1.containsValue(temp1)) { 
			 int key = 0; 
			 for (Map.Entry<Integer,String> entry : map1.entrySet()) { 
				 if (temp1.equals(entry.getValue())) { 
				 key = (Integer) entry.getKey();
				 break; // breaking because its one to one map 
				 } }
				 num2 = key; }
		  
		 else { num2 = counter; map1.put(counter, temp1); counter++;
		  
		  }
		  
		  if (map1.containsValue(temp2)) { 
			  int key = 0; 
			  for (Map.Entry<Integer,String> entry : map1.entrySet()) { 
				  if (temp2.equals(entry.getValue())) { 
					  key = (Integer) entry.getKey();
				  
		  
		  break; // breaking because its one to one map 
		  } }
		  
		  num3 = key; }
		  
		  else { num3 = counter; map1.put(counter, temp2); counter++;
		  
		  }
		  
		  
		  if (map1.containsValue(temp)) {
		  
		  int key = 0; 
		  for (Map.Entry<Integer, String> entry : map1.entrySet()){ 
			  if (temp.equals(entry.getValue())) { 
			  key = (Integer)
			  entry.getKey();
		 
		  break; // breaking because its one to one map 
		  } }
		  
		  num1 = key; }
		  
		  else { num1 = counter; map1.put(counter, temp); counter++;
		  
		  }
		  
		  toWrite = toWrite + "\n" + num1 + " -> " + num2 + ";"; toWrite =
		  toWrite + "\n" + num1 + " -> " + num3 + ";";
		  
		  count++; lenght = el.array.length; }
		  
		  
		  for (int i : map1.keySet()) {
		  
		  String s = map1.get(i); if (s.startsWith("[label=<C_")){ toWrite =
		  "\n" + i + map1.get(i) + toWrite;}
		  
		  else{ toWrite = "\n" + i + "[label=ID_"+i+"]" + toWrite; } }
		  
		  toWrite = toWrite + "\n}";
		  
		  toWrite = "digraph G {\n" + toWrite;
		  
		  
		  
		  
		  @SuppressWarnings("resource") Writer writer = new BufferedWriter(new
		  OutputStreamWriter( new FileOutputStream("file.dot"), "utf-8"));
		  writer.write(toWrite); writer.close();
		 	
		 	
		 	
		 	}

	public static Element1 cluster(Element1 element) {
		double[][] old = element.array;
		double[][] newArray;
		Vector<Vector<Integer>> vector = element.vector;
		double min = 2;
		int xMin = 2;
		int yMin = 2;

		for (int i = 0; i < old.length; i++) {
			for (int z = i + 1; z < old.length; z++) {
				if (old[i][z] < min) {
					min = old[i][z];
					xMin = i;
					yMin = z;

				}

			}
		}

		String xm = element.vector.get(xMin).toString();
		;
		String ym = element.vector.get(yMin).toString();

		System.out.println("xMin " + xMin + "  yMin " + yMin + "   MIN " + min);

		for (int k = 0; k < vector.get(xMin).size(); k++) {
			vector.get(yMin).add(vector.get(xMin).get(k));

		}

		vector.remove(xMin);

		System.out.println(vector.toString());
		Vector<Integer> string = (vector.get(yMin - 1));

		newArray = new double[vector.size()][vector.size()];
		for (int i = 0; i < vector.size(); i++) {
			for (int z = 0; z < vector.size(); z++) {
				newArray[i][z] = 99.00;

			}
		}

		for (int i = 0; i < vector.size(); i++) {
			Vector<Integer> tem = vector.get(i);
			for (int z = i + 1; z < vector.size(); z++) {
				Vector<Integer> tem1 = vector.get(z);
				newArray[i][z] = 0;
				if (tem1.size() == 1 && tem.size() == 1) {

					newArray[i][z] = array[tem.get(0)][tem1.get(0)];
				}

				else {
					for (int p = 0; p < tem.size(); p++) {
						for (int l = 0; l < tem1.size(); l++) {
							newArray[i][z] += array[Math.min(tem.get(p),
									tem1.get(l))][Math.max(tem.get(p),
									tem1.get(l))];

						}

					}
					newArray[i][z] = newArray[i][z]
							/ (tem.size() * tem1.size());

				}

			}
		}

		Element1 e = new Element1(newArray, vector);
		e.min = string;
		e.Xmin = xm;
		e.Ymin = ym;

		return e;

	}

	public static Vector<String> createPair(Commit commit) throws SAXException,
			IOException, ParserConfigurationException {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();

		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(commit.text));

		Document doc = db.parse(is);

		Vector<String> vector = new Vector<String>();
		vector.add("(c)");

		NodeList commits = doc.getElementsByTagName("commit");
		int issue_counter = 0;
		int commit_counter = 0;
		int file_counter = 0;
		Node node = commits.item(0);
		NodeList issues = node.getChildNodes();

		for (int i = 0; i < issues.getLength(); i++) {
			Node issue = issues.item(i);
			vector.add("(c, i_" + issue_counter + ")");

			if (issue.hasChildNodes()) {
				NodeList commitss = issue.getChildNodes();
				for (int z = 0; i < commitss.getLength(); i++) {
					Node c = commitss.item(i);
					vector.add("(i_" + issue_counter + ", " + "c_"
							+ commit_counter + ")");

					if (c.hasChildNodes()) {
						NodeList files = c.getChildNodes();
						for (int y = 0; y < files.getLength(); y++) {
							Node file = files.item(y);
							vector.add("(c_" + commit_counter + ", f_"
									+ file_counter + ")");
							file_counter++;

						}
					}
					commit_counter++;
				}

			}
			issue_counter++;

		}
		System.out.println(vector.toString());

		return vector;

	}


	public static Vector<String> createPath(Commit commit) throws SAXException,
			IOException, ParserConfigurationException {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();

		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(commit.text));

		Document doc = db.parse(is);

		Vector<String> vector = new Vector<String>();
		vector.add("(c)");

		NodeList commits = doc.getElementsByTagName("commit");
		int issue_counter = 0;
		int commit_counter = 0;
		int file_counter = 0;
		Node node = commits.item(0);
		NodeList issues = node.getChildNodes();

		for (int i = 0; i < issues.getLength(); i++) {
			Node issue = issues.item(i);
			String iss = "(c, i_" + issue_counter + ")";
			vector.add(iss);
			iss = iss.replace(")", "");
			if (issue.hasChildNodes()) {
				NodeList commitss = issue.getChildNodes();
				for (int z = 0; z < commitss.getLength(); z++) {
					Node c = commitss.item(z);
					String com = iss + ", c_" + commit_counter + ")";
					vector.add(com);
					com = com.replace(")", "");

					if (c.hasChildNodes()) {
						
					
						NodeList files = c.getChildNodes();
						for (int y = 0; y < files.getLength(); y++) {
							Node file = files.item(y);
							String fi = iss.replace(")", "");
							fi = com + ", f_" + file_counter + ")";
							vector.add(fi);
							file_counter++;

						}
					}
					commit_counter++;
				}

			}
			issue_counter++;

		}
		System.out.println(vector.toString());

		return vector;

	}

	
	public static NodePqGram createPqGram(Commit commit) throws SAXException,
			IOException, ParserConfigurationException {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();

		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(commit.text));

		Document doc = db.parse(is);

		NodeList commits = doc.getElementsByTagName("commit");

		int issue_counter = 0;
		int commit_counter = 0;
		int file_counter = 0;
		Node node = commits.item(0);

		NodePqGram root = new NodePqGram("*");
		root.isRoot = true;
		NodePqGram co = new NodePqGram("c");

		NodeList issues = node.getChildNodes();
		
		if (!node.hasChildNodes()){
			co.addChild(new NodePqGram("*"));
			
		}
		
		
		

		for (int i = 0; i < issues.getLength(); i++) {
			Node issue = issues.item(i);
			NodePqGram iss = new NodePqGram("i_" + issue_counter);
			co.addChild(iss);

			if (issue.hasChildNodes()) {
				NodeList commitss = issue.getChildNodes();
				for (int z = 0; z < commitss.getLength(); z++) {
					Node c = commitss.item(z);
					NodePqGram comm = new NodePqGram("c_" + commit_counter);
					iss.addChild(comm);

					if (c.hasChildNodes()) {

						NodeList files = c.getChildNodes();
						for (int y = 0; y < files.getLength(); y++) {
							// Node file = files.item(y);
							NodePqGram fill = new NodePqGram("f_"
									+ file_counter);
							fill.addChild(new NodePqGram("*"));
							comm.addChild(fill);
							file_counter++;

						}
					}

					else {
						comm.addChild(new NodePqGram("*"));

					}
					commit_counter++;
				}

			}
			issue_counter++;

		}

		root.addChild(co);
		return root;
	}

	
	//CI SONO DEI BUG nel performing cross vor allem con pqGram da rivedere integralmente
	public static double getCrossDistance(Commit c1, Commit c2)
			throws SAXException, IOException, ParserConfigurationException {
		 Vector<String> result = new Vector<String>();
		Vector<String> result2 = new Vector<String>();
		 //Vector<String> v1 = createPair(c1);
		 //Vector<String> v2 = createPair(c2);
		Vector<String> v1 = createPath(c1);
		 Vector<String> v2 = createPath(c2);
		/*result1.clear();
		done.clear();
		NodePqGram node = createPqGram(c1);
	
	
		NodePqGram node2 = createPqGram(c2);

	
		
		pqGram(node, node.children.firstElement());
	

	
		Vector<String> v1 = (Vector<String>) result1.clone();
		
	
	
		result1.clear(); done.clear();
	
		pqGram(node2, node2.children.get(0));
		
	Vector<String> v2 = (Vector<String>) result1.clone();
		*/
System.out.println(v1.toString());
System.out.println(v2.toString());		
	
		
		
		String tem = "";
		for (int i = 0; i < v1.size(); i++) {
		
			if (i >= v2.size()) {
				if (!tem.equals("")) {
					result.add(tem);
				}
				tem = "";
				result.add(v1.get(i));

			} else {
				if (v1.size() != 1) {
					if (!v1.get(i).equals(v2.get(i))) {
						if (!tem.isEmpty()) {
							result.add(tem);
							tem = v1.get(i);
							result.add(tem);
							tem = "";
						} else {
							result.add(v1.get(i));

						}

					}

					else {
						tem += v1.get(i);
						if (i == v1.size() - 1) {
							if (!tem.isEmpty()) {
								result.add(tem);
							}

						}
					}
				} else {
					result.add(v1.get(i));

				}

			}
		}

		tem = "";
		for (int i = 0; i < v2.size(); i++) {
			if (i >= v1.size()) {
				if (!tem.isEmpty()) {
					result2.add(tem);
				}
				tem = "";
				result2.add(v2.get(i));

			} else {
				if (v2.size() != 1) {
					if (!v1.get(i).equals(v2.get(i))) {
						if (!tem.isEmpty()) {
							result2.add(tem);
							tem = v2.get(i);
							result2.add(tem);
							tem = "";

						}

						else {
							result2.add(v2.get(i));

						}

					}

					else {

						tem += v2.get(i);
						if (i == v2.size() - 1) {
							result2.add(tem);

						}

					}

				} else {

					result2.add(v2.get(i));

				}
			}
		}

	
		
		
		String x = "";
		for (String st : v1) {
			x += st;

		}

		
		String y = "";
		for (String st : v2) {
			y += st;

		}

		
	
		
		
	

		if (result.contains(y)) {
			result.remove(y);

		}

		if (result2.contains(x)) {
			result2.remove(x);

		}
		v1.remove("");
		v2.remove("");
		
		System.out.println(result.toString());
		System.out.println(result2.toString());
		double dist = ((((double) result.size()) / v1.size()) + (((double) result2.size()) / v2.size())) / 2;

		System.out.println(dist);

		return dist;
	}

	public static double getJacobDistance(Commit c1, Commit c2)
			throws SAXException, IOException, ParserConfigurationException {
		
		 Vector<String> result = new Vector<String>();
			Vector<String> result2 = new Vector<String>();
			 Vector<String> v1 = createPair(c1);
			 Vector<String> v2 = createPair(c2);
			//Vector<String> v1 = createPath(c1);
			//Vector<String> v2 = createPath(c2);
			result1.clear();
			done.clear();
		/*	NodePqGram node = createPqGram(c1);
		
		
			NodePqGram node2 = createPqGram(c2);

		
			
			pqGram(node, node.children.firstElement());
		

		
			Vector<String> v1 = (Vector<String>) result1.clone();
			
		
		
			result1.clear(); done.clear();
		
			pqGram(node2, node2.children.get(0));
			
		Vector<String> v2 = (Vector<String>) result1.clone();*/
			
		
		
		
		//Vector<String> v1 = createPath(c1);
		//Vector<String> v2 = createPath(c2);
		Vector<String> v3 = new Vector<String>();

		v3.addAll(v2);

		double union = 0;

		for (int i = 0; i < v1.size(); i++) {
			String st = v1.get(i);
			if (v2.contains(st)) {
				union++;
			} else {
				v3.add(v1.get(i));
			}

		}

		double resultf = 1 - ((union) / (v3.size()));

		return resultf;
	}

	public static void createMap() throws ParserConfigurationException,
			SAXException, IOException {
		map = new HashMap<Integer, Commit>();
		FileInputStream file = new FileInputStream("homebrew.xml");

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();

		DocumentBuilder builder = builderFactory.newDocumentBuilder();

		Document xmlDocument = builder.parse(file);

		NodeList persone = xmlDocument.getElementsByTagName("commit");

		// System.out.println(persone.getLength());

		int count = 0;

		for (int i = 0; i < NUMBER_COMMITS; i++) {
			Node nodo = persone.item(i);
			Node node = nodo.getParentNode();
			if ((node.getNodeName().equals("msr2014"))) {
				String result1 = "";
				// System.out.println("*********************");
				result1 = "<commit " + nodo.getAttributes().getNamedItem("sha")
						+ ">";
				// System.out.print("<commit " +
				// nodo.getAttributes().getNamedItem("sha")+">");
				NodeList list = nodo.getChildNodes();
				// System.out.println("--issues addressed by commit #" + count
				// + "--");

				for (int z = 0; z < list.getLength(); z++) {

					node = list.item(z);
					NodeList child = node.getChildNodes();
					result1 = result1 + "<issue "
							+ node.getAttributes().getNamedItem("id") + " "
							+ node.getAttributes().getNamedItem("state") + ">";

					// System.out.print("\n\t <issue "
					// + node.getAttributes().getNamedItem("id") +" "+
					// node.getAttributes().getNamedItem("state") + ">");
					// System.out.println("\t--commits related to the issue--");
					for (int p = 0; p < child.getLength(); p++) {
						Node subcommit = child.item(p);
						result1 = result1 + "<commit "
								+ subcommit.getAttributes().getNamedItem("sha")
								+ ">";
						// System.out.println("\n\t\t<commit " +
						// subcommit.getAttributes().getNamedItem("sha") + ">");
						// System.out.println("\t\t--files changed--");
						// System.out.println(result1);
						for (int u = 0; u < subcommit.getChildNodes()
								.getLength(); u++) {
							result1 = result1
									+ "<file "
									+ subcommit.getChildNodes().item(u)
											.getAttributes()
											.getNamedItem("name") + "/>";
							// System.out.println(result1);

						}
						result1 = result1 + ("</commit>");

					}
					result1 = result1 + "</issue>";

				}

				result1 = result1 + ("</commit>");
				// System.out.println(result1);
				Commit commit = new Commit(count, result1);
				map.put(count, commit);

				Writer writer = null;

				try {
					writer = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream("homebrew" + count + ".xml"),
							"utf-8"));
					writer.write(result1);
					count++;

				} catch (IOException ex) {
					// report
				} finally {
					try {
						writer.close();
					} catch (Exception ex) {
					}
				}
			}
		}

		System.out.println(count);

	}

	public static long getSize(String nomeZip) throws FileNotFoundException {
		File file = new File(nomeZip);

		return file.length();

	}

	public static void zipFile(File inputFile, String zipFilePath) {
		try {

			// Wrap a FileOutputStream around a ZipOutputStream
			// to store the zip stream to a file. Note that this is
			// not absolutely necessary
			FileOutputStream fileOutputStream = new FileOutputStream(
					zipFilePath);
			ZipOutputStream zipOutputStream = new ZipOutputStream(
					fileOutputStream);

			// a ZipEntry represents a file entry in the zip archive
			// We name the ZipEntry after the original file's name
			ZipEntry zipEntry = new ZipEntry(inputFile.getName());
			zipOutputStream.putNextEntry(zipEntry);

			FileInputStream fileInputStream = new FileInputStream(inputFile);
			byte[] buf = new byte[1024];
			int bytesRead;

			// Read the input file by chucks of 1024 bytes
			// and write the read bytes to the zip stream
			while ((bytesRead = fileInputStream.read(buf)) > 0) {
				zipOutputStream.write(buf, 0, bytesRead);
			}

			// close ZipEntry to store the stream to the file
			zipOutputStream.closeEntry();

			zipOutputStream.close();
			fileOutputStream.close();

			// System.out.println("Regular file :" +
			// inputFile.getCanonicalPath()+" is zipped to archive :"+zipFilePath);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeFile(String nome, int i) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(nome), "utf-8"));
			writer.write(map.get(i).text);

		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}

			zipFile(new File(nome), nome.replace(".txt", ".zip"));
			deleteFile(nome);
		}

	}

	public static void writeFile(String nome, String file) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(nome), "utf-8"));
			writer.write(file);

		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}

			zipFile(new File(nome), nome.replace(".txt", ".zip"));
			// deleteFile(nome);
		}

	}

	public static void deleteFile(String name) {
		File f = new File(name);

		// Make sure the file or directory exists and isn't write protected
		if (!f.exists())
			throw new IllegalArgumentException(
					"Delete: no such file or directory: " + name);

		if (!f.canWrite())
			throw new IllegalArgumentException("Delete: write protected: "
					+ name);

		// If it is a directory, make sure it is empty
		if (f.isDirectory()) {
			String[] files = f.list();
			if (files.length > 0)
				throw new IllegalArgumentException(
						"Delete: directory not empty: " + name);
		}

		// Attempt to delete it
		boolean success = f.delete();

		if (!success)
			throw new IllegalArgumentException("Delete: deletion failed");
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