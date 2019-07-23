import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-11 14:57
 * @Descrption
 **/
public class MybatisTest {

    @Test
    public void testPath() throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("com/shiyi/mybatis/mapper");
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        List<String> list = new ArrayList<String>();
        String line;
        while((line = br.readLine()) != null){
            list.add(line);
            if(line.endsWith(".xml")){
                System.out.println(line);
            }
        }

    }
}
