package sistema_universidad.universidad.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sistema_universidad.universidad.entity.Alumno;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class AlumnoDaoImp implements AlumnoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Alumno> getAlumnos() {
        String query = "FROM Alumno"; // Cambiar 'Alumnos' a 'Alumno'
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(Alumno alumno) {
    entityManager.persist(alumno);
    }

    @Override
    public void update(Alumno alumno) {
        entityManager.merge(alumno);
    }

    @Override
    public void delete(Long id) {
        Alumno alumno = entityManager.find(Alumno.class, id);
        entityManager.remove(alumno);
    }
}