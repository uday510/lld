public class Student {

    int id;
    String name;
    double psp;
    int age;
    String univName;
    int gradYear;

    private Student(Builder builder) {

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    static class Builder {

        int id;
        String name;
        double psp;
        int age;
        String univName;
        int gradYear;

//    Student(int id, String name...) {}


        public int getId() {
            return id;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public double getPsp() {
            return psp;
        }

        public Builder setPsp(double psp) {
            this.psp = psp;
            return this;
        }

        public int getAge() {
            return age;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public String getUnivName() {
            return univName;
        }

        public Builder setUnivName(String univName) {
            this.univName = univName;
            return this;
        }

        public int getGradYear() {
            return gradYear;
        }

        public Builder setGradYear(int gradYear) {
            this.gradYear = gradYear;
            return this;
        }

        public Student build() {

            if (this.getAge() > 25) {
                throw new IllegalArgumentException();
            }

            return new Student(this);
        }
    }

}
