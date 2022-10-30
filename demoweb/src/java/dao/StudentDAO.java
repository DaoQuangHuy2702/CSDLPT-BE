/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBConnect;
import entity.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LENOVO
 */
public class StudentDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Student> getListStudent() {
        try{
            String query = "select * from Student";

            conn = new DBConnect().getConnection();
            
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            List<Student> listStudent = new ArrayList();
            
            while(rs.next()) {
                Student student = new Student();
                
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setBranchId(rs.getString("branch_id"));
                
                listStudent.add(student);      
            }
            
            return listStudent;
        } catch(Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
    
    public int addStudent(Student student) {
        try {
            String query
                = "insert into Student(id, name, branch_id) "
                + "values (?, ?, ?)";
            
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getBranchId());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
    public int updateStudent(Student student) {
        try {
            String query
                = "update Student set " + 
                    "name = ?, " +
                    "branch_id = ? " +
                    "where id = ?";
            
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, student.getName());
            ps.setString(2, student.getBranchId());
            ps.setString(3, student.getId());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
    public int deleteStudent(String id) {
        try {
            String query = "delete from Student " + "where id = ?";
            
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, id);
            
            System.out.println("Huy hello"); 
            
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
    public Student getStudent(String id) {
        try {
            String query = "select * from Student " + "where id = ?";
            
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            Student student = new Student();
            
            if(rs.next()) { 
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setBranchId(rs.getString("branch_id"));   
            }
            
            return student;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
