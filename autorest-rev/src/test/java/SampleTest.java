
public class SampleTest {
	public static void main(String args[]) throws Exception {
		String str = "AMZN.OQ";
		if (str.contains(".")) {
			str = str.substring(0, str.indexOf("."));
		}
		System.out.println(str);
		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.valueOf("992558833208302773911"));
	}
}
