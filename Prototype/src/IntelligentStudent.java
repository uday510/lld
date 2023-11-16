public class IntelligentStudent extends Student {
    int iq;

    IntelligentStudent() {};
    IntelligentStudent(IntelligentStudent intelligentStudent) {
        super(intelligentStudent);
        this.iq = intelligentStudent.iq;
    }
    @Override
    public IntelligentStudent clone() {
        IntelligentStudent intelligentStudent = new IntelligentStudent();
        return null;
    }
}
