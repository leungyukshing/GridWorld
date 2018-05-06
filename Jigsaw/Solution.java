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
        
        // Set the constant variables
        final int MAX_NODE_NUM = 29000;
        final int DIRS = 4;
        
        // reset the searchnumber and searchpath
        this.searchedNodesNum = 0;
        this.solutionPath = null;
        
        // Put the beginJNode into two list
        this.visitedList.add(this.beginJNode);
        exploreList.add(this.beginJNode);
        
        // If the number is smaller than 29000 and the exploreList is not emplty, loops
        //  Else, the search is failed
        while (this.searchedNodesNum < MAX_NODE_NUM && !exploreList.isEmpty()) {
        	// update the search time
        	this.searchedNodesNum++;
        	
        	// (2-1)Add the current node into the exploreList
        	this.currentJNode = exploreList.poll();
        	// If the current node is equal to the endJNode, that means we find a path
          // Search succeed
        	if(this.currentJNode.equals(eNode)) {
        		// Get the path from the begin to the end
        		this.getPath();
        		break;
        	}
        	
        	// Create four child nodes of the present node
        	JigsawNode[] nextNodes = new JigsawNode[] {
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)};
        	
        	// Get the four different directions
        	for (int i = 0; i < 4; i++) {
        		// Make sure the state has not been visited
        		if (nextNodes[i].move(i) && !this.visitedList.contains(nextNodes[i])) {
        			// Now we visit this node
        			this.visitedList.add(nextNodes[i]);
        			// And we put to the queue, later we may process it
        			exploreList.add(nextNodes[i]);
        		}
        	}
        }
        
        // Print the search result
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
        
        int t = 0; // The distance from the wrong position to the right position
        for (int i = 1; i <= dimension * dimension; i++) {
        	// If is wrong, calculate the distance
        	if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0) {
        		int rightRow = (i-1) / dimension;
        		int rightColumn = (i-1) % dimension;
        		int presentRow = (jNode.getNodesState()[i] - 1) / dimension;
        		int presentColumn = (jNode.getNodesState()[i]-1) % dimension;
        		
        		int distance = (int) Math.sqrt((Math.pow((rightRow-presentRow), 2) + Math.pow(rightColumn-presentColumn, 2)));
        		t += distance;
        	}
        }
        
        int u = 0; // The number of wrong position
        for (int i = 1; i <= dimension * dimension; i++) {
        	if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0) {
        		u++;
        	}
        }
        // Give the weighs of three value
        int []weight = {3, 10, 3};
        
        // Calculate the EstimatedValue
        int value = weight[0] * s + weight[1] * t + weight[2] * u;
        jNode.setEstimatedValue(value);
    }
}
