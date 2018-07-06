package main.app.dot;

import java.util.ArrayList;

public class Edge extends Object {
	protected Node from;
	protected Node to;
	protected String label;
	protected String style;
	protected String arrowhead;
	
	public Node getFrom() {
		return from;
	}
	
	public void setFrom(Node from) {
		this.from = from;
	}
	
	public Node getTo() {
		return to;
	}
	
	public void setTo(Node to) {
		this.to = to;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getStyle() {
		return style;
	}
	
	public void setStyle(String style) {
		this.style = style;
	}
	
	public String getArrowhead() {
		return label;
	}
	
	public void setArrowhead(String arrowhead) {
		this.arrowhead = arrowhead;
	}
	
	public String toDot()
	{
		ArrayList<String> argumentList = new ArrayList<>();

		if (this.label != null && !this.label.equals("")) {
			argumentList.add("label=\""+this.label+"\"");
		}
		if (this.style != null && !this.style.equals("")) {
			argumentList.add("style=\""+this.style+"\"");
		}
		if (this.arrowhead != null && !this.arrowhead.equals("")) {
			argumentList.add("arrowhead=\""+this.arrowhead+"\"");
		}
		
		return "\""+this.getFrom().getId()+"\" -> \""+this.getTo().getId()+"\" ["+String.join(", ", argumentList)+"]";
	}
	
	public String toString()
	{
		return this.toDot();
	}
}
