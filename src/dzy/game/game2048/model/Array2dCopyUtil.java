package dzy.game.game2048.model;

/**
 * Created by moper3 on 2017/11/28.
 * 各种莫名其妙的复制
 */
class Array2dCopyUtil {

    static int[][] copy(int[][] arr2d, CopyOption option) {
        switch (option) {
            case identity:
                return identityCopy(arr2d);
            case mirror:
                return mirrorCopy(arr2d);
            case transposition:
                return transpositionCopy(arr2d);
            case anti_transposition:
                return antiTranspositionCopy(arr2d);
            default:
                return new int[0][0];
        }
    }

    private static int[][] antiTranspositionCopy(int[][] arr2d) {
        int row = arr2d.length;
        int column = arr2d[0].length;
        int[][] ren = new int[column][row];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                ren[column - 1 - j][row - 1 - i] = arr2d[i][j];

        return ren;
    }

    private static int[][] identityCopy(int[][] arr2d) {
        int[][] ren = new int[arr2d.length][];
        for (int i = 0; i < arr2d.length; i++)
            ren[i] = arr2d[i].clone();

        return ren;
    }

    private static int[][] mirrorCopy(int[][] arr2d) {
        int row = arr2d.length;
        int column = arr2d[0].length;
        int[][] ren = new int[row][column];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                ren[i][j] = arr2d[i][column - 1 - j];

        return ren;
    }

    private static int[][] transpositionCopy(int[][] arr2d) {
        int row = arr2d.length;
        int column = arr2d[0].length;
        int[][] ren = new int[column][row];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                ren[j][i] = arr2d[i][j];

        return ren;
    }

    /**
     * 复制选项
     */
    public enum CopyOption {
        /**
         * 等同复制
         */
        identity,
        /**
         * 左右对称复制
         */
        mirror,
        /**
         * 主对角线转置复制
         */
        transposition,
        /**
         * 副对角线转置复制
         */
        anti_transposition
    }
}