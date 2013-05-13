import java.util.Vector;


public class Engine {
	private double epsilon;
	private double[][] mAcum;
	public Vector <double[]> history;


	public Engine() {
		mAcum = new double[3][3];
		mAcum[0][0] = 1.0/4.0;
		mAcum[0][1] = 3.0/4.0;
		mAcum[0][2] = 1.0;
		mAcum[1][0] = 3.0/4.0;
		mAcum[1][1] = 1.0;
		mAcum[1][2] = 1.0;
		mAcum[2][0] = 0.0;
		mAcum[2][1] = 1.0/2.0;
		mAcum[2][2] = 1.0;
		
		this.epsilon = 0.0001;
		history = new Vector<double[]>();
	}
	
	private boolean Converge(double[] v1, double[] v2)
	{
		for (int i = 0; i < v1.length; i++) {
			if(Math.abs(v1[i] - v2[i])>this.epsilon)
			{
				return false;
			}
		}

		return true;
	}
	
	private int GetNextStep(int prevStep)
	{
		double r = Math.random();
		for (int i = 0; i < mAcum[prevStep].length; i++) {
			if(r <= mAcum[prevStep][i])
			{
				return i;
			}
		}

		return 0;
	}
	
	private double[] Divide(double[] v, int count)
	{
		double[] aux = new double[v.length];
		for (int i = 0; i < v.length; i++) {
			aux[i] = v[i]/ count;
		}
		
		return aux;
	}
	
	public double[] Run()
	{
		int prevStep = 0;
		int currentStep = 0;
		double[] vCount = new double[] {1,0.0,0.0};
		double[] vCurrent = new double[] {0.0,0.0,0.0};
		double[] vPrev = new double[] {0.0,0.0,0.0};
		int count = 0;
		
		while(!Converge(vCurrent, vPrev) || count < 1000)
		{
			currentStep = this.GetNextStep(prevStep);
			vCount[currentStep]++;
			count ++;
			vPrev = vCurrent;
			prevStep = currentStep;
			vCurrent = this.Divide(vCount, count);
			history.add(vCurrent.clone());
		}

		return vCurrent;

	}

}
