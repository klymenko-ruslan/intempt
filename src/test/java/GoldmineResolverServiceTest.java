import com.intempt.GoldmineResolverService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class GoldmineResolverServiceTest {

    @Test
    public void testEmpty() {
        Assert.assertEquals(0, GoldmineResolverService.findOptimalPath(new int[][]{}));
    }

    @Test
    public void test1file() throws IOException {
        String inputFile = "test.txt";
        String outputFile = "test_out.txt";
        GoldmineResolverService.findOptimalPath(inputFile, outputFile);
        String output = new String(Files.readAllBytes(Paths.get("test_out.txt")));
        Assert.assertTrue(12l == Long.valueOf(output));
    }

    @Test
    public void test1() {
        Assert.assertEquals(12, GoldmineResolverService.findOptimalPathNew(
                new int [][]{{1, 3, 3},
                        {2, 1, 4},
                        {0, 6, 4}}));
    }

//    @Test
//    public void test2() {
//        Assert.assertEquals(16, GoldmineResolverService.findOptimalPathNew(
//                new int [][]{ {1, 3, 1, 5},
//                        {2, 2, 4, 1},
//                        {5, 0, 2, 3},
//                        {0, 6, 1, 2}}));
//    }
//
//    @Test
//    public void test3() {
//        Assert.assertEquals(83, GoldmineResolverService.findOptimalPathNew(
//                new int [][]{{10, 33, 13 ,15},
//                             {22, 21, 4, 1},
//                             {5, 0, 2, 3},
//                             {0, 6, 14, 2}
//                }));
//    }
}
