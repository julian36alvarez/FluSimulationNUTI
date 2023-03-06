package nuti.agents;

import java.util.List;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.SimUtilities;

public abstract class Human {

    protected ContinuousSpace<Object> space;
    protected Grid<Object> grid;
    protected String status;
    protected int timeWorked;
    protected int timeEntertained;
    protected boolean usesMask;
    private int idHouse;

    public Human(ContinuousSpace<Object> space, Grid<Object> grid, String status, int timeWorked, int timeEntertained, boolean usesMask) {
        this.space = space;
        this.grid = grid;
        this.status = status;
        this.timeWorked = timeWorked;
        this.timeEntertained = timeEntertained;
        this.usesMask = usesMask;
    }
    
	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		GridPoint pt = grid.getLocation(this);

		GridCellNgh<Workplace> nghCreator = new GridCellNgh<Workplace>(grid, pt, Workplace.class, 1, 1);
		List<GridCell<Workplace>> gridCells = nghCreator.getNeighborhood(true);

        GridCellNgh<Entertainment> nghCreator2 = new GridCellNgh<Entertainment>(grid, pt, Entertainment.class, 1, 1);
        List<GridCell<Entertainment>> gridCells2 = nghCreator2.getNeighborhood(true);

		SimUtilities.shuffle(gridCells, RandomHelper.getUniform());

		GridPoint pointWithWorkPlace = null;
        GridPoint pointWithEntertainment = null;


        // random desition to go to work or to entertainment place or to stay at home
        int random = RandomHelper.nextIntFromTo(0, 2);
        if (random == 0) {
            int maxCount = -1;
            for (GridCell<Workplace> cell : gridCells) {
                if (cell.size() > maxCount) {
                    pointWithWorkPlace = cell.getPoint();
                    maxCount = cell.size();
                }
            }
            moveTowards(pointWithWorkPlace);
            setTimeWorked(getTimeWorked() + 1);
            System.out.println("getTimeWorked() = " + getTimeWorked());
        } else if (random == 1) {
            int maxCount = -1;
            for (GridCell<Entertainment> cell : gridCells2) {
                if (cell.size() > maxCount) {
                    pointWithEntertainment = cell.getPoint();
                    maxCount = cell.size();
                }
            }
            moveTowards(pointWithEntertainment);
            setTimeEntertained(getTimeEntertained() + 1);
            System.out.println("getTimeEntertained() = " + getTimeEntertained());
        } else {
            System.out.println("Stay at home");
        }


		/*int maxCount = -1;
		for (GridCell<Workplace> cell : gridCells) {
			if (cell.size() > maxCount) {
				pointWithWorkPlace = cell.getPoint();
				maxCount = cell.size();
			}
		}
		moveTowards(pointWithWorkPlace);
        setTimeWorked(getTimeWorked() + 1);
        System.out.println("getTimeWorked() = " + getTimeWorked());*/
	}


	private void moveTowards(GridPoint pt) {
		NdPoint myPoint = space.getLocation(this);
		NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
		double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint, otherPoint);
		space.moveByVector(this, 1, angle, 0);
		grid.moveTo(this, (int)space.getLocation(this).getX(), (int)space.getLocation(this).getY());
	}
    
    public NdPoint getLocation() {
        return space.getLocation(this);
    }

    public void move(int x, int y) {
        grid.moveTo(this, x, y);
    }
    
    // Getters y setters
    public ContinuousSpace<Object> getSpace() {
        return space;
    }

    public void setSpace(ContinuousSpace<Object> space) {
        this.space = space;
    }

    public Grid<Object> getGrid() {
        return grid;
    }

    public void setGrid(Grid<Object> grid) {
        this.grid = grid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(int timeWorked) {
        this.timeWorked = timeWorked;
    }

    public int getTimeEntertained() {
        return timeEntertained;
    }

    public void setTimeEntertained(int timeEntertained) {
        this.timeEntertained = timeEntertained;
    }

    public boolean isUsesMask() {
        return usesMask;
    }

    public void setUsesMask(boolean usesMask) {
        this.usesMask = usesMask;
    }

    public int getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(int idHouse) {
        this.idHouse = idHouse;
    }
}
