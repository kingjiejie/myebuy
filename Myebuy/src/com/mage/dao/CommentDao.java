package com.mage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mage.po.Comment;
import com.mage.util.DBUtil;
/**
 * 	留言模块Dao层
 */
public class CommentDao {
	private Comment comment = null;
	/**
	 * 	分页显示留言内容
	 * @param currentPage
	 * @param pageSize
	 * @return commentList 存储数据的集合
	 */
	public  List<Comment> queryListByPage(int currentPage,int pageSize){
		// 创建list集合存储数据
		List<Comment> commentList = new ArrayList<>();
		// 获取查询开始数据索引
	    int index	= pageSize*(currentPage-1);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写SQL语句
			String sql = "select content,nickName,createTime,replyContent,replyTime from t_comment order by createTime desc limit ?,? ";
			// 预编译
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, index);
			pstmt.setInt(2, pageSize);
			// 执行查询
			rest = pstmt.executeQuery();
			// 分析结果集
			while(rest.next()) {
				comment = new Comment();
				comment.setContent(rest.getString("content"));
				comment.setNickName(rest.getString("nickName"));
				comment.setCreateTime(rest.getTimestamp("createTime"));
				comment.setReplyContent(rest.getString("replyContent"));
				comment.setReplyTime(rest.getDate("replyTime"));
				commentList.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭连接
			DBUtil.close(rest, pstmt, conn);
		}
		return commentList;
	}
	
	// 查询所有留言数
	public int queryAllCount() {
		int  count = 0;
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rest  = null;
		try {
			// 建立连接
			conn =DBUtil.getConnection();
			// 编写SQL语句
			String sql  = "select count(1) from t_comment";
			// 预编译
			pstmt = conn.prepareStatement(sql);
			// 执行查询
			rest = pstmt.executeQuery();
			// 分析结果集
			while(rest.next()) {
				 count = rest.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 	通过传入参数添加留言信息
	 * @param comment
	 */
	public void addComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 建立连接
			conn = DBUtil.getConnection();
			// 编写SQL语句
			String sql = "insert into t_comment (content,createTime,nickName) values(?,now(),?)";
			// 预编译
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, comment.getContent());
			pstmt.setString(2, comment.getNickName());
			// 执行添加
			int row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭资源
			DBUtil.close(null, pstmt, conn);
		}
	}
}
