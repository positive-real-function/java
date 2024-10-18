import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 文件的基本信息方法
 * @author 金卓远
 */
public class File_information {

    /**
     * 打印文件夹内的所有文件名并将绝对路径存在字典中
     * path:文件夹路径
     * map：字典用于存放文件的绝对路径
     */

    public static void all_file_absolution(File path, Map map) {
        //进入文件
        File[] files = path.listFiles();
        //遍历数组
        assert files != null;
        for (File f : files) {
            //判断：文件or文件夹
            if (f.isFile()) {
                //是文件则打印文件名
//                System.out.println(f.getName());
                //将文件的绝对路径放入字典map中
                map.put(f.getName(),f.getAbsoluteFile());
            } else {
                //是文件夹
                //先打印文件夹名
//                System.out.println(f.getName());
                //递归
                all_file_absolution(f,map);
            }
        }
    }

    /**
     * 打印文件夹内的所有文件
     * @param path
     */
    public static void print_all_file_name(File path) {
        //进入文件
        File[] files = path.listFiles();
        //遍历数组
        assert files != null;
        for (File f : files) {
            //判断：文件or文件夹
            if (f.isFile()) {
                //是文件则打印文件名
                System.out.println(f.getName());
            } else {
                //是文件夹
                //先打印文件夹名
                System.out.println("--"+f.getName());

                //递归
                print_all_file_name(f);
            }
        }
    }


//    /**
//     * 返回文件绝对路径
//     */
//    public static String back_absolution(File path, String file_name) {
//        File[] files = path.listFiles();
//        //遍历数组
//        String result = null;
//        assert files != null;
//        for (File f : files) {
//            //判断：文件or文件夹
//            if (f.isFile() && f.getName().equals(file_name)) {
//                //是文件则比较
//                //名字相同则输出绝对路径
//                result = f.getAbsolutePath();
//            } else {
//                //递归
//                back_absolution(f, file_name);
//            }
//        }
//        return result;
//    }

    /**
     * 计算文件夹大小
     */
    public static long get_file_size(File src) {
        //计数器
        long len = 0;
        //创建数组储存文件夹
        File[] files = src.listFiles();
        //遍历数组
        assert files != null;
        for (File f : files) {
            //判断：
            if (f.isFile()) {
                //如果是文件则累加
                len += f.length();
            } else {
                //如果不是文件则递归
                len += get_file_size(f);
            }
        }
        return len;
    }


    /**
     * 判断文件或者文件夹的大小是否为0字节
     * 如果为零字节输出告知用户
     */
    public static void file_null(String path) {
        File f = new File(path);
        //判断path路径下的是否是文件夹
        if (f.isFile()) {
            //路径是文件
            long size = f.length();
            String name = f.getName();
//        System.out.println(size);
            //判断文件大小是否为0
            if (0 == size) {
                System.out.println("文件 " + name + " 的大小为0字节！");
                System.out.println("空文件的路径是 "+path);
            }
        } else {
            //路径是文件夹
            //计算文件夹大小
            long size = get_file_size(f);
            String name = f.getName();
            //判断文件夹大小是否为0
            if (0 == size) {
                System.out.println("文件夹 " + name + " 的大小为0字节！");
                System.out.println("空文件的夹路径是 "+path);
            }
        }
    }

    /**
     * 通过是否能够查看文件长宽像素来判断是否为图片类型
     * @param picture
     * @return
     * @throws IOException
     */
    //判断是否为图片类型
    public static boolean test_img(File picture) throws  IOException {
        try {
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
//            System.out.println(String.format("%.1f",picture.length()/1024.0));// 图片大小
            sourceImg.getWidth(); // 图片宽度
            sourceImg.getHeight(); // 图片高度
            return true;
        }catch(NullPointerException e){
            return false;
        }

    }

    /**
     * 判断文件夹内是否有空文件
     */
    public static void blank_file(File path){
        File[] files = path.listFiles();
        //遍历数组
        assert files != null;
        for (File f : files) {
            //判断：文件or文件夹
            if (f.isFile() &&f.length()==0) {
                //判断文件是否为空
                System.out.println("文件 "+f.getName()+" 为空文件!!!!");
            } else if (f.isDirectory()){
                //递归
                blank_file(f);
            }
        }
    }

    /**
     * 判断文件夹内是否有空文件（结果储存到map）
     * @param path
     * @param map
     */
    public static void blank_file_map(File path,Map map){
        File[] files = path.listFiles();
        //遍历数组
        assert files != null;
        for (File f : files) {
            //判断：文件or文件夹
            if (f.isFile() &&f.length()==0) {
                //判断文件是否为空
//                System.out.println("文件 "+f.getName()+" 为空文件!!!!");
                map.put(f.getName(),f.getAbsoluteFile());
            } else if (f.isDirectory()){
                //递归
                blank_file_map(f,map);
            }
        }
    }


    /**
     * 判断文件夹内所有文件后缀
     * @param path
     */

    public static void check_all_suffix(File path){
        File[] files = path.listFiles();
        //遍历数组
        assert files != null;
        for (File f : files) {
            //判断：文件or文件夹
            if (f.isFile() && !(Get_file_type.type_suffix(f).equals(Get_file_type.type_true(f)))) {
                //判断文件是否为空
                System.out.println("文件 "+f.getName()+" 后缀名不正确"+"  (正确的后缀为：."+Get_file_type.type_true(f)+")");
            } else if (f.isDirectory()){
                //递归
                check_all_suffix(f);
            }
        }
    }

    /**
     * 判断文件夹内所有文件后缀
     * @param path
     */
    public static void check_all_suffix_map(File path,Map map){
        File[] files = path.listFiles();
        //遍历数组
        assert files != null;
        for (File f : files) {
            //判断：文件or文件夹
            if (f.isFile() && !(Get_file_type.type_suffix(f).equals(Get_file_type.type_true(f)))) {
                //判断文件是否为空
//                System.out.println("文件 "+f.getName()+" 后缀名不正确"+"  (正确的后缀为：."+Get_file_type.type_true(f)+")");
                map.put(f.getName(),Get_file_type.type_true(f));
            } else if (f.isDirectory()){
                //递归
                check_all_suffix_map(f,map);
            }
        }
    }

}
