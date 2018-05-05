package Runners;

import java.io.IOException;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import solution.Solution;

public class RunnerPart2 {
    /**
     * ��ʾ�ű�-2
     * ʵ�����������������ʽ������������5*5ƴͼ��24-�������⣩
     * ע�⣺����ǰҪ�޸Ľڵ�ά������JigsawNode���е�dimension��Ϊ5
     * Ҫ�󣺲��޸Ľű����ݣ������ܹ����У��ҵó�Ԥ�ڽ��
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // ���ڵ�ά���Ƿ�Ϊ5
        if (JigsawNode.getDimension() != 5) {
            System.out.print("�ڵ�ά������ȷ���뽫JigsawNode���ά��dimension��Ϊ5");
            return;
        }

        // ����Ŀ��״̬����destNode: {25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0};
        JigsawNode destNode = new JigsawNode(new int[]{25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0});

        // ���������ʼ״̬����startNode����Ŀ��״̬��ɢ�����ɿɽ�������ʼ״̬
        JigsawNode startNode = Solution.scatter(destNode, 1000);
        // JigsawNode startNode = new JigsawNode(new int[]{19,8,7,9,23,10,3,19,5,4,14,2,20,11,6,15,22,13,16,0,1,21,12,18,24,17});

        // ����jigsaw�������ó�ʼ״̬�ڵ�startNode��Ŀ��״̬�ڵ�destNode
        Jigsaw jigsaw = new Solution();

        // ִ������ʽ����ʾ���㷨
        jigsaw.ASearch(startNode, destNode);
    }

}
