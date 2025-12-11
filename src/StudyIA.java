import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudyIA {

    private static class Question {
        String text;
        String answer;
        String[] hints;

        Question(String text, String answer, String[] hints) {
            this.text = text;
            this.answer = answer;
            this.hints = hints;
        }
    }

    private static final List<Question> QUIZ_QUESTIONS = new ArrayList<>();
    private static int currentQuestionIndex = -1;
    private static int correctCount = 0;
    private static int currentHintIndex = 0;

    static {
        QUIZ_QUESTIONS.add(new Question(
                "What is 2 + 2?",
                "4",
                new String[]{"Add 2 and 2.", "2 + 2 = 4."}
        ));
        QUIZ_QUESTIONS.add(new Question(
                "Solve: 2x = 10. What is x?",
                "5",
                new String[]{"Divide both sides by 2.", "10 / 2 = 5."}
        ));
        QUIZ_QUESTIONS.add(new Question(
                "What is the area of a rectangle with length 3 and width 4?",
                "12",
                new String[]{"Use A = length × width.", "3 × 4 = 12."}
        ));
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void startQuiz(JComboBox<String> unitCombo,
                                  JComboBox<String> difficultyCombo,
                                  JLabel questionLabel,
                                  JLabel hintLabel,
                                  JLabel progressLabel,
                                  JTextField answerField) {

        if (QUIZ_QUESTIONS.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No questions available yet.");
            return;
        }

        currentQuestionIndex = 0;
        correctCount = 0;
        currentHintIndex = 0;

        showQuestion(questionLabel, hintLabel, progressLabel, answerField);
    }

    private static void showQuestion(JLabel questionLabel,
                                     JLabel hintLabel,
                                     JLabel progressLabel,
                                     JTextField answerField) {

        if (currentQuestionIndex < 0 || currentQuestionIndex >= QUIZ_QUESTIONS.size()) {
            questionLabel.setText("No more questions. Click 'Finish Quiz'.");
            progressLabel.setText("Progress: " + QUIZ_QUESTIONS.size() + " / " + QUIZ_QUESTIONS.size());
            return;
        }

        Question q = QUIZ_QUESTIONS.get(currentQuestionIndex);
        questionLabel.setText(q.text);
        hintLabel.setText("");
        currentHintIndex = 0;
        answerField.setText("");

        progressLabel.setText("Progress: " + (currentQuestionIndex + 1) + " / " + QUIZ_QUESTIONS.size());
    }

    private static void checkAnswer(JTextField answerField,
                                    JLabel hintLabel) {

        if (currentQuestionIndex < 0 || currentQuestionIndex >= QUIZ_QUESTIONS.size()) {
            JOptionPane.showMessageDialog(null, "Start the quiz first.");
            return;
        }

        String userAnswer = answerField.getText().trim();
        if (userAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please type an answer.");
            return;
        }

        Question q = QUIZ_QUESTIONS.get(currentQuestionIndex);
        String correct = q.answer.trim();

        if (userAnswer.equalsIgnoreCase(correct)) {
            correctCount++;
            JOptionPane.showMessageDialog(null, "Correct!");
        } else {
            JOptionPane.showMessageDialog(null, "Not quite. The correct answer is: " + correct);
        }
    }

    private static void showNextHint(JLabel hintLabel) {
        if (currentQuestionIndex < 0 || currentQuestionIndex >= QUIZ_QUESTIONS.size()) {
            JOptionPane.showMessageDialog(null, "Start the quiz first.");
            return;
        }

        Question q = QUIZ_QUESTIONS.get(currentQuestionIndex);

        if (q.hints == null || q.hints.length == 0) {
            hintLabel.setText("No hints available for this question.");
            return;
        }

        if (currentHintIndex < q.hints.length) {
            hintLabel.setText(q.hints[currentHintIndex]);
            currentHintIndex++;
        } else {
            hintLabel.setText("No more hints for this question.");
        }
    }

    private static void nextQuestion(JLabel questionLabel,
                                     JLabel hintLabel,
                                     JLabel progressLabel,
                                     JTextField answerField) {

        if (currentQuestionIndex < 0) {
            JOptionPane.showMessageDialog(null, "Start the quiz first.");
            return;
        }

        if (currentQuestionIndex < QUIZ_QUESTIONS.size() - 1) {
            currentQuestionIndex++;
            showQuestion(questionLabel, hintLabel, progressLabel, answerField);
        } else {
            JOptionPane.showMessageDialog(null, "You are at the last question. Press 'Finish Quiz'.");
        }
    }

    private static void finishQuiz() {
        if (currentQuestionIndex < 0) {
            JOptionPane.showMessageDialog(null, "Start the quiz first.");
            return;
        }

        int total = QUIZ_QUESTIONS.size();
        double percent = (correctCount * 100.0) / total;

        JOptionPane.showMessageDialog(
                null,
                "Quiz finished!\n" +
                        "Score: " + correctCount + " out of " + total + "\n" +
                        "Percent: " + String.format("%.1f", percent) + "%"
        );

        currentQuestionIndex = -1;
        correctCount = 0;
        currentHintIndex = 0;
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Math Study Helper");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Math Study Helper");
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(titleLabel, gbc);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Home", createHomePanel());
        tabbedPane.addTab("Quiz", createQuizPanel());
        tabbedPane.addTab("Formulas", createFormulasPanel());
        tabbedPane.addTab("Results", createResultsPanel());

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(tabbedPane, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createHomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JLabel welcomeLabel = new JLabel("Welcome!");
        Font welcomeFont = new Font("Arial", Font.BOLD, 20);
        welcomeLabel.setFont(welcomeFont);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(welcomeLabel, gbc);

        JTextArea infoArea = new JTextArea(
                "This system helps you:\n" +
                        "- Study formulas and math concepts\n" +
                        "- Practice questions with a quiz\n" +
                        "- View your past results\n\n" +
                        "Use the tabs at the top to navigate:\n" +
                        "Home, Quiz, Formulas, Results."
        );
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(infoArea), gbc);

        return panel;
    }

    private static JPanel createQuizPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel unitLabel = new JLabel("Unit:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(unitLabel, gbc);

        JComboBox<String> unitCombo = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(unitCombo, gbc);

        JLabel difficultyLabel = new JLabel("Difficulty:");
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(difficultyLabel, gbc);

        JComboBox<String> difficultyCombo = new JComboBox<>();
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(difficultyCombo, gbc);

        JButton startQuizButton = new JButton("Start Quiz");
        gbc.gridx = 4;
        gbc.gridy = 0;
        panel.add(startQuizButton, gbc);

        JLabel questionTitleLabel = new JLabel("Question:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(questionTitleLabel, gbc);

        JLabel questionLabel = new JLabel("");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        panel.add(questionLabel, gbc);

        JLabel answerLabel = new JLabel("Your answer:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(answerLabel, gbc);

        JTextField answerField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(answerField, gbc);

        JButton checkAnswerButton = new JButton("Check Answer");
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(checkAnswerButton, gbc);

        JLabel hintLabelTitle = new JLabel("Hint:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(hintLabelTitle, gbc);

        JLabel hintLabel = new JLabel("");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(hintLabel, gbc);

        JButton showHintButton = new JButton("Show next hint");
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(showHintButton, gbc);

        JLabel progressLabel = new JLabel("Progress: 0 / 0");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(progressLabel, gbc);

        JButton nextQuestionButton = new JButton("Next Question");
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(nextQuestionButton, gbc);

        JButton finishQuizButton = new JButton("Finish Quiz");
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(finishQuizButton, gbc);

        unitCombo.addItem("Algebra");
        unitCombo.addItem("Geometry");
        unitCombo.addItem("Fractions");

        difficultyCombo.addItem("Easy");
        difficultyCombo.addItem("Medium");
        difficultyCombo.addItem("Hard");

        startQuizButton.addActionListener(e ->
                startQuiz(unitCombo, difficultyCombo, questionLabel, hintLabel, progressLabel, answerField)
        );

        checkAnswerButton.addActionListener(e ->
                checkAnswer(answerField, hintLabel)
        );

        showHintButton.addActionListener(e ->
                showNextHint(hintLabel)
        );

        nextQuestionButton.addActionListener(e ->
                nextQuestion(questionLabel, hintLabel, progressLabel, answerField)
        );

        finishQuizButton.addActionListener(e ->
                finishQuiz()
        );

        return panel;
    }

    private static JPanel createFormulasPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel unitLabel = new JLabel("Unit:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(unitLabel, gbc);

        JComboBox<String> unitCombo = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(unitCombo, gbc);

        JButton loadFormulasButton = new JButton("Show Formulas");
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(loadFormulasButton, gbc);

        JList<String> formulaList = new JList<>();
        JScrollPane listScroll = new JScrollPane(formulaList);
        listScroll.setPreferredSize(new Dimension(200, 300));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(listScroll, gbc);

        JTextArea formulaDetails = new JTextArea();
        formulaDetails.setEditable(false);
        formulaDetails.setLineWrap(true);
        formulaDetails.setWrapStyleWord(true);
        JScrollPane detailsScroll = new JScrollPane(formulaDetails);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(detailsScroll, gbc);

        return panel;
    }

    private static JPanel createResultsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel titleLabel = new JLabel("Past Quiz Results:");
        Font titleFont = new Font("Arial", Font.BOLD, 16);
        titleLabel.setFont(titleFont);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        panel.add(titleLabel, gbc);

        String[] columnNames = {"Date", "Unit", "Score", "Percent"};
        Object[][] data = {};

        JTable resultsTable = new JTable(data, columnNames);
        JScrollPane tableScroll = new JScrollPane(resultsTable);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(tableScroll, gbc);

        return panel;
    }
}


