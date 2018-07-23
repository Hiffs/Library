package cn.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 展示创建表格的方法， 将表格放到容器中，但是在这个表格中没有横标
 * 
 * @author burns
 * 
 */
public class Test1 {

    public Test1() {
        JFrame f = new JFrame();
        Object[][] playerInfo = {
                // 创建表格中的数据
                { "王鹏", new Integer(91), new Integer(100), new Integer(191),
                        new Boolean(true) },
                { "朱学莲", new Integer(82), new Integer(69), new Integer(151),
                        new Boolean(true) },
                { "梅婷", new Integer(47), new Integer(57), new Integer(104),
                        new Boolean(false) },
                { "赵龙", new Integer(61), new Integer(57), new Integer(118),
                        new Boolean(false) },
                { "李兵", new Integer(90), new Integer(87), new Integer(177),
                        new Boolean(true) }, };
        // 创建表格中的横标题
        String[] Names = { "姓名", "语文", "数学", "总分", "及格" };
        // 以Names和playerInfo为参数，创建一个表格
        JTable table = new JTable(playerInfo, Names);
        // 设置此表视图的首选大小
        table.setPreferredScrollableViewportSize(new Dimension(550, 30));
        // 将表格加入到滚动条组件中

        f.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);// 运行后会发现，这个表格没有表头。解决办法是，把这行删掉，用下面的2个解决办法的任意一个即可。
        /**
         * 第一种方法： 
         * f.getContentPane().add(new JScrollPane(table),
         * BorderLayout.CENTER);
         */

        /**
         * 第二种方法：
         * f.getContentPane().add(table.getTableHeader(),BorderLayout.NORTH);
         * f.getContentPane().add(table, BorderLayout.CENTER);
         */

        // 再将滚动条组件添加到中间容器中
        f.setTitle("表格测试窗口");
        f.pack();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
    }
}
