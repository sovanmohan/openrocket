package info.openrocket.swing.gui.plot;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import info.openrocket.core.l10n.Translator;
import info.openrocket.core.simulation.FlightDataBranch;
import info.openrocket.core.simulation.FlightDataType;
import info.openrocket.core.simulation.FlightEvent;
import info.openrocket.core.startup.Application;
import info.openrocket.core.unit.Unit;
import info.openrocket.core.util.ArrayList;
import info.openrocket.core.util.BugException;
import info.openrocket.core.util.MathUtil;
import info.openrocket.core.util.Pair;


public class PlotConfiguration implements Cloneable {
	
	private static final Translator trans = Application.getTranslator();
	
	public static final PlotConfiguration[] DEFAULT_CONFIGURATIONS;
	static {
		ArrayList<PlotConfiguration> configs = new ArrayList<PlotConfiguration>();
		PlotConfiguration config;
		
		//// Vertical motion vs. time
		config = new PlotConfiguration(trans.get("PlotConfiguration.Verticalmotion"));
		config.addPlotDataType(FlightDataType.TYPE_ALTITUDE, 0);
		config.addPlotDataType(FlightDataType.TYPE_VELOCITY_Z);
		config.addPlotDataType(FlightDataType.TYPE_ACCELERATION_Z);
		config.setEvent(FlightEvent.Type.IGNITION, true);
		config.setEvent(FlightEvent.Type.BURNOUT, true);
		config.setEvent(FlightEvent.Type.APOGEE, true);
		config.setEvent(FlightEvent.Type.RECOVERY_DEVICE_DEPLOYMENT, true);
		config.setEvent(FlightEvent.Type.STAGE_SEPARATION, true);
		config.setEvent(FlightEvent.Type.GROUND_HIT, true);
		config.setEvent(FlightEvent.Type.TUMBLE, true);
		config.setEvent(FlightEvent.Type.EXCEPTION, true);
		config.setEvent(FlightEvent.Type.SIM_ABORT, true);
		configs.add(config);
		
		//// Total motion vs. time
		config = new PlotConfiguration(trans.get("PlotConfiguration.Totalmotion"));
		config.addPlotDataType(FlightDataType.TYPE_ALTITUDE, 0);
		config.addPlotDataType(FlightDataType.TYPE_VELOCITY_TOTAL);
		config.addPlotDataType(FlightDataType.TYPE_ACCELERATION_TOTAL);
		config.setEvent(FlightEvent.Type.IGNITION, true);
		config.setEvent(FlightEvent.Type.BURNOUT, true);
		config.setEvent(FlightEvent.Type.APOGEE, true);
		config.setEvent(FlightEvent.Type.RECOVERY_DEVICE_DEPLOYMENT, true);
		config.setEvent(FlightEvent.Type.STAGE_SEPARATION, true);
		config.setEvent(FlightEvent.Type.GROUND_HIT, true);
		config.setEvent(FlightEvent.Type.TUMBLE, true);
		config.setEvent(FlightEvent.Type.EXCEPTION, true);
		config.setEvent(FlightEvent.Type.SIM_ABORT, true);
		configs.add(config);
		
		//// Flight side profile
		config = new PlotConfiguration(trans.get("PlotConfiguration.Flightside"), FlightDataType.TYPE_POSITION_X);
		config.addPlotDataType(FlightDataType.TYPE_ALTITUDE);
		config.setEvent(FlightEvent.Type.IGNITION, true);
		config.setEvent(FlightEvent.Type.BURNOUT, true);
		config.setEvent(FlightEvent.Type.APOGEE, true);
		config.setEvent(FlightEvent.Type.RECOVERY_DEVICE_DEPLOYMENT, true);
		config.setEvent(FlightEvent.Type.STAGE_SEPARATION, true);
		config.setEvent(FlightEvent.Type.GROUND_HIT, true);
		config.setEvent(FlightEvent.Type.TUMBLE, true);
		config.setEvent(FlightEvent.Type.EXCEPTION, true);
		config.setEvent(FlightEvent.Type.SIM_ABORT, true);
		configs.add(config);
	
		
		//// Ground track
		config = new PlotConfiguration(trans.get("PlotConfiguration.Groundtrack"), FlightDataType.TYPE_POSITION_X);
		config.addPlotDataType(FlightDataType.TYPE_POSITION_Y, 0);
		config.addPlotDataType(FlightDataType.TYPE_ALTITUDE, 1);
		config.setEvent(FlightEvent.Type.IGNITION, true);
		config.setEvent(FlightEvent.Type.BURNOUT, true);
		config.setEvent(FlightEvent.Type.APOGEE, true);
		config.setEvent(FlightEvent.Type.RECOVERY_DEVICE_DEPLOYMENT, true);
		config.setEvent(FlightEvent.Type.GROUND_HIT, true);
		config.setEvent(FlightEvent.Type.EXCEPTION, true);
		config.setEvent(FlightEvent.Type.SIM_ABORT, true);
		configs.add(config);
		
		//// Stability vs. time
		config = new PlotConfiguration(trans.get("PlotConfiguration.Stability"));
		config.addPlotDataType(FlightDataType.TYPE_STABILITY, 0);
		config.addPlotDataType(FlightDataType.TYPE_CP_LOCATION, 1);
		config.addPlotDataType(FlightDataType.TYPE_CG_LOCATION, 1);
		config.setEvent(FlightEvent.Type.IGNITION, true);
		config.setEvent(FlightEvent.Type.BURNOUT, true);
		config.setEvent(FlightEvent.Type.APOGEE, true);
		config.setEvent(FlightEvent.Type.RECOVERY_DEVICE_DEPLOYMENT, true);
		config.setEvent(FlightEvent.Type.STAGE_SEPARATION, true);
		config.setEvent(FlightEvent.Type.GROUND_HIT, true);
		config.setEvent(FlightEvent.Type.TUMBLE, true);
		config.setEvent(FlightEvent.Type.SIM_ABORT, true);
		configs.add(config);
		
		//// Drag coefficients vs. Mach number
		config = new PlotConfiguration(trans.get("PlotConfiguration.Dragcoef"),
				FlightDataType.TYPE_MACH_NUMBER);
		config.addPlotDataType(FlightDataType.TYPE_DRAG_COEFF, 0);
		config.addPlotDataType(FlightDataType.TYPE_FRICTION_DRAG_COEFF, 0);
		config.addPlotDataType(FlightDataType.TYPE_BASE_DRAG_COEFF, 0);
		config.addPlotDataType(FlightDataType.TYPE_PRESSURE_DRAG_COEFF, 0);
		config.setEvent(FlightEvent.Type.EXCEPTION, true);
		config.setEvent(FlightEvent.Type.SIM_ABORT, true);
		configs.add(config);
		
		//// Roll characteristics
		config = new PlotConfiguration(trans.get("PlotConfiguration.Rollcharacteristics"));
		config.addPlotDataType(FlightDataType.TYPE_ROLL_RATE, 0);
		config.addPlotDataType(FlightDataType.TYPE_ROLL_MOMENT_COEFF, 1);
		config.addPlotDataType(FlightDataType.TYPE_ROLL_FORCING_COEFF, 1);
		config.addPlotDataType(FlightDataType.TYPE_ROLL_DAMPING_COEFF, 1);
		config.setEvent(FlightEvent.Type.IGNITION, true);
		config.setEvent(FlightEvent.Type.LAUNCHROD, true);
		config.setEvent(FlightEvent.Type.BURNOUT, true);
		config.setEvent(FlightEvent.Type.APOGEE, true);
		config.setEvent(FlightEvent.Type.RECOVERY_DEVICE_DEPLOYMENT, true);
		config.setEvent(FlightEvent.Type.STAGE_SEPARATION, true);
		config.setEvent(FlightEvent.Type.GROUND_HIT, true);
		config.setEvent(FlightEvent.Type.TUMBLE, true);
		config.setEvent(FlightEvent.Type.EXCEPTION, true);
		config.setEvent(FlightEvent.Type.SIM_ABORT, true);
		configs.add(config);
		
		//// Angle of attack and orientation vs. time
		config = new PlotConfiguration(trans.get("PlotConfiguration.Angleofattack"));
		config.addPlotDataType(FlightDataType.TYPE_AOA, 0);
		config.addPlotDataType(FlightDataType.TYPE_ORIENTATION_PHI);
		config.addPlotDataType(FlightDataType.TYPE_ORIENTATION_THETA);
		config.setEvent(FlightEvent.Type.IGNITION, true);
		config.setEvent(FlightEvent.Type.BURNOUT, true);
		config.setEvent(FlightEvent.Type.APOGEE, true);
		config.setEvent(FlightEvent.Type.RECOVERY_DEVICE_DEPLOYMENT, true);
		config.setEvent(FlightEvent.Type.STAGE_SEPARATION, true);
		config.setEvent(FlightEvent.Type.GROUND_HIT, true);
		config.setEvent(FlightEvent.Type.TUMBLE, true);
		config.setEvent(FlightEvent.Type.EXCEPTION, true);
		config.setEvent(FlightEvent.Type.SIM_ABORT, true);
		configs.add(config);
		
		//// Simulation time step and computation time
		config = new PlotConfiguration(trans.get("PlotConfiguration.Simulationtime"));
		config.addPlotDataType(FlightDataType.TYPE_TIME_STEP);
		config.addPlotDataType(FlightDataType.TYPE_COMPUTATION_TIME);
		config.setEvent(FlightEvent.Type.IGNITION, true);
		config.setEvent(FlightEvent.Type.BURNOUT, true);
		config.setEvent(FlightEvent.Type.APOGEE, true);
		config.setEvent(FlightEvent.Type.RECOVERY_DEVICE_DEPLOYMENT, true);
		config.setEvent(FlightEvent.Type.STAGE_SEPARATION, true);
		config.setEvent(FlightEvent.Type.GROUND_HIT, true);
		config.setEvent(FlightEvent.Type.TUMBLE, true);
		config.setEvent(FlightEvent.Type.EXCEPTION, true);
		config.setEvent(FlightEvent.Type.SIM_ABORT, true);
		configs.add(config);
		
		DEFAULT_CONFIGURATIONS = configs.toArray(new PlotConfiguration[0]);
	}
	
	
	
