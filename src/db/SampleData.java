package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SampleData {

    public static void insertSampleQuestions() {

        String sql = "INSERT INTO questions(topic, difficulty, prompt, correct_answer, hint_steps) VALUES (?,?,?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            // ALGEBRA EASY
            ps.setString(1, "Algebra");
            ps.setInt(2, 1);
            ps.setString(3, "Solve: 2x + 3 = 11");
            ps.setString(4, "4");
            ps.setString(5, "Subtract 3 from both sides||Divide by 2");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 1);
            ps.setString(3, "Solve: x - 5 = 7");
            ps.setString(4, "12");
            ps.setString(5, "Add 5 to both sides");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 1);
            ps.setString(3, "Solve: x + 9 = 15");
            ps.setString(4, "6");
            ps.setString(5, "Subtract 9 from both sides");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 1);
            ps.setString(3, "Solve: 3x = 18");
            ps.setString(4, "6");
            ps.setString(5, "Divide both sides by 3");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 1);
            ps.setString(3, "Solve: x / 2 = 5");
            ps.setString(4, "10");
            ps.setString(5, "Multiply both sides by 2");
            ps.executeUpdate();

            // ALGEBRA MEDIUM
            ps.setString(1, "Algebra");
            ps.setInt(2, 2);
            ps.setString(3, "Solve: 3x + 5 = 20");
            ps.setString(4, "5");
            ps.setString(5, "Subtract 5 from both sides||Divide by 3");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 2);
            ps.setString(3, "Solve: 4x - 8 = 12");
            ps.setString(4, "5");
            ps.setString(5, "Add 8 to both sides||Divide by 4");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 2);
            ps.setString(3, "Solve: 5x + 10 = 35");
            ps.setString(4, "5");
            ps.setString(5, "Subtract 10 first||Then divide by 5");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 2);
            ps.setString(3, "Solve: 2x - 7 = 9");
            ps.setString(4, "8");
            ps.setString(5, "Add 7 to both sides||Divide by 2");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 2);
            ps.setString(3, "Solve: x / 3 + 2 = 6");
            ps.setString(4, "12");
            ps.setString(5, "Subtract 2 first||Multiply by 3");
            ps.executeUpdate();

            // ALGEBRA HARD
            ps.setString(1, "Algebra");
            ps.setInt(2, 3);
            ps.setString(3, "Solve: 2(x + 4) = 18");
            ps.setString(4, "5");
            ps.setString(5, "Divide by 2||Subtract 4");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 3);
            ps.setString(3, "Solve: 3(x - 2) = 15");
            ps.setString(4, "7");
            ps.setString(5, "Divide by 3||Add 2");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 3);
            ps.setString(3, "Solve: 2x + 5 = x + 12");
            ps.setString(4, "7");
            ps.setString(5, "Subtract x from both sides||Subtract 5");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 3);
            ps.setString(3, "Solve: 4(x + 1) = 20");
            ps.setString(4, "4");
            ps.setString(5, "Divide by 4||Subtract 1");
            ps.executeUpdate();

            ps.setString(1, "Algebra");
            ps.setInt(2, 3);
            ps.setString(3, "Solve: 5(x - 1) = 30");
            ps.setString(4, "7");
            ps.setString(5, "Divide by 5||Add 1");
            ps.executeUpdate();

            // GEOMETRY EASY
            ps.setString(1, "Geometry");
            ps.setInt(2, 1);
            ps.setString(3, "What is the sum of angles in a triangle?");
            ps.setString(4, "180");
            ps.setString(5, "Think about triangle angle rule");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 1);
            ps.setString(3, "How many sides does a square have?");
            ps.setString(4, "4");
            ps.setString(5, "Count the equal sides");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 1);
            ps.setString(3, "How many sides does a triangle have?");
            ps.setString(4, "3");
            ps.setString(5, "Think of the word triangle");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 1);
            ps.setString(3, "How many degrees is a right angle?");
            ps.setString(4, "90");
            ps.setString(5, "It is a quarter turn");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 1);
            ps.setString(3, "How many sides does a rectangle have?");
            ps.setString(4, "4");
            ps.setString(5, "A rectangle is a quadrilateral");
            ps.executeUpdate();

            // GEOMETRY MEDIUM
            ps.setString(1, "Geometry");
            ps.setInt(2, 2);
            ps.setString(3, "What is the area of a triangle with base 10 and height 6?");
            ps.setString(4, "30");
            ps.setString(5, "Use A = 1/2 × base × height");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 2);
            ps.setString(3, "What is the perimeter of a square with side 7?");
            ps.setString(4, "28");
            ps.setString(5, "Add all 4 sides");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 2);
            ps.setString(3, "What is the area of a rectangle with length 8 and width 5?");
            ps.setString(4, "40");
            ps.setString(5, "Use area = length × width");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 2);
            ps.setString(3, "What is the circumference of a circle with diameter 10? Use 3.14 for pi.");
            ps.setString(4, "31.4");
            ps.setString(5, "Use C = pi × diameter");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 2);
            ps.setString(3, "What is the area of a square with side 6?");
            ps.setString(4, "36");
            ps.setString(5, "Multiply side by side");
            ps.executeUpdate();

            // GEOMETRY HARD
            ps.setString(1, "Geometry");
            ps.setInt(2, 3);
            ps.setString(3, "Find the area of a circle with radius 7. Use 3.14 for pi.");
            ps.setString(4, "153.86");
            ps.setString(5, "Use A = pi r squared||Square 7 first");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 3);
            ps.setString(3, "A triangle has angles 35 and 65. What is the third angle?");
            ps.setString(4, "80");
            ps.setString(5, "Angles in a triangle add to 180");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 3);
            ps.setString(3, "What is the area of a parallelogram with base 9 and height 4?");
            ps.setString(4, "36");
            ps.setString(5, "Use area = base × height");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 3);
            ps.setString(3, "What is the circumference of a circle with radius 6? Use 3.14 for pi.");
            ps.setString(4, "37.68");
            ps.setString(5, "Use C = 2 × pi × r");
            ps.executeUpdate();

            ps.setString(1, "Geometry");
            ps.setInt(2, 3);
            ps.setString(3, "A rectangle has length 12 and width 4. What is its area?");
            ps.setString(4, "48");
            ps.setString(5, "Use length × width");
            ps.executeUpdate();

            // FRACTIONS EASY
            ps.setString(1, "Fractions");
            ps.setInt(2, 1);
            ps.setString(3, "What is 1/2 + 1/4?");
            ps.setString(4, "3/4");
            ps.setString(5, "Find a common denominator||Add numerators");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 1);
            ps.setString(3, "What is 1/3 + 1/3?");
            ps.setString(4, "2/3");
            ps.setString(5, "Same denominator, just add numerators");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 1);
            ps.setString(3, "What is 3/4 - 1/4?");
            ps.setString(4, "2/4");
            ps.setString(5, "Same denominator, subtract numerators");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 1);
            ps.setString(3, "What is 2/5 + 1/5?");
            ps.setString(4, "3/5");
            ps.setString(5, "Same denominator");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 1);
            ps.setString(3, "What is 4/6 - 1/6?");
            ps.setString(4, "3/6");
            ps.setString(5, "Subtract numerators only");
            ps.executeUpdate();

            // FRACTIONS MEDIUM
            ps.setString(1, "Fractions");
            ps.setInt(2, 2);
            ps.setString(3, "What is 3/4 + 2/5?");
            ps.setString(4, "23/20");
            ps.setString(5, "Use denominator 20||Convert both fractions");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 2);
            ps.setString(3, "What is 5/6 - 1/3?");
            ps.setString(4, "3/6");
            ps.setString(5, "Convert 1/3 into sixths");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 2);
            ps.setString(3, "What is 2/3 + 1/6?");
            ps.setString(4, "5/6");
            ps.setString(5, "Use denominator 6");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 2);
            ps.setString(3, "What is 7/8 - 1/4?");
            ps.setString(4, "5/8");
            ps.setString(5, "Convert 1/4 into eighths");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 2);
            ps.setString(3, "What is 1/2 + 2/3?");
            ps.setString(4, "7/6");
            ps.setString(5, "Use denominator 6");
            ps.executeUpdate();

            // FRACTIONS HARD
            ps.setString(1, "Fractions");
            ps.setInt(2, 3);
            ps.setString(3, "What is 5/6 divided by 2/3?");
            ps.setString(4, "5/4");
            ps.setString(5, "Multiply by reciprocal||Then simplify");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 3);
            ps.setString(3, "What is 3/5 multiplied by 10/9?");
            ps.setString(4, "2/3");
            ps.setString(5, "Multiply top and bottom||Simplify");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 3);
            ps.setString(3, "What is 4/7 divided by 8/21?");
            ps.setString(4, "3/2");
            ps.setString(5, "Use reciprocal of second fraction");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 3);
            ps.setString(3, "What is 2/3 multiplied by 9/4?");
            ps.setString(4, "3/2");
            ps.setString(5, "Multiply then simplify");
            ps.executeUpdate();

            ps.setString(1, "Fractions");
            ps.setInt(2, 3);
            ps.setString(3, "What is 7/10 divided by 14/15?");
            ps.setString(4, "3/4");
            ps.setString(5, "Multiply by reciprocal||Simplify carefully");
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}