
public class Tests {
	
	public static void main(String[] args) {
		
		// It may help to run just one test at a time until it is working correctly.
		// Debugging is usually easier on isolated test cases.
		
		test1();
		//test2();
		//test3();

	}
	
	private static void test1 () {
		System.out.print("Test 1 - Path");
		CampusWalk walk = null;
		try {
			
			Hexagon.TIME_DELAY = 0;
			walk = new CampusWalk("map1.txt", false);
			String res = walk.findPath();
			String exp = "0 1 2 5 8 7 6";
			
			if (checkPath(res, exp)) {
				System.out.println("\t\t\t PASSED!"); 
			} else {
				System.out.println("\t\t\t FAILED");
				System.out.println("Expected path: " + exp);
				System.out.println("Your path: " + res);
			}
		} catch (Exception e) {
			System.out.println("\t\t\t FAILED (Exception)");
			System.out.println(e + " - " + e.getMessage());
		} finally {
			walk.exit();
		}
	}
	
	private static void test2 () {
		System.out.print("Test 2 - Path");
		CampusWalk walk = null;
		try {

			Hexagon.TIME_DELAY = 0;
			walk = new CampusWalk("map2.txt", false);
			String res = walk.findPath();
			String exp = "6 11 16 22 21 20 15 10 5 1 0 1 2 3 4 9 14 18";
			
			if (checkPath(res, exp)) {
				System.out.println("\t\t\t PASSED!");
			} else {
				System.out.println("\t\t\t FAILED");
				System.out.println("Expected path: " + exp);
				System.out.println("Your path: " + res);
			}
		} catch (Exception e) {
			System.out.println("\t\t\t FAILED (Exception)");
			System.out.println(e + " - " + e.getMessage());
		} finally {
			walk.exit();
		}
	}

	private static void test3 () {
		System.out.print("Test 3 - Path");
		CampusWalk walk = null;
		try {
			
			Hexagon.TIME_DELAY = 0;
			walk = new CampusWalk("map3.txt", false);
			String res = walk.findPath();
			String exp = "0 1 2 3 4 9 8 7 6 5 10 11 12 17";
			
			if (checkPath(res, exp)) {
				System.out.println("\t\t\t PASSED!");
			} else {
				System.out.println("\t\t\t FAILED");
				System.out.println("Expected path: " + exp);
				System.out.println("Your path: " + res);
			}
		} catch (Exception e) {
			System.out.println("\t\t\t FAILED (Exception)");
			System.out.println(e + " - " + e.getMessage());
		} finally {
			walk.exit();
		}
	}
	
	private static boolean checkPath (String stuRes, String expRes) {
		return stuRes.toLowerCase().trim().equals(expRes.toLowerCase().trim());
	}
}
