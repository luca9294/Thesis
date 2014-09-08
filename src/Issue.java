import java.util.Vector;


public class Issue {
public String id,label,state;
private Vector<CommitN> commits;
 
public Issue(String id, String label, String state,  Vector<CommitN> commits){
	this.id = id;
	this.label = label;
	this.state = state;
	this.commits = commits;
	
}


}
