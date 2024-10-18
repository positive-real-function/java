import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

/**
 * 程序的GUI界面
 * @author 金卓远
 */
public class AnalyseFile extends JFrame {

    String FilePath;//文件路径
    String fileName;//文件名
    String FilesPath;//文件夹路径

    //文件夹部分
    JLabel jl1 = new JLabel("文件夹路径:");
    JTextField jtf1 = new JTextField(25);
    JButton jb1 = new JButton("预览");

    //文件夹按钮
    JButton jButton1=new JButton("查看文件夹大小");
    JButton jButton2=new JButton("查看所有文件");
    JButton jButton3=new JButton("检查空文件");
    JButton jButton4=new JButton("检查后缀名");

    //文件夹弹窗(运行结果）
    JTextArea jTextArea1=new JTextArea();
    JTextArea jTextArea2=new JTextArea();
    JTextArea jTextArea3=new JTextArea();
    JTextArea jTextArea4=new JTextArea();



    //文件部分
    JLabel jl2 = new JLabel(" 文件路径: ");
    JTextField jtf2 = new JTextField(25);
    JButton jb2 = new JButton("预览");

    //文件按钮
    JButton jButton5=new JButton("查看文件大小");
    JButton jButton6=new JButton("查看文件类型");

    //文件弹窗（运行结果）
    JTextArea jTextArea5=new JTextArea();
    JTextArea jTextArea6=new JTextArea();


    //分析按钮
    JButton jbAnalyse1 = new JButton("分析文件夹");
    JButton jbAnalyse2 = new JButton(" 分析文件 ");
    JLabel jlTip = new JLabel();

    //保存
    JLabel jLabel_save=new JLabel(" 保存路径：");
    JTextField jTextField_save=new JTextField(25);
    JButton jButton_save=new JButton("预览");


    //Tips
    JLabel jl3 = new JLabel();


    public AnalyseFile() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //窗口属性设置
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        this.setTitle("文件分析程序");
        this.setLayout(new GridLayout(7, 1));
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //布局1 文件夹目录输入
        JPanel jp1 = new JPanel();
        jb1.addMouseListener(new ButtonListener1());
        jp1.add(jl1);
        jp1.add(jtf1);
        jp1.add(jb1);
        this.add(jp1);

        //布局5 文件夹分析按钮
        JPanel jp5=new JPanel();
        jButton1.addMouseListener(new FilesCheck1());
        jButton2.addMouseListener(new FilesCheck2());
        jButton3.addMouseListener(new FilesCheck3());
        jButton4.addMouseListener(new FilesCheck4());
        jp5.add(jButton1);
        jp5.add(jButton2);
        jp5.add(jButton3);
        jp5.add(jButton4);
        this.add(jp5);

        //布局2 文件目录输入
        JPanel jp2 = new JPanel();
        jb2.addMouseListener(new ButtonListener2());
        jp2.add(jl2);
        jp2.add(jtf2);
        jp2.add(jb2);
        this.add(jp2);

        //布局6 文件分析按钮
        JPanel jp6=new JPanel();
        jButton5.addMouseListener(new FilesCheck5());
        jButton6.addMouseListener(new FilesCheck6());
        jp6.add(jButton5);
        jp6.add(jButton6);
        this.add(jp6);

        //布局7 保存路径输入
        JPanel jp7=new JPanel();
        jButton_save.addMouseListener(new ButtonListener3());
        jp7.add(jLabel_save);
        jp7.add(jTextField_save);
        jp7.add(jButton_save);
        this.add(jp7);

        //布局3 保存按钮
        JPanel jp3 = new JPanel();
        jbAnalyse1.addMouseListener(new Analyse1());
        jbAnalyse2.addMouseListener(new Analyse2());
        jp3.add(jbAnalyse1);
        jp3.add(jbAnalyse2);
        this.add(jp3);

        //布局4 分析完成提示
        JPanel jp4 = new JPanel();
        jp4.add(jl3);
        this.add(jp4);


    }

    /**
     * 文件夹功能内部类
     */
    //文件夹大小
    class FilesCheck1 extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            File f=new File(FilesPath);

            JFrame jf =new JFrame("文件夹大小");
            jf.setSize(400,300);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //滚动条
            JScrollPane jScrollPane=new JScrollPane();//创建滚动条面板
            jScrollPane.setViewportView(jTextArea1);//把text1组件放到滚动面板里
            jf.add(jScrollPane);//将滚动条面板加到窗体

//            jf.add(jTextArea1);
            jTextArea1.append(" "+files_size_string(f)+"\n");
            jf.setVisible(true);

