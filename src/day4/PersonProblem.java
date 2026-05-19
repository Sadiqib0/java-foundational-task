import java.util.ArrayList;
import java.util.List;
public class PersonProblem {
    public enum ProblemType {
        FINANCIAL, SPIRITUAL, EDUCATION, BUSINESS, TECHNICAL
    }
    public static class Problem {
        private String name;
        private ProblemType type;
        private boolean isSolved;

        public Problem(String name, ProblemType type) {
            this.name = name;
            this.type = type;
            this.isSolved = false; // Default status
        }

        public void solve() {
            this.isSolved = true;
        }

        public boolean isSolved() {
            return isSolved;
        }
        @Override
        public String toString() {
            return String.format("[%s] %s (Solved: %b)", type, name, isSolved);
        }

    }
    public static void main(String[] args) {

        }
}
