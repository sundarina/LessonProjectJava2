

public class CcoloredPoint extends CPoint implements Figure, ColorAble{

	private int color;

	//private CPoint localpoint;
	
	public CcoloredPoint(int x, int y, int color) {
		super(x, y);
		this.color = color;
	}

	public CcoloredPoint(CPoint point, int color) {
		//super(point.getX(), point.getY()); //извлекаем с родителя 
		super(point); 
	//	localpoint = point; //треб констр родительского класса
		this.color = color;
	}
	


	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "CcoloredPoint x = " + super.getX() + " y = " + this.getY() + ", color = " + color;
	}

	@Override
	public void display() {

		System.out.println(this);

	}

}
