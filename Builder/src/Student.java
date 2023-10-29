public class Student {
    int id;
    String name;
    double psp;
    int age;
    String univName;
    int gradYear;
    public Student student;

    private Student(Builder builder) {
        this.age = builder.getAge();
        this.name = builder.getName();
        this.gradYear = builder.getGradYear();
    }

    public static Builder getBuilder() {
        return new Builder();
    }

   static class Builder {
        /*
        TODO: Builder is a creational design pattern that lets you construct complex objects step by step.
             The pattern allows you to produce different types and representations of an object using the same construction code.
         */
        int id;
        String name;
        double psp;
        int age;
        String univName;
        int gradYear;

        public int getGradYear() {
            return gradYear;
        }

        public Builder setGradYear(int gradYear) {
            this.gradYear = gradYear;
            return this;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public void setPsp(double psp) {
            this.psp = psp;
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

        public Builder getBuilder() {
            return this;
        }

        public void setUnivName(String univName) {
            this.univName = univName;
        }

        public Student build() {
            // validations
            if (age > 25) {
                throw new IllegalArgumentException();
            }
            return new Student(this);
        }
    }
}
