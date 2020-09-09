package com.intempt;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class GoldmineResolverService {

    public static void findOptimalPath(String inputFileName, String outputFileName) throws IOException {
        int [][] data = Files.lines(Paths.get(inputFileName), Charset.defaultCharset())
                .map(it -> Arrays.stream(it.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);
        long result = findOptimalPathNew(data);
        Files.write(Paths.get(outputFileName), String.valueOf(result).getBytes());
    }

    public static long findOptimalPath(int [][] map) {
        if(map == null || map.length == 0 || map[0].length == 0) return 0;
        long max = 0;
        for(int i = 0; i < map.length; i++) {
            long localMax = findOptimalPath(map, i, 0, 0);
            if(max < localMax) {
                max = localMax;
            }
        }
        return max;
    }
    private static long findOptimalPath(int [][] map, int i, int j, long prevSum) {
        if(map[0].length <= j || i < 0 || i >= map.length) return prevSum;
        prevSum += map[i][j];
        return Math.max(
                findOptimalPath(map, i - 1, j + 1, prevSum),
                Math.max(findOptimalPath(map, i, j + 1, prevSum), findOptimalPath(map, i + 1, j + 1, prevSum)));
    }

    public static int findOptimalPathNew(int map[][]) {
        int [][] buffer = new int[map.length][map[0].length];
        for (int j = map[0].length - 1; j >= 0; j--) {
            for (int i = 0; i < map.length; i++) {
                int top = 0;
                if(i > 0 && j != map[0].length - 1) {
                    top = buffer[i - 1][j + 1];
                }
                int middle = 0;
                if(j != map[0].length - 1) {
                    middle = buffer[i][j + 1];
                }
                int bottom = 0;
                if(i != map.length-1 && j != map[0].length - 1) {
                    bottom = buffer[i + 1][j + 1];
                }
                buffer[i][j] = map[i][j] + Math.max(middle, Math.max(top, bottom));
            }
            System.out.println(Arrays.deepToString(buffer));
        }
        int res = 0;
        for (int [] row: buffer) {
            res = Math.max(res, row[0]);
        }
        return res;
    }

}