	/** Bonus given for the first type being on the first axis */
	private static final double BONUS_FIRST_TYPE_ON_FIRST_AXIS = 1.0;
	
	/**
	 * Bonus given if the first axis includes zero (to prefer first axis having zero over 
	 * the others) 
	 */
	private static final double BONUS_FIRST_AXIS_HAS_ZERO = 2.0;
	
	/** Bonus given for a common zero point on left and right axes. */
	private static final double BONUS_COMMON_ZERO = 40.0;
	
	/** Bonus given for only using a single axis. */
	private static final double BONUS_ONLY_ONE_AXIS = 50.0;
	
	
	private static final double INCLUDE_ZERO_DISTANCE = 0.3; // 30% of total range
	
	
	
	/** The data types to be plotted. */
	private ArrayList<FlightDataType> plotDataTypes = new ArrayList<FlightDataType>();
	
	private ArrayList<Unit> plotDataUnits = new ArrayList<Unit>();
	
	/** The corresponding Axis on which they will be plotted, or null to auto-select. */
	private ArrayList<Integer> plotDataAxes = new ArrayList<Integer>();
	
	private EnumSet<FlightEvent.Type> events = EnumSet.noneOf(FlightEvent.Type.class);
	
	/** The domain (x) axis. */
	private FlightDataType domainAxisType = null;
	private Unit domainAxisUnit = null;
	
	
	/** All available axes. */
	private final int axesCount;
	private ArrayList<Axis> allAxes = new ArrayList<Axis>();
	
