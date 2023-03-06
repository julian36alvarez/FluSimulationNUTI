package nuti.agents;

import repast.simphony.space.grid.Grid;
import repast.simphony.space.continuous.ContinuousSpace;

public class Residence {
    
    private Grid<Object> grid;
    private ContinuousSpace<Object> space;
    private int maxCapacity;
    private int currentCapacity;
    private int id;
    
    public Residence(Grid<Object> grid, ContinuousSpace<Object> space, int maxCapacity, int id) {
        this.grid = grid;
        this.space = space;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.id = id;
    }
    
    public void addHuman(Human human) {
        if (currentCapacity < maxCapacity) {
            currentCapacity++;
        }
    }
    
    public int getCurrentCapacity() {
        return currentCapacity;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getId() {
        return id;
    }

}
