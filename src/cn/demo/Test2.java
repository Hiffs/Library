package cn.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * չʾ�������ķ��� ͨ��JTable(int numRows,int numColumns)���������numRowsָ������numColumnsָ����
 * 
 * @author burns
 * 
 */
public class Test2 {

    public Test2() {
        JFrame f = new JFrame();
        // ����һ��10�к�10�еĿձ��
        JTable table = new JTable(10, 10);
        table.setPreferredScrollableViewportSize(new Dimension(550, 30));
        JScrollPane scrollPane = new JScrollPane(table);
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);
        f.setTitle("�����Դ���");
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
