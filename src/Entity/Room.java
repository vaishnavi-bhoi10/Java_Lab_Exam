package Entity;

public class Room {
	
	    private int roomId;
	    private String roomType;
	    private double pricePerNight;
	    private boolean availability;

	    public Room(int roomId, String roomType, double pricePerNight) {
	        this.roomId = roomId;
	        this.roomType = roomType;
	        this.pricePerNight = pricePerNight;
	        this.availability = true; 
	    }

	    public int getRoomId() {
	        return roomId;
	    }

	    public String getRoomType() {
	        return roomType;
	    }

	    public double getPricePerNight() {
	        return pricePerNight;
	    }

	    public boolean isAvailable() {
	        return availability;
	    }

	    public void setAvailability(boolean availability) {
	        this.availability = availability;
	    }

	    @Override
	    public String toString() {
	        return "Room ID: " + roomId + ", Type: " + roomType + ", Price per Night: $" + pricePerNight + ", Available: " + (availability ? "Yes" : "No");
	    }
	}