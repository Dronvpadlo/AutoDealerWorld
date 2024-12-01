package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.RolesPermission;
import com.example.autodealerworld.repository.PermissionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PermissionsDataInitializer implements CommandLineRunner {

    private final PermissionRepository permissionRepository;

    public PermissionsDataInitializer(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    @Override
    public void run(String... args) {
        if (permissionRepository.count() == 0) {
            RolesPermission readPermission = new RolesPermission();
            readPermission.setName("CREATE");
            permissionRepository.save(readPermission);

            RolesPermission writePermission = new RolesPermission();
            writePermission.setName("UPDATE");
            permissionRepository.save(writePermission);

            RolesPermission deletePermission = new RolesPermission();
            deletePermission.setName("DELETE");
            permissionRepository.save(deletePermission);
        }
    }
}