import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;

import db.SampleData;
import db.ModelSetup;
import db.QuestionDatabase;

public class StudyIA {

    private static DefaultTableModel resultsModel;

    private static final List<QuestionDatabase.StudyQuestion> QUIZ_QUESTIONS = new ArrayList<>();

    private static int currentQuestionIndex = -1;
    private static int correctCount = 0;
    private static int currentHintIndex = 0;
    private static boolean answeredThisQuestion = false;

    private static String selectedTopic;
    private static String selectedDifficulty;

    private static int difficultyToNumber(String difficulty) {
        if (difficulty.equals("Easy")) {
            return 1;
        } else if (difficulty.equals("Medium")) {
            return 2;
        } else {
            return 3;
        }
    }
    private static String[] getFormulasForTopic(String topic)  {

        if (topic.equals("Algebra")) {
            return new String[]{
                    "x + a = b",
                    "ax = b",
                    "2(x + a)",
                    "x / a = b"

            };
        } else if (topic.equals("Geometry")) {
            return new String[]{
                    "Area of triangle = 1/2 x base x height",
                    "Area of rectangle = length x width",
                    "Area of circle = pi x r x r",
                    "Circumefernce = 2 x pi x r",
                    "Angles in triangle = 180",
                    "Area of rectangle = length x width"
            };
        } else if (topic.equals("Fractions")) {
                return new String[]{
                        "a/b + c/d = (ad + bc) / bd",
                        "a/b - c/d = (ad - bc) / bd",
                        "a/b x c/d = ac / bd",
                        "a/b ÷ c/d = a/b x d/c"
                };
        }

        return new String[]{};
    }

    private static String getFormulaExplanation(String formula) {
        if (formula.equals("x + a = b")) {
            return "To solve this, subtract a from both sides to find x.";
        } else if (formula.equals("ax = b")) {
            return "To solve this, divide both sides by a.";
        } else if (formula.equals("Area of rectangle = length × width")) {
            return "Multiply the length by the width to find the area.";
        } else if (formula.equals("Area of triangle = 1/2 × base × height")) {
            return "Multiply the base by the height, then divide by 2.";
        } else if (formula.equals("Area of circle = pi × r × r")) {
            return "Square the radius, then multiply by pi.";
        } else if (formula.equals("Circumference = 2 × pi × r")) {
            return "Multiply 2, pi, and the radius.";
        } else if (formula.equals("Angles in triangle = 180")) {
            return "The angles in a triangle always add up to 180 degrees.";
        } else if (formula.equals("a/b + c/d = (ad + bc) / bd")) {
            return "Find a common denominator, then add the numerators.";
        } else if (formula.equals("a/b - c/d = (ad - bc) / bd")) {
            return "Find a common denominator, then subtract the numerators.";
        } else if (formula.equals("a/b × c/d = ac / bd")) {
            return "Multiply the numerators and multiply the denominators.";
        } else if (formula.equals("a/b ÷ c/d = a/b × d/c")) {
            return "To divide fractions, multiply by the reciprocal.";
        } else {
            return "No explanation available.";
        }
    }

    public static void main(String[] args) {


        ModelSetup.init();
        //SampleData.insertSampleQuestions();

        SwingUtilities.invokeLater(StudyIA::createAndShowGUI);
    }

    private static void startQuiz(JComboBox<String> unitCombo,
                                  JComboBox<String> difficultyCombo,
                                  JLabel questionLabel,
                                  JLabel hintLabel,
                                  JLabel progressLabel,
                                  JTextField answerField) {

        String topic = (String) unitCombo.getSelectedItem();
        String difficultyText = (String) difficultyCombo.getSelectedItem();
        int difficulty = difficultyToNumber(difficultyText);
        selectedTopic = topic;
        selectedDifficulty = difficultyText;

        QUIZ_QUESTIONS.clear();
        QUIZ_QUESTIONS.addAll(QuestionDatabase.getRandomQuestions(topic, difficulty, 5));
        Collections.shuffle(QUIZ_QUESTIONS);

        QUIZ_QUESTIONS.clear();
        QUIZ_QUESTIONS.addAll(QuestionDatabase.getRandomQuestions(topic, difficulty, 5));

        if (QUIZ_QUESTIONS.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No questions available for this topic.");
            return;
        }

        currentQuestionIndex = 0;
        correctCount = 0;
        currentHintIndex = 0;
        answeredThisQuestion = false;

        showQuestion(questionLabel, hintLabel, progressLabel, answerField);
    }

