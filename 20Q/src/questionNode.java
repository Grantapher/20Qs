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
	
	public boolean isQuestion() {
		return yes != null || no != null;
	}
	
	public void cleanUpQuestion() {
		Scanner s = new Scanner(data);
		StringBuilder newData = new StringBuilder();
		String next;
		while(s.hasNext()) {
			next = s.next().replaceAll("[^A-Za-z]", "");
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
	
	public void cleanUpAnswer() {
		Scanner s = new Scanner(data);
		StringBuilder newData = new StringBuilder();
		String next;
		while(s.hasNext()) {
			next = s.next().replaceAll("[^A-Za-z]", "");
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
