package solution;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * �ڴ���������㷨�������ƴͼ��Ϸ��N-�������⣩
 */
public class Solution extends Jigsaw {
	private List<JigsawNode> solutionPath;  // ��·�������Ա������ʼ״̬����Ŀ��״̬���ƶ�·���е�ÿһ��״̬�ڵ�
    private int searchedNodesNum;           // �ѷ��ʽڵ��������Լ�¼���з��ʹ��Ľڵ������
    private Set<JigsawNode> visitedList;    // ���Ա����ѷ��ֵĽڵ�
    
    /**
     * ƴͼ���캯��
     */
    public Solution() {
    }

    /**
     * ƴͼ���캯��
     * @param bNode - ��ʼ״̬�ڵ�
     * @param eNode - Ŀ��״̬�ڵ�
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *��ʵ��һ��������������㷨����ָ��5*5ƴͼ��24-�������⣩�����Ž�
     * ���˺���������Solution���������������������
     * @param bNode - ��ʼ״̬�ڵ�
     * @param eNode - Ŀ��״̬�ڵ�
     * @return �����ɹ�ʱΪtrue,ʧ��Ϊfalse
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        this.visitedList = new HashSet<JigsawNode>(1000);
        Queue<JigsawNode> exploreList = new LinkedList<JigsawNode>();
        
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
        
        // ���ʽڵ�������29000������Ϊ����ʧ��
        final int MAX_NODE_NUM = 29000;
        final int DIRS = 4;
        
        // ���������
        this.searchedNodesNum = 0;
        this.solutionPath = null;
        
        // ��1������ʼ�ڵ����exploreList��
        this.visitedList.add(this.beginJNode);
        exploreList.add(this.beginJNode);
        
        // ��2)���exploreListΪ�գ����߷��ʽڵ�������MAX_NODE_NUM��
        //  ������ʧ�ܣ������޽�
        while (this.searchedNodesNum < MAX_NODE_NUM && !exploreList.isEmpty()) {
        	// ���ʴ�����1
        	this.searchedNodesNum++;
        	
        	// (2-1)ȡ��exploreList��ͷ�ڵ㣬������Ӷ�����pop��
        	this.currentJNode = exploreList.poll();
        	// ��cuurentJNode����ֹ״̬���������ɹ�������ѭ��
        	if(this.currentJNode.equals(eNode)) {
        		// ��÷��ʵ���ֹ״̬������·��
        		this.getPath();
        		break;
        	}
        	
        	// ������ǰ�ڵ���ĸ���״̬
        	JigsawNode[] nextNodes = new JigsawNode[] {
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)};
        	
        	// �����ĸ���״̬�����ƶ�
        	for (int i = 0; i < 4; i++) {
        		// �����i�����ƶ�������ģ����ƶ����״̬Ϊ�����ʹ�
        		if (nextNodes[i].move(i) && !this.visitedList.contains(nextNodes[i])) {
        			// ����ǰ�ڵ����״̬�����ѷ��ֵļ����У�ȷ����״̬�����ظ�����
        			this.visitedList.add(nextNodes[i]);
        			// ����ǰ�ڵ����״̬�����ѷ��ֵ�δ�����ʵĶ����У�ȷ����״̬�л��ᱻ����
        			exploreList.add(nextNodes[i]);
        		}
        	}
        }
        
        // ��ӡ������Ϣ
        System.out.println("Jigsaw BFSearch Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        System.out.println("Solution Path: ");
        System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + this.searchedNodesNum);
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        
        return this.isCompleted();
    }


    /**
     *��Demo+ʵ��������㲢�޸�״̬�ڵ�jNode�Ĵ��۹���ֵ:f(n)
     * �� f(n) = s(n). s(n)��������ڵ㲻��ȷ���������
     * �˺�����ı�ýڵ��estimatedValue����ֵ
     * �޸Ĵ˺���������Solution���������������������
     * @param jNode - Ҫ������۹���ֵ�Ľڵ�
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // �����ڵ㲻��ȷ���������
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
        
        int t = 0; // ���зŴ�λ������������ȷλ�õľ���֮��
        for (int i = 1; i <= dimension * dimension; i++) {
        	// ���հ�λ���⣬�����ǰNode�ĵ�i��Ԫ���±겻��i����λ�ò���ȷ
        	if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0) {
        		int rightRow = (i-1) / dimension;
        		int rightColumn = (i-1) % dimension;
        		int presentRow = (jNode.getNodesState()[i] - 1) / dimension;
        		int presentColumn = (jNode.getNodesState()[i]-1) % dimension;
        		
        		int distance = (int) Math.sqrt((Math.pow((rightRow-presentRow), 2) + Math.pow(rightColumn-presentColumn, 2)));
        		t += distance;
        	}
        }
        
        int u = 0; // ���зŴ�λ���������
        for (int i = 1; i <= dimension * dimension; i++) {
        	// ���հ�λ���⣬�����ǰNode�ĵ�i��Ԫ���±겻��i����λ�ò���ȷ
        	if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0) {
        		u++;
        	}
        }
        // ����Ȩ��
        int []weight = {3, 10, 3};
        
        // �������Ȩ���ֵ
        int value = weight[0] * s + weight[1] * t + weight[2] * u;
        jNode.setEstimatedValue(value);
    }
}
