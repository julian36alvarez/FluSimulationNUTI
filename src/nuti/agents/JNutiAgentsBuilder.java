package nuti.agents;


import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.util.ContextUtils;


public class JNutiAgentsBuilder implements ContextBuilder<Object> {
	

    @Override
    public Context build(Context<Object> context) {
        context.setId("nuti-agents");

        NetworkBuilder<Object> netBuilder = new NetworkBuilder<Object>(
                "infection network", context, true);
        netBuilder.buildNetwork();

        ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder
                .createContinuousSpaceFactory(null);
        ContinuousSpace<Object> space = spaceFactory.createContinuousSpace(
                "space", context, new RandomCartesianAdder<Object>(),
                new repast.simphony.space.continuous.WrapAroundBorders(),
                150, 150);

        GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
        Grid<Object> grid = gridFactory.createGrid("grid", context,
                new GridBuilderParameters<Object>(new WrapAroundBorders(),
                        new SimpleGridAdder<Object>(), true,
                        150, 150
                ));
        Parameters params = RunEnvironment.getInstance().getParameters();
        int humanCount = (Integer) params.getValue("population_size");
        int sickHumanCount = ((humanCount) * 10) / 100;
        int residenceCount = (Integer) params.getValue("total_residences");
        int residenceMax = (Integer) params.getValue("residence_max");
        int entretainmentCount = (Integer) params.getValue("total_entretainment");
        int workPlaceCount = (Integer) params.getValue("total_workspaces");


        for (int i = 0; i < residenceCount; i++) {
            context.add(new Residence(grid, space, residenceMax, i));
        }

        for (int i = 0; i < entretainmentCount; i++) {
            context.add(new Entertainment());
        }

        for (int i = 0; i < workPlaceCount; i++) {
            context.add(new Workplace(grid, space, 10));
        }

        for (int i = 0; i < humanCount; i++) {
            Human human;
            if (i < sickHumanCount) {
                human = new SickHuman(space, grid, 0, 0, false);
            } else {
                human = new HealthyHuman(space, grid, 0, 0, false);
            }
            context.add(human);

			for (Object obj : context.getObjects(Object.class)) {
				NdPoint pt = space.getLocation(obj);
				grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
			}

            int humanCountPerResidence = residenceMax;
            int humanCountAdded = 0;


            // Encontrar la residencia más cercana con menos de 4 humanos
            Residence closestResidence = null;
            double closestDistance = Double.MAX_VALUE;
            for (Object obj : context.getObjects(Residence.class)) {
                Residence residence = (Residence) obj;

                if (residence.getCurrentCapacity() < residenceMax) {
                    double distance = space.getDistance(space.getLocation(human), space.getLocation(residence));
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestResidence = residence;
                    }
                }
            }

            // Agregar el humano a la residencia más cercana
            if (closestResidence != null) {
                closestResidence.addHuman(human);

                humanCountAdded++;

                GridPoint pt = grid.getLocation(closestResidence);
                //System.out.println( pt.getX() +" - "+ pt.getY());
                human.setGridHousePoint(pt);
                space.moveTo(human, pt.getX(), pt.getY());

                if (humanCountAdded % humanCountPerResidence == 0) {
                    // Si ya se agregaron 4 humanos a la residencia, buscar la siguiente
                    closestDistance = Double.MAX_VALUE;
                }
            }
        }

        // 1 semana son 2016 ticks
        // 1 dia son 288 ticks
        // parar luego de 4 semanas -> total de 8064 ticks
        RunEnvironment.getInstance().endAt(8064);
        
       
        return context;
    }
    
    
    


}
