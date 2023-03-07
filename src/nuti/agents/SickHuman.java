package nuti.agents;


import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;


public class SickHuman extends Human{
	

    
    public SickHuman(ContinuousSpace<Object> space, Grid<Object> grid, int timeWorked, int timeEntertained, boolean usesMask) {
        super(space, grid, timeWorked, timeEntertained, usesMask);
    }

	
	 public boolean isIncubated() {
		return (step < 1052);
	}

	public boolean isInfected() {
		return (step > 1052 && step < 3168);
	}
	
	public boolean isCured() {
		return (step > 3168);
	}
	
	



}
