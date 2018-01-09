package com.weshape3d.mvpdemo.mycollection;

import android.content.Intent;
import android.util.Log;

import org.w3c.dom.Node;

/**
 * Created by WESHAPE-DEV02 on 2017/11/24.
 */

public class AVLNode <T extends Comparable> {
    public AVLNode<T> left;//左节点
    public AVLNode<T> right;//右节点
    public T data;//数据
    public int height;//当前节点的高度

    public AVLNode(T data){
        this(null,null,data);
    }

    public AVLNode(AVLNode<T> left,AVLNode<T> right,T data){
        this(left,right,data,0);
    }

    public AVLNode(AVLNode<T> left,AVLNode<T> right,T data,int height){
        this.left = left;
        this.right = right;
        this.data = data;
        this.height = height;
    }

    /**
     *
     * @param  node 根节点
     * @return 树的高度
     */
    public int getHeight(AVLNode node){
        if(node == null){
            return 0;
        }
        int height = 1;//说明当前输入的节点不为null
        if(node.left!=null){//左节点不为空的时候
            height =height+ getHeight(node.left);
        }
        if(node.right!=null){
            int h = 1+getHeight(node.right);
            height = (height>=h?height:h);
        }
        return height;
    }

    /**
     * 判断是否为平衡二叉树
     */
    boolean isBalance(AVLNode node){
        if(node==null){
            return true;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        int diff = left - right;
        if(diff > 1 || diff < -1)
            return false;
        return isBalance(node.left)&&isBalance(node.right);
    }

    public void insertNode(AVLNode<T> node){
        if(this.data.compareTo(node.data)>=0){
            if(right==null){
                 right = node;
            }else {
                right.insertNode(node);
            }

        }else {
            if(left==null){
                left = node;
            }else {
                left.insertNode(node);
            }
        }
    }

    public void println(AVLNode node){
        if(node!=null){
            Log.d("drummor",node.data.toString());
            if(node.left!=null){
              node.println(node.left);
             }
             if(node.right!=null){
                node.println(node.right);
             }
        }
    }

    /**
     * 左旋
     */
    public void rightChange(AVLNode<T> node){
       if(isBalance(node)){
          if( getHeight(node.right) ==  getHeight(node.left)){
              //左右高度相等
          }else if(getHeight(node.right)>getHeight(node.left)){

          }else {
          }
       }
    }
}



