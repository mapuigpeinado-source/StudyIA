package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SampleData {

    public static void insertSampleQuestions() {

        String sql = "Insert INTO questions(topic, difficulty, prompt, correct_answer, hint_steps) VALUES (?,?,?,?,?)";
         try (Connection c = Database.getConnection();
              PreparedStatement ps = c.prepareStatement(sql)) {

             ps.setString(1, "Algebra");
             ps.setInt(2, 1);
             ps.setString(3, "Solve: 2x + 3 = 11");
             ps.setString(4, "4");
             ps.setString(5,"Subtract 3 from both sides || Divide by 2");
             ps.executeUpdate();

         } catch (Exception e) {
             e.printStackTrace();
         }
    }
}