    private static void showQuestion(JLabel questionLabel,
                                     JLabel hintLabel,
                                     JLabel progressLabel,
                                     JTextField answerField) {

        if (currentQuestionIndex < 0 || currentQuestionIndex >= QUIZ_QUESTIONS.size()) {
            questionLabel.setText("No more questions. Click Finish Quiz.");
            hintLabel.setText("");
            progressLabel.setText("Progress: " + QUIZ_QUESTIONS.size() + " / " + QUIZ_QUESTIONS.size());
            return;
        }

        QuestionDatabase.StudyQuestion q = QUIZ_QUESTIONS.get(currentQuestionIndex);

        questionLabel.setText(q.text);
        hintLabel.setText("");
        answerField.setText("");
        currentHintIndex = 0;
        answeredThisQuestion = false;

        progressLabel.setText("Progress: " + (currentQuestionIndex + 1) + " / " + QUIZ_QUESTIONS.size());
    }

    private static void checkAnswer(JTextField answerField,
                                    JLabel hintLabel) {

        if (currentQuestionIndex < 0 || currentQuestionIndex >= QUIZ_QUESTIONS.size()) {
            JOptionPane.showMessageDialog(null, "Start the quiz first.");
            return;
        }

        if (answeredThisQuestion) {
            JOptionPane.showMessageDialog(null, "You already answered this question.");
            return;
        }

        String userAnswer = answerField.getText().trim();

        if (userAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please type an answer.");
            return;
        }

        QuestionDatabase.StudyQuestion q = QUIZ_QUESTIONS.get(currentQuestionIndex);
        String correctAnswer = q.answer.trim();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            correctCount++;
            JOptionPane.showMessageDialog(null, "Correct!");
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect. Try using a hint.");
        }

        answeredThisQuestion = true;
    }

    private static void showNextHint(JLabel hintLabel) {
        if (currentQuestionIndex < 0 || currentQuestionIndex >= QUIZ_QUESTIONS.size()) {
            JOptionPane.showMessageDialog(null, "Start the quiz first.");
            return;
        }

        QuestionDatabase.StudyQuestion q = QUIZ_QUESTIONS.get(currentQuestionIndex);

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

        if (!answeredThisQuestion) {
            JOptionPane.showMessageDialog(null, "Please answer the question before moving on.");
            return;
        }

        if (currentQuestionIndex < QUIZ_QUESTIONS.size() - 1) {
            currentQuestionIndex++;
            showQuestion(questionLabel, hintLabel, progressLabel, answerField);
        } else {
            JOptionPane.showMessageDialog(null, "You are at the last question. Press Finish Quiz.");
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

        String unitForRow = selectedTopic + "_" + selectedDifficulty;
        String dateForRow = "Today";
        String scoreText = correctCount + " / " + total;
        String percentText = String.format("%.1f%%", percent);

        if (resultsModel != null) {
            resultsModel.addRow(new Object[]{dateForRow, unitForRow, scoreText, percentText});
        }

        currentQuestionIndex = -1;
        correctCount = 0;
        currentHintIndex = 0;
        answeredThisQuestion = false;
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
        panel.add(unitLabel, gbc);

        JComboBox<String> unitCombo = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(unitCombo, gbc);

        unitCombo.addItem("Algebra");
        unitCombo.addItem("Geometry");
        unitCombo.addItem("Fractions");

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

        loadFormulasButton.addActionListener(e -> {
            String topic = (String) unitCombo.getSelectedItem();
            String[] formulas = getFormulasForTopic(topic);
            formulaList.setListData(formulas);
            formulaDetails.setText("");

            formulaList.addListSelectionListener(event ->{
                if (!event.getValueIsAdjusting()) {
                    String selectedFormula = formulaList.getSelectedValue();
                    if(selectedFormula != null) {
                        formulaDetails.setText(getFormulaExplanation(selectedFormula));
                    }
                }
            });
        });

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
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        panel.add(titleLabel, gbc);

        String[] columnNames = {"Date", "Unit", "Score", "Percent"};
        if (resultsModel == null) {
            resultsModel = new DefaultTableModel(columnNames, 0);
        }

        JTable resultsTable = new JTable(resultsModel);
        JScrollPane tableScroll = new JScrollPane(resultsTable);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(tableScroll, gbc);

        return panel;
    }
}
