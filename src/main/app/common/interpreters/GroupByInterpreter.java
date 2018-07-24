package main.app.common.interpreters;

import java.util.List;

import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.core.VarExprList;

import main.app.common.DotVisualizer;
import main.app.dot.Edge;
import main.app.dot.Node;
import main.app.dot.objects.AggregateNode;
import main.app.dot.objects.EntityNode;

public class GroupByInterpreter implements Interpreter {

	@Override
	public void interpret(Object obj, DotVisualizer visualizer) throws Exception
	{
		if (obj.getClass() != VarExprList.class) {
			throw new Exception(VarExprList.class+" needed as Object. Given: "+obj.getClass());
		}
		
		VarExprList groupByVarExpressions = (VarExprList) obj;
		List<Var> groupByVars = groupByVarExpressions.getVars();

		Node groupByNode = new AggregateNode("GROUP BY");
		String groupByString = "GROUP BY\\n---------\\n";
		
		for(Var groupByVar: groupByVars) {
			Node varNode = new EntityNode("?"+groupByVar.getName());
			groupByString += "* ?"+groupByVar.getName()+"\\l";

			Edge edge = new Edge();
			edge.setArrowhead("dot");
			edge.setFrom(groupByNode);
			edge.setTo(varNode);
			edge.setStyle("dotted");
			visualizer.getGraph().addEdge(edge);
			
			visualizer.getSubgraph().addNode(varNode);
		}
		
		groupByNode.setLabel(groupByString);

		visualizer.getGraph().addNode(groupByNode);
	}

}