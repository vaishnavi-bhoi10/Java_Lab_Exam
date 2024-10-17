package EntityVehicle;

public class Vehicle {

	private int vehicleId;
	private String type;
	private boolean isAvailable;

	public Vehicle(int vehicleId, String type) {
		this.vehicleId = vehicleId;
		this.type = type;
		this.isAvailable = true; 
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public String getType() {
		return type;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}
}
