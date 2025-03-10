public class CampusWalk {
    private Map map;

    public CampusWalk(String filename, boolean showMap) {
        try {this.map = new Map(filename);} catch (Exception e) {System.out.println("Error occurred while loading file. ");}

        if (showMap) {
            map.showGUI();
        } else  {
            map.hideGUI();
        }
    }

    public int neighbourGooseCount(Hexagon cell) {
        int count = 0;
        for (int i = 0; i<=5; i++) {
            if (cell.getNeighbour(i) != null && cell.getNeighbour(i).isGooseCell()) {
                count++;
            }
        } return count;
    }

    public Hexagon findBest(Hexagon cell) {

        for (int i = 0; i<=5; i++) {

            Hexagon nextCell = cell.getNeighbour(i);

            if (nextCell != null) { // check 0: not null cell

                if (!nextCell.isGooseCell()) { // check 1: not goose cell

                    if (neighbourGooseCount(nextCell) < 3 || nextCell.isEnd()) { // check 2: goose count < 3

                        if (!nextCell.isMarked()) { // check 3: not marked cell

                            if (nextCell.isEnd()) {
                                return nextCell;

                            } else if (nextCell.isBookCell()) {

                                Hexagon[] cellArray = new Hexagon[6];

                                for (int j = 0; j <= 5; j++) if (cell.getNeighbour(j).isBookCell()) cellArray[j] = cell.getNeighbour(j);

                                for (Hexagon j : cellArray) {
                                    if (j.isBookCell()) return j;
                                    break;
                                }

                            } else if (nextCell.isGrassCell()) {

                                // loop through all neighbouring grass cells to see who has the lowest goose-neighbour-count

                                int gooseCount = 5; // max allowed gooseCount on a cell
                                Hexagon[] cellArray = new Hexagon[6]; // array that stores cells with least gooseCount

                                for (int j = 0; j <= 5; j++) {
                                    Hexagon nextCell_b = cell.getNeighbour(j);
                                    if (nextCell_b.isGrassCell() && neighbourGooseCount(nextCell_b)< gooseCount) {
                                        gooseCount = neighbourGooseCount(nextCell_b); // update gooseCount
                                    }
                                } // loop that finds the last gooseCount

                                for (int j = 0; j <= 5; j++) {
                                    if (neighbourGooseCount(cell.getNeighbour(j)) == gooseCount && cell.getNeighbour(j).isGrassCell()) cellArray[j] = cell.getNeighbour(j);
                                } // loop that initializes the array with cells of lowest gooseCount

                                for (Hexagon j : cellArray) {
                                    if (j.isGrassCell()) return j;
                                    break;
                                }
                            } else if (nextCell.isSnowCell()) {
                                Hexagon[] cellArray = new Hexagon[6];

                                for (int j = 0; j <= 5; j++) if (cell.getNeighbour(j).isSnowCell()) cellArray[j] = cell.getNeighbour(j);

                                for (Hexagon j : cellArray) {
                                    if (j.isSnowCell()) return j;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } return null;
    }
}
