package jigsaw;

import java.io.IOException;

/**
 * ƴͼ��Ϸ�����ݽṹ��������ƴͼ��Ϸ�Ľڵ�״̬�ͽڵ����
 * @author abe
 *
 */
public class JigsawNode {
	// private static final int dimension = 3;  // ƴͼ��ά��
    private static final int dimension = 5;     // ƴͼ��ά�� 5*5
    private int[] nodesState;                   // ƴͼ״̬����һλ�洢�հ׸��λ�ã��������洢��Ӧ�����е���ֵ��
    private int nodeDepth;                      // �ӳ�ʼ״̬�����״̬�Ĳ���
    private JigsawNode parent;                  // �����״̬����һ��ƴͼ״̬�����ڻ�ȡ��·��
    private int estimatedValue;                 // ���۹���ֵ

    /**
     * JigsawNode���캯���������½�һ���ڵ�
     * @param data - �ڵ�״̬����һ��N*N+1��һά���飨NΪƴͼά��������1λ����հ׸�����λ�ã�����N*Nλ�ֱ����ÿһ�������ŷ������ֵ���������к������򣩡�
     */
    public JigsawNode(int[] data) {
        if(data.length == this.dimension*dimension+1){
            this.nodesState = new int[data.length];
            for (int i = 0; i < this.nodesState.length; i++)
                this.nodesState[i] = data[i];
            this.nodeDepth = 0;
            this.parent = null;
            this.estimatedValue = 0;
        } else
            System.out.println("����������󣺵�ǰ�Ľڵ�ά��Ϊ3.�봫�볤��Ϊ" + (dimension * dimension + 1)
                    + "�Ľڵ�״̬���飬���ߵ���Jigsaw���еĽڵ�ά��dimension");
    }

    /**
     * JigsawNode���캯��������һ�������������ͬ�Ľڵ�
     * @param jNode - ���Ը��ƵĽڵ�
     */
    public JigsawNode(JigsawNode jNode) {
        this.nodesState = new int[dimension * dimension + 1];
        this.nodesState = (int[]) jNode.nodesState.clone();
        this.nodeDepth = jNode.nodeDepth;
        this.parent = jNode.parent;
        this.estimatedValue = jNode.estimatedValue;
    }

    /**
     * ��ȡƴͼά��
     * @return dimension - ��ǰƴͼά��
     */
    public static int getDimension() {
        return dimension;
    }

    /**
     * ��ȡ�ڵ�״̬����һ��N*N+1��һά���顣��1λ����հ׸�����λ�ã�����N*Nλ�ֱ����ÿһ�������ŷ������ֵ���������к������򣩡�
     * @return nodesState - �ڵ�״̬����
     */
    public int[] getNodesState() {
        return nodesState;
    }

    /**
     * ��ȡ���ڵ�Ľڵ���ȣ����ӳ�ʼ״̬�����״̬���ƶ�����
     * @return nodeDepth - �ڵ����
     */
    public int getNodeDepth() {
        return nodeDepth;
    }

    /**
     * ��ȡ�ڵ�ĸ��ڵ㣬������˽ڵ����һ���ڵ�
     * @return parent - ���ڵ�
     */
    public JigsawNode getParent() {
        return parent;
    }

    /**
     * ��ȡ�ڵ�Ĵ��۹���ֵ
     * @return estimatedValue - �ڵ�Ĵ��۹���ֵ
     */
    public int getEstimatedValue() {
        return estimatedValue;
    }

