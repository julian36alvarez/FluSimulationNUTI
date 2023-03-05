package nuti.agents;


import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Workplace {
	
	private ContinuousSpace<Object> space;
    private Grid<Object> grid;

    public Workplace(Grid<Object> grid, ContinuousSpace<Object> space, int capacity) {
        this.grid = grid;
        this.space = space;

    }
    
    

}
