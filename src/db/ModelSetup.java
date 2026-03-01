package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelSetup {

    public static void init() {

        String createQuestions = """
            CREATE TABLE IF NOT EXISTS questions (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              topic TEXT NOT NULL,
              difficulty INTEGER NOT NULL,
              prompt TEXT NOT NULL,
              correct_answer TEXT NOT NULL,
              hint_steps TEXT NOT NULL
            );
        """;

        String createResults = """
            CREATE TABLE IF NOT EXISTS quiz_results (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              username TEXT NOT NULL,
              topic TEXT NOT NULL,
              difficulty INTEGER NOT NULL,
              total_questions INTEGER NOT NULL,
              score INTEGER NOT NULL,
              created_at TEXT NOT NULL
            );
        """;

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement()) {

            st.execute(createQuestions);
            st.execute(createResults);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
