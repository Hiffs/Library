package cn.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 展示创建表格的方法 通过JTable(int numRows,int numColumns)来创建表格，numRows指行数，numColumns指列数
 * 
 * @author burns
 * 
 */
public class Test2 {

    public Test2() {
        JFrame f = new JFrame();
        // 创建一个10行和10列的空表格
        JTable table = new JTable(10, 10);
        table.setPreferredScrollableViewportSize(new Dimension(550, 30));
        JScrollPane scrollPane = new JScrollPane(table);
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);
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
        Test2 t2 = new Test2();
    }
}
