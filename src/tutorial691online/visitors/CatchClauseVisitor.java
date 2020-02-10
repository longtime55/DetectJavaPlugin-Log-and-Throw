package tutorial691online.visitors;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;

import tutorial691online.handlers.SampleHandler;

public class CatchClauseVisitor extends ASTVisitor{

	private HashSet<CatchClause> dummyCatches = new HashSet<>();
	private HashSet<CatchClause> emptyCatches = new HashSet<>();
	
	@Override
	public boolean visit(CatchClause node) {
		SampleHandler.printMessage(String.format("Visit the %s pattern", node));
		
		MethodInvocationVisitor methodInvocationVisitor = new MethodInvocationVisitor("LogCatchSwitch");
		node.accept(methodInvocationVisitor);
		
		if(isEmptyException(node)) {
			emptyCatches.add(node);
		}
//		SampleHandler.printMessage(String.format("11dsaffrom the %s pattern", node.getBody().statements().size()));
//		SampleHandler.printMessage(String.format("22dsaffrom the %s pattern", methodInvocationVisitor.getLogPrintDefaultStatements()));
//		SampleHandler.printMessage(String.format(" the %s pattern", node.getBody().statements()));
		if(node.getBody().statements().size() >= methodInvocationVisitor.getLogPrintDefaultStatements() && !emptyCatches.contains(node)){
			
			dummyCatches.add(node);
		}

		return super.visit(node);
	}
	
	public HashSet<CatchClause> getDummyCatches() {
		return dummyCatches;
	}
	
	public HashSet<CatchClause> getEmptyCatches() {
		return emptyCatches;
	}


	private boolean isEmptyException(CatchClause node) {
		
		return node.getBody().statements().isEmpty();
		
	}
	
}
