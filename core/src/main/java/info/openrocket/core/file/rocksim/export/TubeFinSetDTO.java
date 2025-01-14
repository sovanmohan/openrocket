package info.openrocket.core.file.rocksim.export;

import info.openrocket.core.file.rocksim.RockSimCommonConstants;
import info.openrocket.core.rocketcomponent.TubeFinSet;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * This class models an XML element for a Rocksim TubeFinSet.
 */
@XmlRootElement(name = RockSimCommonConstants.TUBE_FIN_SET)
@XmlAccessorType(XmlAccessType.FIELD)
public class TubeFinSetDTO extends BasePartDTO {

    @XmlElement(name = RockSimCommonConstants.OD)
    private double od = 0d;
    @XmlElement(name = RockSimCommonConstants.ID)
    private double id = 0d;
    @XmlElement(name = RockSimCommonConstants.TUBE_COUNT)
    private int tubeCount = 0;
    @XmlElement(name = RockSimCommonConstants.MAX_TUBES_ALLOWED)
    private int maxTubeCount = 0;

    /**
     * Default constructor.
     */
    public TubeFinSetDTO() {
    }

    /**
     * Copy constructor. Fully populates this instance with values taken from the OR
     * TubeFinSet.
     *
     * @param theORTubeFinSet The OR TubeFinSet component to be serialized in
     *                        Rocksim format
     */
    public TubeFinSetDTO(TubeFinSet theORTubeFinSet) {
        super(theORTubeFinSet);
        setId(theORTubeFinSet.getInnerRadius() * RockSimCommonConstants.ROCKSIM_TO_OPENROCKET_RADIUS);
        setOd(theORTubeFinSet.getOuterRadius() * RockSimCommonConstants.ROCKSIM_TO_OPENROCKET_RADIUS);
        setRadialAngle(theORTubeFinSet.getBaseRotation());
        setTubeCount(theORTubeFinSet.getFinCount());
    }

    /**
     * Set the outer diameter of the tube fin(s).
     *
     * @return diameter in meters
     */
    public double getOd() {
        return od;
    }

    /**
     * Set the outer diameter of the tube fin(s).
     *
     * @param theOd diameter in meters
     */
    public void setOd(double theOd) {
        od = theOd;
    }

    /**
     * Get the inner diameter of the tube fin(s).
     *
     * @return diameter in meters
     */
    public double getId() {
        return id;
    }

    /**
     * Set the inner diameter of the tube fin(s).
     *
     * @param theId diameter in meters
     */
    public void setId(double theId) {
        id = theId;
    }

    /**
     * Get the tube fin count.
     *
     * @return # tube fins
     */
    public int getTubeCount() {
        return tubeCount;
    }

    /**
     * Set the tube fin count.
     *
     * @param theTubeCount # tube fins
     */
    public void setTubeCount(final int theTubeCount) {
        tubeCount = theTubeCount;
        maxTubeCount = tubeCount;
    }

    /**
     * Get the max tube fin count. Since OR doesn't have this concept, just set it
     * to the actual count.
     *
     * @return # tube fins
     */
    public int getMaxTubeCount() {
        return maxTubeCount;
    }
}
