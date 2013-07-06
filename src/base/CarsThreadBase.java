package base;

public abstract class CarsThreadBase implements Runnable {
	public static volatile boolean active=false;
	public abstract void run();
}
