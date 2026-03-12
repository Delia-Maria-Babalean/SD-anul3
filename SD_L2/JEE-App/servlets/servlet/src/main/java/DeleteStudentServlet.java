import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            // JPQL: DELETE folosind numele clasei StudentEntity
            Query query = em.createQuery("DELETE FROM StudentEntity s WHERE s.nume = :nume AND s.prenume = :prenume");
            query.setParameter("nume", nume);
            query.setParameter("prenume", prenume);
            query.executeUpdate();
            em.getTransaction().commit();
            response.sendRedirect("./fetch-student-list");
        } finally {
            em.close();
            emf.close();
        }
    }
}