//            JOptionPane.showMessageDialog(jf,"hello world");
        }
    }
    //查看文件夹内所有文件
    class FilesCheck2 extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            File f=new File(FilesPath);
            Map map=new HashMap<>();
            File_information.all_file_absolution(f,map);

            JFrame jf =new JFrame("所有文件");
            jf.setSize(400,300);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //滚动条
            JScrollPane jScrollPane=new JScrollPane();//创建滚动条面板
            jScrollPane.setViewportView(jTextArea2);//把text1组件放到滚动面板里
            jf.add(jScrollPane);//将滚动条面板加到窗体

//            jf.add(jTextArea2);
            for (Object key : map.keySet()){
                jTextArea2.append(" "+key.toString()+"\n");
//                System.out.println(key);
            }
            jTextArea2.append("\n");

            jf.setVisible(true);

        }
    }
    //检查文件夹内空文件
    class FilesCheck3 extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            File f=new File(FilesPath);
            Map map=new HashMap<>();
            File_information.blank_file_map(f,map);

            JFrame jf =new JFrame("空文件");
            jf.setSize(400,300);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //滚动条
            JScrollPane jScrollPane=new JScrollPane();//创建滚动条面板
            jScrollPane.setViewportView(jTextArea3);//（这是关键！不是用add）把text1组件放到滚动面板里
            jf.add(jScrollPane);//将滚动条面板加到窗体

//            jf.add(jTextArea3);
            for (Object key:map.keySet()){
                jTextArea3.append(" 文件 "+key.toString()+" 为空!!!!"+"\n");
            }
            jTextArea3.append("\n");
            jf.setVisible(true);
        }
    }
    //类型不匹配的文件
    class FilesCheck4 extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            File f=new File(FilesPath);
            Map map=new HashMap<>();
            File_information.check_all_suffix_map(f,map);

            JFrame jf =new JFrame("文件类型不匹配");
            jf.setSize(400,300);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //滚动条
            JScrollPane jScrollPane=new JScrollPane();//创建滚动条面板
            jScrollPane.setViewportView(jTextArea4);//（这是关键！不是用add）把text1组件放到滚动面板里
            jf.add(jScrollPane);//将滚动条面板加到窗体
//            jf.add(jTextArea4);

            Set keyset = map.keySet();
            for (Object key:keyset){
                jTextArea4.append(" 文件 "+key+" 后缀名不正确！！！"+"   (正确的后缀名为："+map.get(key)+")"+"\n");
            }
            jTextArea4.append("\n");
            jf.setVisible(true);
        }
    }

    /**
     * 文件功能内部类
     */
    //文件大小
    class FilesCheck5 extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            File file=new File(FilePath);

            JFrame jf =new JFrame("文件大小");
            jf.setSize(400,300);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //滚动条
            JScrollPane jScrollPane=new JScrollPane();//创建滚动条面板
            jScrollPane.setViewportView(jTextArea5);//（这是关键！不是用add）把text1组件放到滚动面板里
            jf.add(jScrollPane);//将滚动条面板加到窗体
//            jf.add(jTextArea5);

            if (0 == file.length()) {
                jTextArea5.append(" 文件大小为0！！！！");
            } else {
                jTextArea5.append(" 文件大小为： ");
                long size = file.length();
                if (size <= 1024) {
                    jTextArea5.append(file.length() + " B");
                } else if (size<=1024*1024) {
                    jTextArea5.append((float) file.length()/1024+" KB");
                }else if (size<=1024*1024*1024){
                    jTextArea5.append((float) file.length()/1024/1024+" KB");
                }else{
                    jTextArea5.append((float) file.length()/1024/1024/1024+" GB");
                }
            }
            jf.setVisible(true);
        }
    }
    //判断文件后缀名是否正确
    class FilesCheck6 extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            JFrame jf =new JFrame("文件类型");
            jf.setSize(400,300);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //滚动条
            JScrollPane jScrollPane=new JScrollPane();//创建滚动条面板
            jScrollPane.setViewportView(jTextArea6);//（这是关键！不是用add）把text1组件放到滚动面板里
            jf.add(jScrollPane);//将滚动条面板加到窗体
