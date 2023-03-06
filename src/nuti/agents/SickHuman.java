package nuti.agents;


import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;


public class SickHuman extends Human{
	

	private int incubationTime;
    private int illnessTime;
    private boolean hasSymptoms;
    
    public SickHuman(ContinuousSpace<Object> space, Grid<Object> grid, String status, int timeWorked, int timeEntertained, boolean usesMask) {
        super(space, grid, status, timeWorked, timeEntertained, usesMask);
    }

	
	 public int getIncubationTime() {
	        return incubationTime;
	}

	    public void setIncubationTime(int incubationTime) {
	        this.incubationTime = incubationTime;
	    }

	    public int getIllnessTime() {
	        return illnessTime;
	    }

	    public void setIllnessTime(int illnessTime) {
	        this.illnessTime = illnessTime;
	    }

	    public boolean hasSymptoms() {
	        return hasSymptoms;
	    }

	    public void setHasSymptoms(boolean hasSymptoms) {
	        this.hasSymptoms = hasSymptoms;
	    }



}
