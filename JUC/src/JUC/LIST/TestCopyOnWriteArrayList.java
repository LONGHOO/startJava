package JUC.LIST;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @Author: 十一
 * @Date: 2019-07-14 16:39
 * @Descrption 使用copyOnWriteArrayList做大量添加是效率低，每次添加后都会做复制的操作
 **/
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWrite cp = new CopyOnWrite();
        for (int i = 0; i < 20; i++) {
            new Thread(cp).start();
        }
    }

}

class CopyOnWrite implements Runnable {

    //  private static List<String> list = Collections.synchronizedList(new ArrayList<>());
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    static {
        list.add("aa");
        list.add("BB");
        list.add("Cc");
    }

    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("dd");
        }
    }
}