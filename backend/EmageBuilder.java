package backend;

public class EmageBuilder {
		
	/**
	 * Enum class for specifiying which type of aggregation happens in the EmageBuilder
	 * 
	 * op-MAX -> maximum value of all the STTPoints that fit in that EmageCell
	 * op-MIN -> min '''''
	 * sum -> sum of all values of the STTPoints in that specific cell
	 * count -> number of STTPoint in that specfic cell
	 * avg -> avg value of the STTPoints in that cell
	 */
	public enum Operator {
		MAX, MIN, SUM, COUNT, AVG
	}
	
	/*
	 * TODO: Takes a pointstream (and possibly a timewindow or some limiting factor) and
	 * returns an emage worthy of forwarding and adding to the DB
	 * 
	 * TODO: should we assume pointstream will always return something?
	 */
	
	public Emage buildEmage(PointStream pointStream, double timeWindow, Operator operator) {
		
		
		
		switch (operator) {
		case MAX:
			
			break;
		case MIN:
			break;
		case SUM:
			break;
		case COUNT:
			break;
		case AVG:
			break;
		}
		
		
		return null;
	}
}
