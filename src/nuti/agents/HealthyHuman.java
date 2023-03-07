package nuti.agents;


import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;


public class HealthyHuman extends Human{


	int step;
	public HealthyHuman(ContinuousSpace<Object> space, Grid<Object> grid,  int timeWorked, int timeEntretained,
            boolean usesMask) {
	super(space, grid,  timeWorked, timeEntretained, usesMask);
	}

	@Override
	protected boolean isInfected() {
		return false;
	}

	@Override
	protected boolean isCured() {
		return true;
	}


}
