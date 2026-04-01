package org.example.project_cuoiky_congnghephanmem_oose.service.user;

import org.springframework.web.multipart.MultipartFile;

public interface IAvatarService {
    String uploadAvatar(MultipartFile file, String username);
}