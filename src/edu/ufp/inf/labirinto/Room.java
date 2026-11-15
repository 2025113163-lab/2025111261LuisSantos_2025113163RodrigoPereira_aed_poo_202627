package edu.ufp.inf.labirinto;

// Representa uma sala ou zona do labirinto com nivel de segurança e indice de perigo
public class Room extends Entity {

    private ZoneType zone;
    private String code;
    private int securityLevel;
    private Date detectionDate;
    private double hazardIndex;


    /**
    * Construtor para uma nova sala no labirinto
    * @param id - identificador unico da sala
    * @param zone - tipo de zona
    * @param code - codigo da sala
    * @param securityLevel - nivel de segurança
    * @param detectionDate - data de detecao da sala
    * @param hazardIndex - indice de perigo ambiental
    * @throws IllegalArgumentException
    */
    public Room(String id, ZoneType zone, String code, int securityLevel, Date detectionDate, double hazardIndex) throws IllegalArgumentException {
        super(id);
        if (code == null || code.isBlank()) {
           throw new IllegalArgumentException("Codigo da sala não pode ser nulo ou vazio");
        }
        if (securityLevel < 0) {
           throw new IllegalArgumentException("SecurityLevel deve ser positivo / nulo");
        }
        if (hazardIndex < 0.0) {
           throw new IllegalArgumentException("HazardIndex deve ser positivo / nulo");
        }
        this.zone = (zone != null) ? zone : ZoneType.UNKNOWN;
        this.detectionDate = (detectionDate != null) ? new Date(detectionDate) : new Date();
        this.code = code;
        this.securityLevel = securityLevel;
        this.hazardIndex = hazardIndex;
    }

    public ZoneType getZone() { return zone; }
    public void setZone(ZoneType zone) {
        if (zone != null) {
            this.zone = zone;
        }
    }

    public String getCode() { return code; }
    public void setCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Codigo da sala não pode ser nulo ou vazio");
        }
        this.code = code;
    }

    public int getSecurityLevel() { return securityLevel; }
    public void setSecurityLevel(int securityLevel) {
        if (securityLevel < 0) {
            throw new IllegalArgumentException("SecurityLevel deve ser positivo / nulo");
        }
        this.securityLevel = securityLevel;
    }

    public Date getDetectionDate() { return new Date(detectionDate); }
    public void setDetectionDate(Date detectionDate) {
        if (detectionDate != null) {
            this.detectionDate = new Date(detectionDate);
        }
    }

    public double getHazardIndex() { return hazardIndex; }
    public void setHazardIndex(double hazardIndex) {
        if (hazardIndex < 0.0) {
            throw new IllegalArgumentException("HazardIndex deve ser positivo / nulo");
        }
        this.hazardIndex = hazardIndex;
    }

    @Override
    public String toString() {
        return "Room{" + "id='" + getId() + '\'' + ", code='" + code + '\'' + ", zone=" + zone + ", securityLevel=" + securityLevel + ", hazardIndex=" + hazardIndex + ", detectionDate=" + detectionDate + '}';
    }
}