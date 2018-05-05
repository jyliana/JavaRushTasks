package com.javarush.task.task39.task3911;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        if (!versionHistoryMap.containsKey(rollbackVersion))
            return false;
        else {
            Map<Integer, String> mapCopy = new HashMap<>(versionHistoryMap);
            for (Map.Entry<Integer, String> pair : mapCopy.entrySet()) {
                if (pair.getKey() > rollbackVersion)
                    versionHistoryMap.remove(pair.getKey());
            }
            currentVersion = rollbackVersion;
            return true;
        }
    }
}
