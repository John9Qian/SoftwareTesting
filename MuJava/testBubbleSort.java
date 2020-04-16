import org.junit.Test;
import static org.junit.Assert.*;

public class testBubbleSort{
	@Test
	public void test() {
		int[] testArray = {1,6,2,2,5};
		int[] trueResult = {1,2,2,5,6};
		assertArrayEquals(trueResult, BubbleSort.BubbleSort(testArray));
	}

}