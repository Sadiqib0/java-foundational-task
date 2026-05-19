import java.util.Scanner;

public class StudentGrade {
    private final int[][] scores;
    private final int numberOfStudents;
    private final int numberOfSubjects;

    public StudentGrade(int students, int subjects) {
        this.numberOfStudents = students;
        this.numberOfSubjects = subjects;
        this.scores = new int[students][subjects];
    }

    public void addScore(int studentIndex, int subjectIndex, int score) {
        if (score < 0 || score > 100) throw new IllegalArgumentException("Score must be between 0 and 100");
        scores[studentIndex][subjectIndex] = score;
    }

    public int getStudentTotal(int studentIndex) {
        int total = 0;
        for (int score : scores[studentIndex]) total += score;
        return total;
    }

    public double getStudentAverage(int studentIndex) {
        return getStudentTotal(studentIndex) / (double) numberOfSubjects;
    }


    public int getSubjectPasses(int subjectIndex) {
        int passes = 0;
        for (int i = 0; i < numberOfStudents; i++) if (scores[i][subjectIndex] >= 50) passes++;
        return passes;
    }
    public int getSubjectFails(int subjectIndex) {
        int fails = 0;
        for (int i = 0; i < numberOfStudents; i++) if (scores[i][subjectIndex] < 50) fails++;
        return fails;
    }


    public int getHighestScoringStudentForSubject(int subjectIndex) {
        int highestIndex = 0;
        for (int i = 1; i < numberOfStudents; i++)
            if (scores[i][subjectIndex] > scores[highestIndex][subjectIndex]) highestIndex = i;
        return highestIndex;
    }

    public int getLowestScoringStudentForSubject(int subjectIndex) {
        int lowestIndex = 0;
        for (int i = 1; i < numberOfStudents; i++)
            if (scores[i][subjectIndex] < scores[lowestIndex][subjectIndex]) lowestIndex = i;
        return lowestIndex;
    }

    public int getClassTotalScore() {
        int classTotal = 0;
        for (int i = 0; i < numberOfStudents; i++) classTotal += getStudentTotal(i);
        return classTotal;
    }

    public double getClassAverageScore() {
        return (double) getClassTotalScore() / numberOfStudents;
    }

    public int getBestGraduatingStudent() {
        int bestIndex = 0;
        for (int i = 1; i < numberOfStudents; i++) if (getStudentTotal(i) > getStudentTotal(bestIndex)) bestIndex = i;
        return bestIndex;
    }

    public int getWorstGraduatingStudent() {
        int worstIndex = 0;
        for (int i = 1; i < numberOfStudents; i++) if (getStudentTotal(i) < getStudentTotal(worstIndex)) worstIndex = i;
        return worstIndex;
    }

    public int getHardestSubject() {
        int hardestIndex = 0;
        for (int j = 1; j < numberOfSubjects; j++) if (getSubjectFails(j) > getSubjectFails(hardestIndex)) hardestIndex = j;
        return hardestIndex;
    }
    public int getEasiestSubject() {
        int easiestIndex = 0;
        for (int j = 1; j < numberOfSubjects; j++) if (getSubjectPasses(j) > getSubjectPasses(easiestIndex)) easiestIndex = j;
        return easiestIndex;
    }
    public int getStudentPosition(int studentIndex) {
        int currentTotal = getStudentTotal(studentIndex);
        int position = 1;
        for (int i = 0; i < numberOfStudents; i++) if (getStudentTotal(i) > currentTotal) position++;
        return position;
    }

    static void main(String[] args) {
        Scanner inputReader  = new Scanner(System.in);
        System.out.println("How many students do you have?"); 
        int numberOfStudents = inputReader.nextInt();
        System.out.println("How many subjects do they offer?"); 
        int numberOfSubjects = inputReader.nextInt();
        StudentGrade gradeSystem = new StudentGrade(numberOfStudents, numberOfSubjects);

        for (int studentIndex = 0; studentIndex < numberOfStudents; studentIndex++) {
            System.out.println("Entering scores for student " + (studentIndex + 1));
            for (int subjectIndex = 0; subjectIndex < numberOfSubjects; subjectIndex++) {
                while (true) {
                    try {
                        System.out.println("Enter score for subject " + (subjectIndex + 1));
                        gradeSystem.addScore(studentIndex, subjectIndex, inputReader.nextInt());
                        System.out.println("Saving >>>>>>>>>>>>>>>>>>>>>>>\nSaved successfully\n"); 
                        break;
                    } catch (Exception e) { 
                        System.out.println("Invalid score! " + e.getMessage() + ". Please try again."); 
                    }
                }
            }
        }

        printLine();
        System.out.printf("%-10s", "STUDENT");
        for (int i = 1; i <= numberOfSubjects; i++) System.out.print("\tSUB" + i);
        System.out.println("\tTOT \tAVG \tPOS");
        printLine();

        for (int studentIndex = 0; studentIndex < numberOfStudents; studentIndex++) {
            System.out.printf("%-10s", "Student " + (studentIndex + 1));
            for (int subjectIndex = 0; subjectIndex < numberOfSubjects; subjectIndex++) 
                System.out.print("\t" + gradeSystem.scores[studentIndex][subjectIndex]);
            System.out.printf("\t%d \t%.2f \t%d%n", 
                gradeSystem.getStudentTotal(studentIndex), 
                gradeSystem.getStudentAverage(studentIndex), 
                gradeSystem.getStudentPosition(studentIndex));
        }

        printLine(); 
        System.out.println("\nSUBJECT SUMMARY");
        for (int subjectIndex = 0; subjectIndex < numberOfSubjects; subjectIndex++) {
            System.out.println("Subject " + (subjectIndex + 1));
            System.out.println("Highest scoring student is: Student " + (gradeSystem.getHighestScoringStudentForSubject(subjectIndex) + 1));
            System.out.println("Lowest scoring student is: Student " + (gradeSystem.getLowestScoringStudentForSubject(subjectIndex) + 1));
            System.out.println("Number of passes: " + gradeSystem.getSubjectPasses(subjectIndex) + "\nNumber of fails: " + gradeSystem.getSubjectFails(subjectIndex));
        }

        System.out.println("\nCLASS SUMMARY");
        int bestStudentIndex = gradeSystem.getBestGraduatingStudent();
        int worstStudentIndex = gradeSystem.getWorstGraduatingStudent();
        System.out.println("Best Graduating Student is: Student " + (bestStudentIndex + 1) + " scoring " + gradeSystem.getStudentTotal(bestStudentIndex));
        System.out.println("Worst Graduating Student is: Student " + (worstStudentIndex + 1) + " scoring " + gradeSystem.getStudentTotal(worstStudentIndex));
        printLine();
        System.out.println("Class total score is: " + gradeSystem.getClassTotalScore());
        System.out.printf("Class average score is: %.2f%n", gradeSystem.getClassAverageScore());
        printLine();
    }
    private static void printLine() { System.out.println(" "); }
}