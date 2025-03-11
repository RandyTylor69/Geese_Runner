public class Main {
    public static void main(String[] args) {
        Hexagon.TIME_DELAY = 600; // Change speed of animation.
        String file = "map1.txt"; // Change when trying other maps.
        CampusWalk walk = new CampusWalk(file, true);
        String result = walk.findPath();
        System.out.println(result);
    }

}