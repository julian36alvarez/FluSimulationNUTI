package nuti.agents;


import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;


public class HealthyHuman extends Human{


	int step;
	public HealthyHuman(ContinuousSpace<Object> space, Grid<Object> grid, String status, int timeWorked, int timeEntretained,
            boolean usesMask) {
	super(space, grid, status, timeWorked, timeEntretained, usesMask);
	}

	public void infect() {
		status = "incubando";
	}

	@Override
	protected boolean isInfected() {
		return false;
	}

	@Override
	protected boolean isCure() {
		return true;
	}


}
