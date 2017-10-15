package cat.tecnocampus.useCases;

import cat.tecnocampus.domain.Classroom;
import cat.tecnocampus.persintence.ClassroomDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserUseCases {

    private ClassroomDAO classroomDAO;

    public UserUseCases(ClassroomDAO classroomDAO) {
        this.classroomDAO = classroomDAO;
    }

    public Classroom createClassroom(String name, int capacity, String orientation, boolean plug){
        Classroom classroom = new Classroom.ClassroomBuilder()
                .name(name)
                .capacity(capacity)
                .orientation(orientation)
                .plugs(plug)
                .build();
        insert(classroom);
        return classroom;
    }

    public List<Classroom> findAll() {
        return classroomDAO.findAll();
    }

    public List<Classroom> findCapacityLargerThan(int capacity) {
        return classroomDAO.findCapacityLargerThan(capacity);
    }

    public List<Classroom> findCapacityLowerThan(int capacity) {
        return classroomDAO.findCapacityLowerThan(capacity);
    }

    public List<Classroom> findWithPlugs(){
        return classroomDAO.findWithPlugs();
    }

    public List<Classroom> findWithNoPlugs(){
        return classroomDAO.findWithNoPlugs();
    }

    @Transactional
    public int insert(Classroom classroom) {
        return classroomDAO.insert(classroom);
    }

    public int[] insertBatch(List<Classroom> classrooms) {
        return classroomDAO.insertBatch(classrooms);
    }
}