    /**
     * ���ýڵ�Ĵ��۹���ֵ
     * @param estimatedValue - ����Ĵ��۹���ֵ
     */
    public void setEstimatedValue(int estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    /**
     * ��ʼ���ڵ�Ĵ��۹�ֵestimatedValue���ڵ����nodeDepth�͸��ڵ�parent�������ɢƴͼ����scatter֮��
     *
     */
    public void setInitial() {
        this.estimatedValue = 0;
        this.nodeDepth = 0;
        this.parent = null;
    }

    /**
     * �Ƚ�����ƴͼ״̬�����ڼ��һ���ڵ��Ƿ�ΪĿ��ڵ�
     * @param obj - JigsawNode��ʵ���������뵱ǰ�ڵ���бȽϵĽڵ�
     * @return ״̬��ͬ�򷵻�true�����򷵻�false
     */
    @Override
    public boolean equals(Object obj) {
        for (int i = 0; i < this.nodesState.length; i++) {
            if (this.nodesState[i] != ((JigsawNode) obj).nodesState[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a hash code.
     * @return a hash code for this node
     */
    @Override
    public int hashCode() {
        String str = new String();
        for (int index = dimension * dimension; index > 0; index--) {
            str += this.nodesState[index];
        }
        return str.hashCode();
    }

    /**
     * Returns true if this node is valid.
     * @return true if this node is valid
     */
    public boolean isValid() {
        if (this.nodesState == null || this.nodesState.length != (dimension * dimension + 1)
            || this.nodesState[0] < 0 || this.nodesState[0] >= this.nodesState.length
            || this.nodesState[this.nodesState[0]] != 0) {
            return false;
        }
        boolean[] has = new boolean[dimension * dimension];
        for (int i = dimension * dimension; i > 0; i--) {
            if (this.nodesState[i] < 0 || this.nodesState[i] >= has.length) {
                return false;
            }
            has[this.nodesState[i]] = true;
        }
        for (int i = has.length - 1; i >= 0; i--) {
            if (!has[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * ��ȡ��ʾ��ǰƴͼ״̬���ַ����ı�����һά������ʽ��ʾ
     * @return String ��ʾ��ǰƴͼ״̬���ַ����ı���һά������ʽ��
     */
    public String toString() {
        String str = new String();
        str += "{" + this.nodesState[0];
        for(int index = 1; index <= dimension * dimension; index++)
            str += "," + this.nodesState[index];
        str += "}";
        return str;
    }

    /**
     * ��ȡ��ʾ��ǰƴͼ״̬���ַ����ı��������о�����ʽ��ʾ
     * @return String ��ʾ��ǰƴͼ״̬���ַ����ı������о�����ʽ��
     */
    public String toMatrixString() {
        String str = new String();
        for (int x = 1,index = 1; x <= dimension; x++) {
            for (int y = 1; y <= dimension; y++,index++){
                str += this.nodesState[index] + "  ";
            }
            str += "\n";
        }
        return str;
    }

    /**
     * ̽�⵱ǰ״̬�пհ׸�Ŀ��ƶ���λ
     *
     * @return ����һ����λ���飬1��4λ�ֱ����հ׸��Ƿ������ϡ��¡������ƶ��� ֵΪ1ʱ����÷�����ƶ���ֵΪ0ʱ����÷��򲻿��ƶ���
     */
    public int[] canMove() {
        int[] movable = new int[] { 0, 0, 0, 0 };
        if (this.nodesState[0] > dimension
                && this.nodesState[0] <= dimension * dimension)
            movable[0] = 1; // �հ׸��������
        if (this.nodesState[0] >= 1
                && this.nodesState[0] <= dimension * (dimension - 1))
            movable[1] = 1; // �հ׸��������
        if (this.nodesState[0] % dimension != 1)
            movable[2] = 1; // �հ׸��������
        if (this.nodesState[0] % dimension != 0)
            movable[3] = 1; // �հ׸��������
        return movable;
    }

    /**
     * ̽�⵱ǰ״̬�пհ׸��ܷ������ƶ�
     * @return �������ƶ��򷵻�true,���򷵻�false
     */
    public boolean canMoveEmptyUp() {
        return (this.nodesState[0] > dimension && this.nodesState[0] <= dimension
                * dimension);
        // ����հ׸��ڵ�һ����������ƶ�
    }

    /**
     * ̽�⵱ǰ״̬�пհ׸��ܷ������ƶ�
     * @return �������ƶ��򷵻�true,���򷵻�false
     */
    public boolean canMoveEmptyDown() {
        return (this.nodesState[0] >= 1 && this.nodesState[0] <= dimension
                * (dimension - 1));
        // ����հ׸������һ����������ƶ�
    }

    /**
     * ̽�⵱ǰ״̬�пհ׸��ܷ������ƶ�
     * @return �������ƶ��򷵻�true,���򷵻�false
     */
    public boolean canMoveEmptyLeft() {
        return (this.nodesState[0] % dimension != 1);
        // ����հ׸��ڵ�һ����������ƶ�
    }

    /**
     * ̽�⵱ǰ״̬�пհ׸��ܷ������ƶ�
     * @return �������ƶ��򷵻�true,���򷵻�false
     */
    public boolean canMoveEmptyRight() {
        return (this.nodesState[0] % dimension != 0);
        // ����հ׸������һ����������ƶ�
    }

    /**
     * ��ĳһ�����ƶ���ǰƴͼ״̬�еĿհ׸�
     * @param direction - �����ǣ�0Ϊ���ϣ�1Ϊ���£�2Ϊ����3Ϊ����
     * @return �ƶ��ɹ�����true��ʧ�ܷ���false
     */
    public boolean move(int direction) {
        switch (direction) {
        case 0:
            return this.moveEmptyUp();
        case 1:
            return this.moveEmptyDown();
        case 2:
            return this.moveEmptyLeft();
        case 3:
            return this.moveEmptyRight();
        default:
            return false;
        }
    }

    /**
     * �����ƶ���ǰƴͼ״̬�еĿհ׸�
     * @return �ƶ��ɹ�����true��ʧ�ܷ���false
     */
    public boolean moveEmptyUp() {
        int emptyPos = this.nodesState[0];
        int emptyValue = this.nodesState[emptyPos];
        if (this.nodesState[0] > dimension
                && this.nodesState[0] <= dimension * dimension) {
            this.parent = new JigsawNode(this);
            this.nodeDepth++;

            this.nodesState[emptyPos] = this.nodesState[emptyPos - dimension];
            this.nodesState[emptyPos - dimension] = emptyValue;
            this.nodesState[0] = emptyPos - dimension;

            return true;
        }
        return false;
    }

    /**
     * �����ƶ���ǰƴͼ״̬�еĿհ׸�
     * @return �ƶ��ɹ�����true��ʧ�ܷ���false
     */
    public boolean moveEmptyDown() {
        int emptyPos = this.nodesState[0];
        int emptyValue = this.nodesState[emptyPos];
        if (this.nodesState[0] >= 1
                && this.nodesState[0] <= dimension * (dimension - 1)) {
            this.parent = new JigsawNode(this);
            ;
            this.nodeDepth++;

            this.nodesState[emptyPos] = this.nodesState[emptyPos + dimension];
            this.nodesState[emptyPos + dimension] = emptyValue;
            this.nodesState[0] = emptyPos + dimension;
            return true;
        }
        return false;
    }

    /**
     * �����ƶ���ǰƴͼ״̬�еĿհ׸�
     * @return �ƶ��ɹ�����true��ʧ�ܷ���false
     */
    public boolean moveEmptyLeft() {
        int emptyPos = this.nodesState[0];
        int emptyValue = this.nodesState[emptyPos];
        if (this.nodesState[0] % dimension != 1) {
            this.parent = new JigsawNode(this);
            ;
            this.nodeDepth++;

            this.nodesState[emptyPos] = this.nodesState[emptyPos - 1];
            this.nodesState[emptyPos - 1] = emptyValue;
            this.nodesState[0] = emptyPos - 1;
            return true;
        }
        return false;
    }

    /**
     * �����ƶ���ǰƴͼ״̬�еĿհ׸�
     * @return �ƶ��ɹ�����true��ʧ�ܷ���false
     */
    public boolean moveEmptyRight() {
        int emptyPos = this.nodesState[0];
        int emptyValue = this.nodesState[emptyPos];
        if (this.nodesState[0] % dimension != 0) {
            this.parent = new JigsawNode(this);
            ;
            this.nodeDepth++;

            this.nodesState[emptyPos] = this.nodesState[emptyPos + 1];
            this.nodesState[emptyPos + 1] = emptyValue;
            this.nodesState[0] = emptyPos + 1;
            return true;
        }
        return false;
    }

}