	private String name = null;
	
	
	public PlotConfiguration() {
		this(null, FlightDataType.TYPE_TIME);
	}
	
	public PlotConfiguration(String name) {
		this(name, FlightDataType.TYPE_TIME);
	}
	
	public PlotConfiguration(String name, FlightDataType domainType) {
		this.name = name;
		// Two axes
		allAxes.add(new Axis());
		allAxes.add(new Axis());
		axesCount = 2;
		
		setDomainAxisType(domainType);
	}
	
	//// Axis
	
	public FlightDataType getDomainAxisType() {
		return domainAxisType;
	}
	
	public void setDomainAxisType(FlightDataType type) {
		boolean setUnit;
		
		if (domainAxisType != null && domainAxisType.getUnitGroup() == type.getUnitGroup())
			setUnit = false;
		else
			setUnit = true;
		
		domainAxisType = type;
		if (setUnit)
			domainAxisUnit = domainAxisType.getUnitGroup().getDefaultUnit();
	}
	
	public Unit getDomainAxisUnit() {
		return domainAxisUnit;
	}
	
	public void setDomainAxisUnit(Unit u) {
		if (!domainAxisType.getUnitGroup().contains(u)) {
			throw new IllegalArgumentException("Setting unit " + u + " to type " + domainAxisType);
		}
		domainAxisUnit = u;
	}
	
	//// FlightDataTypes
	
