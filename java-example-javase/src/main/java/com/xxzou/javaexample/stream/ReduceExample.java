package com.xxzou.javaexample.stream;

import com.xxzou.javaexample.stream.entity.StudentScore;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author zxx
 * @date 2022/10/10 18:16
 */
public class ReduceExample {

    public static void main(String[] args) {

        Stream<StudentScore> studentScoreStream = Stream.of(
                new StudentScore("stu1", 1, 63),
                new StudentScore("stu2", 1, 90),
                new StudentScore("stu3", 1, 50),
                new StudentScore("stu1", 2, 70),
                new StudentScore("stu2", 2, 83),
                new StudentScore("stu3", 2, 68),
                new StudentScore("stu1", 3, 70),
                new StudentScore("stu2", 3, 91),
                new StudentScore("stu3", 3, 87)
        );

        // calculate total score of stu1
        Optional<StudentScore> stuScoreOptional = studentScoreStream.filter(stu -> stu.getName().equals("stu1")).reduce((s1, s2) -> new StudentScore("stu1", null, s1.getScore() + s2.getScore()));
        System.out.println(stuScoreOptional.get().getScore());

        // calculate total score of stu2, using a combiner , return a int result
        Integer stu2TotalScore = studentScoreStream.filter(stu -> stu.getName().equals("stu2")).reduce(0, (partialResult, s) -> partialResult + s.getScore(), (a,b)->a+b);
        System.out.println(stu2TotalScore);

    }

}
