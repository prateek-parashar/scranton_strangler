package byog.Core;

import static byog.Core.Parameters.getBaseParameters;

public class TestPlay {
    public static void main(String[] args) {
        long currentSeed = 64511135;
        getBaseParameters().tileRenderer.initialize(50, 50);
        getBaseParameters().setSEED(currentSeed);
        getBaseParameters().initializeWorld();

        BSPTree gameTree = splitAndGrow(getBaseParameters().getBaseWorld(), 3);
        gameTree.drawPartitions();
        getBaseParameters().tileRenderer.renderFrame(getBaseParameters().getWorld());

    }

    public static BSPTree splitAndGrow(Room r, int iterations) {
        BSPTree baseTree = new BSPTree(r);

        if(iterations > 0) {
            int toss = iterations % 2;
            Room[] childRooms = baseTree.randomSplitAndGrow(toss);
            baseTree.addLeftChild(splitAndGrow(childRooms[0], iterations - 1));
            baseTree.addRightChild(splitAndGrow(childRooms[1], iterations - 1));
        }
        return baseTree;
    }
}