	public void addPlotDataType(FlightDataType type) {
		plotDataTypes.add(type);
		plotDataUnits.add(type.getUnitGroup().getDefaultUnit());
		plotDataAxes.add(-1);
	}
	
	public void addPlotDataType(FlightDataType type, int axis) {
		if (axis >= axesCount) {
			throw new IllegalArgumentException("Axis index too large");
		}
		plotDataTypes.add(type);
		plotDataUnits.add(type.getUnitGroup().getDefaultUnit());
		plotDataAxes.add(axis);
	}
	
	
	public void setPlotDataType(int index, FlightDataType type) {
		FlightDataType origType = plotDataTypes.get(index);
		plotDataTypes.set(index, type);
		
		if (origType.getUnitGroup() != type.getUnitGroup()) {
			plotDataUnits.set(index, type.getUnitGroup().getDefaultUnit());
		}
	}
	
	public void setPlotDataUnit(int index, Unit unit) {
		if (!plotDataTypes.get(index).getUnitGroup().contains(unit)) {
			throw new IllegalArgumentException("Attempting to set unit " + unit + " to group "
					+ plotDataTypes.get(index).getUnitGroup());
		}
		plotDataUnits.set(index, unit);
	}
	
	public void setPlotDataAxis(int index, int axis) {
		if (axis >= axesCount) {
			throw new IllegalArgumentException("Axis index too large");
		}
		plotDataAxes.set(index, axis);
	}
	
	public void setPlotDataType(int index, FlightDataType type, Unit unit, int axis) {
		if (axis >= axesCount) {
			throw new IllegalArgumentException("Axis index too large");
		}
		plotDataTypes.set(index, type);
		plotDataUnits.set(index, unit);
		plotDataAxes.set(index, axis);
	}
	
	public void removePlotDataType(int index) {
		plotDataTypes.remove(index);
		plotDataUnits.remove(index);
		plotDataAxes.remove(index);
	}
	
	
	
	public FlightDataType getType(int index) {
		return plotDataTypes.get(index);
	}
	
	public Unit getUnit(int index) {
		return plotDataUnits.get(index);
	}
	
	public int getAxis(int index) {
		return plotDataAxes.get(index);
	}
	
	public int getTypeCount() {
		return plotDataTypes.size();
	}
	
	
	/// Events
	
	public Set<FlightEvent.Type> getActiveEvents() {
		return events.clone();
	}
	
	public void setEvent(FlightEvent.Type type, boolean active) {
		if (active) {
			events.add(type);
		} else {
			events.remove(type);
		}
	}
	
	public boolean isEventActive(FlightEvent.Type type) {
		return events.contains(type);
	}
	
	
	
	
	
	public List<Axis> getAllAxes() {
		List<Axis> list = new ArrayList<Axis>();
		list.addAll(allAxes);
		return list;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of this PlotConfiguration.
	 */
	@Override
	public String toString() {
		return name;
	}
	
	
	
	/**
	 * Find the best combination of the auto-selectable axes.
	 * 
	 * @return	a new PlotConfiguration with the best fitting auto-selected axes and
	 * 			axes ranges selected.
	 */
	public PlotConfiguration fillAutoAxes(FlightDataBranch data) {
		PlotConfiguration config = recursiveFillAutoAxes(data).getU();
		//System.out.println("BEST FOUND, fitting");
		config.fitAxes(data);
		return config;
	}
	
	
	
	
	/**
	 * Recursively search for the best combination of the auto-selectable axes.
	 * This is a brute-force search method.
	 * 
	 * @return	a new PlotConfiguration with the best fitting auto-selected axes and
	 * 			axes ranges selected, and the goodness value
	 */
	private Pair<PlotConfiguration, Double> recursiveFillAutoAxes(FlightDataBranch data) {
		
		// Create copy to fill in
		PlotConfiguration copy = this.clone();
		
		int autoindex;
		for (autoindex = 0; autoindex < plotDataAxes.size(); autoindex++) {
			if (plotDataAxes.get(autoindex) < 0)
				break;
		}
		
		
		if (autoindex >= plotDataAxes.size()) {
			// All axes have been assigned, just return since we are already the best
			return new Pair<PlotConfiguration, Double>(copy, copy.getGoodnessValue(data));
		}
		
		
		// Set the auto-selected index one at a time and choose the best one
		PlotConfiguration best = null;
		double bestValue = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < axesCount; i++) {
			copy.plotDataAxes.set(autoindex, i);
			Pair<PlotConfiguration, Double> result = copy.recursiveFillAutoAxes(data);
			if (result.getV() > bestValue) {
				best = result.getU();
				bestValue = result.getV();
			}
		}
		
		return new Pair<PlotConfiguration, Double>(best, bestValue);
	}


