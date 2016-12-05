package EssObject;

public class Device {
	private String deviceName;
	private int deviceAmount;
	
	public Device(){
		this("",0);
	}
	
	public Device(String deviceName){
		this(deviceName , 0);
	}
	
	public Device(String deviceName , int deviceAmount){
		this.deviceName = deviceName;
		this.deviceAmount = deviceAmount;
	}
	
	public void setName(String deviceName){
		this.deviceName = deviceName;
	}
	
	public void setAmount(int deviceAmount){
		this.deviceAmount = deviceAmount;
	}
	
	public String getName(){
		return deviceName;
	}
	
	public int getAmount(){
		return deviceAmount;
	}
	
	@Override
	public String toString(){
		return deviceName + " " + deviceAmount;
	}
	
	@Override
	public boolean equals(Object o){
		if(o.getClass() != this.getClass())return false;
		Device device = (Device)o;
		if(device.deviceName != this.deviceName)return false;
		return true;
	}
}
