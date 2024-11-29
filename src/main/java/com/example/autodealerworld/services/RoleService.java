package com.example.autodealerworld.services;

import com.example.autodealerworld.repository.RoleRepository;
import com.example.autodealerworld.util.RoleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleUtil roleUtil;

}
