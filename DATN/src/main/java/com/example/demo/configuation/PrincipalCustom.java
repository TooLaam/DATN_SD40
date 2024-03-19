package com.example.demo.configuation;

import com.example.demo.configuation.userdetail.UserInfoUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class PrincipalCustom {
    public UserInfoUserDetailsService getCurrentUserNameAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (authentication != null && authentication.isAuthenticated()) {
            // Lấy thông tin chi tiết của người dùng
            Object principal = authentication.getPrincipal();

            // Kiểm tra xem principal có phải là một đối tượng UserDetails không
            if (principal instanceof UserInfoUserDetailsService) {
                UserInfoUserDetailsService userDetails = (UserInfoUserDetailsService) principal;

                // Kiểm tra xem người dùng có vai trò "ADMIN" không
                if (userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                    // Nếu không có vai trò "ADMIN", trả về tên người dùng
                    System.out.println("*******: "+ userDetails.getHoVaTen());
                    return userDetails;
                }
            }
        }

        return null; // Hoặc có thể trả về giá trị mặc định hoặc ném một ngoại lệ tùy vào logic của bạn
    }
    public String getCurrentUserNameCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (authentication != null && authentication.isAuthenticated()) {
            // Lấy thông tin chi tiết của người dùng
            Object principal = authentication.getPrincipal();

            // Kiểm tra xem principal có phải là một đối tượng UserDetails không
            if (principal instanceof UserInfoUserDetailsService) {
                UserInfoUserDetailsService userDetails = (UserInfoUserDetailsService) principal;

                // Kiểm tra xem người dùng có vai trò "ADMIN" không
                if (userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                    // Nếu không có vai trò "ADMIN", trả về tên người dùng
                    System.out.println("*******: customer "+ userDetails.getUsername());
                    return userDetails.getUsername();
                }
            }
        }

        return null; // Hoặc có thể trả về giá trị mặc định hoặc ném một ngoại lệ tùy vào logic của bạn
    }
}
