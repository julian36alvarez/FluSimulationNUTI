package nuti.agents;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Human {

	private ContinuousSpace<Object> space;

	private Grid<Object> grid;	

	// sano, incubando, enfermo, curado
	private String state;
	
	private int timeWorked;
	
	private int timeEntretained;
	
	private boolean mask;
	
	public Human(
			ContinuousSpace<Object> space, 
			Grid<Object> grid, 
			String state, 
			int timeWorked, 
			int timeEntretained
	) {
		this.space = space;
		this.grid = grid;
		this.state = state;
		this.timeWorked = timeWorked;
		this.timeEntretained = timeEntretained;
		this.mask = false;
	}
	
	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		// pasan 5 minutos -> 1 tick -> 
	}
	

	public void infect() {
		state = "incubando";
	}
	
	
	// TODO: mirar velocidad
	public void moveTowards(GridPoint pt) {
		if (!pt.equals(grid.getLocation(this))) {
			NdPoint myPoint = space.getLocation(this);
			NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
			double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint,
					otherPoint);
			space.moveByVector(this, 1, angle, 0);
			myPoint = space.getLocation(this);
			grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
		}	
	}
	

}
