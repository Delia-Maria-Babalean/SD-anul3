import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String numeVechi = request.getParameter("numeVechi");
        String prenumeVechi = request.getParameter("prenumeVechi");
        String numeNou = request.getParameter("numeNou");
        String prenumeNou = request.getParameter("prenumeNou");

        int varstaVechi;
        int varstaNou;

        try {
            varstaVechi = Integer.parseInt(request.getParameter("varstaVechi"));
            varstaNou = Integer.parseInt(request.getParameter("varstaNou"));
        } catch (NumberFormatException e) {
            response.getWriter().println("Varsta trebuie sa fie un numar valid!");
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Query query = em.createQuery(
                    "UPDATE StudentEntity s " +
                            "SET s.nume = :numeNou, " +
                            "    s.prenume = :prenumeNou, " +
                            "    s.varsta = :varstaNou " +
                            "WHERE s.nume = :numeV " +
                            "AND s.prenume = :prenumeV " +
                            "AND s.varsta = :varstaV"
            );

            query.setParameter("numeNou", numeNou);
            query.setParameter("prenumeNou", prenumeNou);
            query.setParameter("varstaNou", varstaNou);
            query.setParameter("numeV", numeVechi);
            query.setParameter("prenumeV", prenumeVechi);
            query.setParameter("varstaV", varstaVechi);

            int updatedRows = query.executeUpdate();
            em.getTransaction().commit();

            if (updatedRows == 0) {
                response.getWriter().println("Nu s-a gasit niciun student cu datele introduse.");
            } else {
                response.sendRedirect("./fetch-student-list");
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}