/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import dao.StudentDAO;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
public class StudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        String url = "/index.jsp";
        
        StudentDAO studentDAO = new StudentDAO();
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "display_students";  // default action
        }
        
        // perform action and set URL to appropriate page
        if (action.equals("display_students")) {            
            // get list of users
            List<Student> students = studentDAO.getListStudent();   
            System.out.println("Student: " + students);
            request.setAttribute("students", students);
        } 
        else if(action.equals("new_student")) {
            url = "/studentAdd.jsp";
        }
        else if(action.equals("add_student")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String branchId = request.getParameter("branchId");

            // get and update user
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setBranchId(branchId);
            
            studentDAO.addStudent(student);

            // get and set updated users
            List<Student> students = studentDAO.getListStudent();            
            request.setAttribute("students", students); 
        }
        else if (action.equals("display_student")) {
            String id = request.getParameter("id");
            Student student = studentDAO.getStudent(id);
            session.setAttribute("student", student);
            url = "/studentUpdate.jsp";
        }
        else if (action.equals("update_student")) {
            // get parameters from the request
            String name = request.getParameter("name");
            String branchId = request.getParameter("branchId");

            // get and update user
            Student student = (Student) session.getAttribute("student");        
            student.setName(name);
            student.setBranchId(branchId);
            
            studentDAO.updateStudent(student);

            // get and set updated users
            List<Student> students = studentDAO.getListStudent();            
            request.setAttribute("students", students);            
        }
        else if (action.equals("delete_student")) {
            // get the user
            String id = request.getParameter("id");
            
            // delte the user
            studentDAO.deleteStudent(id);
            
            // get and set updated users
            List<Student> students = studentDAO.getListStudent();            
            request.setAttribute("students", students);      
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        doPost(request, response);
    }
}