	protected void fitAxes(FlightDataBranch data) {
		this.fitAxes(Collections.singletonList(data));
	}

	/**
	 * Fit the axes to hold the provided data.  All of the plotDataAxis elements must
	 * be non-negative.
	 * <p>
	 * NOTE: This method assumes that only two axes are used.
	 */
	public void fitAxes(List<FlightDataBranch> data) {
		// Reset axes
		for (Axis a : allAxes) {
			a.reset();
		}
		
		// Add full range to the axes
		int length = plotDataTypes.size();
		for (int i = 0; i < length; i++) {
			FlightDataType type = plotDataTypes.get(i);
			Unit unit = plotDataUnits.get(i);
			int index = plotDataAxes.get(i);
			if (index < 0) {
				throw new IllegalStateException("fitAxes called with auto-selected axis");
			}
			Axis axis = allAxes.get(index);
			
			double min = unit.toUnit(data.get(0).getMinimum(type));
			double max = unit.toUnit(data.get(0).getMaximum(type));

			for (int j = 1; j < data.size(); j++) {
				// Ignore empty data
				if (data.get(j).getLength() == 0) {
					continue;
				}
				min = Math.min(min, unit.toUnit(data.get(j).getMinimum(type)));
				max = Math.max(max, unit.toUnit(data.get(j).getMaximum(type)));
			}
			
			axis.addBound(min);
			axis.addBound(max);
		}

		// Ensure non-zero (or NaN) range, add a few percent range, include zero if it is close
		for (Axis a : allAxes) {
			if (MathUtil.equals(a.getMinValue(), a.getMaxValue())) {
				a.addBound(a.getMinValue() - 1);
				a.addBound(a.getMaxValue() + 1);
			}
			
			double addition = a.getRangeLength() * 0.03;
			a.addBound(a.getMinValue() - addition);
			a.addBound(a.getMaxValue() + addition);
			
			double dist;
			dist = Math.min(Math.abs(a.getMinValue()), Math.abs(a.getMaxValue()));
			if (dist <= a.getRangeLength() * INCLUDE_ZERO_DISTANCE) {
				a.addBound(0);
			}
		}
		
		
		// Check whether to use a common zero
		Axis left = allAxes.get(0);
		Axis right = allAxes.get(1);
		
		if (left.getMinValue() > 0 || left.getMaxValue() < 0 ||
				right.getMinValue() > 0 || right.getMaxValue() < 0 ||
				Double.isNaN(left.getMinValue()) || Double.isNaN(right.getMinValue()))
			return;
		
		
		
		//// Compute common zero
		
		double min1 = left.getMinValue();
		double max1 = left.getMaxValue();
		double min2 = right.getMinValue();
		double max2 = right.getMaxValue();

		// Get the zero locations, scaled down to a value from 0 to 1 (0 = bottom of y axis, 1 = top)
		double zeroLoc1 = -min1 / left.getRangeLength();
		double zeroLoc2 = -min2 / right.getRangeLength();

		// Scale the right axis
		if (zeroLoc1 > zeroLoc2) {
			min2 = -max2 * (zeroLoc1 / (1 - zeroLoc1));
		} else {
			max2 = min2 * (-1 / zeroLoc1 + 1);
		}

		// Apply scale
		left.addBound(min1);
		left.addBound(max1);
		right.addBound(min2);
		right.addBound(max2);
	}
	
	
	
	private double roundScale(double scale) {
		double mul = 1;
		while (scale >= 10) {
			scale /= 10;
			mul *= 10;
		}
		while (scale < 1) {
			scale *= 10;
			mul /= 10;
		}
		
		// 1 2 4 5 10
		
		if (scale > 7.5) {
			scale = 10;
		} else if (scale > 4.5) {
			scale = 5;
		} else if (scale > 3) {
			scale = 4;
		} else if (scale > 1.5) {
			scale = 2;
		} else {
			scale = 1;
		}
		return scale * mul;
	}
	
	
	
