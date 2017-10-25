import java.util.Random;
/**
 *  The <code>RandomGenerator</code> class redefines the random function to randomly select a ride
 * 
 * @author Ying Zhang
 * ID: 110864972
 * Homework # 4
 * CSE214-R02
 * TA: David s. Li
 *
 */
public class RandomGenerator 
{
	/**
	 * selects an element of the array passed in with equal probability for each one.
	 * 
	 * @param rides an array of all the rides in <code>SevenFlags</code>
	 * 
	 * @return returns a ride randomly selected 
	 * 
	 */
	public static Ride selectRide(Ride[] rides)
	{
		Random randInt = new Random();
		return rides[randInt.nextInt(rides.length)];

	}
	/**
	 * selects a ride with user-defined probability 
	 * 
	 * @param rides an array of all the rides in <code>SevenFlags</code>
	 * @param probabilities an array of used chosen probabilities of choosing each ride 
	 * @return returns a ride chosen with user-defined probability 
	 * @throws IllegalArgumentException
	 * 		indicates the probabilities in the array does not add up to 1 
	 */
	public static Ride selectRide(Ride[] rides, double[] probabilities)throws IllegalArgumentException
	{
		double result = 0;
		for(int i = 0;i<probabilities.length;i++)
		{
			result+=probabilities[i];
		}
		if(result>1.00000001||result<0.9999999)
			throw new IllegalArgumentException("probability must add up to 1");
		
		double probability = Math.random();
		if(probability<probabilities[0])
		{
			return rides[0];
		}
		else if(probability<(probabilities[0]+probabilities[1]))
			return rides[1];
		else if(probability<(probabilities[0]+probabilities[1]+probabilities[2]))
			return rides[2];
		else return rides[3];
	}
	
	
}
