package byog.Core;

import java.util.ArrayList;
import java.util.Random;

import static byog.Core.Parameters.getBaseParameters;

public class TestPlay {
    public static void main(String[] args) {
        long currentSeed = 3000;
        getBaseParameters().getTileRenderer().initialize(getBaseParameters().getBaseWorld().getWidth(),
                                                                getBaseParameters().getBaseWorld().getHeight());
        getBaseParameters().setRandomGenerator(new Random(currentSeed));
        getBaseParameters().initializeWorld();

        BSPTree gameTree = splitAndGrow(getBaseParameters().getBaseWorld(), 4);
        gameTree.generateHallways();
        gameTree.generateRooms();
        renderRooms(getBaseParameters().getFinalRooms());
        getBaseParameters().getTileRenderer().renderFrame(getBaseParameters().getWorld());

    }

    public static BSPTree splitAndGrow(Partition r, int iterations) {
        BSPTree baseTree = new BSPTree(r);

        if(iterations > 0) {
            int toss = iterations % 2;
            Partition[] childPartitions = baseTree.randomSplitAndGrow(toss);
            baseTree.addLeftChild(splitAndGrow(childPartitions[0], iterations - 1));
            baseTree.addRightChild(splitAndGrow(childPartitions[1], iterations - 1));
        }
        return baseTree;
    }

    public static void renderRooms(ArrayList<Room> roomList) {
        for (Room r : roomList) {
            r.drawRoom();
        }
    }

    public static void printRooms(ArrayList<Room> roomList) {
        for (Room r : roomList) {
            System.out.println(r);
        }
    }

}
