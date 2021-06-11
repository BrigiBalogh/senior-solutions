package studentfunctional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class School {

    private List<Student> students;

    public School(List<Student> students) {
        this.students = students;
    }

    private static boolean equalSurnameAndFirstNameStartingCharacter(Student s) {
        return s.getName().charAt(0) == s.getName().charAt(s.getName().lastIndexOf(' ') + 1);
    }

    private static boolean equalSurnameAndFirstNameStartingCharacter2(Student s) {
        String[] parts = s.getName().split(" ");
        return parts[0].charAt(0) == parts[1].charAt(0);
    }

    public void foundStudentNameByStartsWithG() { //4.

                students.stream()
                        .filter(st -> st.getName().startsWith("G"))
                        .forEach(System.out::println);
    }


    public List<String> collectStudentName() { //6
        return students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    public List<String> collectStudentNameReverse() { //7
        return students.stream()
               .map(n -> n.getName().split(" "))
               .map(arr ->String.format("%s %s", arr[1], arr[0]))
               .collect(Collectors.toList());
    }

    public void printedStudent() { //8
        students.stream()
                .map(s -> String.format("%s (%d)",s.getName(), s.getBirthYear()))
                .forEach(System.out::println);
    }

    public List<Student> foundStudentsBefore2000() { //9
        return students.stream()
                .filter(s -> s.getBirthYear() < 2000)
                .filter(st -> st.getAverage() < 3)
                .collect(Collectors.toList());
    }

    public void foundEqualSurnameAndFirstNameStartingCharacterAnd2000AndAverageLowerThan3() { //10
        students.stream()
                .filter(School::equalSurnameAndFirstNameStartingCharacter2)
                .filter(s -> s.getBirthYear() ==2000)
                .filter(st -> st.getAverage() < 3)
                .forEach(System.out::println);
    }

    public List<Double> foundAllAverage() { //11
        return students.stream()
                .map(s -> s.getAverage())
                .collect(Collectors.toList());
    }

    public void countStudentsNumber(){ //12
       students.stream()
                .collect(Collectors.counting());
        System.out.println(students.stream().count());
    }

    public  double foundHighestAverage() { //13
        return students.stream()
                .mapToDouble(s -> s.getAverage())
                .max().orElse(0);

    }

    public double foundLowestAverage() { //14
        return students.stream()
                .mapToDouble(s -> s.getAverage())
                .min().orElse(0);
    }

    public double foundAverage() {
        return students.stream()
                .mapToDouble(s->s.getAverage())
                .summaryStatistics()
                .getMax();

    }

    public void sortByName() {  //15
        students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(System.out::println);
    }

    public void sortByAverageReversed() {  //16
        students.stream()
                .sorted(Comparator.comparing(Student::getAverage, Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    public void sortByBirthYear() {  //17
        students.stream()
                .sorted(Comparator.comparing(Student::getBirthYear, Comparator.naturalOrder())
        .thenComparing(Student::getName, Comparator.naturalOrder()))
                .forEach(System.out::println);
    }


    public void line(int n) {
        //int n = 4;
        // 0,1,2,3
        String l = IntStream.range(0, n).mapToObj(i -> "*").collect(Collectors.joining());
        System.out.println(l);
        String l2 = "";
        for (int i = 0; i < n; i++) {
            l2 += "*";
        }
        System.out.println(l2);
    }

    public void leftTriangle() {
        int n = 4;
        /*for (int i = 0; i < n; i++) {
            //line(i+1);
            System.out.println(IntStream.range(0, i+1).mapToObj(num -> "*").collect(Collectors.joining()));
        }*/
        String triangle = IntStream.range(0, n)
                .mapToObj(l -> IntStream.range(0, l+1).mapToObj(num -> "*").collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));

        System.out.println(triangle);
    }

    public void rightTriangle() {
        int n = 4;
        for (int lineNumber = 0; lineNumber < n; lineNumber++) {
            /*for (int starCount = 0; starCount < lineNumber+1; starCount++) {
                System.out.print("*");
            gv }*/
            line(n - (lineNumber+1), " ");
            line(lineNumber+1, "*");
            System.out.println();
        }
    }

    public  void rightTriangleWithStream() {
        int n = 4;

        String triangle = IntStream.range(0,n)
                .mapToObj(l -> IntStream.range(0,n-(l + 1))
                        .mapToObj(s -> " ")
                        .collect(Collectors.joining())
                        + IntStream.range(0,l + 1)
                                        .mapToObj(s -> "*").collect(Collectors.joining())
                ).collect(Collectors.joining("\n"));
    }

    private void line(int count, String s) {
        for (int starCount = 0; starCount < count; starCount++) {
            System.out.print(s);
        }
    }



    public boolean foundPrimeTest(int number) {
        int countRealDivisor = 0;
        for (int divisor = 2; divisor < number; divisor++) {
            if (number % divisor == 0) {
                countRealDivisor++;
            }
        }
        return countRealDivisor == 0;
    }

    public boolean foundPrimeTestWithStream(int number) {
        /*return IntStream.range(2,number)
                .filter(n -> number % n == 0)
                .count() == 0;*/
        return IntStream.range(2,number)
                .noneMatch(n -> number % n == 0);

    }
}
