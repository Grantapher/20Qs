import java.util.Scanner;

public class questionNode {
	public String		data;
	public questionNode	yes;
	public questionNode	no;
	public questionNode	undo;
	
	questionNode(String data, questionNode undo) {
		this.data = data;
		this.undo = undo;
	}
	
	public boolean isQuestion() {	//is a question if it has children
		return yes != null || no != null;
	}
	
	public void cleanUpQuestion() {	//only retains alphabetic and numeric characters and adds a question mark
		Scanner s = new Scanner(data);
		StringBuilder newData = new StringBuilder();
		String next;
		while(s.hasNext()) {
			next = s.next().replaceAll("[^A-Za-z0-9]", "");
			newData.append(next.toLowerCase() + " ");
		}
		s.close();
		newData.deleteCharAt(newData.length() - 1);
		data = newData.toString();
		if(data.length() > 0)
			data = data.substring(0, 1).toUpperCase()
					+ data.substring(1).toLowerCase() + "?";
		else
			data = data.toUpperCase() + "?";
	}
	
	public void cleanUpAnswer() { //only retains alphabetic and numeric characters
		Scanner s = new Scanner(data);
		StringBuilder newData = new StringBuilder();
		String next;
		while(s.hasNext()) {
			next = s.next().replaceAll("[^A-Za-z0-9]", "");
			if(next.length() > 1)
				newData.append(next.substring(0, 1).toUpperCase()
						+ next.substring(1).toLowerCase() + " ");
			else
				newData.append(next.toUpperCase() + " ");
		}
		s.close();
		newData.deleteCharAt(newData.length() - 1);
		data = newData.toString();
	}
}
