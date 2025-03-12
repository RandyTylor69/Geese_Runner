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
        int cellVal;
        int bestCellVal =0;
        int bestCellGooseCount = 0;
        int gooseCount;

        for (int i = 0; i<=5; i++) {

            Hexagon nextCell = cell.getNeighbour(i);

            if (nextCell!=null) {
                if (nextCell.isEnd()) {
                    bestCell = nextCell;
                    return bestCell;
                } if (!nextCell.isGooseCell()) {
                    if (neighbourGooseCount(nextCell)<3) {
                        if(!nextCell.isMarked()) {

                            if (nextCell.isBookCell()) {
                                cellVal = 3;
                                if (cellVal > bestCellVal) {
                                    bestCell = nextCell;
                                    bestCellVal = cellVal;
                                }
                            }

                            else if (nextCell.isGrassCell()) {
                                cellVal = 2;

                                // here comes the FUCKING GOOSE CHECK OH LAWRD HERE HE COMETH
                                gooseCount = neighbourGooseCount(nextCell);

                                if (cellVal > bestCellVal || gooseCount < bestCellGooseCount) {

                                    bestCell = nextCell;
                                    bestCellVal = cellVal;
                                    bestCellGooseCount = gooseCount;

                                }
                            }

                            else if (nextCell.isSnowCell()) {
                                cellVal = 1;
                                if (cellVal > bestCellVal) {
                                    bestCell = nextCell;
                                    bestCellVal = cellVal;
                                }
                            }

                        }

                    }
                }
            }
        }
        if (bestCell!=null) {
            return bestCell;
        } else return null;


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
