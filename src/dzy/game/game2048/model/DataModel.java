package dzy.game.game2048.model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static dzy.game.game2048.model.Array2dCopyUtil.CopyOption.*;

/**
 * 2048数据模型
 */
public final class DataModel {
    public static final int ROW = 4;
    public static final int COLUMN = 4;
    private static final int WIN_NUM = 11; // (2 pow 11 = 2048)
    private static final Random random = new Random();

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private int[][] data;
    private int score;

    public DataModel() {
        clear();
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    public int[][] getData() {
        return data;
    }

    public int getScore() {
        return score;
    }

    // 向矩阵之中随机添加数据
    private void randomAddNumber() {
        // 查找空位
        ArrayList<Point> emptyPos = new ArrayList<>();
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLUMN; j++) if (data[i][j] == 0) emptyPos.add(new Point(i, j));

        // 填充空位
        if (emptyPos.size() != 0) {
            Point point = emptyPos.get(random.nextInt(emptyPos.size()));
            data[point.x][point.y] = random.nextDouble() > 0.8 ? 2 : 1;
        }
    }

    // 向矩阵之中随机添加数据, 同时发出通知
    public void randomAddNumberWithNotify() {
        randomAddNumber();
        pcs.firePropertyChange("data", null, data);
    }

    public final void clear() {
        score = 0;
        data = new int[ROW][COLUMN];
        randomAddNumber();
        randomAddNumber();

        pcs.firePropertyChange("score", -1, 0);
        pcs.firePropertyChange("data", null, data);
    }

    // 检查游戏是否胜利, 也就是检测是否有数值到达11(2 pow 11 = 2048)
    public boolean isGameWin() {
        for (int[] arr : data) for (int i : arr) if (i == WIN_NUM) return true;

        return false;
    }

    // 检测游戏是否失败. 如果没有剩余空间, 上下方向与左右方向都不能合并, 那就游戏失败
    public boolean isGameLose() {
        // 检测是否有空方块
        for (int[] arr : data) for (int i : arr) if (i == 0) return false;

        // 检测是否有左右相邻的相同的方块
        for (int[] arr : data) {
            int lastNumber = 0;
            for (int i : arr) {
                if (i != 0 && lastNumber == i) return false;

                lastNumber = i;
            }
        }

        // 检测是否有上下相邻的相同的方块
        for (int j = 0; j < COLUMN; j++) {
            int lastNumber = 0;
            for (int[] arr : data) {
                int i = arr[j];
                if (i != 0 && lastNumber == i) return false;

                lastNumber = i;
            }
        }

        return true;
    }

    public boolean moveLeft() {
        return move(identity);
    }

    public boolean moveRight() {
        return move(mirror);
    }

    public boolean moveUp() {
        return move(transposition);
    }

    public boolean moveDown() {
        return move(anti_transposition);
    }

    // 返回是否移动成功
    private boolean move(Array2dCopyUtil.CopyOption option) {
        int[][] boxesCopy = Array2dCopyUtil.copy(data, identity); // 备份原有数组
        int[][] boxesTran = Array2dCopyUtil.copy(data, option); // 把原来的数组变换到新的数组

        for (int[] aBoxesTran : boxesTran) { // 合并相同值
            compact(aBoxesTran);
            merge(aBoxesTran);
            compact(aBoxesTran);
        }

        data = Array2dCopyUtil.copy(boxesTran, option); // 写回原矩阵
        pcs.firePropertyChange("data", null, data);
        return !isEqualArray(data, boxesCopy);
    }

    // 数组compact, 从左向右. 例子: 1 1 0 2 -> 0 1 1 2
    private void compact(int[] a) {
        int insetIndex = 0;
        for (int i = 0, n = a.length; i < n; i++) {
            if (a[i] != 0) {
                a[insetIndex] = a[i];

                if (i != insetIndex) a[i] = 0;

                insetIndex++;
            }
        }
    }

    // 相同值合并(不包括零), 从右向左, 这样可以避免连续合并. 例子: 0 1 1 2 -> 0 2 0 2
    private void merge(int[] a) {
        for (int i = 0, n = a.length - 1; i < n; i++) {
            if (a[i] == a[i + 1] && a[i] != 0) {
                a[i] = a[i] + 1;
                a[i + 1] = 0;

                score += Math.pow(2, a[i]);
                pcs.firePropertyChange("score", -1, score);
            }
        }
    }

    // 检测俩个同型矩阵是否等同
    private boolean isEqualArray(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) if (!Arrays.equals(a[i], b[i])) return false;
        return true;
    }
}
