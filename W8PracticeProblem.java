
import java.util.*;

interface Question {
    boolean checkAnswer(String answer);
}

class MCQ implements Question {
    int number;
    String correctAnswer;

    MCQ(int number, String correctAnswer) {
        this.number = number;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalse implements Question {
    String correctAnswer;

    TrueFalse(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    String name;
    ArrayList<Question> questions = new ArrayList<>();

    Examination(String name) {
        this.name = name;
    }

    void addQuestion(Question q) {
        questions.add(q);
    }
}

class Attempt {
    Student student;
    Examination exam;
    ArrayList<String> answers = new ArrayList<>();
    boolean submitted = false;

    Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
    }

    void answerQuestion(int questionNumber, String answer) {
        if (submitted) {
            System.out.println("Attempt already submitted. Answers cannot be changed.");
            return;
        }

        while (answers.size() < exam.questions.size()) {
            answers.add("");
        }

        answers.set(questionNumber - 1, answer);
        System.out.println("Question " + questionNumber + " answered with '" + answer + "'.");
    }

    void submit() {
        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }

        submitted = true;
        System.out.println("Examination '" + exam.name + "' submitted successfully.");

        int score = 0;

        for (int i = 0; i < exam.questions.size(); i++) {
            if (exam.questions.get(i).checkAnswer(answers.get(i))) {
                score++;
            }
        }

        System.out.println("Result for '" + exam.name + "' attempt: "
                + score + "/" + exam.questions.size() + " correct");
    }
}

class Student {
    String name;
    ArrayList<Attempt> attempts = new ArrayList<>();

    Student(String name) {
        this.name = name;
    }

    Attempt startExam(Examination exam) {
        for (Attempt a : attempts) {
            if (a.exam == exam && a.submitted) {
                System.out.println("A submitted attempt already exists for this examination.");
                return null;
            }
        }

        Attempt attempt = new Attempt(this, exam);
        attempts.add(attempt);

        System.out.println("Examination '" + exam.name + "' started by " + name + ".");
        return attempt;
    }
}

public class W8PracticeProblem {
    public static void main(String[] args) {
        Student student = new Student("Student");

        Examination exam = new Examination("Math Quiz");
        exam.addQuestion(new MCQ(1, "A"));
        exam.addQuestion(new MCQ(2, "B"));

        Attempt attempt = student.startExam(exam);

        if (attempt != null) {
            attempt.answerQuestion(1, "A");
            attempt.answerQuestion(2, "C");
            attempt.submit();
        }
    }
}


