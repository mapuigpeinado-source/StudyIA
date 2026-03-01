package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuestionDatabase {

    public static ArrayList<StudyQuestion> getRandomQuestions(String topic, int limit) {

        ArrayList<StudyQuestion> questions = new ArrayList<>();

        String sql = """
                SELECT prompt, correct_answer, hint_steps
                FROM questions
                WHERE topic = ?
                ORDER BY RANDOM()
                LIMIT ?
                """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, topic);
            ps.setInt(2, limit);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String prompt = rs.getString("prompt");
                String answer = rs.getString("correct_answer");
                String hintRaw = rs.getString("hint_steps");
                String[] hints = hintRaw.split("\\|\\|");

                questions.add(new StudyQuestion(prompt, answer, hints));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

    public static class StudyQuestion {
        public String text;
        public String answer;
        public String[] hints;

        public StudyQuestion(String text, String answer, String[] hints) {
            this.text = text;
            this.answer = answer;
            this.hints = hints;
        }
    }
}