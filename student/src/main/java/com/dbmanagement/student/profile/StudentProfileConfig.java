//package com.dbmanagement.student.profile;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class StudentProfileConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(
//            StudentProfileRepository profileRepository
//    ){
//        return args -> {
//            StudentProfile studentProfileSample_1 = new StudentProfile(
//                    "A. M. Almarufuzzaman",
//                    LocalDate.of(1995, Month.DECEMBER, 31),
//                    "Male",
//                    "Learning Spring-boot framework."
//            );
//
//            StudentProfile studentProfileSample_2 = new StudentProfile(
//                    "Muntasir Talukdar",
//                    LocalDate.of(1996, Month.MARCH, 16),
//                    "Male",
//                    "Managing DB."
//            );
//
//            profileRepository.saveAll(
//                List.of(studentProfileSample_1, studentProfileSample_2)
//            );
//        };
//    }
//}
