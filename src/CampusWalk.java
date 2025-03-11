/*
CS 1027B – Assignment 3
Name: Ziyin Mao
Student Number: 251428503
Email: Zmao47@uwo.ca
Created: March 10, 2025
*/

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

        Hexagon bestCell = null;
        boolean selected  = false; // created purely to distinguish two cells with 0 adjacent goose cells

        for (int i = 0; i<=5; i++) {

            Hexagon nextCell = cell.getNeighbour(i);

            // check 0: not null cell
            if (nextCell != null) {

                //System.out.println();

                //System.out.println("  checking cell number" + nextCell.getID());

                // check 1: not goose cell
                if (!nextCell.isGooseCell()) {

                    // check 2: goose count < 3
                    if (neighbourGooseCount(nextCell) < 3 || nextCell.isEnd()) {

                        // check 3: not marked cell
                        if (!nextCell.isMarked()) {


                            // 3.1
                            for (int j = 0; j <= 5; j++) {
                                Hexagon neighbourCell = cell.getNeighbour(j);
                                if (neighbourCell != null && neighbourCell.isEnd()) {
                                    bestCell = neighbourCell;
                                    return bestCell; }
                            }

                            if (nextCell.isEnd()) { return nextCell; }

                            // 3.2
                            else if (nextCell.isBookCell()) {
                                for (int j = 0; j <= 5; j++) {
                                    Hexagon neighbourCell = cell.getNeighbour(j);
                                    if (neighbourCell!=null && neighbourCell.isBookCell()) {
                                        bestCell = neighbourCell;
                                    }
                                }
                                //System.out.println("cell number " + bestCell.getID() + " is returned");
                                return bestCell;
                            }


                            // 3.3
                            else if (nextCell.isGrassCell()) {

                                int gooseCount = 6; // max allowed gooseCount on a cell + 1

                                for (int j = 0; j <= 5; j++) { // looping through curr's neighbours

                                    if (cell.getNeighbour(j) != null) {

                                        Hexagon neighbourCell = cell.getNeighbour(j);

                                        if (neighbourGooseCount(neighbourCell) < gooseCount
                                    && neighbourCell.isGrassCell()) {
                                            //System.out.println("best grass cell checking: number " + neighbourCell.getID() + "(has " + neighbourGooseCount(neighbourCell) + " goose neighbors)");

                                            if (neighbourGooseCount(neighbourCell) == 0 && !selected) {
                                                bestCell = neighbourCell;
                                                selected = true;
                                            } else if (neighbourGooseCount(neighbourCell) != 0){
                                                bestCell = neighbourCell;
                                            }
                                        }
                                    }
                                }

                               // System.out.println("cell number " + bestCell.getID() + " is returned");
                                return bestCell;
                            }

                            // 3.4
                            else if (nextCell.isSnowCell()) {
                                for (int j = 0; j <= 5; j++) {
                                    Hexagon neighbourCell = cell.getNeighbour(j);
                                    if (cell.getNeighbour(j)!=null && cell.getNeighbour(j).isSnowCell()) {
                                        bestCell = neighbourCell;
                                    }
                                }
                                System.out.println("cell number " + bestCell.getID() + " is returned");
                                return bestCell;
                            }

                        }
                    }
                }
            }
        }

        return null;
    }

    public String findPath() {
        StackADT<Hexagon> s = new ArrayStack<>();
        boolean running = true;
        StringBuilder st = new StringBuilder();

        s.push(map.getStart());
        map.getStart().markInStack();

        while (!s.isEmpty() && running) {
            Hexagon curr = s.peek();
            st.append(curr.getID() + " ");
            if (curr.isEnd()) {
                running = false;
                break;
            }

            Hexagon next = findBest(curr);

            if (next==null){
                System.out.println("null");
                s.pop();
                curr.markOutStack();
            } else {
                s.push(next);
                next.markInStack();
            }
        }

        if (!running) return st.toString();
        else {
            return "No path found";
        }
    }



    public void exit() {
        map.exit();
    }
}
