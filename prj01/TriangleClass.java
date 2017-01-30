
public class TriangleClass implements Figure {

	private CPoint apexA, apexB, apexC;
	private CLine sideAB, sideBC, sideCA;

	public TriangleClass(CPoint apexA, CPoint apexB, CPoint apexC) {
		this.apexA = apexA;
		this.apexB = apexB;
		this.apexC = apexC;
	}
	
	public TriangleClass(CLine ab, CLine bc, CLine ca) {
		sideAB = ab;
		sideBC = bc;
		sideCA = ca;
	}

	// ленивая или отложенная инициализация (в момент обращения метода созд обьект)
	// синглтон
	// singleton

	public CLine getSideAB() {
		if (sideAB == null) { // если ссылка на сторону пустая - создаем, если  нет - возвращаем адрес существующей стороны
								
			sideAB = new CLine(apexA, apexB);
		}
		return sideAB;
	}

	public CPoint getApexA() {
		return apexA;
	}

	public void setApexA(CPoint apexA) {
		this.apexA = apexA;
		sideAB = null; //если меняем вершину, сбрасываем стороны. они появятся через get метод
		sideCA = null; 
	}

	public CPoint getApexB() {
		return apexB;
	}

	public void setApexB(CPoint apexB) {
		this.apexB = apexB;
		sideBC = null;
		sideAB = null;
	}

	public CPoint getApexC() {
		return apexC;
	}

	public void setApexC(CPoint apexC) {
		this.apexC = apexC;
		sideCA = null;
		sideBC = null;
	}

	

	public CLine getSideBC() {
		if (sideBC == null) {
			sideBC = new CLine(apexB, apexC);
		}
		return sideBC;
	}

	public CLine getSideCA() {
		if (sideCA == null) {
			sideCA = new CLine(apexC, apexA);
		}
		return sideCA;
	}

	@Override
	public String toString() {
		
		return "Triangle A " + apexA + ", B " + apexB + ", C " + apexC;
		//return "Triangle A " + sideAB + ", B " + sideBC + ", C " + sideCA;
	}
	
	public double lengthAB() {
		return this.getSideAB().length(); //делегирование метода , чтобы не писать длинную цепочку методов 
	}

	@Override
	public void display() {
		System.out.println(this);
	} 
}
