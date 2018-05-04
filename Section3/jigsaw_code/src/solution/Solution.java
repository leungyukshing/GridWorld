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
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
	private List<JigsawNode> solutionPath;  // 解路径：用以保存从起始状态到达目标状态的移动路径中的每一个状态节点
    private int searchedNodesNum;           // 已访问节点数：用以记录所有访问过的节点的数量
    private Set<JigsawNode> visitedList;    // 用以保存已发现的节点
    
    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        this.visitedList = new HashSet<JigsawNode>(1000);
        Queue<JigsawNode> exploreList = new LinkedList<JigsawNode>();
        
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
        
        // 访问节点数大于29000个则认为搜索失败
        final int MAX_NODE_NUM = 29000;
        final int DIRS = 4;
        
        // 重置求解标记
        this.searchedNodesNum = 0;
        this.solutionPath = null;
        
        // （1）将起始节点放入exploreList中
        this.visitedList.add(this.beginJNode);
        exploreList.add(this.beginJNode);
        
        // （2)如果exploreList为空，或者访问节点数大于MAX_NODE_NUM个
        //  则搜索失败，问题无解
        while (this.searchedNodesNum < MAX_NODE_NUM && !exploreList.isEmpty()) {
        	// 访问次数加1
        	this.searchedNodesNum++;
        	
        	// (2-1)取出exploreList的头节点，并将其从队列中pop出
        	this.currentJNode = exploreList.poll();
        	// 若cuurentJNode是终止状态，则搜索成功，跳出循环
        	if(this.currentJNode.equals(eNode)) {
        		// 获得访问到终止状态的搜索路径
        		this.getPath();
        		break;
        	}
        	
        	// 建立当前节点的四个子状态
        	JigsawNode[] nextNodes = new JigsawNode[] {
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)};
        	
        	// 对于四个子状态进行移动
        	for (int i = 0; i < 4; i++) {
        		// 如果向i方向移动是允许的，且移动后的状态为被访问过
        		if (nextNodes[i].move(i) && !this.visitedList.contains(nextNodes[i])) {
        			// 将当前节点的子状态放入已发现的集合中，确保该状态不被重复访问
        			this.visitedList.add(nextNodes[i]);
        			// 将当前节点的子状态放入已发现但未被访问的队列中，确保该状态有机会被访问
        			exploreList.add(nextNodes[i]);
        		}
        	}
        }
        
        // 打印搜索信息
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
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
        
        int t = 0; // 所有放错位的数码与其正确位置的距离之和
        for (int i = 1; i <= dimension * dimension; i++) {
        	// 除空白位置外，如果当前Node的第i个元素下标不是i，则位置不正确
        	if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0) {
        		int rightRow = (i-1) / dimension;
        		int rightColumn = (i-1) % dimension;
        		int presentRow = (jNode.getNodesState()[i] - 1) / dimension;
        		int presentColumn = (jNode.getNodesState()[i]-1) % dimension;
        		
        		int distance = (int) Math.sqrt((Math.pow((rightRow-presentRow), 2) + Math.pow(rightColumn-presentColumn, 2)));
        		t += distance;
        	}
        }
        
        int u = 0; // 所有放错位的数码个数
        for (int i = 1; i <= dimension * dimension; i++) {
        	// 除空白位置外，如果当前Node的第i个元素下标不是i，则位置不正确
        	if (jNode.getNodesState()[i] != i && jNode.getNodesState()[i] != 0) {
        		u++;
        	}
        }
        // 设置权重
        int []weight = {3, 1, 2};
        
        // 计算出加权后的值
        int value = weight[0] * s + weight[1] * t + weight[2] * u;
        jNode.setEstimatedValue(value);
    }
}
