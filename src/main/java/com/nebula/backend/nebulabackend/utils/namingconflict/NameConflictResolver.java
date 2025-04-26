package com.nebula.backend.nebulabackend.utils.namingconflict;

import java.util.List;

public class NameConflictResolver {

    public static String resolveConflict(String desiredName, List<String> existingNames) {
        if (!existingNames.contains(desiredName)) {
            return desiredName;
        }

        int counter = 2;
        String newName;

        do {
            newName = desiredName + " " + counter;
            counter++;
        } while (existingNames.contains(newName));

        return newName;
    }
}
