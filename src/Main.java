public class Main {
    public static void main(String[] args) {
        Hexagon.TIME_DELAY = 100; // Change speed of animation.
        String file = "map3.txt"; // Change when trying other maps.
        CampusWalk walk = new CampusWalk(file, true);
        String result = walk.findPath();
        System.out.println(result);
    }

}