//package org.example.project_cuoiky_congnghephanmem_oose.util;
//
//import org.example.project_cuoiky_congnghephanmem_oose.entity.User;
//import org.example.project_cuoiky_congnghephanmem_oose.repository.IUserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class TestDbRunner implements CommandLineRunner {
//
//    private final IUserRepository userRepository;
//
//    public TestDbRunner(IUserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<User> users = userRepository.findAll();
//
//        if (users.isEmpty()) {
//            System.out.println("=== Bảng User trống hoặc chưa có dữ liệu ===");
//        } else {
//            System.out.println("=== Danh sách User ===");
//            users.forEach(u ->
//                System.out.println("ID: " + u.getUserID()
//                    + " | Username: " + u.getUsername()
//                    + " | Email: " + u.getEmail()
//                    + " | Phone: " + u.getPhone()
//                )
//            );
////        }
//    }
//}