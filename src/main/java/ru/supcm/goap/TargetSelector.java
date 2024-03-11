package ru.supcm.goap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TargetSelector {
    private final Set<Target> targets = new HashSet<>();
    public TargetSelector(Target... targets) {
        this.targets.addAll(Arrays.stream(targets).toList());
    }
    public Set<Target> getTargets() {
        return targets;
    }

    public void updateTargets() {
       targets.forEach(Target::update);
    }
}