	@SuppressWarnings("unused")
	private double roundScaleUp(double scale) {
		double mul = 1;
		while (scale >= 10) {
			scale /= 10;
			mul *= 10;
		}
		while (scale < 1) {
			scale *= 10;
			mul /= 10;
		}
		
		if (scale > 5) {
			scale = 10;
		} else if (scale > 4) {
			scale = 5;
		} else if (scale > 2) {
			scale = 4;
		} else if (scale > 1) {
			scale = 2;
		} else {
			scale = 1;
		}
		return scale * mul;
	}
	
	
	@SuppressWarnings("unused")
	private double roundScaleDown(double scale) {
		double mul = 1;
		while (scale >= 10) {
			scale /= 10;
			mul *= 10;
		}
		while (scale < 1) {
			scale *= 10;
			mul /= 10;
		}
		
		if (scale > 5) {
			scale = 5;
		} else if (scale > 4) {
			scale = 4;
		} else if (scale > 2) {
			scale = 2;
		} else {
			scale = 1;
		}
		return scale * mul;
	}
	
	
	
	/**
	 * Fits the axis ranges to the data and returns the "goodness value" of this 
	 * selection of axes.  All plotDataAxis elements must be non-null.
	 * <p>
	 * NOTE: This method assumes that all data can fit into the axes ranges and
	 * that only two axes are used.
	 *
	 * @return	a "goodness value", the larger the better.
	 */
	protected double getGoodnessValue(FlightDataBranch data) {
		double goodness = 0;
		int length = plotDataTypes.size();
		
		// Fit the axes ranges to the data
		fitAxes(data);
		
		/*
		 * Calculate goodness of ranges.  100 points is given if the values fill the
		 * entire range, 0 if they fill none of it.
		 */
		for (int i = 0; i < length; i++) {
			FlightDataType type = plotDataTypes.get(i);
			Unit unit = plotDataUnits.get(i);
			int index = plotDataAxes.get(i);
			if (index < 0) {
				throw new IllegalStateException("getGoodnessValue called with auto-selected axis");
			}
			Axis axis = allAxes.get(index);
			
			double min = unit.toUnit(data.getMinimum(type));
			double max = unit.toUnit(data.getMaximum(type));
			if (Double.isNaN(min) || Double.isNaN(max))
				continue;
			if (MathUtil.equals(min, max))
				continue;
			
			double d = (max - min) / axis.getRangeLength();
			d = MathUtil.safeSqrt(d); // Prioritize small ranges
			goodness += d * 100.0;
		}
		
		
		/*
		 * Add extra points for specific things.
		 */
		
		// A little for the first type being on the first axis
		if (plotDataAxes.get(0) == 0)
			goodness += BONUS_FIRST_TYPE_ON_FIRST_AXIS;
		
		// A little bonus if the first axis contains zero
		Axis left = allAxes.get(0);
		if (left.getMinValue() <= 0 && left.getMaxValue() >= 0)
			goodness += BONUS_FIRST_AXIS_HAS_ZERO;
		
		// A boost if a common zero was used in the ranging
		Axis right = allAxes.get(1);
		if (left.getMinValue() <= 0 && left.getMaxValue() >= 0 &&
				right.getMinValue() <= 0 && right.getMaxValue() >= 0)
			goodness += BONUS_COMMON_ZERO;
		
		// A boost if only one axis is used
		if (Double.isNaN(left.getMinValue()) || Double.isNaN(right.getMinValue()))
			goodness += BONUS_ONLY_ONE_AXIS;
		
		return goodness;
	}
	
	
	
	/**
	 * Reset the units of this configuration to the default units. Returns this
	 * PlotConfiguration.
	 * 
	 * @return   this PlotConfiguration.
	 */
	public PlotConfiguration resetUnits() {
		for (int i = 0; i < plotDataTypes.size(); i++) {
			plotDataUnits.set(i, plotDataTypes.get(i).getUnitGroup().getDefaultUnit());
		}
		return this;
	}
	
	
	
	
	@Override
	public PlotConfiguration clone() {
		try {
			
			PlotConfiguration copy = (PlotConfiguration) super.clone();
			
			// Shallow-clone all immutable lists
			copy.plotDataTypes = this.plotDataTypes.clone();
			copy.plotDataAxes = this.plotDataAxes.clone();
			copy.plotDataUnits = this.plotDataUnits.clone();
			copy.events = this.events.clone();
			
			// Deep-clone all Axis since they are mutable
			copy.allAxes = new ArrayList<Axis>();
			for (Axis a : this.allAxes) {
				copy.allAxes.add(a.clone());
			}
			
			return copy;
			
			
		} catch (CloneNotSupportedException e) {
			throw new BugException("BUG! Could not clone().");
		}
	}
	
}