//            jf.add(jTextArea6);

            jTextArea6.append("文件后缀名为；"+Get_file_type.type_suffix(FilePath)+"\n");
            jTextArea6.append("文件MIME类型为："+Get_file_type.type_true(FilePath)+"\n");
            jTextArea6.append("结果：");
            jTextArea6.append(Get_file_type.is_correct_string(FilePath));


            jf.setVisible(true);
        }
    }

    /**
     * 文件选择窗口
     */
    //选择文件夹
    class ButtonListener1 extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {

            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int val = fc.showOpenDialog(null); // 文件打开对话框
            if (val == fc.APPROVE_OPTION) {
                // 正常选择文件
                FilesPath = fc.getSelectedFile().getPath();
                jtf1.setText(FilesPath);
            } else {
                // 未正常选择文件，如选择取消按钮
                jtf1.setText("未选择文件夹！");
            }
        }
    }
    //选择文件
    class ButtonListener2 extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {

            JFileChooser fc = new JFileChooser();
            int val = fc.showOpenDialog(null); // 文件打开对话框
            if (val == fc.APPROVE_OPTION) {
                // 正常选择文件
                FilePath = fc.getSelectedFile().getAbsolutePath();
                fileName = fc.getSelectedFile().getName();
                jtf2.setText(FilePath);
            } else {
                // 未正常选择文件，如选择取消按钮
                jtf2.setText("未选择文件！");
            }
        }
    }
    //选择保存目录
    class ButtonListener3 extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {

            JFileChooser fc = new JFileChooser();
            int val = fc.showOpenDialog(null); // 文件打开对话框
            if (val == fc.APPROVE_OPTION) {
                // 正常选择文件
                FilePath = fc.getSelectedFile().getAbsolutePath();
                fileName = fc.getSelectedFile().getName();
                jTextField_save.setText(FilePath);
            } else {
                // 未正常选择文件，如选择取消按钮
                jTextField_save.setText("未选择文件！");
            }
        }
    }
    /**
     * 文件夹操作
     */
    class Analyse1 extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Thread t = new Thread(new Runnable() {  //创建一个分析文件夹文件的线程
                @Override
                public void run() {
                    //分析文件夹结果
                    /**
                     * 1.文件夹大小
                     * 2.文件夹内容
                     * 3.文件夹内空文件
                     * 4.文件夹内类型不匹配的文件
                     */
                    //打印到txt
                    try {
                        PrintStream printStream =new PrintStream(FilePath);
                        System.setOut(printStream);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                    File f=new File(FilesPath);
                    //1.文件大小
                    files_size(f);

                    System.out.println("====================");

                    //2.遍历文件夹
                    File_information.print_all_file_name(f);

                    System.out.println("====================");
                    //3.检查空文件
                    File_information.blank_file(f);

                    System.out.println("====================");
                    //4.检查文件后缀
                    File_information.check_all_suffix(f);


                    jl3.setForeground(Color.green);
                    jl3.setText("文件夹分析完成! 分析结果保存在"+FilePath);
                }
            });
            t.start();
        }
    }
    /**
     * 文件操作
     */
    class Analyse2 extends MouseAdapter {
        //Todo 分析文件
        @Override
        public void mouseClicked(MouseEvent e) {
            Thread t = new Thread(new Runnable() {  //创建一个文件的线程
                @Override
                public void run() {
                    /**
                     * 1.文件大小（大小为0时警告）
                     * 2.文件后缀名和真实文件类型，给出结果
                     */

                    //打印到txt
                    try {
                        PrintStream printStream =new PrintStream(FilePath);
                        System.setOut(printStream);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                    String path = jtf2.getText();
                    File file = new File(path);

                    //1.文件大小
                    if (0 == file.length()) {
//                        System.out.println("文件大小为0！");
                        File_information.file_null(path);
                    } else {
                        System.out.println("文件大小为：");
                        long size = file.length();
                        if (size <= 1024) {
                            System.out.println(file.length() + "B");
                        } else if (size<=1024*1024) {
                            System.out.println((float) file.length()/1024+"KB");
                        }else if (size<=1024*1024*1024){
                            System.out.println((float) file.length()/1024/1024+"MB");
                        }else{
                            System.out.println((float) file.length()/1024/1024/1024+"GB");
                        }
                    }

                    System.out.println("====================");

                    //2.文件类型
                    System.out.println("文件后缀名为；"+Get_file_type.type_suffix(path));
                    System.out.println("文件MIME类型为："+Get_file_type.type_true(path));
                    System.out.println("结果：");
                    Get_file_type.is_correct(path);


                    //分析完成提示
                    jl3.setForeground(Color.green);
                    jl3.setText("文件分析完成! 分析结果保存在"+FilePath);
                }
            });
            t.start();
        }
    }



    //计算文件夹大小
    public static void files_size(File f){
        long size=File_information.get_file_size(f);
        if (size <= 1024) {
            System.out.println(size + "B");
        } else if (size<=1024*1024) {
            System.out.println((float) size/1024+"KB");
        }else if (size<=1024*1024*1024){
            System.out.println((float) size/1024/1024+"MB");
        }else{
            System.out.println((float) size/1024/1024/1024+"GB");
        }
    }

    public static String files_size_string(File f){
        long size=File_information.get_file_size(f);
        if (size <= 1024) {
            return (size + "B");
        } else if (size<=1024*1024) {
            return ((float) size/1024+"KB");
        }else if (size<=1024*1024*1024){
            return ((float) size/1024/1024+"MB");
        }else{
            return ((float) size/1024/1024/1024+"GB");
        }
    }
}


