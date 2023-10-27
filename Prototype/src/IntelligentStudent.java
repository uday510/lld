import java.util.Objects;

public class IntelligentStudent extends Student {

    int iq;

    @Override
    public IntelligentStudent clone() {
        IntelligentStudent intelligentStudent = new IntelligentStudent();
        intelligentStudent.iq = this.iq;
        intelligentStudent.setAge(this.getAge());

        return intelligentStudent;
    }
}
