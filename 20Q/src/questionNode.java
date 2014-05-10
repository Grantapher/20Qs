public class questionNode {
	public String		data;
	public questionNode	yes;
	public questionNode	no;
	
	questionNode(String data) {
		this(data, null, null);
	}
	
	questionNode(String data, questionNode yes, questionNode no) {
		this.data = data;
		this.yes = yes;
		this.no = no;
	}
	
	public boolean isQuestion() {
		return yes != null || no != null;
	}
}
