package cn.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * չʾ�������ķ����� �����ŵ������У���������������û�к��
 * 
 * @author burns
 * 
 */
public class Test1 {

    public Test1() {
        JFrame f = new JFrame();
        Object[][] playerInfo = {
                // ��������е�����
                { "����", new Integer(91), new Integer(100), new Integer(191),
                        new Boolean(true) },
                { "��ѧ��", new Integer(82), new Integer(69), new Integer(151),
                        new Boolean(true) },
                { "÷��", new Integer(47), new Integer(57), new Integer(104),
                        new Boolean(false) },
                { "����", new Integer(61), new Integer(57), new Integer(118),
                        new Boolean(false) },
                { "���", new Integer(90), new Integer(87), new Integer(177),
                        new Boolean(true) }, };
        // ��������еĺ����
        String[] Names = { "����", "����", "��ѧ", "�ܷ�", "����" };
        // ��Names��playerInfoΪ����������һ�����
        JTable table = new JTable(playerInfo, Names);
        // ���ô˱���ͼ����ѡ��С
        table.setPreferredScrollableViewportSize(new Dimension(550, 30));
        // �������뵽�����������

        f.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);// ���к�ᷢ�֣�������û�б�ͷ������취�ǣ�������ɾ�����������2������취������һ�����ɡ�
        /**
         * ��һ�ַ����� 
         * f.getContentPane().add(new JScrollPane(table),
         * BorderLayout.CENTER);
         */

        /**
         * �ڶ��ַ�����
         * f.getContentPane().add(table.getTableHeader(),BorderLayout.NORTH);
         * f.getContentPane().add(table, BorderLayout.CENTER);
         */

        // �ٽ������������ӵ��м�������
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
        Test1 t = new Test1();
    }
}
