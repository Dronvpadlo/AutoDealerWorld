package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.repository.RoleRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoleDeserializer extends JsonDeserializer<Role> {

    private final RoleRepository roleRepository;

    public RoleDeserializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String roleName = parser.getValueAsString();
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }
}